/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

/**
 *
 * @author HP
 */
public class KhachHangviewmodel {
    private String maKH;
    private String hotenKH;
    private String SDT;
    private String diachi;

    public KhachHangviewmodel() {
    }

    public KhachHangviewmodel(String maKH, String hotenKH, String SDT, String diachi) {
        this.maKH = maKH;
        this.hotenKH = hotenKH;
        this.SDT = SDT;
        this.diachi = diachi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHotenKH() {
        return hotenKH;
    }

    public void setHotenKH(String hotenKH) {
        this.hotenKH = hotenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    
}
