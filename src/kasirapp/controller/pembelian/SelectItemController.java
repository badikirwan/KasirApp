/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pembelian;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.pembelian.Pembelian;
import kasirapp.model.pembelian.PembelianGateway;
import kasirapp.model.pembelian.PembelianItem;
import kasirapp.model.pembelian.PembelianItemModel;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class SelectItemController implements Initializable {
    
    public String barangID, pembelianID;
    @FXML
    public TextField tfSearch, txtKodeItems, txtNamaItems, txtHargaItems, txtJmlPembelian, txtSubTotal;
    @FXML
    public Button btnAddItems, btnCancelItems;
    @FXML        
    Label txtJudul;
    ObservableList<PembelianItemModel> item = FXCollections.observableArrayList();
    
    Barang barang = new Barang();
    PembelianItem pembelianItem = new PembelianItem();
    BarangGateway barangGateway = new BarangGateway();
    PembelianGateway pembelianGateway = new PembelianGateway();
    
    @FXML
    public void btnAddItemsOnAction(ActionEvent event) {
        if (btnAddItems.getText().equals("Add")) {
            pembelianItem.id = pembelianID;
            pembelianItem.itemID = txtKodeItems.getText();
            pembelianItem.qty = txtJmlPembelian.getText();
            pembelianItem.harga = txtHargaItems.getText();
            pembelianItem.subtotal = txtSubTotal.getText();
            pembelianGateway.save(pembelianItem);
            Stage stage = (Stage) btnCancelItems.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } else {
            pembelianItem.id = pembelianID;
            pembelianItem.itemID = txtKodeItems.getText();
            pembelianItem.qty = txtJmlPembelian.getText();
            pembelianItem.harga = txtHargaItems.getText();
            pembelianItem.subtotal = txtSubTotal.getText();
            pembelianGateway.updateItem(pembelianItem);
            Stage stage = (Stage) btnCancelItems.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }
    }
    
    @FXML
    public void btnCancelItemsOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancelItems.getScene().getWindow();
        stage.close();
    }
         
    @FXML
    public void tfJumlahPembelian(KeyEvent event) {
        if (!txtJmlPembelian.getText().isEmpty()) {
            int jumlah = Integer.parseInt(txtJmlPembelian.getText());
            float harga = Float.parseFloat(txtHargaItems.getText());
            String subtotal = String.valueOf(jumlah * harga );
            txtSubTotal.setText(subtotal);
        } else {
            txtSubTotal.setText("0.0");
        }
    }
    
    public void showDetails() {
        barang.id = barangID;
        barangGateway.selectedViewItem(barang);
        txtKodeItems.setText(barang.id);
        txtNamaItems.setText(barang.namaBarang);
        txtHargaItems.setText(barang.hargaBeli);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
