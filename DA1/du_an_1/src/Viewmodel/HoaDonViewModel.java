/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

import java.util.Date;

/**
 *
 * @author HP
 */
public class HoaDonViewModel {

    private String maHD;
    private String ngayTao;
    private int TrangThai;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String maHD, String ngayTao, int TrangThai) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.TrangThai = TrangThai;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

 
    
}
