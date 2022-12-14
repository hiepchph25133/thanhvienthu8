/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Khachhang {

    private String maKH;
    private String hotenKH;
    private String ngaSinh;
    private String diachi;
    private String SDT;
    private String thanhpho;
    private String quocgia;

    public Khachhang() {
    }

    public Khachhang(String maKH, String hotenKH, String ngaSinh, String diachi, String SDT, String thanhpho, String quocgia) {
        this.maKH = maKH;
        this.hotenKH = hotenKH;
        this.ngaSinh = ngaSinh;
        this.diachi = diachi;
        this.SDT = SDT;
        this.thanhpho = thanhpho;
        this.quocgia = quocgia;
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

    public String getNgaSinh() {
        return ngaSinh;
    }

    public void setNgaSinh(String ngaSinh) {
        this.ngaSinh = ngaSinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

  
}
