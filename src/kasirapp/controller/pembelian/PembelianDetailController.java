/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pembelian;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.controller.BaseController;
import kasirapp.controller.penjualan.PenjualanController;
import kasirapp.model.pembelian.Pembelian;
import kasirapp.model.pembelian.PembelianGateway;
import kasirapp.model.pembelian.PembelianModel;
import kasirapp.model.penjualan.PenjualanModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PembelianDetailController extends BaseController {

    @FXML
    private StackPane spMainContent;
    @FXML
    private TableView<PembelianModel> tblPembelian;
    @FXML
    private TableColumn<Object, Object> clmKodePembelian;
    @FXML
    private TableColumn<Object, Object> clmPegawaiID;
    @FXML
    private TableColumn<Object, Object> clmTglPembelian;
    @FXML
    private TableColumn<Object, Object> clmTotal;
    @FXML
    private TableColumn<Object, Object> clmKeterangan;
    @FXML
    private TextField tfSearch;
    
    Pembelian pembelian = new Pembelian();
    PembelianGateway pembelianGateway = new PembelianGateway();
    
    @FXML
    public void actPembelian(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            detailData();
        }
    }
    
    @FXML
    public void btnAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/Pembelian.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tambah Transaksi Pembelian");       
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event1) -> {
            ShowData();
        });
    }
    
    @FXML
    public void btnUpdateOnAction(ActionEvent event) throws IOException {
        detailData();
    }
    
    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        PembelianModel selectItem = tblPembelian.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin untuk menghapus item ini. \n Untuk konfirmasi klik OK");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                pembelian.id = selectItem.getId();
                pembelianGateway.deletePembelian(pembelian);
                tblPembelian.getItems().clear();
                ShowData();                     
            }
        }
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        pembelian.pembelianDetail.clear();
        pembelian.id = tfSearch.getText().trim();
        tblPembelian.setItems(pembelian.pembelianDetail);
        clmKodePembelian.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPegawaiID.setCellValueFactory(new PropertyValueFactory<>("pegawaiID"));
        clmTglPembelian.setCellValueFactory(new PropertyValueFactory<>("tanggalBeli"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        pembelianGateway.searchView(pembelian);
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        ShowData();
    }
    
    private void detailData() throws IOException {
        PembelianModel selectPembelian = tblPembelian.getSelectionModel().getSelectedItem();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/Pembelian.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        PembelianController pembeliandetail = fXMLLoader.getController();
        pembeliandetail.pembelianid = selectPembelian.getId();
        pembeliandetail.status = "detail";
        pembeliandetail.btnSave.setText("Update");
        pembeliandetail.txtTitle.setText("Detail Pembelian");
        pembeliandetail.showDetailPembelian();
        pembeliandetail.showDetailItem();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Detail Transaksi Penjualan");       
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.show();

        stage.setOnCloseRequest((WindowEvent event1) -> {
         ShowData();
        });
    }
    
    public void ShowData() {
        tblPembelian.setItems(pembelian.pembelianDetail);
        clmKodePembelian.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmPegawaiID.setCellValueFactory(new PropertyValueFactory<>("pegawaiID"));
        clmTglPembelian.setCellValueFactory(new PropertyValueFactory<>("tanggalBeli"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmKeterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        pembelianGateway.view(pembelian);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowData();
    }    
    
}
