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
public class PembelianItemModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty itemID;
//    public final SimpleStringProperty nama;
    public final SimpleStringProperty qty;
    public final SimpleStringProperty harga;
    public final SimpleStringProperty subtotal;
    
    public PembelianItemModel(String id, String itemID, String qty, String harga, String subtotal) {
        this.id = new SimpleStringProperty(id);
        this.itemID = new SimpleStringProperty(itemID);
//        this.nama = new SimpleStringProperty(nama);
        this.qty = new SimpleStringProperty(qty);
        this.harga = new SimpleStringProperty(harga);
        this.subtotal = new SimpleStringProperty(subtotal);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getItemID() {
        return itemID.get();
    }
    
    public void setSetItemiID(String itemID) {
        this.itemID.set(itemID);
    }
    
//    public String getNama() {
//        return nama.get();
//    }
//    
//    public void setNama(String nama) {
//        this.nama.set(nama);
//    }
      
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
    
    public String getSubtotal() {
        return subtotal.get();
    }
    
    public void setSubtotal(String subtotal) {
        this.subtotal.set(subtotal);
    }
}
