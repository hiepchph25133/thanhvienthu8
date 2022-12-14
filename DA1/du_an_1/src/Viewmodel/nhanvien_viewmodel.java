/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viewmodel;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class nhanvien_viewmodel {

    private String manv;
    private String tennv;
    private Date ngaysinh;
    private int gioitinh;
    private String sdt, macv, diachi;
    private int trangthai;
    private String matkhau;

    public nhanvien_viewmodel(String manv, String tennv, Date ngaysinh, int gioitinh, String sdt, String macv, String diachi, int trangthai, String matkhau) {
        this.manv = manv;
        this.tennv = tennv;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.macv = macv;
        this.diachi = diachi;
        this.trangthai = trangthai;
        this.matkhau = matkhau;
    }

    public nhanvien_viewmodel() {
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMacv() {
        return macv;
    }

    public void setMacv(String macv) {
        this.macv = macv;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String laygioitinhcuthe() {
        if (this.gioitinh == 0) {
            return "Nam";
        } else {
            return "Nữ";
        }
    }

    public String laytrangthaicuthe() {
        if (this.trangthai == 0) {
            return "Đang Đi Làm";
        } else {
            return "Đã Nghỉ";
        }
    }
}
