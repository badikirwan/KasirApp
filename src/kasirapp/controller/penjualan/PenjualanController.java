/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.controller.BaseController;
import kasirapp.model.penjualan.PembayaranDPModel;
import kasirapp.model.penjualan.Penjualan;
import kasirapp.model.penjualan.PenjualanGateway;
import kasirapp.model.penjualan.PenjualanItem;
import kasirapp.model.penjualan.PenjualanItemModel;
import kasirapp.model.penjualan.pembayaranDP;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PenjualanController extends BaseController {

    @FXML
    public String idPenjualan, status;
    @FXML
    public Label txtTotal, txtTitle;
    @FXML
    TextField txtKodePenjualan, txtNamaCustomer, txtTelepon, txtKeterangan;
    @FXML
    ComboBox<String> cbJenisPembayaran = new ComboBox();
    @FXML
    DatePicker txtTanggal;
    @FXML
    Button btnClose, btnSave;
    @FXML
    TableView<PenjualanItemModel> tblPenjualan;
    @FXML
    TableColumn<Object, Object> clmKodeItem;
    @FXML
    TableColumn<Object, Object> clmNamaItem;
    @FXML
    TableColumn<Object, Object> clmQty;
    @FXML
    TableColumn<Object, Object> clmHarga;
    @FXML
    TableColumn<Object, Object> clmSubTotal;
    @FXML
    TableView<PembayaranDPModel> tblDP;
    @FXML
    TableColumn<Object, Object> clmKodeDP;
    @FXML
    TableColumn<Object, Object> clmTanggalDP;
    @FXML
    TableColumn<Object, Object> clmTotalDP;
    @FXML
    TableColumn<Object, Object> clmSisa;
    @FXML
    TableColumn<Object, Object> clmStatus;
    
    PenjualanGateway penjualanGateway = new PenjualanGateway();
    PenjualanItem penjualanItem = new PenjualanItem();
    pembayaranDP dp = new pembayaranDP();
    Penjualan penjualan = new Penjualan();
    
    //menutup dialog
    @FXML
    public void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        
        if (!tblPenjualan.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Data belum disimpan, apakah anda ingin menyimpannya?");
            
            ButtonType btnTypeYes = new ButtonType("Yes");
            ButtonType btnTypeNo = new ButtonType("No");
            ButtonType btnTypeCancel = new ButtonType("Cancel");
            alert.getButtonTypes().setAll(btnTypeYes, btnTypeNo, btnTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == btnTypeYes) {
                save();
            } else if (result.isPresent() && result.get() == btnTypeNo) {
                delete();
                stage.close();
            } else {
                alert.close();
            }
        } else {
            stage.close();
        }       
    }
    
    public void delete() {
        if (btnSave.getText().equals("Save")) {
            penjualanGateway.DeleteDP(dp);
            penjualanItem.id = txtKodePenjualan.getText();
            penjualanGateway.DeleteAllItemPenjualan(penjualanItem);
        }
    }
    
    //menambahkan item
    @FXML
    public void btnAddItemOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/PenjualanItem.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        PenjualanItemController pj = fXMLLoader.getController();
        pj.penjualanID = txtKodePenjualan.getText();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Pilih Item");       
        stage.getIcons().add(new Image("kasirapp/assets/icon.png"));
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent event1) -> {
            showData();
            sumTotal();
        });
    }
    
    //menghapus item penjualan
    @FXML
    public void btnDeleteItemOnAction(ActionEvent event) {
        deleteItem();
    }
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        save();
    }
    
    @FXML
    public void btnCancelOnAction(ActionEvent event) {
        btnCloseOnAction(event);
    }
    
    @FXML
    public void actMain(KeyEvent event) {
        switch (event.getCode()) {
            case DELETE :
                deleteItem();
            break;
            default :
            break;
        }
    }
     
    @FXML
    public void actItemClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            PenjualanItemModel selectItem = tblPenjualan.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Update Items");
                item.btnAddItems.setText("Update");
                item.barangID = selectItem.getId();
                item.penjualanID = txtKodePenjualan.getText();
                String[] kode = item.barangID.split("[0-9]");
                if (kode[0].equals("BRG")) {
                    item.showDetailBarang();
                } else {
                    item.showDetailJasa();
                }
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
                
                nStage.setOnCloseRequest((WindowEvent event1) -> {
                    showData();
                    sumTotal();
                });
            } catch (IOException ex) {
                  Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void cbJenisPembayaranOnClick(MouseEvent event) {
        
    }
    
    @FXML 
    public void btnBuatdpOnAction(ActionEvent event) {
        if (cbJenisPembayaran.getSelectionModel().getSelectedItem().equals("CREDIT")) {
            if (tblDP.getItems().isEmpty()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/PembayaranDP.fxml"));

                try {
                    fxmlLoader.load();
                    Parent parent = fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0, 0, 0, 0));
                    PembayaranDPController dp = fxmlLoader.getController();
                    dp.total.setText(txtTotal.getText());
                    dp.idpenjualan.setText(txtKodePenjualan.getText());
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.setTitle("Pembayaran DP");       
                    nStage.getIcons().add(new Image("kasirapp/assets/icon.png"));
                    nStage.show();

                    nStage.setOnCloseRequest((WindowEvent event1) -> {
                        showDP();
                    });
                } catch (IOException ex) {
                    Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Sudah ada pembayaran uang muka,\nhanya boleh satu ada satu pembayaran uang muka untuk satu invoice penjualan ");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Pembayaran cash tidak perlu buat DP!");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void btnDeletedpOnAction(ActionEvent event) {
        deleteDP();
    }
    
    public void save() {
        penjualan.id = txtKodePenjualan.getText().trim();
        penjualan.tanggal = txtTanggal.getValue().toString().trim();
        penjualan.customer = txtNamaCustomer.getText().trim();
        penjualan.customertlp = txtTelepon.getText().trim();
        penjualan.pembayarantype = cbJenisPembayaran.getSelectionModel().getSelectedItem();
        penjualan.total = txtTotal.getText().trim();
        penjualan.keterangan = txtKeterangan.getText().trim();
        
        if (btnSave.getText().equals("Save")) {
            if (penjualanGateway.Save(penjualan)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Invoice penjualan berhasil disimpan!!\napakah mau cetak struck");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    PrintReport("report_faktur_penjualan", "id_penjualan", penjualan.id);      
                }                    
            }
        } else {
            if (penjualanGateway.Update(penjualan)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informasi");
                alert.setHeaderText(null);
                alert.setContentText("Invoice penjualan berhasil diupdate!!");
                alert.showAndWait(); 
            }
        }
         Stage stage = (Stage) btnClose.getScene().getWindow();
         stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
     //delete item
    public void deleteItem() {
        PenjualanItemModel selectItem = tblPenjualan.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ada kesalahan!!");
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            penjualanGateway.DeleteItemPenjualan(penjualanItem);
            showData();
            sumTotal();
        }
    }
    
     //delete item
    public void deleteDP() {
        PembayaranDPModel selectItem = tblDP.getSelectionModel().getSelectedItem();
        if (selectItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Ada kesalahan!!");
            alert.setContentText("Ooops, pilih item yang akan dihapus!");
            alert.showAndWait();
        } else {
            penjualanGateway.DeleteDP(dp);
            showDP();
        }
    }
    
    public void showDetailPenjualan() {
        penjualan.id = idPenjualan;
        penjualanGateway.selectedViewPenjualan(penjualan);
        txtKodePenjualan.setText(penjualan.id);
        txtTanggal.setValue(LocalDate.parse(penjualan.tanggal));
        txtNamaCustomer.setText(penjualan.customer);
        txtTelepon.setText(penjualan.customertlp);
        cbJenisPembayaran.setValue(penjualan.pembayarantype);
        txtKeterangan.setText(penjualan.keterangan);
        txtTotal.setText(penjualan.total);
    }
    
    public void showDetailItem() {
        penjualanItem.id = idPenjualan;
        tblPenjualan.setItems(penjualanItem.penjualanItemDetail);
        clmKodeItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaItem.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        clmSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        penjualanGateway.ViewPenjualanItem(penjualanItem);
    }
    
    public void showDetailDP() {
        dp.penjualanid = idPenjualan;
        tblDP.setItems(dp.dpDetail);
        clmKodeDP.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggalDP.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmTotalDP.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        clmSisa.setCellValueFactory(new PropertyValueFactory<>("sisadp"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        penjualanGateway.ViewDP(dp);
    }
    
    //menampilkan data
    public void showData() {
        tblPenjualan.setItems(penjualanItem.penjualanItemDetail);
        penjualanItem.id = txtKodePenjualan.getText();
        clmKodeItem.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaItem.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        clmSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        penjualanGateway.ViewPenjualanItem(penjualanItem);
    }
    
    public void showDP() {
        tblDP.setItems(dp.dpDetail);
        dp.penjualanid = txtKodePenjualan.getText();
        clmKodeDP.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmTanggalDP.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        clmTotalDP.setCellValueFactory(new PropertyValueFactory<>("totaldp"));
        penjualanGateway.ViewDP(dp);
    }
    
    //menhitung total
    public void sumTotal() {
        tblPenjualan.getSelectionModel().selectFirst();
        int sum = 0;
        int items = tblPenjualan.getItems().size();
        
        for (int i = 0; i < items; i++) {
            tblPenjualan.getSelectionModel().select(i);
            String selectItems = tblPenjualan.getSelectionModel().getSelectedItem().getSubtotal();
            int newfloat = Integer.parseInt(selectItems);
            sum = sum + newfloat;
        }    
        String total = String.valueOf(sum);
        txtTotal.setText(total);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtKodePenjualan.setText(penjualanGateway.AutoKode());
        cbJenisPembayaran.getItems().addAll("CASH", "CREDIT");
        showData();
        showDP();
        sumTotal();
    }    
    
}
