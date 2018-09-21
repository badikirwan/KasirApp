/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.jasa;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import kasirapp.model.jasa.Jasa;
import kasirapp.model.jasa.JasaGateway;
import kasirapp.model.jasa.JasaModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class JasaController extends BaseController {
    
    @FXML
    private TableView<JasaModel> tblJasa;
    @FXML
    private TableColumn<Object, Object> clmKodeJasa;
    @FXML
    private TableColumn<Object, Object> clmNamaJasa;
    @FXML
    private TableColumn<Object, Object> clmBiayaJasa;
    @FXML
    private TableColumn<Object, Object> clmKeterangan;
    @FXML
    private TableColumn<Object, Object> clmTanggalInput;
    @FXML
    private TextField tfSearch;
   
    Jasa jasa = new Jasa();
    JasaGateway jasaGateway = new JasaGateway();
    
    @FXML
    public void btnAddOnAction(ActionEvent event) throws IOException {
        modalPopUp("/kasirapp/view/jasa/AddJasa.fxml", "Tambah jasa baru");
    }
    
    @FXML
    public void btnUpdateOnAction(ActionEvent event) {
        if (tblJasa.getSelectionModel().getSelectedItem() != null) {
            JasaModel selectJasa = tblJasa.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/jasa/AddJasa.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddJasaController jasaController = fxmlLoader.getController();
                jasaController.txtJudul.setText("Details Jasa");
                jasaController.btnSave.setText("Update");
                jasaController.barangID = selectJasa.getId();
                jasaController.showDetails();
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
        JasaModel selectJasa = tblJasa.getSelectionModel().getSelectedItem();
        if (selectJasa == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ada kesalahan!!");
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin untuk menghapus item ini. \n Untuk konfirmasi klik OK");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                jasa.id = selectJasa.getId();
                System.out.println(jasa.id+ "On hear");
                jasaGateway.delete(jasa);
                tblJasa.getItems().clear();
                showData();                     
            }
        }
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        jasa.jasaDetail.clear();
        jasa.namaJasa = tfSearch.getText().trim();
        tblJasa.setItems(jasa.jasaDetail);
        clmKodeJasa.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaJasa.setCellValueFactory(new PropertyValueFactory<>("namaJasa"));
        clmBiayaJasa.setCellValueFactory(new PropertyValueFactory<>("biayaJasa"));
        clmKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        clmTanggalInput.setCellValueFactory(new PropertyValueFactory<>("tanggalInput"));
        jasaGateway.searchView(jasa);
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        showData();
    }
    
    public void showData() {
        tblJasa.setItems(jasa.jasaDetail);
        clmKodeJasa.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaJasa.setCellValueFactory(new PropertyValueFactory<>("namaJasa"));
        clmBiayaJasa.setCellValueFactory(new PropertyValueFactory<>("biayaJasa"));
        clmKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        clmTanggalInput.setCellValueFactory(new PropertyValueFactory<>("tanggalInput"));
        jasaGateway.view(jasa);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
    }    
    
}
