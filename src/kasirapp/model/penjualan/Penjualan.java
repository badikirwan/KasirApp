/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.penjualan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class Penjualan {
    
    public String id;
    public String idkasir;
    public String tanggal;
    public String customer;
    public String customertlp;
    public String pembayarantype;
    public String total;
    public String keterangan;
    public String totaldp;
    public String sisadp;
    public String status;
    
    public ObservableList<PenjualanModel> penjualanDetail = FXCollections.observableArrayList();
}
