/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.barang;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kasirapp.model.barang.Barang;
import kasirapp.model.barang.BarangGateway;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class AddBarangController implements Initializable {

    public String barangID, jenisBarang;
    @FXML
    public Button btnSave, btnCancel, btnUpdate;
    @FXML
    public TextField txtKodeBarang, txtNamaBarang, txtHargaBeli, txtHargaJual, txtStok, txtSatuan;
    @FXML
    public Label txtPesan, txtJudul;
    @FXML
    public TextArea txtKeterangan;
    @FXML
    ComboBox cbKategori = new ComboBox();
    
    Barang barang = new Barang();
    BarangController barangController = new BarangController();
    BarangGateway barangGateway = new BarangGateway();
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        barang.namaBarang = txtNamaBarang.getText();
        barang.hargaBeli = txtHargaBeli.getText();
        barang.hargaJual = txtHargaJual.getText();
        barang.stokBarang = txtStok.getText();
        barang.satuan = txtSatuan.getText();
        barang.keterangan = txtKeterangan.getText();
        barang.jenisBarang = jenisBarang;
        
        if (btnSave.getText().equals("Save")) {
            if (barangGateway.save(barang)) {
                txtPesan.setText("Data berhasil disimpan!!");
                txtPesan.setTextFill(Color.web("#4ac571"));
            } else {
                txtPesan.setText("Data gagal disimpan!!");
                txtPesan.setTextFill(Color.web("#c44d4a"));
            }
        } else {
            if (barangGateway.update(barang)) {
                txtPesan.setText("Data berhasil diupdate!!");
                txtPesan.setTextFill(Color.web("#4ac571"));
            } else {
                txtPesan.setText("Data gagal diupdate!!");
                txtPesan.setTextFill(Color.web("#c44d4a"));
            }
        }
        clearView();
    }
    
    @FXML
    public void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cbAction(ActionEvent event) {
        if (cbKategori.getValue().equals("Barang Produksi")) {
            txtHargaBeli.setDisable(true);
            txtHargaJual.setDisable(true);
            txtStok.setDisable(true);
             txtHargaBeli.setText("0");
            txtHargaJual.setText("0");
            txtStok.setText("0");
            jenisBarang = "2";
        } else {
            txtHargaBeli.setDisable(false);
            txtHargaJual.setDisable(false);
            txtStok.setDisable(false);
            jenisBarang = "1";
        }
    }
    
    public void showDetails() {
        barang.id = barangID;
        barangGateway.selectedViewItem(barang);
        txtNamaBarang.setText(barang.namaBarang);
        txtHargaBeli.setText(barang.hargaBeli);
        txtHargaJual.setText(barang.hargaJual);
        txtStok.setText(barang.stokBarang);
        txtSatuan.setText(barang.satuan);
        txtKeterangan.setText(barang.keterangan);
        if (barang.jenisBarang.equals("1")) {
            cbKategori.getSelectionModel().select("Barang Jadi");
        } else {
            cbKategori.getSelectionModel().select("Barang Produksi");
            txtHargaBeli.setDisable(true);
            txtHargaJual.setDisable(true);
            txtStok.setDisable(true);
        }
    }
    
    public void clearView() {
        txtNamaBarang.clear();
        txtHargaBeli.clear();
        txtHargaJual.clear();
        txtStok.clear();
        txtSatuan.clear();
        txtKeterangan.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbKategori.getItems().addAll("Barang Jadi", "Barang Produksi");
    }    
    
}
