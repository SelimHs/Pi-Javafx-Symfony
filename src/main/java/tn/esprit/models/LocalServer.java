package tn.esprit.models;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalServer {

    private static String latestEventData = "{}"; // Store event data for the webpage

    public static void main(String[] args) {
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        // ✅ Serve the HTML event page
        server.createContext("/", new HtmlHandler());

        // ✅ Handle QR Code Data from the phone
        server.createContext("/event", new EventHandler());

        // ✅ Serve event_data.json for the webpage to fetch event details
        server.createContext("/event_data.json", new JsonHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("🚀 Server started at http://172.16.0.208:5000/");
    }

    // ✅ Serve the HTML page
    static class HtmlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("event_page.html");
            if (!file.exists()) {
                String errorMessage = "<html><body><h2>404 - Page Not Found</h2></body></html>";
                exchange.sendResponseHeaders(404, errorMessage.length());
                OutputStream os = exchange.getResponseBody();
                os.write(errorMessage.getBytes(StandardCharsets.UTF_8));
                os.close();
                return;
            }

            byte[] fileBytes = Files.readAllBytes(file.toPath());
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, fileBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(fileBytes);
            os.close();
        }
    }

    // ✅ Handle incoming QR Code data
    static class EventHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.contains("data=")) {
                    latestEventData = URLDecoder.decode(query.substring(query.indexOf("data=") + 5), StandardCharsets.UTF_8);
                    System.out.println("📡 Data received: " + latestEventData);

                    // ✅ Save event data to event_data.json
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("event_data.json"))) {
                        writer.write(latestEventData);
                        writer.flush();
                    }

                    // ✅ Open the event page automatically
                    openWebPage("http://172.16.0.208:5000/");
                }

                // ✅ Send response to phone scanner
                String response = "✅ Data received and webpage opened!";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        }
    }

    // ✅ Serve event_data.json to the webpage
    static class JsonHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            File file = new File("event_data.json");
            if (!file.exists()) {
                String errorJson = "{ \"error\": \"Event data not found\" }";
                exchange.sendResponseHeaders(404, errorJson.length());
                OutputStream os = exchange.getResponseBody();
                os.write(errorJson.getBytes(StandardCharsets.UTF_8));
                os.close();
                return;
            }

            byte[] fileBytes = Files.readAllBytes(file.toPath());
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, fileBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(fileBytes);
            os.close();
        }
    }

    // ✅ Open the event page in a browser
    public static void openWebPage(String url) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else {
                Runtime.getRuntime().exec("xdg-open " + url);
            }
            System.out.println("🌐 Opening webpage: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
