/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.setting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import kasirapp.model.KoneksiDB;
import kasirapp.model.setting.Perusahaan;
import kasirapp.model.setting.SettingGateway;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PerusahaanSettingController implements Initializable {

    @FXML
    TextField txtNamaPerusahaan, txtKota, txtNomorTlp, txtFax;
    @FXML
    TextArea txtAlamat;
    @FXML
    public Button btnSave;
    @FXML
    private Rectangle retImage;
    
    private Image image;
    private File file;
    private FileInputStream fileInput;
    private FileOutputStream fileOutput;
    private byte[] userImage;
    private String imgPath;
    private String status;
    
    Perusahaan perusahaan = new Perusahaan();
    SettingGateway settingGateway = new SettingGateway();
    
    @FXML
    private void attachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterjpeg = new FileChooser.ExtensionFilter("jpeg files (*.jpeg)", "*.jpeg");

        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng, extFilterjpeg);

        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() < 6000000) {
                System.out.print("Condition ok");
                System.out.println(file.length());
                BufferedImage bufferedImage = ImageIO.read(file);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
                retImage.setFill(new ImagePattern(image));
                imgPath = file.getAbsolutePath();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another image");
                alert.initStyle(StageStyle.UNDECORATED);
            }
        }
    }
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        if (btnSave.getText().equals("Save")) {
            perusahaan.nama = txtNamaPerusahaan.getText();
            perusahaan.kota = txtKota.getText();
            perusahaan.tlp = txtNomorTlp.getText();
            perusahaan.fax = txtFax.getText();
            perusahaan.alamat = txtAlamat.getText();
            perusahaan.imagePath = imgPath;
            perusahaan.image = image;
            settingGateway.SaveProfilePerusahaan(perusahaan);
        } else {
            perusahaan.id = status;
            perusahaan.nama = txtNamaPerusahaan.getText();
            perusahaan.kota = txtKota.getText();
            perusahaan.tlp = txtNomorTlp.getText();
            perusahaan.fax = txtFax.getText();
            perusahaan.alamat = txtAlamat.getText();
            perusahaan.imagePath = imgPath;
            perusahaan.image = image;
            settingGateway.UpdateProfilePerusahaan(perusahaan);
        }
    }
    
    public void showProfile() {
        settingGateway.ViewProfile(perusahaan);
        status = perusahaan.id;
        txtNamaPerusahaan.setText(perusahaan.nama);
        txtKota.setText(perusahaan.kota);
        txtNomorTlp.setText(perusahaan.tlp);
        txtFax.setText(perusahaan.fax);
        txtAlamat.setText(perusahaan.alamat);
        image = perusahaan.image;
        retImage.setFill(new ImagePattern(image));     
    }
    
    private void check() {
        Connection con;
        PreparedStatement pst;
        Statement stm;
        ResultSet rst;
        try {
            con = KoneksiDB.connector();
            pst = con.prepareStatement("SELECT * FROM tb_perusahaan");
            rst = pst.executeQuery();
            
            if (rst.next()) {
                showProfile();
                btnSave.setText("Update");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerusahaanSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        check();
    }    
    
    
}
