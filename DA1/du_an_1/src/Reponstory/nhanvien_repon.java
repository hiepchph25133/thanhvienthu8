/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.nhanvien_domainmodel;
import Utilites.JDBCHelper;
import Viewmodel.chucvu_viewmodel;
import Viewmodel.nhanvien_viewmodel;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class nhanvien_repon {

    JDBCHelper helper;

    public ArrayList<nhanvien_viewmodel> getAllnv() {
        ArrayList<nhanvien_viewmodel> listnv = new ArrayList<>();
        helper = new JDBCHelper();
        String sql = "select MaNV, HoTenNV, NgaySinh, GioiTinh, Sdt, TenCV, diachi, TrangThai, MatKhau from NhanVien a join ChucVu b on b.MaCV = a.MaCV";
        ResultSet rs = helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                nhanvien_viewmodel nv = new nhanvien_viewmodel(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
                listnv.add(nv);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi Insert: " + sql);
            ex.printStackTrace();
        }
        return listnv;
    }
   
    public boolean addnv(nhanvien_viewmodel nvmodel) {
        String sql = "insert into NhanVien values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Integer row = JDBCHelper.excuteUpdate(sql, nvmodel.getManv(), nvmodel.getTennv(), nvmodel.getNgaysinh(), nvmodel.getGioitinh(), nvmodel.getSdt(), nvmodel.getMacv(), nvmodel.getDiachi(), nvmodel.getTrangthai(), nvmodel.getMatkhau());
        return row > 0;
    }

    public boolean updatenv(nhanvien_viewmodel nvmodel) {
        String sql = "update NhanVien set MaNV=?, HoTenNV=?, NgaySinh=?, GioiTinh=?, Sdt=?, MaCV=?, diachi=?, TrangThai=?, MatKhau=? where MaNV=?";
        Integer row = JDBCHelper.excuteUpdate(sql, nvmodel.getManv(), nvmodel.getTennv(), nvmodel.getNgaysinh(), nvmodel.getGioitinh(), nvmodel.getSdt(), nvmodel.getMacv(), nvmodel.getDiachi(), nvmodel.getTrangthai(), nvmodel.getMatkhau(), nvmodel.getManv());
        return row > 0;
    }

    public boolean deleterow(String manv) {
        String sql = "delete from NhanVien where MaNV=?";
        Integer row = JDBCHelper.excuteUpdate(sql, manv);
        return row > 0;
    }
}
