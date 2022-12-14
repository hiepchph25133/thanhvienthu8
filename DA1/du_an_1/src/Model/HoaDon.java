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
public class HoaDon {

    private String maHD;
    private String maNV;
    private String maKH;
    private String NgayTao;
    private int tinhtrang;
    private double tongtien;
    private String tennguoiNhan;
    private String diachi;
    private String sdt;
    private Double giamgia;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maNV, String maKH, String NgayTao, int tinhtrang, double tongtien, String tennguoiNhan, String diachi, String sdt, Double giamgia) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.NgayTao = NgayTao;
        this.tinhtrang = tinhtrang;
        this.tongtien = tongtien;
        this.tennguoiNhan = tennguoiNhan;
        this.diachi = diachi;
        this.sdt = sdt;
        this.giamgia = giamgia;
    }

   

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getTennguoiNhan() {
        return tennguoiNhan;
    }

    public void setTennguoiNhan(String tennguoiNhan) {
        this.tennguoiNhan = tennguoiNhan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Double getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(Double giamgia) {
        this.giamgia = giamgia;
    }

    
    
    
}
