/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.jasa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;

/**
 *
 * @author BadikIrwan
 */
public class JasaGateway {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    
    public void connectDB() {
        con = KoneksiDB.connector();
    }
    
    public void disconnectDB() throws SQLException {
        con.close();
    }
    
    public void view(Jasa jasa) {
        jasa.jasaDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_jasa");
            rst = pst.executeQuery();           
            while(rst.next()) {
                jasa.id = rst.getString("jasa_id");
                jasa.namaJasa = rst.getString("nama");
                jasa.biayaJasa = rst.getString("biaya");
                jasa.keterangan = rst.getString("keterangan");
                jasa.tanggalInput = rst.getString("tgl_input");
                jasa.jasaDetail.addAll(new JasaModel(jasa.id, jasa.namaJasa, jasa.biayaJasa, jasa.keterangan, jasa.tanggalInput));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void searchView(Jasa jasa) {
        connectDB();
        try {
            pst = con.prepareCall("SELECT * FROM tb_jasa WHERE nama LIKE ? ORDER BY jasa_id");
            pst.setString(1, "%" + jasa.namaJasa + "%");
            rst = pst.executeQuery();
            while (rst.next()) {
                jasa.id = rst.getString("jasa_id");
                jasa.namaJasa = rst.getString("nama");
                jasa.biayaJasa = rst.getString("biaya");
                jasa.keterangan = rst.getString("keterangan");
                jasa.tanggalInput = rst.getString("tgl_input");
                jasa.jasaDetail.addAll(new JasaModel(jasa.id, jasa.namaJasa, jasa.biayaJasa, jasa.keterangan, jasa.tanggalInput));      
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jasa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectedViewItem(Jasa jasa) {
        connectDB();
        
        try {
            pst = con.prepareCall("SELECT * FROM tb_jasa WHERE jasa_id=?");
            pst.setString(1, jasa.id);
            rst = pst.executeQuery();  
            while (rst.next()) {
                jasa.id = rst.getString("jasa_id");
                jasa.namaJasa = rst.getString("nama");
                jasa.biayaJasa = rst.getString("biaya");
                jasa.keterangan = rst.getString("keterangan");
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jasa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new Date(today.getTime());
    }
    
    public boolean save(Jasa jasa) {
        connectDB();
        try {
            pst = con.prepareStatement("INSERT INTO tb_jasa VALUES(?,?,?,?,?)");
            pst.setString(1, "");
            pst.setString(2, jasa.namaJasa);
            pst.setString(3, jasa.biayaJasa);
            pst.setString(4, jasa.keterangan);
            pst.setDate(5, getCurrentDate());
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Jasa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    public boolean update(Jasa jasa) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_jasa SET nama=?, biaya=?, keterangan=? WHERE jasa_id=?");
            pst.setString(1, jasa.namaJasa);
            pst.setString(2, jasa.biayaJasa);
            pst.setString(3, jasa.keterangan);
            pst.setString(4, jasa.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void delete(Jasa jasa) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_jasa WHERE jasa_id=?");
            pst.setString(1, jasa.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Jasa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
}
