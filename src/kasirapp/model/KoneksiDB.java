/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BadikIrwan
 */
public class KoneksiDB {
    
    public static Connection connector() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://localhost:3306/db_kasir";
            Connection conn = DriverManager.getConnection(URL, "root", "");
            return conn;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
