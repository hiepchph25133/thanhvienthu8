/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class chucvu_domainmodel {
    private String MaCV, TenCV, Mota;

    public chucvu_domainmodel(String MaCV, String TenCV, String Mota) {
        this.MaCV = MaCV;
        this.TenCV = TenCV;
        this.Mota = Mota;
    }

    public chucvu_domainmodel() {
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
   
}
