package tn.esprit.utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class myDatabase {
public static myDatabase instance;
private final String URL="jdbc:mysql://localhost:3306/lamma";
private final String Username="root";
private final String Password="";

private Connection conn;

private myDatabase(){
    try {
        conn = DriverManager.getConnection(URL,Username,Password);
        System.out.println("Connection established.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public static myDatabase getInstance(){
    if (instance==null)
        instance=new myDatabase();
        return instance;
}
public Connection getConnection(){
    return conn;
}


}
