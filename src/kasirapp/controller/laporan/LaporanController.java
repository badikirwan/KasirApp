/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.laporan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import kasirapp.controller.BaseController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class LaporanController extends BaseController {

    @FXML
    public void btnPenjualanHarianOnAction(ActionEvent event) throws IOException {
        modalPopUp("/kasirapp/view/laporan/Dialog.fxml", "Print report penjualan detail");
    }
    
    @FXML
    public void btnBarangOnAction(ActionEvent event) {
        PrintReport("report_barang", "", ""); 
    }
    
    @FXML
    public void btnJasaOnAction(ActionEvent event) {
        PrintReport("report_jasa", "", ""); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
