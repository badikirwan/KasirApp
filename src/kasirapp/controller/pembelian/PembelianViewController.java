/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pembelian;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import kasirapp.controller.BaseController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PembelianViewController extends BaseController {

    @FXML
    public StackPane spMainContent;
    @FXML
    ToggleButton btnPembelian;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setView(spMainContent, "pembelian/PembelianDetail.fxml");
        } catch (IOException ex) {
            Logger.getLogger(PembelianViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
