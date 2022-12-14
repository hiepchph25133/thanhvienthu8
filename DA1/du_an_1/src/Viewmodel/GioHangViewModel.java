/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

/**
 *
 * @author HP
 */
public class GioHangViewModel {

    private String maSP;
    private String tenSp;
    private int SoLuong;
    private Double donGia;
    private Double thanhtien;
    private Double GiamGia;
  

    public GioHangViewModel() {
    }

    public GioHangViewModel(String maSP, String tenSp, int SoLuong, Double donGia) {
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
//        this.thanhtien = thanhtien;
//        this.GiamGia = GiamGia;
//        this.HinhThucGiamGia = HinhThucGiamGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public double getThanhtien() {
        return donGia * SoLuong;
    }

    public void setThanhtien(double thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Double getGiamGia() {
        return GiamGia;
   }

    public void setGiamGia(Double GiamGia) {
        this.GiamGia = GiamGia;
    }

//    public String getHinhThucGiamGia() {
//        return HinhThucGiamGia;
//    }
//
//    public void setHinhThucGiamGia(String HinhThucGiamGia) {
//        this.HinhThucGiamGia = HinhThucGiamGia;
//    }
    
}
