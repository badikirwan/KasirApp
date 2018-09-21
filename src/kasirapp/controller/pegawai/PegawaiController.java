/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pegawai;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kasirapp.controller.BaseController;
import kasirapp.model.pegawai.Pegawai;
import kasirapp.model.pegawai.PegawaiGateway;
import kasirapp.model.pegawai.PegawaiModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PegawaiController extends BaseController {

    @FXML
    private TableView<PegawaiModel> tblPegawai;
    @FXML
    private TableColumn clmKodePegawai;
    @FXML
    private TableColumn clmNamaPegawai;
    @FXML
    private TableColumn clmTelepon;
    @FXML
    private TableColumn clmAlamat;
    @FXML
    private TextField tfSearch;
    
    Pegawai pegawai = new Pegawai();
    PegawaiGateway pegawaiGateway = new PegawaiGateway();
    
    @FXML
    public void btnAddAction(ActionEvent event) throws IOException {
        modalPopUp("/kasirapp/view/pegawai/AddPegawai.fxml", "Tambah item baru");
    }
    
    @FXML
    public void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (tblPegawai.getSelectionModel().getSelectedItem() != null) {
            PegawaiModel selectBarang = tblPegawai.getSelectionModel().getSelectedItem();
            String barang = selectBarang.getId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/pegawai/AddPegawai.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddPegawaiController barangController = fxmlLoader.getController();
                barangController.txtJudul.setText("Details Pegawai");
                barangController.btnSave.setText("Update");
                barangController.barangID = selectBarang.getId();
                barangController.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
            } catch (IOException e) {
                 e.printStackTrace();
            }
        } else {
            
        } 
    }
    
    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        PegawaiModel selectPegawai = tblPegawai.getSelectionModel().getSelectedItem();
        if (selectPegawai == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ada kesalahan!!");
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin untuk menghapus item ini. \n Untuk konfirmasi klik OK");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                pegawai.id = selectPegawai.getId();
                System.out.println(pegawai.id+ "On hear");
                pegawaiGateway.delete(pegawai);
                tblPegawai.getItems().clear();
                ShowData();                     
            }
        }       
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        pegawai.pegawaiDetail.clear();
        pegawai.nama = tfSearch.getText().trim();
        tblPegawai.setItems(pegawai.pegawaiDetail);
        clmKodePegawai.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaPegawai.setCellValueFactory(new PropertyValueFactory<>("nama"));
        clmAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        clmTelepon.setCellValueFactory(new PropertyValueFactory<>("notlp"));
        pegawaiGateway.searchView(pegawai);
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        ShowData();
    }
    
    private void ShowData() {
        tblPegawai.setItems(pegawai.pegawaiDetail);
        clmKodePegawai.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaPegawai.setCellValueFactory(new PropertyValueFactory<>("nama"));
        clmAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        clmTelepon.setCellValueFactory(new PropertyValueFactory<>("notlp"));
        pegawaiGateway.view(pegawai);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowData();
    }    
    
}
