/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.barang.BarangModel;
import kasirapp.model.jasa.Jasa;
import kasirapp.model.jasa.JasaGateway;
import kasirapp.model.jasa.JasaModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class PenjualanItemController implements Initializable {

    public String barangID, penjualanID;
    @FXML
    public TableView<BarangModel> tblItem;
    @FXML
    public TableColumn<Object, Object> clmKodeBarang;
    @FXML
    public TableColumn<Object, Object> clmNamaBarang;
    @FXML
    public TableColumn<Object, Object> clmSatuan;
    @FXML
    public TableColumn<Object, Object> clmStok;
     @FXML
    public TableView<JasaModel> tblJasa;
    @FXML
    public TableColumn<Object, Object> clmKodeJasa;
    @FXML
    public TableColumn<Object, Object> clmNamaJasa;
    @FXML
    public Button btnClose;
    @FXML
    public TextField tfSearch, txtKodeItems, txtNamaItems, txtHargaItems, txtJmlPembelian, txtSubTotal;
    @FXML
    Label txtJudul;
    
    Barang barang = new Barang();
    Jasa jasa = new Jasa();
    BarangGateway barangGateway = new BarangGateway();
    JasaGateway jasaGateway = new JasaGateway();
    
    @FXML
    public void btnAddOnAction(ActionEvent event) {
        
    }
    
    @FXML
    public void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        showDataBarang();
        showDataJasa();
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        barang.barangDetail.clear();
        barang.namaBarang = tfSearch.getText().trim();
        tblItem.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.searchView(barang);
    }
    
    @FXML
    public void AddItem(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            BarangModel selectItem = (BarangModel) tblItem.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Select Items");
                item.barangID = selectItem.getId();
                item.penjualanID = penjualanID;
                item.showDetailBarang();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void actClikItem(MouseEvent event) {
        if (event.getClickCount() == 2) {
            BarangModel selectItem = (BarangModel) tblItem.getSelectionModel().getSelectedItem();
            if (selectItem.getStokBarang().equals("0")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Ada kesalahan!!");
                alert.setContentText("Ooops, stok kosong");
                alert.showAndWait();
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Select Items");
                item.barangID = selectItem.getId();
                item.penjualanID = penjualanID;
                item.showDetailBarang();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
            } catch (IOException ex) {
                Logger.getLogger(PenjualanItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        }
    }
    
    @FXML
    public void actClickJasa(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectJasa();
        }
    }
    
    @FXML
    public void actKeyJasa(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            selectJasa();
        }
    }
    
    public void selectJasa() {
        JasaModel selectJasa = tblJasa.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/penjualan/SelectItem.fxml"));
        
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            SelectItemController item = fxmlLoader.getController();
            item.txtJudul.setText("Select Jasa");
            item.barangID = selectJasa.getId();
            item.penjualanID = penjualanID;
            item.showDetailJasa();
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PenjualanItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showDetails() {
        barang.id = barangID;
        barangGateway.selectedViewItem(barang);
        txtKodeItems.setText(barang.id);
        txtNamaItems.setText(barang.namaBarang);
        txtHargaItems.setText(barang.hargaBeli);
    }
    
    //menampilkan daftar barang
    public void showDataBarang() {
        tblItem.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmStok.setCellValueFactory(new PropertyValueFactory<>("stokBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.viewBJ(barang);
    }
    
    //menampilkan daftar jasa
    public void showDataJasa() {
        tblJasa.setItems(jasa.jasaDetail);
        clmKodeJasa.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaJasa.setCellValueFactory(new PropertyValueFactory<>("namaJasa"));
        jasaGateway.view(jasa);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDataBarang();
        showDataJasa();
    }    
    
}
