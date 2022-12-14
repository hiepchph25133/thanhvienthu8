/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

/**
 *
 * @author Lenovo
 */
public class chucvu_viewmodel {
    private String MaCV, TenCV, Mota;

    public chucvu_viewmodel(String MaCV, String TenCV, String Mota) {
        this.MaCV = MaCV;
        this.TenCV = TenCV;
        this.Mota = Mota;
    }

    public chucvu_viewmodel() {
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    @Override
    public String toString() {
        return TenCV;
    }
    
}
