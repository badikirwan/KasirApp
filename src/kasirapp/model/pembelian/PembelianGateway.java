/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pembelian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;
import kasirapp.model.barang.Barang;
import kasirapp.model.penjualan.Penjualan;
import kasirapp.model.penjualan.PenjualanGateway;

/**
 *
 * @author BadikIrwan
 */
public class PembelianGateway {
    
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
    
    public String AutoKode() {
        connectDB();
        
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT MAX(right(pembelian_id,5)) FROM tb_pembelian");
            while (rst.next()) {
                int a = rst.getInt(1);
                return String.format("PBL0000"+ Integer.toString(a+1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
        return "";
    }
    
    //MENAMPILKAN SEMUA DAFTAR PEMBELIAN
    public void view(Pembelian pembelian) {
        pembelian.pembelianDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT n.*, s.nama FROM tb_pembelian n INNER JOIN tb_pegawai s ON s.pegawai_id = n.pegawai_id ORDER BY n.pembelian_id ASC");
            rst = pst.executeQuery();           
            while(rst.next()) {
                pembelian.id = rst.getString("pembelian_id");
                pembelian.pegawaiID = rst.getString("nama");
                pembelian.tanggalBeli = rst.getString("tanggal_beli");
                pembelian.total = rst.getString("total");
                pembelian.keterangan = rst.getString("keterangan");
                pembelian.pembelianDetail.addAll(new PembelianModel(pembelian.id, pembelian.pegawaiID, pembelian.tanggalBeli, pembelian.total, pembelian.keterangan));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void selectedViewPenjualan(Pembelian pembelian) {
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_pembelian WHERE pembelian_id=?");
            pst.setString(1, pembelian.id);
            rst = pst.executeQuery();
            
            while (rst.next()) {
                pembelian.id = rst.getString("pembelian_id");
                pembelian.pegawaiID = rst.getString("pegawai_id");
                pembelian.tanggalBeli = rst.getString("tanggal_beli");
                pembelian.pembayaranType = rst.getString("pembayaran_type");
                pembelian.total= rst.getString("total");
                pembelian.keterangan = rst.getString("keterangan");
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //MENAMPILKAN DAFTAR ITEM YANG AKAN DI BELI
    public void viewPembelian(PembelianItem pembelianItem) {
        pembelianItem.pembelianItemDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT n.*, s.nama FROM tb_pembelian_item n INNER JOIN tb_items s ON s.item_id = n.item_id WHERE n.pembelian_id=?");
            pst.setString(1, pembelianItem.id);
            rst = pst.executeQuery();           
            while(rst.next()) {
                pembelianItem.id = rst.getString("item_id");
                pembelianItem.itemID = rst.getString("nama");
                pembelianItem.qty = rst.getString("qty");
                pembelianItem.harga = rst.getString("harga");
                pembelianItem.subtotal = rst.getString("subtotal");
                pembelianItem.pembelianItemDetail.addAll(new PembelianItemModel(pembelianItem.id, pembelianItem.itemID, pembelianItem.qty, pembelianItem.harga, pembelianItem.subtotal));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //MEYIMPAN PEMBELIAN ITEMS
    public boolean save(PembelianItem pembelian) {
        connectDB();
        try {
            pst = con.prepareStatement("INSERT INTO tb_pembelian_item VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, pembelian.id);
            pst.setString(3, pembelian.itemID);
            pst.setString(4, pembelian.qty);
            pst.setString(5, pembelian.harga);
            pst.setString(6, "0");
            pst.setString(7, pembelian.subtotal);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    public boolean update(Pembelian item) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_pembelian SET pegawai_id=?, tanggal_beli=?, pembayaran_type=?, total=?, keterangan=? WHERE pembelian_id=?");
            pst.setString(1, item.pegawaiID);
            pst.setString(2, item.tanggalBeli);
            pst.setString(3, "CASH");
            pst.setString(4, item.total);
            pst.setString(5, item.keterangan);
            pst.setString(6, item.id);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PembelianGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    //UPDATE PEMBELIAN ITEMS
    public boolean updateItem(PembelianItem pembelianItem) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_Pembelian_item SET pembelian_id=?, item_id=?, qty=?, harga=?, diskon_persen=?, subtotal=? WHERE item_id=? AND pembelian_id=?");
            pst.setString(1, pembelianItem.id);
            pst.setString(2, pembelianItem.itemID);
            pst.setString(3, pembelianItem.qty);
            pst.setString(4, pembelianItem.harga);
            pst.setString(5, "0");
            pst.setString(6, pembelianItem.subtotal);
            pst.setString(7, pembelianItem.itemID);
            pst.setString(8, pembelianItem.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //MENYIMPAN PEMBELIAN
    public boolean savePembelian(Pembelian pembelian) {
        connectDB();
        try {
            pst = con.prepareStatement("INSERT INTO tb_pembelian VALUES(?,?,?,?,?,?)");
            pst.setString(1, pembelian.id);
            pst.setString(2, pembelian.pegawaiID);
            pst.setString(3, pembelian.tanggalBeli);
            pst.setString(4, "CASH");
            pst.setString(5, pembelian.total);
            pst.setString(6, pembelian.keterangan);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    //MENGHAPUS ITEM YG AKAN DIBELI
    public void delete(PembelianItem pembelianItem) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_pembelian_item WHERE item_id=?");
            pst.setString(1, pembelianItem.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    //MENGHAPUS SEMUA ITEM YG AKAN DIBELI
    public void deleteAll(Pembelian pembelian) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_pembelian_item WHERE pembelian_id=?");
            pst.setString(1, pembelian.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    public void deletePembelian(Pembelian pembelian) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_pembelian WHERE pembelian_id=?");
            pst.setString(1, pembelian.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(Pembelian.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    public void searchView(Pembelian pembelian) {
        connectDB();
        try {
            pst = con.prepareCall("SELECT n.*, s.nama FROM tb_pembelian n INNER JOIN tb_pegawai s ON s.pegawai_id = n.pegawai_id WHERE n.pembelian_id LIKE ? ORDER BY n.pembelian_id DESC");
            pst.setString(1, "%" + pembelian.id + "%");
            rst = pst.executeQuery();
            while(rst.next()) {
                pembelian.id = rst.getString("pembelian_id");
                pembelian.pegawaiID = rst.getString("nama");
                pembelian.tanggalBeli = rst.getString("tanggal_beli");
                pembelian.total = rst.getString("total");
                pembelian.keterangan = rst.getString("keterangan");
                pembelian.pembelianDetail.addAll(new PembelianModel(pembelian.id, pembelian.pegawaiID, pembelian.tanggalBeli, pembelian.total, pembelian.keterangan));
            }    
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Barang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
