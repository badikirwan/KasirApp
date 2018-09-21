/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pegawai;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class Pegawai {
    
    public String id;
    public String nama;
    public String alamat;
    public String notlp;
    
    public ObservableList<PegawaiModel> pegawaiDetail = FXCollections.observableArrayList();
}
