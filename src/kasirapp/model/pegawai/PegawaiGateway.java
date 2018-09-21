/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pegawai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;
import kasirapp.model.barang.Barang;

/**
 *
 * @author BadikIrwan
 */
public class PegawaiGateway {
    
    Connection con;
    Statement stm;
    PreparedStatement pst;
    ResultSet rst;
    
    public void connectDB() {
        con = KoneksiDB.connector();
    }
    
    public void disconnectDB() throws SQLException {
        con.close();
    }
    
    public void view(Pegawai barang) {
        barang.pegawaiDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_pegawai1");
            rst = pst.executeQuery();           
            while(rst.next()) {
                barang.id = rst.getString("pegawai_id");
                barang.nama = rst.getString("nama");
                barang.alamat = rst.getString("alamat");
                barang.notlp = rst.getString("no_tlp");
                barang.pegawaiDetail.addAll(new PegawaiModel(barang.id, barang.nama, barang.alamat, barang.notlp));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void selectedViewItem(Pegawai barang) {
        connectDB();
        
        try {
            pst = con.prepareCall("SELECT * FROM tb_pegawai1 WHERE pegawai_id=?");
            pst.setString(1, barang.id);
            rst = pst.executeQuery();  
            while (rst.next()) {
                barang.id = rst.getString("pegawai_id");
                barang.nama= rst.getString("nama");
                barang.notlp = rst.getString("no_tlp");
                barang.alamat = rst.getString("alamat");
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchView(Pegawai pegawai) {
        connectDB();
        try {
            pst = con.prepareCall("SELECT * FROM tb_pegawai1 WHERE nama LIKE ? ORDER BY pegawai_id");
            pst.setString(1, "%" + pegawai.nama + "%");
            rst = pst.executeQuery();
            while (rst.next()) {
                pegawai.id = rst.getString("pegawai_id");
                pegawai.nama = rst.getString("nama");
                pegawai.alamat = rst.getString("alamat");
                pegawai.notlp = rst.getString("no_tlp");
                pegawai.pegawaiDetail.addAll(new PegawaiModel(pegawai.id, pegawai.nama, pegawai.alamat, pegawai.notlp));      
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean save(Pegawai barang) {
        connectDB();
        try {
            pst = con.prepareStatement("INSERT INTO tb_pegawai1 VALUES(?,?,?,?)");
            pst.setString(1, "");
            pst.setString(2, barang.nama);
            pst.setString(3, barang.notlp);
            pst.setString(4, barang.alamat);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    public boolean update(Pegawai barang) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_pegawai1 SET nama=?, no_tlp=?, alamat=? WHERE pegawai_id=?");
            pst.setString(1, barang.nama);
            pst.setString(2, barang.notlp);
            pst.setString(3, barang.alamat);
            pst.setString(4, barang.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void delete(Pegawai pegawai) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_pegawai1 WHERE pegawai_id=?");
            pst.setString(1, pegawai.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Pegawai.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
}
