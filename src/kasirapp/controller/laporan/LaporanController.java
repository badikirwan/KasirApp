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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kasirapp.controller.BaseController;
import kasirapp.controller.barang.AddBarangController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class LaporanController extends BaseController {

    @FXML
    public void btnPenjualanHarianOnAction(ActionEvent event) throws IOException {
        OpenModal("Rekap Penjualan Harian"); 
    }
    
    @FXML
    public void btnBarangOnAction(ActionEvent event) {
        PrintReport("report_barang", "", ""); 
    }
    
    @FXML
    public void btnJasaOnAction(ActionEvent event) {
        PrintReport("report_jasa", "", ""); 
    }
    
    @FXML
    public void rekapPembelianFakturItemOnAction(ActionEvent event) {
        OpenModal("Rekap pembelian per item");
    }
    
    @FXML
    public void rekapPembelianFakturOnAction(ActionEvent event) throws IOException {
        OpenModal("Rekap pembelian per faktur"); 
    }
    
    
    public void OpenModal(String title) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/laporan/Dialog.fxml"));
            
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            DialogController controller = fxmlLoader.getController();
            //controller.txtJudul.setText("Details Barang");
            controller.judul = title;
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
