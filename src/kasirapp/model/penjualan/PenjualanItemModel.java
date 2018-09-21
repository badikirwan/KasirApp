/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.penjualan;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BadikIrwan
 */
public class PenjualanItemModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty itemid;
    public final SimpleStringProperty nama;
    public final SimpleStringProperty qty;
    public final SimpleStringProperty harga;
    public final SimpleStringProperty diskon;
    public final SimpleStringProperty subtotal;
    
    public PenjualanItemModel(String id, String itemid,String nama, String qty, String harga, String diskon, String subtotal) {
        this.id = new SimpleStringProperty(id);
        this.itemid = new SimpleStringProperty(itemid);
        this.nama = new SimpleStringProperty(nama);
        this.qty = new SimpleStringProperty(qty);
        this.harga = new SimpleStringProperty(harga);
        this.diskon = new SimpleStringProperty(diskon);
        this.subtotal = new SimpleStringProperty(subtotal);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getItemid() {
        return itemid.get();
    }
    
    public void setItemid(String itemid) {
        this.itemid.set(itemid);
    }
    
    public String getNama() {
        return nama.get();
    }
    
    public void setNama(String nama) {
        this.nama.set(nama);
    }
    
    public String getQty() {
        return qty.get();
    }
    
    public void setQty(String qty) {
        this.qty.set(qty);
    }
    
    public String getHarga() {
        return harga.get();
    }
    
    public void setHarga(String harga) {
        this.harga.set(harga);
    }
    
    public String getDiskon() {
        return diskon.get();
    }
    
    public void setDiskon(String diskon) {
        this.diskon.set(diskon);
    }
    
    public String getSubtotal() {
        return subtotal.get();
    }
    
    public void setSubtotal(String subtotal) {
        this.subtotal.set(subtotal);
    }
}
