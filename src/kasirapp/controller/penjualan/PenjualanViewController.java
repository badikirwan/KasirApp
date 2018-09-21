/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import kasirapp.controller.BaseController;
import kasirapp.controller.pembelian.PembelianViewController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PenjualanViewController extends BaseController {

    @FXML
    public StackPane spMainContent;
    
    @FXML
    public void btnPenjualanOnAction(ActionEvent event) {
        
    }
    
    @FXML
    public void btnPenjualanDetailOnAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setView(spMainContent, "penjualan/PenjualanDetail.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PenjualanViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
