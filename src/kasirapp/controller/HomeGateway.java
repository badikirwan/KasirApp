/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import kasirapp.model.KoneksiDB;

/**
 *
 * @author BadikIrwan
 */
public class HomeGateway {
    
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
    
    public int CountBarang() throws SQLException {
        connectDB();
        int rowCount;
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT COUNT(*) FROM tb_items");
            rst.next();
            rowCount = rst.getInt(1);
        } finally {
            rst.close();
            stm.close();
        }
        return rowCount;
    }
    
    public int CountJasa() throws SQLException {
        connectDB();
        int rowCount;
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT COUNT(*) FROM tb_jasa");
            rst.next();
            rowCount = rst.getInt(1);
        } finally {
            rst.close();
            stm.close();
        }
        return rowCount;
    }
    
    public int CountPegawai() throws SQLException {
        connectDB();
        int rowCount;
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT COUNT(*) FROM tb_pegawai1");
            rst.next();
            rowCount = rst.getInt(1);
        } finally {
            rst.close();
            stm.close();
        }
        return rowCount;
    }
    
    public int CountPengeluaran() throws SQLException {
        connectDB();
        int rowCount;
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT SUM(total) FROM tb_pembelian WHERE YEAR(tanggal_beli) = YEAR(NOW())");
            rst.next();
            rowCount = rst.getInt(1);
        } finally {
            rst.close();
            stm.close();
        }
        return rowCount;
    }
    
    public int CountPendapatan() throws SQLException {
        connectDB();
        int rowCount;
        try {
            stm = con.createStatement();
            rst = stm.executeQuery("SELECT SUM(total) FROM tb_penjualan WHERE YEAR(tanggal_jual) = YEAR(NOW())");
            rst.next();
            rowCount = rst.getInt(1);
        } finally {
            rst.close();
            stm.close();
        }
        return rowCount;
    }
}
