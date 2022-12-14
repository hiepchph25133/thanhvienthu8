/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.HoaDonChiTiet;
import Utilites.DBConnection;
import Utilites.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author HP
 */
public class HoaDonChiTietReponstory {

    public List<HoaDonChiTiet> getallHoaDonReponstorys() {
        List<HoaDonChiTiet> listHD = new ArrayList<>();
        try {
            String sql = "SELECT [MaHD]\n"
                    + "      ,[MaSP]\n"
                    + "      ,[SoLuong]\n"
                    + "      ,[dongia]\n"
                    + "      ,[tiethua]\n"
                    + "      ,[tiengiamgia]\n"
                    + "      ,[trangthai]\n"
                    + "      ,[tongtien]\n"
                    + "  FROM [dbo].[ChiTietHoaDon]";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                listHD.add(new HoaDonChiTiet(rs.getString("MaHD"), rs.getString("MaSP"), rs.getInt("SoLuong"),
                        rs.getDouble("dongia"), rs.getDouble("tiethua"), rs.getDouble("tiengiamgia"), rs.getString("trangthai"),
                        rs.getDouble("tongtien")));

            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("loi truy van");
        }
        return null;
    }

    public boolean UpdateSLCTSP(String Masp, int SoLuong) {
        try {
            String sql = "UPDATE [dbo].[ChiTietHoaDon]\n"
                    + "   SET \n"
                    + "      [SoLuong] = ?\n"
                    + " WHERE MaSP = ?";
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, SoLuong);
            ps.setString(2, Masp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
            return false;
        }
    }

    public Integer addHoaDonChiTiet(HoaDonChiTiet hdct) {
        try {
            String sql = "INSERT INTO [dbo].[ChiTietHoaDon]\n"
                    + "           ([MaHD]\n"
                    + "           ,[MaSP]\n"
                    + "           ,[SoLuong]\n"
                    + "           ,[dongia]\n"
                    + "           ,[tiethua]\n"
                    + "           ,[tiengiamgia]\n"
                    + "           ,[trangthai]\n"
                    + "           ,[tongtien])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            Integer row = JDBCHelper.excuteUpdate(sql, hdct.getMaHD(),hdct.getMaSP(),hdct.getSoLuong(),
                    hdct.getDongia(),hdct.getTienthua(),hdct.getTiengiamgia(),hdct.getTrangThai(),
                    hdct.getTongTien()  );
          return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van HDCT");
        }
        return null;
    }
}
