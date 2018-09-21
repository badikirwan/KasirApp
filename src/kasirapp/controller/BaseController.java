/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kasirapp.model.KoneksiDB;
import kasirapp.model.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author BadikIrwan
 */
public class BaseController implements Initializable {
    
    public LoginModel loginModel = new LoginModel();
    
    public void newWindow(String fxml, String title) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("/kasirapp/view/" + fxml));
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.resizableProperty().setValue(Boolean.TRUE);
        stage.setMaximized(true);
        stage.show();
    }
    
    public void modalPopUp(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxml));       
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    
    public void setView(Pane pane, String fxml) throws IOException {
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/kasirapp/view/" + fxml));
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);    
    }
    
    public void PrintReport(String file, String key, String value) {
        Connection con = KoneksiDB.connector();
        String filepath = "/kasirapp/reports/"+file+".jasper";
        HashMap param = new HashMap();
                
        try {
            InputStream report = getClass().getResourceAsStream(filepath);
            param.put(key, value);
            param.put("pathlogo", BaseController.class.getResourceAsStream("/kasirapp/assets/logo.jpeg"));
            JasperPrint jprint = JasperFillManager.fillReport(report, param, con);
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void PrintReport2(String file, String key1, Date value1, String key2, Date value2) {
        Connection con = KoneksiDB.connector();
        String filepath = "/kasirapp/reports/"+file+".jasper";
        HashMap param = new HashMap();
                
        try {
            InputStream report = getClass().getResourceAsStream(filepath);
            param.put(key1, value1);
            param.put(key1, value2);
            param.put("pathlogo", BaseController.class.getResourceAsStream("/kasirapp/assets/logo.jpeg"));
            JasperPrint jprint = JasperFillManager.fillReport(report, param, con);
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setFitPageZoomRatio();
            viewer.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
