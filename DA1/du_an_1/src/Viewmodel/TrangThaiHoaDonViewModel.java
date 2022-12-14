/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;



/**
 *
 * @author HP
 */
public class TrangThaiHoaDonViewModel {
    private String maHD ;
    private String nhanVien ;
     private String NgayTao ;
    private int trangthaihoadon ;

    public TrangThaiHoaDonViewModel() {
    }

    public TrangThaiHoaDonViewModel(String maHD, String nhanVien, String NgayTao, int trangthaihoadon) {
        this.maHD = maHD;
        this.nhanVien = nhanVien;
        this.NgayTao = NgayTao;
        this.trangthaihoadon = trangthaihoadon;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getTrangthaihoadon() {
        return trangthaihoadon;
    }

    public void setTrangthaihoadon(int trangthaihoadon) {
        this.trangthaihoadon = trangthaihoadon;
    }
   

    

   
    
}
