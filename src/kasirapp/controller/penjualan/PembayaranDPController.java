/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kasirapp.model.penjualan.PenjualanGateway;
import kasirapp.model.penjualan.pembayaranDP;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PembayaranDPController implements Initializable {

    @FXML 
    public Label total, idpenjualan;
    @FXML
    DatePicker tanggalDP;
    @FXML
    TextField jumlahDP, sisaDP;
    @FXML
    Button btnSaveDP, btnCancel;
    
    FXMLLoader fxmlLoader = new FXMLLoader();
    PenjualanController penjualan = fxmlLoader.getController();
    pembayaranDP pembayarandp = new pembayaranDP();
    PenjualanGateway penjualanGateway = new PenjualanGateway();
    
    @FXML
    public void btnSaveDpOnAction(ActionEvent event) {
        if (btnSaveDP.getText().equals("Save")) {
            pembayarandp.tanggal = tanggalDP.getValue().toString().trim();
            pembayarandp.totaldp = jumlahDP.getText().trim();
            pembayarandp.sisadp = sisaDP.getText().trim();
            pembayarandp.penjualanid = idpenjualan.getText().trim();
            penjualanGateway.SaveDPPenjualan(pembayarandp);
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } 
    }
    
    @FXML
    public void btnCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void actSisaDP(KeyEvent event) {
        if (!jumlahDP.getText().isEmpty()) {
            int jumlah = Integer.parseInt(jumlahDP.getText());
            int totalp = Integer.parseInt(total.getText());
            String sisa = String.valueOf(totalp - jumlah);
            sisaDP.setText(sisa);
        } else {
            sisaDP.setText("0");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print(total);
    }    
    
}
