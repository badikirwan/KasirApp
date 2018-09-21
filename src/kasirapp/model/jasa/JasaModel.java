/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.jasa;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BadikIrwan
 */
public class JasaModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty namaJasa;
    public final SimpleStringProperty biayaJasa;
    public final SimpleStringProperty keterangan;
    public final SimpleStringProperty tanggalInput;
    
    public JasaModel(String id, String namaJasa, String biayaJasa, String keterangan, String tanggalInput) {
        this.id = new SimpleStringProperty(id);
        this.namaJasa = new SimpleStringProperty(namaJasa);
        this.biayaJasa = new SimpleStringProperty(biayaJasa);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.tanggalInput = new SimpleStringProperty(tanggalInput);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getNamaJasa() {
        return namaJasa.get();
    }
    
    public void setNamaJasa(String namaJasa) {
        this.namaJasa.set(namaJasa);
    }
    
    public String getBiayaJasa() {
        return biayaJasa.get();
    }
    
    public void setBiayaJasa(String biayaJasa) {
        this.biayaJasa.set(biayaJasa);
    }
    
    public String getKeterangan() {
        return keterangan.get();
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan.set(keterangan);
    }
    
    public String getTanggalInput() {
        return tanggalInput.get();
    }
    
    public void setTanggalInput(String tanggalInput) {
        this.tanggalInput.set(tanggalInput);
    }
}
