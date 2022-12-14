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
public class ThongKeDoanhThu {

    public String ngaytao;
    public Double tongdoanhThu;
    public Double loiNhuan;

    public ThongKeDoanhThu() {
    }

    public ThongKeDoanhThu(String ngaytao, Double tongdoanhThu, Double loiNhuan) {
        this.ngaytao = ngaytao;
        this.tongdoanhThu = tongdoanhThu;
        this.loiNhuan = loiNhuan;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Double getTongdoanhThu() {
        return tongdoanhThu;
    }

    public void setTongdoanhThu(Double tongdoanhThu) {
        this.tongdoanhThu = tongdoanhThu;
    }

    public Double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(Double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

   

   
}
