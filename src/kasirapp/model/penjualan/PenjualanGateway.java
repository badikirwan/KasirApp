/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.penjualan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;
////import kasirapp.model.KoneksiDB;

/**
 *
 * @author BadikIrwan
 */
public class PenjualanGateway {
    
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
            rst = stm.executeQuery("SELECT MAX(right(penjualan_id,5)) FROM tb_penjualan");
            while (rst.next()) {
                int a = rst.getInt(1);
                return String.format("PJL0000"+ Integer.toString(a+1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Penjualan.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
        return "";
    }
    
    public boolean Save(Penjualan penjualan) {
        connectDB();
        try {
            pst = con.prepareStatement("INSERT INTO tb_penjualan VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, penjualan.id);
            pst.setString(2, penjualan.tanggal);
            pst.setString(3, penjualan.customer);
            pst.setString(4, penjualan.customertlp);
            pst.setString(5, penjualan.pembayarantype);
            pst.setString(6, penjualan.total);
            pst.setString(7, penjualan.keterangan);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    public boolean Update(Penjualan penjualan) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_penjualan SET tanggal_jual=?, customer=?, customer_tlp=?, pembayaran_type=?, total=?, keterangan=? WHERE penjualan_id=?");
            pst.setString(7, penjualan.id);
            pst.setString(1, penjualan.tanggal);
            pst.setString(2, penjualan.customer);
            pst.setString(3, penjualan.customertlp);
            pst.setString(4, penjualan.pembayarantype);
            pst.setString(5, penjualan.total);
            pst.setString(6, penjualan.keterangan);
            pst.executeUpdate();
            pst.close();
            disconnectDB();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return false;
    }
    
    public void Delete(Penjualan penjualan) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_penjualan WHERE penjualan_id=?");
            pst.setString(1, penjualan.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    //menampilkan semua data penjualan di penjualan detail
    public void ViewPenjualan(Penjualan penjualan) {
        penjualan.penjualanDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT a.*, b.* FROM tb_penjualan a LEFT JOIN tb_dp b ON a.penjualan_id = b.penjualan_id ORDER BY a.penjualan_id DESC");
            rst = pst.executeQuery();
            while (rst.next()) {
                penjualan.id = rst.getString("penjualan_id");
                penjualan.tanggal = rst.getString("tanggal_jual");
                penjualan.customer = rst.getString("customer");
                penjualan.customertlp = rst.getString("customer_tlp");
                penjualan.pembayarantype = rst.getString("pembayaran_type");
                penjualan.total = rst.getString("total");
                penjualan.keterangan = rst.getString("keterangan");
                if (rst.getString("total_dp") == null && rst.getString("sisa_dp") == null) {
                    penjualan.totaldp = "-";
                    penjualan.sisadp  = "-";
                } else {
                    penjualan.totaldp = rst.getString("total_dp");
                    penjualan.sisadp = rst.getString("sisa_dp");
                }
                
                if (rst.getString("pembayaran_type").equals("CASH")) {
                    penjualan.status = "Lunas";
                } else {
                    penjualan.status = rst.getString("status");
                }
                
                penjualan.penjualanDetail.addAll(new PenjualanModel(penjualan.id, penjualan.idkasir, penjualan.tanggal, penjualan.customer,
                penjualan.customertlp, penjualan.pembayarantype, penjualan.total, penjualan.keterangan, penjualan.totaldp, penjualan.sisadp, penjualan.status));
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ViewPenjualanCash(Penjualan penjualan) {
        penjualan.penjualanDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT a.* FROM tb_penjualan a WHERE a.pembayaran_type = 'CASH' ORDER BY a.penjualan_id DESC");
            rst = pst.executeQuery();
            while (rst.next()) {
                penjualan.id = rst.getString("penjualan_id");
                penjualan.tanggal = rst.getString("tanggal_jual");
                penjualan.customer = rst.getString("customer");
                penjualan.customertlp = rst.getString("customer_tlp");
                penjualan.pembayarantype = rst.getString("pembayaran_type");
                penjualan.total = rst.getString("total");
                penjualan.keterangan = rst.getString("keterangan");
                penjualan.totaldp = "-";
                penjualan.sisadp = "-";
                penjualan.status = "Lunas";
                penjualan.penjualanDetail.addAll(new PenjualanModel(penjualan.id, penjualan.idkasir, penjualan.tanggal, penjualan.customer,
                penjualan.customertlp, penjualan.pembayarantype, penjualan.total, penjualan.keterangan, penjualan.totaldp, penjualan.sisadp, penjualan.status));
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ViewPenjualanCredit(Penjualan penjualan) {
        penjualan.penjualanDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT a.*, b.* FROM tb_penjualan a LEFT JOIN tb_dp b ON a.penjualan_id = b.penjualan_id WHERE a.pembayaran_type = 'CREDIT' ORDER BY a.penjualan_id DESC");
            rst = pst.executeQuery();
            while (rst.next()) {
                penjualan.id = rst.getString("penjualan_id");
                penjualan.tanggal = rst.getString("tanggal_jual");
                penjualan.customer = rst.getString("customer");
                penjualan.customertlp = rst.getString("customer_tlp");
                penjualan.pembayarantype = rst.getString("pembayaran_type");
                penjualan.total = rst.getString("total");
                penjualan.keterangan = rst.getString("keterangan");
                penjualan.totaldp = rst.getString("total_dp");
                penjualan.sisadp = rst.getString("sisa_dp");
                penjualan.status = rst.getString("status");
                penjualan.penjualanDetail.addAll(new PenjualanModel(penjualan.id, penjualan.idkasir, penjualan.tanggal, penjualan.customer,
                penjualan.customertlp, penjualan.pembayarantype, penjualan.total, penjualan.keterangan, penjualan.totaldp, penjualan.sisadp, penjualan.status));
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void selectedViewPenjualan(Penjualan penjualan) {
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_penjualan WHERE penjualan_id=?");
            pst.setString(1, penjualan.id);
            rst = pst.executeQuery();
            
            while (rst.next()) {
                penjualan.id = rst.getString("penjualan_id");
                penjualan.tanggal = rst.getString("tanggal_jual");
                penjualan.customer = rst.getString("customer");
                penjualan.customertlp = rst.getString("customer_tlp");
                penjualan.pembayarantype = rst.getString("pembayaran_type");
                penjualan.total = rst.getString("total");
                penjualan.keterangan = rst.getString("keterangan");
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //
    //PENJUALAN
    //menampilkan semua item yg dibeli di penjualan
    public void ViewPenjualanItem(PenjualanItem item) {
        item.penjualanItemDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_penjualan_item WHERE penjualan_id=?");
            pst.setString(1, item.id);
            rst = pst.executeQuery();           
            while(rst.next()) {
                item.id = rst.getString("item_id");
                item.itemid = rst.getString("nama");
                item.qty = rst.getString("qty");
                item.harga = rst.getString("harga");
                item.diskon = rst.getString("diskon_persen");
                item.subtotal = rst.getString("subtotal");
                item.penjualanItemDetail.addAll(new PenjualanItemModel(item.id, item.itemid, item.nama, item.qty, item.harga, item.diskon, item.subtotal));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void DeleteItemPenjualan(PenjualanItem item) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_penjualan_item WHERE item_id=?");
            pst.setString(1, item.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    public void DeleteAllItemPenjualan(PenjualanItem item) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_penjualan_item WHERE penjualan_id=?");
            pst.setString(1, item.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    public boolean SaveItemPenjualan(PenjualanItem item) {
        connectDB();
        
        try {
            pst = con.prepareStatement("INSERT INTO tb_penjualan_item VALUES(?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, item.id);
            pst.setString(3, item.itemid);
            pst.setString(4, item.nama);
            pst.setString(5, item.qty);
            pst.setString(6, item.harga);
            pst.setString(7, "0");
            pst.setString(8, item.subtotal);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean UpdateItemPenjualan(PenjualanItem item) {
        connectDB();
        
        try {
            pst = con.prepareStatement("UPDATE tb_penjualan_item SET penjualan_id=?, item_id=?, nama=?, qty=?, harga=?, diskon_persen=?, subtotal=? WHERE penjualan_id= ? AND item_id=?");
            pst.setString(1, item.id);
            pst.setString(2, item.itemid);
            pst.setString(3, item.nama);
            pst.setString(4, item.qty);
            pst.setString(5, item.harga);
            pst.setString(6, "0");
            pst.setString(7, item.subtotal);
            pst.setString(8, item.id);
            pst.setString(9, item.itemid);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    //DP
    //
    public boolean SaveDPPenjualan(pembayaranDP dp) {
        connectDB();
        
        try {
            pst = con.prepareStatement("INSERT INTO tb_dp VALUES(?,?,?,?,?,?)");
            pst.setString(1, "");
            pst.setString(2, dp.tanggal);
            pst.setString(3, dp.totaldp);
            pst.setString(4, dp.sisadp);
            pst.setString(5, dp.penjualanid);
            pst.setString(6, "Belum Lunas");
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void DeleteDP(pembayaranDP dp) {
        connectDB();
        try {
            pst = con.prepareCall("DELETE FROM tb_dp WHERE dp_id=?");
            pst.setString(1, dp.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch(SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex.getMessage());
        }
    }
    
    public void ViewDP(pembayaranDP dp) {
        dp.dpDetail.clear();
        connectDB();
        
        try {
            pst = con.prepareStatement("SELECT * FROM tb_dp WHERE penjualan_id=?");
            pst.setString(1, dp.penjualanid);
            rst = pst.executeQuery();           
            while(rst.next()) {
                dp.id = rst.getString("dp_id");
                dp.tanggal = rst.getString("tanggal_dp");
                dp.totaldp = rst.getString("total_dp");
                dp.sisadp = rst.getString("sisa_dp");
                dp.status = rst.getString("status");
                dp.penjualanid = rst.getString("penjualan_id");
                dp.dpDetail.addAll(new PembayaranDPModel(dp.id, dp.tanggal, dp.totaldp, dp.sisadp, dp.status, dp.penjualanid));
            }            
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void SearchView(Penjualan penjualan) {
        connectDB();
        try {
            pst = con.prepareCall("SELECT a.*, b.* FROM tb_penjualan a LEFT JOIN tb_dp b ON a.penjualan_id = b.penjualan_id WHERE a.penjualan_id LIKE ? ORDER BY a.penjualan_id");
            pst.setString(1, "%" + penjualan.id + "%");
            rst = pst.executeQuery();
            while (rst.next()) {
                penjualan.id = rst.getString("penjualan_id");
                penjualan.tanggal = rst.getString("tanggal_jual");
                penjualan.customer = rst.getString("customer");
                penjualan.customertlp = rst.getString("customer_tlp");
                penjualan.pembayarantype = rst.getString("pembayaran_type");
                penjualan.total = rst.getString("total");
                penjualan.keterangan = rst.getString("keterangan");
                penjualan.totaldp = rst.getString("total_dp");
                penjualan.sisadp = rst.getString("sisa_dp");
                penjualan.status = rst.getString("status");
                penjualan.penjualanDetail.addAll(new PenjualanModel(penjualan.id, penjualan.idkasir, penjualan.tanggal, penjualan.customer,
                penjualan.customertlp, penjualan.pembayarantype, penjualan.total, penjualan.keterangan, penjualan.totaldp, penjualan.sisadp, penjualan.status));
            }
            pst.close();
            con.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean LunasiDP(Penjualan dp) {
        connectDB();
        try {
            pst = con.prepareStatement("UPDATE tb_dp SET total_dp = ?, sisa_dp = 0, status = 'Lunas' WHERE penjualan_id = ?;");
            int jumlah = Integer.parseInt(dp.sisadp) + Integer.parseInt(dp.totaldp);
            pst.setInt(1, jumlah);
            pst.setString(2, dp.id);
            pst.executeUpdate();
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
