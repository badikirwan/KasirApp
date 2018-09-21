/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import kasirapp.model.KoneksiDB;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class HomeViewController implements Initializable {

    final static String austria = "Januari";
    final static String brazil = "Februari";
    final static String france = "Maret";
    final static String italy = "April";
    final static String usa = "Mei";
    private ObservableList data;
    
    @FXML
    private Label txtBarang;
    @FXML
    private Label txtJasa;
    @FXML
    private Label txtPegawai;
    @FXML
    private Label txtPendapatan;
    @FXML
    private Label txtPengeluaran;
    @FXML
    private BarChart<?,?> BarChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private PieChart pieChart;
    
    HomeGateway homeGateway = new HomeGateway();
    
    private void CountBarang() {
        try {
            txtBarang.setText(Integer.toString(homeGateway.CountBarang()));
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CountJasa() {
        try {
            txtJasa.setText(Integer.toString(homeGateway.CountJasa()));
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CountPegawai() {
        try {
            txtPegawai.setText(Integer.toString(homeGateway.CountPegawai()));
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CountPengeluaran() {
        try {
            txtPengeluaran.setText("Rp."+Integer.toString(homeGateway.CountPengeluaran()));
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CountPendapatan() {
        try {
            txtPendapatan.setText("Rp."+Integer.toString(homeGateway.CountPendapatan()));
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SetGrafik() throws SQLException {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pendapatan");       
        series1.getData().add(new XYChart.Data("Pendapatan", homeGateway.CountPendapatan()));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Pengeluaran");
        series2.getData().add(new XYChart.Data("Pengeluaran", homeGateway.CountPengeluaran()));  
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Keuntungan");
        series3.getData().add(new XYChart.Data("Keuntungan", homeGateway.CountPendapatan() - homeGateway.CountPengeluaran()));
        
        BarChart.getData().addAll(series1, series2, series3);
    }
    
    private void CekLabaRugi() {
        Connection con = KoneksiDB.connector();
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
    }
    
    private void CekStok() {
        Connection con = KoneksiDB.connector();
        data = FXCollections.observableArrayList();
        
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT nama, stok FROM tb_items");
            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));
            }
            pieChart.getData().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CountBarang();
        CountJasa();
        CountPegawai();
        CountPengeluaran();
        CountPendapatan();
        try {
            SetGrafik();
        } catch (SQLException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CekStok();
    }    
    
}
