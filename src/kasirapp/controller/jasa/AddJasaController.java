/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.jasa;

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
import kasirapp.model.jasa.Jasa;
import kasirapp.model.jasa.JasaGateway;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class AddJasaController implements Initializable {

    public String barangID;
    @FXML
    public Button btnSave, btnCancel, btnUpdate;
    @FXML
    public TextField txtNamaJasa, txtBiayaJasa;
    @FXML
    public Label txtPesan, txtJudul;
    @FXML
    public TextArea txtKeterangan;
    
    Jasa jasa = new Jasa();
    JasaController jasaController = new JasaController();
    JasaGateway jasaGateway = new JasaGateway();
    
    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        jasa.namaJasa = txtNamaJasa.getText();
        jasa.biayaJasa = txtBiayaJasa.getText();
        jasa.keterangan = txtKeterangan.getText();
        
        if (btnSave.getText().equals("Save")) {
            if (jasaGateway.save(jasa)) {
                txtPesan.setText("Data berhasil disimpan!!");
                txtPesan.setTextFill(Color.web("#4ac571"));
            } else {
                txtPesan.setText("Data gagal disimpan!!");
                txtPesan.setTextFill(Color.web("#c44d4a"));
            }
        } else {
            if (jasaGateway.update(jasa)) {
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
        jasa.id = barangID;
        jasaGateway.selectedViewItem(jasa);
        txtNamaJasa.setText(jasa.namaJasa);
        txtBiayaJasa.setText(jasa.biayaJasa);
        txtKeterangan.setText(jasa.keterangan);
    }
    
    public void clearView() {
        txtNamaJasa.clear();
        txtBiayaJasa.clear();
        txtKeterangan.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
