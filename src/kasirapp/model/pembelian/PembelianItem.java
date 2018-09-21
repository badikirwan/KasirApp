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
public class PembelianItem {
    
    public String id;
    public String itemID;
//    public String nama;
    public String qty;
    public String harga;
    public String subtotal;
    
    public ObservableList<PembelianItemModel> pembelianItemDetail = FXCollections.observableArrayList();
}
