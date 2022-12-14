/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

/**
 *
 * @author HP
 */
public class SanPhamViewModel {

    private String maSP;
    private String tensp;
    private String tenMS;
    private String tenHang;
    private int soLuong;
    private double gianhap;
    private double giaBan;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String maSP, String tensp, String tenMS, String tenHang, int soLuong, double gianhap, double giaBan) {
        this.maSP = maSP;
        this.tensp = tensp;
        this.tenMS = tenMS;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.gianhap = gianhap;
        this.giaBan = giaBan;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String TenMS) {
        this.tenMS = TenMS;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String TenHang) {
        this.tenHang = TenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGianhap() {
        return gianhap;
    }

    public void setGianhap(double gianhap) {
        this.gianhap = gianhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

}
