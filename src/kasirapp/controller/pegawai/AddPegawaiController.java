/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.pegawai;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kasirapp.model.pegawai.Pegawai;
import kasirapp.model.pegawai.PegawaiGateway;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class AddPegawaiController implements Initializable {
    
    public String barangID;
    @FXML
    public Button btnSave, btnCancel, btnUpdate;
    @FXML
    public TextField txtNamaPegawai, txtTelepon;
    @FXML
    public Label txtPesan, txtJudul;
    @FXML
    public TextArea txtAlamat;
    
    Pegawai pegawai = new Pegawai();
    PegawaiController pegawaiController = new PegawaiController();
    PegawaiGateway pegawaiGateway = new PegawaiGateway();
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        pegawai.nama = txtNamaPegawai.getText();
        pegawai.notlp = txtTelepon.getText();
        pegawai.alamat = txtAlamat.getText();
        
        if (btnSave.getText().equals("Save")) {
            if (pegawaiGateway.save(pegawai)) {
                txtPesan.setText("Data berhasil disimpan!!");
                txtPesan.setTextFill(Color.web("#4ac571"));
            } else {
                txtPesan.setText("Data gagal disimpan!!");
                txtPesan.setTextFill(Color.web("#c44d4a"));
            }
        } else {
            if (pegawaiGateway.update(pegawai)) {
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

    public void showDetails() {
        pegawai.id = barangID;
        pegawaiGateway.selectedViewItem(pegawai);
        txtNamaPegawai.setText(pegawai.nama);
        txtTelepon.setText(pegawai.notlp);
        txtAlamat.setText(pegawai.alamat);
    }
    
    public void clearView() {
        txtNamaPegawai.clear();
        txtTelepon.clear();
        txtAlamat.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
