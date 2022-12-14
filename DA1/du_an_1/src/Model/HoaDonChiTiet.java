/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class HoaDonChiTiet {

    private String maHD;
    private String maSP;
    private int soLuong;
    private Double dongia;
    private Double tienthua;
    private Double tiengiamgia;
    private String trangThai ;
    private Double tongTien ;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHD, String maSP, int soLuong, Double dongia, Double tienthua, Double tiengiamgia, String trangThai, Double tongTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.dongia = dongia;
        this.tienthua = tienthua;
        this.tiengiamgia = tiengiamgia;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

 

    public HoaDonChiTiet(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDongia() {
        return dongia;
    }

    public void setDongia(Double dongia) {
        this.dongia = dongia;
    }

    public Double getTienthua() {
        return tienthua;
    }

    public void setTienthua(Double tienthua) {
        this.tienthua = tienthua;
    }

    public Double getTiengiamgia() {
        return tiengiamgia;
    }

    public void setTiengiamgia(Double tiengiamgia) {
        this.tiengiamgia = tiengiamgia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
