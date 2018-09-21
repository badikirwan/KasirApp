/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.laporan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import kasirapp.controller.BaseController;
import kasirapp.controller.setting.SettingViewController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class LaporanViewController extends BaseController {

    @FXML
    public StackPane spMainContent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setView(spMainContent, "laporan/Laporan.fxml");
        } catch (IOException ex) {
            Logger.getLogger(SettingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
