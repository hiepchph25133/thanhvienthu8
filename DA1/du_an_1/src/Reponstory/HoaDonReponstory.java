/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.HoaDon;
import Model.SanPham;
import Services.Services.IPL.SanPhamServicesIPL;
import Utilites.DBConnection;
import Utilites.JDBCHelper;
import Viewmodel.HoaDonChiTietViewModel;
import Viewmodel.HoaDonViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class HoaDonReponstory {

    private SanPhamServicesIPL sanPhamServicesIPL;

    public Integer DeleteHoaDon(int HD) {
        int rs = 0;
        try {
            String sql = "DELETE FROM [dbo].[HoaDon]\n"
                    + "      WHERE MaHD = ?";
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, HD);
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return rs;
    }

    public Integer InsertHD(HoaDon hd, String manv) {
        int result = 0;
        try {
            String sql = "INSERT INTO [dbo].[HoaDon]\n"
                    + "           ([MaHD]\n"
                    + "           ,[MaNV]\n"
                    + "           ,[NgayTao]\n"
                    + "           ,[TinhTrang])\n"
                    + "\n"
                    + "\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?,?))";
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, hd.getMaHD());
            ps.setString(2, manv);
            ps.setString(3, hd.getNgayTao());
            ps.setInt(4, hd.getTinhtrang());
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van insert 1");
        }
        return null;
    }

    public Integer CapNhapTTHD(TrangThaiHoaDonViewModel ma) {
        try {
            String sql = "UPDATE [dbo].[HoaDon]\n"
                    + "   SET  [TinhTrang] = ?\n"
                    + " WHERE [MaHD] =?";
            Integer row = JDBCHelper.excuteUpdate(sql, ma.getTrangthaihoadon(), ma.getMaHD());
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public List<HoaDon> getallHoaDon() {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            String sql = "Select * from hoadon";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                listHD.add(new HoaDon(rs.getString("MaNV"), rs.getString("MaKH"),
                        rs.getString("Mahinhthucthanhtoan"), rs.getString("NgayTao"),
                        rs.getInt("TinhTrang"), rs.getDouble("tongtien"), rs.getString("TenNguoiNhan"),
                        rs.getString("DiaChi"), rs.getString("Sdt"), rs.getDouble("phantramgiamgia")));
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("LOi truy van HD");
        }
        return null;
    }

//    public boolean CountsoHD(){
//        String sql = "Select count(*) from HoaDon where MaHD like ?";
//        try {
//            
//        } catch (Exception e) {
//        }
//    }
//    public static ResultSet CountsoHD(String SOhoadon){
//        String sql = "Select count(*) from HoaDon where MaHD like ?";
//        return JDBCHelper.excuteQuery(sql, "%" + SOhoadon + "%");
//    }
//    
//    
//       public static ResultSet getBysoHD(String SOhoadon){
//        String sql = "Select * from HoaDon where MaHD = ?";
//        return JDBCHelper.excuteQuery(sql, "%" + SOhoadon + "%");
//    }
    public int getMaHD() {
        ArrayList list = new ArrayList();
        int max = 0;
        try {
            String sql = "Select MaHD from HoaDon";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            if (rs.getString("MaHD").trim().substring(0, 0).endsWith("HĐ")) {
                list.add(rs.getString("MaHD").trim().substring(0, rs.getString("MaHD").trim().length()));
            }
            if (list.size() == 0) {
                return list.size();
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (max < Integer.parseInt(list.get(i).toString())) {
                        max = Integer.parseInt(list.get(i).toString());
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van ma HD");
        }
        return max;
    }
}
