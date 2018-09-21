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
public class pembayaranDP {
    
    public String id;
    public String tanggal;
    public String totaldp;
    public String sisadp;
    public String status;
    public String penjualanid;
    
    public ObservableList<PembayaranDPModel> dpDetail = FXCollections.observableArrayList();
}
