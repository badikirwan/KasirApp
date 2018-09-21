/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.barang;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class Barang {
    
    public String id;
    public String namaBarang;
    public String jenisBarang;
    public String hargaBeli;
    public String hargaJual;
    public String stokBarang;
    public String satuan;
    public String keterangan;
    public String tanggalInput;
    
    public ObservableList<BarangModel> barangDetail = FXCollections.observableArrayList();
}
