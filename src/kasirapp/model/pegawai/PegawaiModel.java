/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pegawai;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BadikIrwan
 */
public class PegawaiModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty nama;
    public final SimpleStringProperty alamat;
    public final SimpleStringProperty notlp;
    
    public PegawaiModel(String id, String nama, String alamat, String notlp) {
        this.id = new SimpleStringProperty(id);
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.notlp = new SimpleStringProperty(notlp);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getNama() {
        return nama.get();
    }
    
    public void setNama(String nama) {
        this.nama.set(nama);
    }
    
    public String getAlamat() {
        return alamat.get();
    }
    
    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }
    
    public String getNotlp() {
        return notlp.get();
    }
    
    public void setNotlp(String notlp) {
        this.notlp.set(notlp);
    }
}
