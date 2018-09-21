/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;


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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.controller.BaseController;
import kasirapp.model.penjualan.Penjualan;
import kasirapp.model.penjualan.PenjualanGateway;
import kasirapp.model.penjualan.PenjualanModel;


/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PenjualanDetailController extends BaseController {

    @FXML
    TextField tfSearch;
    @FXML
    TableView<PenjualanModel> tblPenjualan;
    @FXML
    TableColumn<Object, Object> clmKodePenjualan;
    @FXML
    TableColumn<Object, Object> clmTanggal;
    @FXML
    TableColumn<Object, Object> clmCustomer;
    @FXML
    TableColumn<Object, Object> clmTotal;
    @FXML
    TableColumn<Object, Object> clmTerbayar;
    @FXML
    TableColumn<Object, Object> clmSisa;
    @FXML
    TableColumn<Object, Object> clmDp;
    @FXML
    TableColumn<Object, Object> clmJenisPembayaran;
    @FXML
    TableColumn<Object, Object> clmStatus;
    @FXML
    ComboBox<String> cbFilter = new ComboBox();
    
    Penjualan penjualan = new Penjualan();
    PenjualanGateway penjualanGateway = new PenjualanGateway();
    
    @FXML
    public void actPenjualan(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
           detailData();
        }
    }
    
    @FXML
    public void btnAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/Penjualan.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tambah Transaksi Penjualan");       
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event1) -> {
            showData();
        });
    }
    
    @FXML
    public void btnUpdateOnAction(ActionEvent event) throws IOException {
        detailData();
    }
    
    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        PenjualanModel selectItem = tblPenjualan.getSelectionModel().getSelectedItem();
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
                penjualan.id = selectItem.getId();
                penjualanGateway.Delete(penjualan);
                tblPenjualan.getItems().clear();
                showData();                     
            }
        }
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        penjualan.penjualanDetail.clear();
        penjualan.id = tfSearch.getText().trim();
        tblPenjualan.setItems(penjualan.penjualanDetail);
        clmKodePenjualan.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmTerbayar.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmSisa.setCellValueFactory(new PropertyValueFactory<>("sisadp"));
        clmDp.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmJenisPembayaran.setCellValueFactory(new PropertyValueFactory<>("pembayarantype"));
        penjualanGateway.SearchView(penjualan);
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        showData();
    }
    
    @FXML
    private void miDetailOnAction(ActionEvent event) throws IOException {
        detailData();
    }
    
    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }
    
    @FXML
    private void miLunasidpOnAction(ActionEvent event) {
        PenjualanModel selectPenjualan = tblPenjualan.getSelectionModel().getSelectedItem();
        if (selectPenjualan.getPembayarantype().equals("CREDIT")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin untuk melunasi invoice ini. \n Untuk konfirmasi klik OK");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                
            }
        } 
    }
    
    @FXML
    private void miCetakStrukOnAction(ActionEvent event) throws Exception {
        PenjualanModel selectPenjualan = tblPenjualan.getSelectionModel().getSelectedItem();
        PrintReport("report_faktur_penjualan", "id_penjualan", selectPenjualan.getId());       
    }
    
    @FXML
    private void FilterChanged(ActionEvent event) {
        String value = cbFilter.getValue();
        
        switch(value) {
            case "CASH":
                showDataCash();
                break;
            case "CREDIT":
                showDataCredit();
                break;
            case "ALL":
                showData();
                break;
        }
       
    }
    
    private void detailData() throws IOException {
        PenjualanModel selectPenjualan = tblPenjualan.getSelectionModel().getSelectedItem();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/Penjualan.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        PenjualanController penjualandetail = fXMLLoader.getController();
        penjualandetail.idPenjualan = selectPenjualan.getId();
        penjualandetail.status = "detail";
        penjualandetail.btnSave.setText("Update");
        penjualandetail.txtTitle.setText("Detail Penjualan");
        penjualandetail.showDetailPenjualan();
        penjualandetail.showDetailItem();
        penjualandetail.showDetailDP();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Detail Transaksi Penjualan");       
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.show();

        stage.setOnCloseRequest((WindowEvent event1) -> {
         showData();
        });
    }
    //menampilkan data ke tabel
    public void showData() {
        tblPenjualan.setItems(penjualan.penjualanDetail);
        clmKodePenjualan.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmTerbayar.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmSisa.setCellValueFactory(new PropertyValueFactory<>("sisadp"));
        clmDp.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmJenisPembayaran.setCellValueFactory(new PropertyValueFactory<>("pembayarantype"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        penjualanGateway.ViewPenjualan(penjualan);
    }
    
    public void showDataCash() {
        tblPenjualan.setItems(penjualan.penjualanDetail);
        clmKodePenjualan.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmTerbayar.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmSisa.setCellValueFactory(new PropertyValueFactory<>("sisadp"));
        clmDp.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmJenisPembayaran.setCellValueFactory(new PropertyValueFactory<>("pembayarantype"));
         clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        penjualanGateway.ViewPenjualanCash(penjualan);
    }
    
    public void showDataCredit() {
        tblPenjualan.setItems(penjualan.penjualanDetail);
        clmKodePenjualan.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        clmTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        clmTerbayar.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmSisa.setCellValueFactory(new PropertyValueFactory<>("sisadp"));
        clmDp.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmJenisPembayaran.setCellValueFactory(new PropertyValueFactory<>("pembayarantype"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        penjualanGateway.ViewPenjualanCredit(penjualan);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showData();
        cbFilter.getItems().addAll("ALL","CASH", "CREDIT");
    }    
    
}
