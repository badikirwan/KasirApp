/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class HomeController extends BaseController {

    /**
     * Initializes the controller class.
     * @param event
     */
    @FXML
    private Pane center;
    
    @FXML
    private StackPane acContent;
    
    @FXML
    private ImageView imgHomeBtn, imgBarangBtn, imgJasaBtn, imgPembelianBtn, imgPenjualanBtn, imgLaporanBtn, imgPegawaiBtn, imgSetingBtn;
    
    @FXML
    public Button btnHome, btnBarang, btnJasa, btnPembelian, btnPenjualan, btnLaporan, btnPegawai, btnSeting;
    @FXML
    private AnchorPane acMain;
    
    Image imgHome = new Image("kasirapp/assets/home.png");
    Image imgHomeWhite = new Image("kasirapp/assets/home-white.png");
    Image imgBarang = new Image("kasirapp/assets/barang.png");
    Image imgBarangWhite = new Image("kasirapp/assets/barang-white.png");
    Image imgJasa = new Image("kasirapp/assets/jasa.png");
    Image imgJasaWhite = new Image("kasirapp/assets/jasa-white.png");
    Image imgPembelian = new Image("kasirapp/assets/stock.png");
    Image imgPembelianWhite = new Image("kasirapp/assets/stock-white.png");
    Image imgPenjualan = new Image("kasirapp/assets/beli.png");
    Image imgPenjualanWhite = new Image("kasirapp/assets/beli-white.png");
    Image imgLaporan = new Image("kasirapp/assets/laporan.png");
    Image imgLaporanWhite = new Image("kasirapp/assets/laporan-white.png");
    Image imgPegawai = new Image("kasirapp/assets/pegawai.png");
    Image imgPegawaiWhite = new Image("kasirapp/assets/pegawai-white.png");
    Image imgSeting = new Image("kasirapp/assets/seting.png");
    Image imgSetingWhite = new Image("kasirapp/assets/seting-white.png");
    
    String defultStyle = "-fx-text-fill: #4e4f52; "+"-fx-background-color: none;";
    String activeStyle = "-fx-text-fill: white; "+"-fx-background-color: none;";
    
    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }
    }
    
    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
        
    }
    
    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException {
        HomeActive();
        setView(acContent, "HomeView.fxml");
    }
    
    @FXML
    public void btnBarangOnClick(ActionEvent event) throws IOException {
        BarangActive();
        setView(acContent, "barang/BarangView.fxml");
    }
    
    @FXML
    public void btnJasaOnClick(ActionEvent event) throws IOException {
        JasaActive();
        setView(acContent, "jasa/JasaView.fxml");
    }
    
    @FXML
    public void btnPembelianOnClick(ActionEvent event) throws IOException {
        PembelianActive();
        setView(acContent, "pembelian/PembelianView.fxml");
    }
    
    @FXML
    public void btnPenjualanOnClick(ActionEvent event) throws IOException {
        PenjualanActive();
        setView(acContent, "penjualan/PenjualanView.fxml");
    }
    
    @FXML
    public void btnLaporanOnClick(ActionEvent event) throws IOException {
        LaporanActive();
        setView(acContent, "laporan/LaporanView.fxml");
    }
    
    @FXML
    public void btnPegawaiOnClick(ActionEvent event) throws IOException {
        PegawaiActive();
        setView(acContent, "pegawai/PegawaiView.fxml");
    }
    
    @FXML
    public void btnSetingOnClick(ActionEvent event) throws IOException {
        SettingActive();
        setView(acContent, "setting/SettingView.fxml");
    }
    
    @FXML
    public void btnAboutOnClick(ActionEvent event) {
        
    }
    
    @FXML
    public void btnLogOut(ActionEvent event) {
        
    }
    
    @FXML
    public void mbtnOnClick(ActionEvent event) {
        
    }
    
    
    @FXML
    public void BarangAction(ActionEvent event) throws IOException {
        setView(center, "Barang.fxml");
    }
    
    public void HomeActive() {
        imgHomeBtn.setImage(imgHomeWhite);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(activeStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void BarangActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarangWhite);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(activeStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void JasaActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasaWhite);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(activeStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void PembelianActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelianWhite);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(activeStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void PenjualanActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualanWhite);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(activeStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void LaporanActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporanWhite);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(activeStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void PegawaiActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawaiWhite);
        imgSetingBtn.setImage(imgSeting);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(activeStyle);
        btnSeting.setStyle(defultStyle);
    }
    
    public void SettingActive() {
        imgHomeBtn.setImage(imgHome);
        imgBarangBtn.setImage(imgBarang);
        imgJasaBtn.setImage(imgJasa);
        imgPembelianBtn.setImage(imgPembelian);
        imgPenjualanBtn.setImage(imgPenjualan);
        imgLaporanBtn.setImage(imgLaporan);
        imgPegawaiBtn.setImage(imgPegawai);
        imgSetingBtn.setImage(imgSetingWhite);
        btnHome.setStyle(defultStyle);
        btnBarang.setStyle(defultStyle); 
        btnJasa.setStyle(defultStyle);
        btnPembelian.setStyle(defultStyle);
        btnPenjualan.setStyle(defultStyle);
        btnLaporan.setStyle(defultStyle);
        btnPegawai.setStyle(defultStyle);
        btnSeting.setStyle(activeStyle);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HomeActive();
        try {
            setView(acContent, "HomeView.fxml");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
