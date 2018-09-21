/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.penjualan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BadikIrwan
 */
public class PenjualanItem {
    
    public String id;
    public String itemid;
    public String nama;
    public String qty;
    public String harga;
    public String diskon;
    public String subtotal;
    
    public ObservableList<PenjualanItemModel> penjualanItemDetail = FXCollections.observableArrayList();
}
