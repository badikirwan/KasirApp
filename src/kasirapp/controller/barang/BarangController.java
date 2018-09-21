/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.barang;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kasirapp.controller.BaseController;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.barang.BarangModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class BarangController extends BaseController {

    Barang barang = new Barang();
    BarangGateway barangGateway = new BarangGateway();
    
    @FXML
    private TableView<BarangModel> tblBarang;
    @FXML
    private TableColumn<Object, Object> clmKodeBarang;
    @FXML
    private TableColumn<Object, Object> clmNamaBarang;
    @FXML
    private TableColumn<Object, Object> clmHargaJual;
    @FXML
    private TableColumn<Object, Object> clmStok;
    @FXML
    private TableColumn<Object, Object> clmTanggalInput;
    @FXML
    private TextField tfSearch;
    @FXML
    ComboBox<String> cbFilter = new ComboBox();
    
    
    @FXML
    public void btnAddOnAction(ActionEvent event) throws IOException {
        modalPopUp("/kasirapp/view/barang/AddBarang.fxml", "Tambah item baru");
    }
    
    @FXML
    public void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (tblBarang.getSelectionModel().getSelectedItem() != null) {
            BarangModel selectBarang = tblBarang.getSelectionModel().getSelectedItem();
            String barang = selectBarang.getId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/barang/AddBarang.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                AddBarangController barangController = fxmlLoader.getController();
                barangController.txtJudul.setText("Details Barang");
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
        BarangModel selectBarang = tblBarang.getSelectionModel().getSelectedItem();
        if (selectBarang == null) {
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
                barang.id = selectBarang.getId();
                System.out.println(barang.id+ "On hear");
                barangGateway.delete(barang);
                tblBarang.getItems().clear();
                //showData();                     
            }
        }       
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        barang.barangDetail.clear();
        barang.namaBarang = tfSearch.getText().trim();
        tblBarang.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmHargaJual.setCellValueFactory(new PropertyValueFactory<>("hargaJual"));
        clmStok.setCellValueFactory(new PropertyValueFactory<>("stokBarang"));
        clmTanggalInput.setCellValueFactory(new PropertyValueFactory<>("tanggalInput"));
        barangGateway.searchView(barang);
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        showDataBarangJadi();
    }
    
    @FXML
    private void FilterChanged(ActionEvent event) {
        String value = cbFilter.getValue();
        
        switch(value) {
            case "Barang Jadi":
                showDataBarangJadi();
                break;
            case "Barang Produksi":
                showDataBarangProduksi();
                break;
        }
       
    }
    
    
    public void showDataBarangJadi() {
        tblBarang.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmHargaJual.setCellValueFactory(new PropertyValueFactory<>("hargaJual"));
        clmStok.setCellValueFactory(new PropertyValueFactory<>("stokBarang"));
        clmTanggalInput.setCellValueFactory(new PropertyValueFactory<>("tanggalInput"));
        barangGateway.viewBJ(barang);
    }
    
    public void showDataBarangProduksi() {
        tblBarang.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmHargaJual.setCellValueFactory(new PropertyValueFactory<>("hargaJual"));
        clmStok.setCellValueFactory(new PropertyValueFactory<>("stokBarang"));
        clmTanggalInput.setCellValueFactory(new PropertyValueFactory<>("tanggalInput"));
        barangGateway.viewBP(barang);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDataBarangJadi();
        cbFilter.getItems().addAll("Barang Jadi", "Barang Produksi");
    }
}
