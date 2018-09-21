/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.pembelian;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BadikIrwan
 */
public class PembelianModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty pegawaiID;
    public final SimpleStringProperty tanggalBeli;
    public final SimpleStringProperty total;
    public final SimpleStringProperty keterangan;
    
    public PembelianModel(String id, String pegawaiID, String tanggalBeli, String total, String keterangan) {
        this.id = new SimpleStringProperty(id);
        this.pegawaiID = new SimpleStringProperty(pegawaiID);
        this.tanggalBeli = new SimpleStringProperty(tanggalBeli);
        this.total = new SimpleStringProperty(total);
        this.keterangan = new SimpleStringProperty(keterangan);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getPegawaiID() {
        return pegawaiID.get();
    }
    
    public void setSetPegawaiID(String pegawaiID) {
        this.pegawaiID.set(pegawaiID);
    }
    
    public String getTanggalBeli() {
        return tanggalBeli.get();
    }
    
    public void setTanggalBeli(String tanggalBeli) {
        this.tanggalBeli.set(tanggalBeli);
    }
     
    public String getTotal() {
        return total.get();
    }
    
    public void setTotal(String total) {
        this.total.set(total);
    }
    
    public String getKeterangan() {
        return keterangan.get();
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan.set(keterangan);
    }
}
