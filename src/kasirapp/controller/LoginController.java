/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class LoginController extends BaseController {

    @FXML
    Label pesan;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML 
    Button btnCloseLogin, btnMinLogin;
    @FXML
    private StackPane acContent;
    
    @FXML
    public void LoginAction(ActionEvent event) throws SQLException, IOException {
        if (loginModel.isConnected()) {
            if (loginModel.login(username.getText(), password.getText())) {
                ((Node) event.getSource()).getScene().getWindow().hide();
                newWindow("Home.fxml", "Aplikasi Kasir");
            } else {
                pesan.setText("username dan password salah !!");
            }
        } else {
            pesan.setText("Koneksi database tidak terhubung");
        }
    }
    
    @FXML
    public void usernameOnAction(ActionEvent event) throws SQLException, IOException {
        LoginAction(event);
    }
    
    @FXML
    public void passwordOnAction(ActionEvent event) throws SQLException, IOException {
        LoginAction(event);
    }
    
    @FXML
    public void CloseAction(ActionEvent event) {
        Stage stage = (Stage) btnCloseLogin.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void MinimizeAction(ActionEvent event) {
        Stage stage = (Stage) btnMinLogin.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
