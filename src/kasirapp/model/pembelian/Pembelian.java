/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pembelian;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class Pembelian {
    
    public String id;
    public String pegawaiID;
    public String tanggalBeli;
    public String pembayaranType;
    public String total;
    public String keterangan;
    
    public ObservableList<PembelianModel> pembelianDetail = FXCollections.observableArrayList();
}
