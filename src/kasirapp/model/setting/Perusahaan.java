/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasirapp.model.setting;

import java.sql.Blob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author BadikIrwan
 */
public class Perusahaan {
    
    public String id;
    public String nama;
    public String kota;
    public String tlp;
    public String fax;
    public String alamat;
    public Blob logo;
    public Image image;
    public String imagePath;
    
    public ObservableList<PerusahaanModel> PerusahaanProfile = FXCollections.observableArrayList();
}
