/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author BadikIrwan
 */
public class LoginModel {
    
    Connection koneksi;
    Statement stm;
    ResultSet rst;
    
    public void connect() {
        koneksi = KoneksiDB.connector();
    }
    
    public void disconnect() throws SQLException {
        koneksi.close();
    }
    
    public boolean isConnected() throws SQLException {
        try {
            connect();

            if (koneksi != null) {
                disconnect();
                return true;
            }
        } catch (SQLException e) {
        }

        return false;
    }
    
    public boolean login(String username, String password) throws SQLException {
        connect();
        try {
            stm = koneksi.createStatement();
            rst = stm.executeQuery("SELECT * FROM tb_user WHERE username='"+username+"' AND password=MD5('"+password+"')");
            
            if (rst.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.print(e);
        }    
        
        disconnect();
        return false;
    }
}
