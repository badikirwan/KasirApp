/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.controller.laporan;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import kasirapp.controller.BaseController;

/**
 * FXML Controller class
 *
 * @author BadikIrwan
 */
public class DialogController extends BaseController {

    @FXML
    DatePicker tglAwal, tglAhir;
    @FXML
    Button btnCancel;
    
    @FXML
    public void btnPrintOnAction(ActionEvent event) throws ParseException {
        String tglawal = tglAwal.getValue().toString();
        String tglahir = tglAhir.getValue().toString();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date cok = df.parse(tglawal);
        Date cok1 = df.parse(tglahir);
        
        PrintReport2("report_penjualan_detail", "tgl1", cok, "tgl2", cok1);
    }
    
    @FXML
    public void btnCancelOnAction(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
