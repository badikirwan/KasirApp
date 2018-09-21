/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.setting;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import kasirapp.controller.BaseController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class SettingViewController extends BaseController {

    @FXML
    public StackPane spMainContent;
    
    @FXML
    public void MenuSistemOnAction(ActionEvent event) throws IOException {
        setView(spMainContent, "setting/SistemSetting.fxml");
    }
    
    @FXML
    public void MenuPerusahaanOnAction(ActionEvent event) throws IOException {
        setView(spMainContent, "setting/PerusahaanSetting.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setView(spMainContent, "setting/PerusahaanSetting.fxml");
        } catch (IOException ex) {
            Logger.getLogger(SettingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
