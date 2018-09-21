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
public class PembayaranDPModel {
    
    public final SimpleStringProperty id;
    public final SimpleStringProperty tanggal;
    public final SimpleStringProperty totaldp;
    public final SimpleStringProperty sisadp;
    public final SimpleStringProperty status;
    public final SimpleStringProperty penjualanid;
    
    public PembayaranDPModel(String id, String tanggal, String totaldp, String sisadp, String status, String penjualanid) {
        this.id = new SimpleStringProperty(id);
        this.tanggal = new SimpleStringProperty(tanggal);
        this.totaldp = new SimpleStringProperty(totaldp);
        this.sisadp = new SimpleStringProperty(sisadp);
        this.status = new SimpleStringProperty(status);
        this.penjualanid = new SimpleStringProperty(penjualanid);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getTanggal() {
        return tanggal.get();
    }
    
    public void setTanggal(String tanggal) {
        this.tanggal.set(tanggal);
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
    
    public String getPenjualanid() {
        return penjualanid.get();
    }
    
    public void setPenjualanid(String penjualanid) {
        this.penjualanid.set(penjualanid);
    }
}
