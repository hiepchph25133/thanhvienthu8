/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class SanPham {
    private String maSP;
    private String tensp;
    private String maMS;
    private String maDSP;
    private String maHang;
    private String mota;
    private String loaihang;
    private int soLuong;
    private double gianhap;
    private double giaBan;
    private String hinhanh;
    private Double giamgia ;
    private String QRCOde;
    public SanPham() {
    }

    public SanPham(String maSP, String tensp, String maMS, String maDSP, String maHang, String mota, String loaihang, int soLuong, double gianhap, double giaBan, String hinhanh,String QRCOde ) {
        this.maSP = maSP;
        this.tensp = tensp;
        this.maMS = maMS;
        this.maDSP = maDSP;
        this.maHang = maHang;
        this.mota = mota;
        this.loaihang = loaihang;
        this.soLuong = soLuong;
        this.gianhap = gianhap;
        this.giaBan = giaBan;
        this.hinhanh = hinhanh;
        this.QRCOde = QRCOde;
    }

//    public SanPham(Double giamgia) {
//        this.giamgia = giamgia;
//    }

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

    public String getMaMS() {
        return maMS;
    }

    public void setMaMS(String maMS) {
        this.maMS = maMS;
    }

    public String getMaDSP() {
        return maDSP;
    }

    public void setMaDSP(String maDSP) {
        this.maDSP = maDSP;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getLoaihang() {
        return loaihang;
    }

    public void setLoaihang(String loaihang) {
        this.loaihang = loaihang;
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

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Double getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(Double giamgia) {
        this.giamgia = giamgia;
    }

    public String getQRCOde() {
        return QRCOde;
    }

    public void setQRCOde(String QRCOde) {
        this.QRCOde = QRCOde;
    }
    
    
    
}
