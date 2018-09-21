/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.barang;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;

/**
 *
 * @author BadikIrwan
 */
public class BarangGateway {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rst;
    
    public void connectDB() {
        con = KoneksiDB.connector();
    }
    
    public void disconnectDB() throws SQLException {
        con.close();
    }
    
    public void view(Barang barang) {
        barang.barangDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_items");
            rst = pst.executeQuery();           
            while(rst.next()) {
                barang.id = rst.getString("item_id");
                barang.namaBarang = rst.getString("nama");
                barang.jenisBarang = rst.getString("kategori_id");
                barang.hargaBeli = rst.getString("harga_beli");
                barang.hargaJual = rst.getString("harga_jual");
                barang.stokBarang = rst.getString("stok");
                barang.satuan = rst.getString("satuan");
                barang.keterangan = rst.getString("keterangan");
                barang.tanggalInput = rst.getString("tgl_input");
                barang.barangDetail.addAll(new BarangModel(barang.id, barang.namaBarang, barang.jenisBarang, barang.hargaBeli, barang.hargaJual, barang.stokBarang, barang.satuan, barang.keterangan, barang.tanggalInput));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void viewBJ(Barang barang) {
        barang.barangDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_items WHERE kategori_id = 1");
            rst = pst.executeQuery();           
            while(rst.next()) {
                barang.id = rst.getString("item_id");
                barang.namaBarang = rst.getString("nama");
                barang.jenisBarang = rst.getString("kategori_id");
                barang.hargaBeli = rst.getString("harga_beli");
                barang.hargaJual = rst.getString("harga_jual");
                barang.stokBarang = rst.getString("stok");
                barang.satuan = rst.getString("satuan");
                barang.keterangan = rst.getString("keterangan");
                barang.tanggalInput = rst.getString("tgl_input");
                barang.barangDetail.addAll(new BarangModel(barang.id, barang.namaBarang, barang.jenisBarang, barang.hargaBeli, barang.hargaJual, barang.stokBarang, barang.satuan, barang.keterangan, barang.tanggalInput));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void viewBP(Barang barang) {
        barang.barangDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_items WHERE kategori_id = 2");
            rst = pst.executeQuery();           
            while(rst.next()) {
                barang.id = rst.getString("item_id");
                barang.namaBarang = rst.getString("nama");
                barang.jenisBarang = rst.getString("kategori_id");
                barang.hargaBeli = rst.getString("harga_beli");
                barang.hargaJual = rst.getString("harga_jual");
                barang.stokBarang = rst.getString("stok");
                barang.satuan = rst.getString("satuan");
                barang.keterangan = rst.getString("keterangan");
                barang.tanggalInput = rst.getString("tgl_input");
                barang.barangDetail.addAll(new BarangModel(barang.id, barang.namaBarang, barang.jenisBarang, barang.hargaBeli, barang.hargaJual, barang.stokBarang, barang.satuan, barang.keterangan, barang.tanggalInput));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void searchView(Barang barang) {
        connectDB();
        try {
            pst = con.prepareCall("SELECT * FROM tb_items WHERE nama LIKE ? ORDER BY item_id");
            pst.setString(1, "%" + barang.namaBarang + "%");
            rst = pst.executeQuery();
            while (rst.next()) {
                barang.id = rst.getString("item_id");
                barang.namaBarang = rst.getString("nama");
                barang.jenisBarang = rst.getString("kategori_id");
                barang.hargaBeli = rst.getString("harga_beli");
                barang.hargaJual = rst.getString("harga_jual");
                barang.stokBarang = rst.getString("stok");
                barang.satuan = rst.getString("satuan");
                barang.keterangan = rst.getString("keterangan");
                barang.tanggalInput = rst.getString("tgl_input");
                barang.barangDetail.addAll(new BarangModel(barang.id, barang.namaBarang, barang.jenisBarang, barang.hargaBeli, barang.hargaJual, barang.stokBarang, barang.satuan, barang.keterangan, barang.tanggalInput));      
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectedViewItem(Barang barang) {
        connectDB();
        
        try {
            pst = con.prepareCall("SELECT * FROM tb_items WHERE item_id=?");
            pst.setString(1, barang.id);
            rst = pst.executeQuery();  
            while (rst.next()) {
                barang.id = rst.getString("item_id");
                barang.namaBarang = rst.getString("nama");
                barang.hargaBeli = rst.getString("harga_beli");
                barang.hargaJual = rst.getString("harga_jual");
                barang.stokBarang = rst.getString("stok");
                barang.satuan = rst.getString("satuan");
                barang.keterangan = rst.getString("keterangan");
                barang.jenisBarang = rst.getString("kategori_id");
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new Date(today.getTime());
    }
    
    public boolean save(Barang barang) {
        connectDB();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        try {
            pst = con.prepareStatement("INSERT INTO tb_items VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, "");
            pst.setString(2, barang.namaBarang);
            pst.setString(3, barang.keterangan);
            pst.setString(4, barang.hargaBeli);
            pst.setString(5, barang.hargaJual);
            pst.setString(6, barang.stokBarang);
            pst.setString(7, barang.satuan);
            pst.setDate(8, getCurrentDate());
            pst.setInt(9, Integer.parseInt(barang.jenisBarang));
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
    
    public boolean update(Barang barang) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_items SET nama=?, keterangan=?, harga_beli=?, harga_jual=?, stok=?, satuan=? WHERE item_id=?");
            pst.setString(1, barang.namaBarang);
            pst.setString(2, barang.keterangan);
            pst.setString(3, barang.hargaBeli);
            pst.setString(4, barang.hargaJual);
            pst.setString(5, barang.stokBarang);
            pst.setString(6, barang.satuan);
            pst.setString(7, barang.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void delete(Barang barang) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_items WHERE item_id=?");
            pst.setString(1, barang.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
}
