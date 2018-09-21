/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.barang;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BadikIrwan
 */
public class BarangModel {

    public final SimpleStringProperty id;
    public final SimpleStringProperty namaBarang;
    public final SimpleStringProperty jenisBarang;
    public final SimpleStringProperty hargaBeli;
    public final SimpleStringProperty hargaJual;
    public final SimpleStringProperty stokBarang;
    public final SimpleStringProperty satuan;
    public final SimpleStringProperty keterangan;
    public final SimpleStringProperty tanggalInput;

    public BarangModel(String id, String namaBarang, String jenisBarang, String hargaBeli, String hargaJual, String stokBarang, String satuan, String keterangan, String tanggalInput) {
        this.id = new SimpleStringProperty(id);
        this.namaBarang = new SimpleStringProperty(namaBarang);
        this.jenisBarang = new SimpleStringProperty(jenisBarang);
        this.hargaBeli = new SimpleStringProperty(hargaBeli);
        this.hargaJual = new SimpleStringProperty(hargaJual);
        this.stokBarang = new SimpleStringProperty(stokBarang);
        this.satuan = new SimpleStringProperty(satuan);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.tanggalInput = new SimpleStringProperty(tanggalInput);

    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getNamaBarang() {
        return namaBarang.get();
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang.set(namaBarang);
    }
    
    public String getJenisBarang() {
        return jenisBarang.get();
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang.set(jenisBarang);
    }
    
    public String getHargaBeli() {
        return hargaBeli.get();
    }

    public void setHargaBeli(String hargaBeli) {
        this.hargaBeli.set(hargaBeli);
    }
    
    public String getHargaJual() {
        return hargaJual.get();
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual.set(hargaJual);
    }
    
    public String getStokBarang() {
        return stokBarang.get();
    }

    public void setStokBarang(String stokBarang) {
        this.stokBarang.set(stokBarang);
    }
    
    public String getSatuan() {
        return satuan.get();
    }

    public void setSatuan(String satuan) {
        this.satuan.set(satuan);
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
