/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pembelian;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.controller.BaseController;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.pegawai.Pegawai;
import kasirapp.model.pegawai.PegawaiGateway;
import kasirapp.model.pegawai.PegawaiModel;
import kasirapp.model.pembelian.Pembelian;
import kasirapp.model.pembelian.PembelianGateway;
import kasirapp.model.pembelian.PembelianItem;
import kasirapp.model.pembelian.PembelianItemModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PembelianController extends BaseController {

    @FXML
    private TableView<PembelianItemModel> tblPembelian;
    @FXML
    private TableColumn<Object, Object> clmKodeBarang;
    @FXML
    private TableColumn<Object, Object> clmNamaBarang;
    @FXML
    private TableColumn<Object, Object> clmHarga;
    @FXML
    private TableColumn<Object, Object> clmQty;
    @FXML
    private TableColumn<Object, Object> clmSubtotal;
    @FXML
    public Label txtTotal, txtTitle;
    @FXML
    private DatePicker txtTglPembelian;
    @FXML
    private TextField txtKodePembelian, txtKodePegawai, txtKeterangan;
    @FXML
    public Button btnClose, btnSave;
    
    String PegawaiID, pembelianid, status;
    @FXML
    private TableView<PegawaiModel> tblPegawaiView;
    @FXML
    private TableColumn<Object, Object> clmPegawaiId;
    @FXML
    private TableColumn<Object, Object> clmNamaPegawai;
    @FXML
    private TextField tfPegawaiSearch;
    @FXML
    private MenuButton mbtnPegawai;
    
    Barang barang = new Barang();
    Pembelian pembelian = new Pembelian();
    Pegawai pegawai = new Pegawai();
    PegawaiGateway pegawaiGateway = new PegawaiGateway();
    BarangGateway barangGateway = new BarangGateway();
    PembelianItem pembelianItem = new PembelianItem();
    PembelianGateway pembelianGateway = new PembelianGateway();
    
    @FXML
    public void actMain(KeyEvent event) {
        switch (event.getCode()) {
            case F1:
                
                break;
            case F2:
                break;
            case F3:
                break;
            case F4:
                break;
            default:
                break;
        }
    }
    
    @FXML
    public void mbtnPegawaiOnClicked(MouseEvent event) {
        tblPegawaiView.getItems().clear();
        pegawai.nama = tfPegawaiSearch.getText().trim();
        tblPegawaiView.setItems(pegawai.pegawaiDetail);
        clmPegawaiId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaPegawai.setCellValueFactory(new PropertyValueFactory<>("nama"));
        pegawaiGateway.searchView(pegawai);
    }
    
    @FXML
    public void tblPegawaiOnClick(MouseEvent event) {
        mbtnPegawai.setText(tblPegawaiView.getSelectionModel().getSelectedItem().getNama());
        PegawaiID = tblPegawaiView.getSelectionModel().getSelectedItem().getId();
        System.out.println(PegawaiID);
    }
    
    @FXML
    public void tfPegawaiSearchOnKeyReleased(Event event) {
        pegawai.pegawaiDetail.clear();
        pegawai.nama = tfPegawaiSearch.getText().trim();
        tblPegawaiView.setItems(pegawai.pegawaiDetail);
        clmPegawaiId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaPegawai.setCellValueFactory(new PropertyValueFactory<>("nama"));
        pegawaiGateway.searchView(pegawai);    
    }
    
    @FXML
    public void actItemClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            PembelianItemModel selectItem = tblPembelian.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Update Items");
                item.btnAddItems.setText("Update");
                item.barangID = selectItem.getId();
                item.pembelianID = txtKodePembelian.getText();
                item.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
                
                nStage.setOnCloseRequest((WindowEvent event1) -> {
                    showData();
                    sumTotal();
                });
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void btnAddItemOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/AddPembelian.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        AddPembelianController pj = fXMLLoader.getController();
        pj.pembelianID = txtKodePembelian.getText();
        scene.setFill(new Color(0, 0, 0, 0));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Pilih items");
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event1) -> {
            showData();
            sumTotal();
        });
    }
    
    @FXML
    public void btnDeleteItemOnAction(ActionEvent event) {
        PembelianItemModel selectItem = tblPembelian.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ada kesalahan!!");
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            pembelianGateway.delete(pembelianItem);
            showData();
            sumTotal();
        }
    }
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        SaveData();
    }
    
    @FXML
    public void btnCancelOnAction(ActionEvent event) {
        btnCloseOnAction(event);
    }
    
    @FXML
    public void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        
        if (!tblPembelian.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi");
            alert.setHeaderText(null);
            alert.setContentText("Data belum disimpan, apakah anda ingin menyimpannya?");
            
            ButtonType btnTypeYes = new ButtonType("Yes");
            ButtonType btnTypeNo = new ButtonType("No");
            ButtonType btnTypeCancel = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(btnTypeYes, btnTypeNo, btnTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.isPresent() && result.get() == btnTypeYes) {
                SaveData();
            } else if (result.isPresent() && result.get() == btnTypeNo) {
                deleteData();
                stage.close();
            } else {
                alert.close();
            }
        } else {
            stage.close();
        }
    }
    
    private void SaveData() {
        pembelian.id = txtKodePembelian.getText().trim();
        pembelian.tanggalBeli = txtTglPembelian.getValue().toString().trim();
        pembelian.pegawaiID = PegawaiID;
        pembelian.total = txtTotal.getText().trim();
        pembelian.keterangan = txtKeterangan.getText().trim();
        
        if (btnSave.getText().equals("Simpan")) {
            if (pembelianGateway.savePembelian(pembelian)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Invoice pembelian berhasil disimpan!!\napakah mau cetak struck");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //PrintReport("report_faktur_penjualan", "id_penjualan", penjualan.id);      
                }    
            }
        } else {
            if (pembelianGateway.update(pembelian)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Invoice pembelian berhasil diupdate!!");
                alert.showAndWait(); 
            }
        }
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    public void deleteData() {
        if (btnSave.getText().equals("Simpan")) {
            pembelian.id = txtKodePembelian.getText();
            pembelianGateway.deleteAll(pembelian);
        }
    }
    
    public void showDetailPembelian() {
        pembelian.id = pembelianid;
        pembelianGateway.selectedViewPenjualan(pembelian);
        txtKodePembelian.setText(pembelian.id);
        txtTglPembelian.setValue(LocalDate.parse(pembelian.tanggalBeli));
        mbtnPegawai.setText(pembelian.pegawaiID);
        PegawaiID = pembelian.pegawaiID;
        txtKeterangan.setText(pembelian.keterangan);
        txtTotal.setText(pembelian.total);
        System.out.println(PegawaiID);
    }
    
    public void showDetailItem() {
        pembelianItem.id = pembelianid;
        tblPembelian.setItems(pembelianItem.pembelianItemDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        clmSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        pembelianGateway.viewPembelian(pembelianItem);
    }
    
    public void showData() {
        tblPembelian.setItems(pembelianItem.pembelianItemDetail);
        pembelianItem.id = txtKodePembelian.getText();
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clmHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        pembelianGateway.viewPembelian(pembelianItem);
        System.out.print(pembelianItem.subtotal);
    }
    
    public void sumTotal() {
        tblPembelian.getSelectionModel().selectFirst();
        float sum = 0;
        int items = tblPembelian.getItems().size();
        
        for (int i = 0; i < items; i++) {
            tblPembelian.getSelectionModel().select(i);
            String selectItems = tblPembelian.getSelectionModel().getSelectedItem().getSubtotal();
            float newfloat = Float.parseFloat(selectItems);
            sum = sum + newfloat;
        }
        
        String total = String.valueOf(sum);
        txtTotal.setText(total);
    }
    
    public void clearAll() {
        txtKodePembelian.setText(pembelianGateway.AutoKode());
        txtTglPembelian.setValue(null);
        mbtnPegawai.setText(null);
        txtKeterangan.clear();
        txtTotal.setText(null);
    }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtKodePembelian.setText(pembelianGateway.AutoKode());
        showData();
        sumTotal();
    }    
    
}
