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
public class PenjualanModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty idkasir;
    public final SimpleStringProperty tanggal;
    public final SimpleStringProperty customer;
    public final SimpleStringProperty customertlp;
    public final SimpleStringProperty pembayarantype;
    public final SimpleStringProperty total;
    public final SimpleStringProperty keterangan;
    public final SimpleStringProperty totaldp;
    public final SimpleStringProperty sisadp;
    public final SimpleStringProperty status;
    
    public PenjualanModel(String id, String idkasir, String tanggal, String customer, String customertlp, String pembayarantype, String total, String keterangan, String totaldp, String sisadp, String status) {
        this.id = new SimpleStringProperty(id);
        this.idkasir = new SimpleStringProperty(idkasir);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.customer = new SimpleStringProperty(customer);
        this.customertlp = new SimpleStringProperty(customertlp);
        this.pembayarantype = new SimpleStringProperty(pembayarantype);
        this.total = new SimpleStringProperty(total);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.totaldp = new SimpleStringProperty(totaldp);
        this.sisadp = new SimpleStringProperty(sisadp);
        this.status = new SimpleStringProperty(status);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getIdkasir() {
        return idkasir.get();
    }
    
    public void setIdkasir(String idkasir) {
        this.idkasir.set(idkasir);
    }
    
    public String getTanggal() {
        return tanggal.get();
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal.set(tanggal);
    }
    
    public String getCustomer() {
        return customer.get();
    }
    
    public void setCustomer(String customer) {
        this.customer.set(customer);
    }
    
    public String getCustomertlp() {
        return customertlp.get();
    }
    
    public void setCustomertlp(String customertlp) {
        this.customertlp.set(customertlp);
    }
    
    public String getPembayarantype() {
        return pembayarantype.get();
    }
    
    public void setPembayarantype(String pembayarantype) {
        this.pembayarantype.set(pembayarantype);
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
    
     public String getTotaldp() {
        return totaldp.get();
    }
    
    public void setTotaldp(String totaldp) {
        this.totaldp.set(totaldp);
    }
    
    public String getSisadp() {
        return sisadp.get();
    }
    
    public void setSisadp(String sisadp) {
        this.sisadp.set(sisadp);
    }
    
    public String getStatus() {
        return status.get();
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
}
