/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pembelian;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import kasirapp.controller.Generate;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.barang.BarangModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class AddPembelianController implements Initializable {

    public String barangID, pembelianID;
    @FXML
    public TableView<BarangModel> tblAddPembelian;
    @FXML
    public TableColumn<Object, Object> clmKodeBarang;
    @FXML
    public TableColumn<Object, Object> clmNamaBarang;
    @FXML
    public TableColumn<Object, Object> clmSatuan;
    @FXML
    public Button btnCancel;
    @FXML
    public TextField tfSearch, txtKodeItems, txtNamaItems, txtHargaItems, txtJmlPembelian, txtSubTotal;
    @FXML
    Label txtJudul;
    @FXML
    ComboBox cbKategori = new ComboBox();
    
    Barang barang = new Barang();
    BarangGateway barangGateway = new BarangGateway();
    Generate generate = new Generate();
    
    @FXML
    public void btnAddOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Select Items");
                item.barangID = generate.getRandom();
                item.pembelianID = pembelianID;
                item.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.getIcons().add(new Image("kasirapp/assets/icon.png"));
                nStage.show();
            } catch (IOException e) {
                 e.printStackTrace();
            }
    }
    
    @FXML
    public void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    @FXML
    public void btnRefreshOnAction(ActionEvent event) {
        showData();
    }
    
    @FXML
    public void tfSearchOnAction(Event event) {
        barang.barangDetail.clear();
        barang.namaBarang = tfSearch.getText().trim();
        tblAddPembelian.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.searchView(barang);
    }
    
    @FXML
    public void AddItem(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            BarangModel selectItem = (BarangModel) tblAddPembelian.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Select Items");
                item.barangID = selectItem.getId();
                item.pembelianID = pembelianID;
                item.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.getIcons().add(new Image("kasirapp/assets/icon.png"));
                nStage.show();
               
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void actClikItem(MouseEvent event) {
        if (event.getClickCount() == 2) {
            BarangModel selectItem = (BarangModel) tblAddPembelian.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/kasirapp/view/pembelian/SelectItem.fxml"));
            
            try {
                fxmlLoader.load();
                Parent parent = fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0, 0, 0, 0));
                SelectItemController item = fxmlLoader.getController();
                item.txtJudul.setText("Select Items");
                item.barangID = selectItem.getId();
                item.pembelianID = pembelianID;
                item.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.getIcons().add(new Image("kasirapp/assets/icon.png"));
                nStage.show();
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }
    }
    
    @FXML
    public void cbAction(ActionEvent event) {
        if (cbKategori.getValue().equals("Barang Produksi")) {
            showDataBp();
        } else {
            showDataBj();
        }
    }
    
    @FXML
    public void btnAddItemsOnAction(ActionEvent event) {
        
    }
    
    @FXML
    public void btnCancelItemsOnAction(ActionEvent event) {
        
    }
    
    public void showDetails() {
        barang.id = barangID;
        barangGateway.selectedViewItem(barang);
        txtKodeItems.setText(barang.id);
        txtNamaItems.setText(barang.namaBarang);
        txtHargaItems.setText(barang.hargaBeli);
    }
    
    public void showData() {
        tblAddPembelian.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.view(barang);
    }
    
    public void showDataBj() {
        tblAddPembelian.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.viewBJ(barang);
    }
    
    public void showDataBp() {
        tblAddPembelian.setItems(barang.barangDetail);
        clmKodeBarang.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        clmSatuan.setCellValueFactory(new PropertyValueFactory<>("satuan"));
        barangGateway.viewBP(barang);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDataBj();
        cbKategori.getItems().addAll("Barang Jadi", "Barang Produksi");
    }    
    
}
