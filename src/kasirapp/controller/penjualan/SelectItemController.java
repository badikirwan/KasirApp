/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.penjualan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;
import kasirapp.model.jasa.Jasa;
import kasirapp.model.jasa.JasaGateway;
import kasirapp.model.pembelian.PembelianItemModel;
import kasirapp.model.penjualan.PenjualanGateway;
import kasirapp.model.penjualan.PenjualanItem;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class SelectItemController implements Initializable {

    public String barangID, pembelianID, penjualanID;
    @FXML
    public TextField tfSearch, txtKodeItems, txtNamaItems, txtHargaItems, txtJmlPembelian, txtSubTotal;
    @FXML
    public Button btnAddItems, btnCancelItems;
    @FXML        
    public Label txtJudul;
    ObservableList<PembelianItemModel> item = FXCollections.observableArrayList();
    
    Barang barang = new Barang();
    Jasa jasa = new Jasa();
    PenjualanItem penjualanItem = new PenjualanItem();
    BarangGateway barangGateway = new BarangGateway();
    JasaGateway jasaGateway = new JasaGateway();
    PenjualanGateway penjualanGateway = new PenjualanGateway();
    
    @FXML
    public void btnAddItemsOnAction(ActionEvent event) {
        if (btnAddItems.getText().equals("Add")) {
            penjualanItem.id = penjualanID;
            penjualanItem.itemid = txtKodeItems.getText();
            penjualanItem.nama = txtNamaItems.getText();
            penjualanItem.qty = txtJmlPembelian.getText();
            penjualanItem.harga = txtHargaItems.getText();
            penjualanItem.subtotal = txtSubTotal.getText();
            penjualanGateway.SaveItemPenjualan(penjualanItem);
            Stage stage = (Stage) btnCancelItems.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } else {
            penjualanItem.id = penjualanID;
            penjualanItem.itemid = txtKodeItems.getText();          
            penjualanItem.nama = txtNamaItems.getText();
            penjualanItem.qty = txtJmlPembelian.getText();
            penjualanItem.harga = txtHargaItems.getText();
            penjualanItem.subtotal = txtSubTotal.getText();
            penjualanGateway.UpdateItemPenjualan(penjualanItem);
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
    
    public void showDetailBarang() {
        barang.id = barangID;
        barangGateway.selectedViewItem(barang);
        txtKodeItems.setText(barang.id);
        txtNamaItems.setText(barang.namaBarang);
        txtHargaItems.setText(barang.hargaBeli);
    }
    
    public void showDetailJasa() {
        jasa.id = barangID;
        jasaGateway.selectedViewItem(jasa);
        txtKodeItems.setText(jasa.id);
        txtNamaItems.setText(jasa.namaJasa);
        txtHargaItems.setText(jasa.biayaJasa);
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
