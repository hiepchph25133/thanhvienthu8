/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

import Model.HoaDon;
import Model.SanPham;

/**
 *
 * @author HP
 */
public class HoaDonChiTietViewModel {
    private HoaDon hoadon ;
    private SanPham sanpham ;
    private int  soLuong ;
    private Double dongia ;
    private Double dongiaKhiGiam ;
    private String TrangThai ;
     private Double tongTien ;

    public HoaDonChiTietViewModel() {
    }

    public HoaDonChiTietViewModel( int soLuong, Double dongia, Double dongiaKhiGiam, String TrangThai, Double tongTien) {
        this.hoadon = hoadon;
        this.sanpham = sanpham;
        this.soLuong = soLuong;
        this.dongia = dongia;
        this.dongiaKhiGiam = dongiaKhiGiam;
        this.TrangThai = TrangThai;
        this.tongTien = tongTien;
    }

    

    public HoaDon getHoadon() {
        return hoadon;
    }

    public void setHoadon(HoaDon hoadon) {
        this.hoadon = hoadon;
    }

    public SanPham getSanpham() {
        return sanpham;
    }

    public void setSanpham(SanPham sanpham) {
        this.sanpham = sanpham;
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

    public Double getDongiaKhiGiam() {
        return dongiaKhiGiam;
    }

    public void setDongiaKhiGiam(Double dongiaKhiGiam) {
        this.dongiaKhiGiam = dongiaKhiGiam;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Double getTongTien() {
        return  dongia * soLuong;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
    
    
    
}
