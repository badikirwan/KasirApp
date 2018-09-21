/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.setting;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import kasirapp.controller.setting.PerusahaanSettingController;
import kasirapp.model.KoneksiDB;

/**
 *
 * @author BadikIrwan
 */
public class SettingGateway {
    
    Connection con;
    PreparedStatement pst;
    Statement stm;
    ResultSet rst;
    
    public void connectDB() {
        con = KoneksiDB.connector();
    }
    
    public void disconnectDB() throws SQLException {
        con.close();
    }
    
    public void SaveProfilePerusahaan(Perusahaan perusahaan) {
        connectDB();
        
        try {
            pst = con.prepareStatement("INSERT INTO tb_perusahaan VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, perusahaan.nama);
            pst.setString(3, perusahaan.kota);
            pst.setString(4, perusahaan.tlp);
            pst.setString(5, perusahaan.fax);
            pst.setString(6, perusahaan.alamat);
            if (perusahaan.imagePath != null) {
                InputStream is;
                is = new FileInputStream(new File(perusahaan.imagePath));
                pst.setBlob(7, is);
            } else if (perusahaan.imagePath == null) {
                pst.setBlob(7, (Blob) null);
            }
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess :");
            alert.setHeaderText("Updated !!");
            alert.setContentText("User " + perusahaan.nama + " Updated Sucessfuly");
            alert.showAndWait();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateProfilePerusahaan(Perusahaan perusahaan) {
        connectDB();
        
        try {
            pst = con.prepareStatement("UPDATE tb_perusahaan SET nama=?, kota=?, telp=?, fax=?, alamat=?, logo=? WHERE id=?");
            pst.setString(1, perusahaan.nama);
            pst.setString(2, perusahaan.kota);
            pst.setString(3, perusahaan.tlp);
            pst.setString(4, perusahaan.fax);
            pst.setString(5, perusahaan.alamat);
            pst.setString(7, perusahaan.id);
            if (perusahaan.imagePath != null) {
                InputStream is;
                is = new FileInputStream(new File(perusahaan.imagePath));
                pst.setBlob(6, is);
            } else if (perusahaan.imagePath == null) {
                pst.setBlob(6, (Blob) null);
            }
            pst.executeUpdate();
            pst.close();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Profile " + perusahaan.nama + " Updated Sucessfuly");
            alert.showAndWait();
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(SettingGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ViewProfile(Perusahaan perusahaan) {
        connectDB();
        
        try {
            pst = con.prepareCall("SELECT * FROM tb_perusahaan");
            rst = pst.executeQuery();
            
            while (rst.next()) {
                perusahaan.id = rst.getString("id");
                perusahaan.nama = rst.getString("nama");
                perusahaan.kota = rst.getString("kota");
                perusahaan.tlp = rst.getString("telp");
                perusahaan.fax = rst.getString("fax");
                perusahaan.alamat = rst.getString("alamat");
                perusahaan.logo = rst.getBlob("logo");
                if (perusahaan.logo != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(perusahaan.logo.getBytes(1, (int) perusahaan.logo.length()));
                    perusahaan.image = new Image(byteArrayInputStream);
                } else {
                    perusahaan.image = new Image("kasirapp/assets/logo.jpeg");
                }
            }
            rst.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SettingGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
