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
import java.time.LocalDate;
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

    String judul;
    @FXML
    DatePicker tglAwal, tglAhir;
    @FXML
    Button btnCancel;
    
    @FXML
    public void btnPrintOnAction(ActionEvent event) throws ParseException {
        LocalDate tglawal = tglAwal.getValue();
        LocalDate tglahir = tglAhir.getValue();
        //Date df = new SimpleDateFormat("yyyy/MM/dd").parse(tglawal);
        //Date df1 = new SimpleDateFormat("yyyy/MM/dd").parse(tglahir);
        //Date cok = df.parse(tglawal);
        //Date cok1 = df.parse(tglahir);
        System.out.print(tglawal);
        //System.out.print(df1);
        switch(judul) {
            case "Rekap Penjualan Harian":
                PrintReport2("report_penjualan_detail", tglawal, tglahir);
                break;
            case "Rekap pembelian per faktur":
                PrintReport2("report_pembelian_perfaktur", tglawal, tglahir);
                break;
            case "Rekap pembelian per item":
                PrintReport2("report_pembelian_peritem", tglawal, tglahir);
                break;
        }    
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
