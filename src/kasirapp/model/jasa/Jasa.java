/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.jasa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class Jasa {
    
    public String id;
    public String namaJasa;
    public String biayaJasa;
    public String keterangan;
    public String tanggalInput;
    
    public ObservableList<JasaModel> jasaDetail = FXCollections.observableArrayList();
}
