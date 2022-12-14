/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

/**
 *
 * @author HP
 */
public class SanPham_BanHangViewModel {

    private String loaiHang;
    private String maSP;
    private String TenSP;
    private String moTa;
    private int soLuong;
    private double gia;
    private Double giamgia ;

    public SanPham_BanHangViewModel() {
    }

    public SanPham_BanHangViewModel(String loaiHang, String maSP, String TenSP, String moTa, int soLuong, double gia, Double giamgia) {
        this.loaiHang = loaiHang;
        this.maSP = maSP;
        this.TenSP = TenSP;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.gia = gia;
        this.giamgia = giamgia;
    }

    public String getLoaiHang() {
        return loaiHang;
    }

    public void setLoaiHang(String loaiHang) {
        this.loaiHang = loaiHang;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Double getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(Double giamgia) {
        this.giamgia = giamgia;
    }
  
    
    

   
    
}
