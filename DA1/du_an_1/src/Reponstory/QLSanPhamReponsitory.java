/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.SanPham;
import Utilites.JDBCHelper;
import Viewmodel.SanPhamViewModel;
import iReponsitory.iSanPhamRepository;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class QLSanPhamReponsitory implements iSanPhamRepository {

    @Override
    public List<SanPhamViewModel> getallSanPhamViewModels() {
        List<SanPhamViewModel> sp = new ArrayList<>();
        String sql = "SELECT    dbo.SanPham.MaSP, dbo.SanPham.TenSP, dbo.MauSac.TenMS, dbo.hang.tenhang, dbo.SanPham.SoLuong, dbo.SanPham.GiaNhap, dbo.SanPham.GiaBan\n"
                + "FROM         dbo.SanPham INNER JOIN\n"
                + "                      dbo.hang ON dbo.SanPham.MaHang = dbo.hang.MaHang INNER JOIN\n"
                + "                      dbo.MauSac ON dbo.SanPham.MaMS = dbo.MauSac.MaMS";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                sp.add(new SanPhamViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLSanPhamReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp;
    }

    @Override
    public Integer add(SanPhamViewModel sp) {
        String sql = "insert into SanPham(MaSP,TenSP,MaMS,MaHang,SoLuong,GiaNhap,GiaBan)\n"
                + "values (?,?,?,?,?,?,?)";
        int row = 0;
        row = JDBCHelper.excuteUpdate(sql, sp.getMaSP(), sp.getTensp(), sp.getTenMS(), sp.getTenHang(), sp.getSoLuong(), sp.getGianhap(), sp.getGiaBan());
        return row;
    }

    @Override
    public Integer Delete(String Ma) {
        String sql = "delete SanPham where MaSP = ?";
        int row = JDBCHelper.excuteUpdate(sql, Ma);
        return row;
    }

    @Override
    public Integer Update(SanPhamViewModel sp) {
        String sql = "update SanPham set TenSP=?,MaMS=?,MaHang=?,SoLuong=?,GiaNhap=?,GiaBan=? where MaSP=?";
        int row = 0;
        row = JDBCHelper.excuteUpdate(sql, sp.getTensp(), sp.getTenMS(), sp.getTenHang(), sp.getSoLuong(), sp.getGianhap(), sp.getGiaBan(), sp.getMaSP());
        return row;
    }

}
