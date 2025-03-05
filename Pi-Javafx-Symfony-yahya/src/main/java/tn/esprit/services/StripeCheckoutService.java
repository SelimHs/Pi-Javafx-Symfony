package tn.esprit.services;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import java.util.HashMap;
import java.util.Map;

public class StripeCheckoutService {

    public static String createCheckoutSession(double amount, String currency) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("payment_method_types", new String[]{"card"});
            params.put("mode", "payment");
            params.put("success_url", "http://localhost:8080/success");
            params.put("cancel_url", "http://localhost:8080/cancel");

            Map<String, Object> lineItem = new HashMap<>();
            lineItem.put("price_data", Map.of(
                    "currency", currency,
                    "unit_amount", (int) (amount * 100),
                    "product_data", Map.of("name", "Event Ticket")
            ));
            lineItem.put("quantity", 1);

            params.put("line_items", new Object[]{lineItem});

            Session session = Session.create(params);
            return session.getUrl(); // URL for Stripe Checkout
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
