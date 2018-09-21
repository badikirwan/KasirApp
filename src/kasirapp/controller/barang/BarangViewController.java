/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.barang;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import kasirapp.controller.BaseController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class BarangViewController extends BaseController {

    @FXML
    private StackPane spMainContent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setView(spMainContent, "barang/Barang.fxml");
        } catch (IOException ex) {
            Logger.getLogger(BarangViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
