/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseUtils {
    private static String URL = "jdbc:sqlserver://localhost:1433;database=SlcProject;trustServerCertificate=true;";
    private static String USER= "sa";
    private static String PASSWORD = "12345";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connect success");
        return conn;
    }
    
}
