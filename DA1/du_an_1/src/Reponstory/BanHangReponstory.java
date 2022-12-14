/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.HoaDon;
import Model.SanPham;
import Model.nhanvien_domainmodel;
import Utilites.DBConnection;
import Utilites.JDBCHelper;
import Viewmodel.HoaDonChiTietViewModel;
import Viewmodel.SanPham_BanHangViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author HP
 */
public class BanHangReponstory {

    public List<SanPham> getall() {
        List<SanPham> listsp = new ArrayList<>();
        try {
            String sql = " Select * from SanPham";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                listsp.add(new SanPham(rs.getString("MaSP"), rs.getString("TenSP"),
                        rs.getString("MaMS"), rs.getString("MaDSP"), rs.getString("MaHang"),
                        rs.getString("MoTa"), rs.getString("loaihang"), rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"), rs.getString("HinhAnh"), rs.getString("QRCOde")
                ));
            }
            return listsp;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public List<TrangThaiHoaDonViewModel> getTrangthaiHD() {
        List<TrangThaiHoaDonViewModel> listHD = new ArrayList<>();
        try {
            String sql = "SELECT        dbo.HoaDon.MaHD, dbo.NhanVien.HoTenNV, dbo.HoaDon.TinhTrang, dbo.HoaDon.NgayTao\n"
                    + "FROM            dbo.HoaDon INNER JOIN\n"
                    + "                         dbo.NhanVien ON dbo.HoaDon.MaNV = dbo.NhanVien.MaNV";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                listHD.add(new TrangThaiHoaDonViewModel(rs.getString("MaHD"), rs.getString("HoTenNV"),
                        rs.getString("NgayTao"), rs.getInt("TinhTrang")));
            }
            return listHD;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public List<SanPham_BanHangViewModel> getallDB() {
        List<SanPham> listSPVM = getall();
        List<SanPham_BanHangViewModel> listsp = new ArrayList<>();
        for (SanPham x : listSPVM) {
            SanPham_BanHangViewModel sp = new SanPham_BanHangViewModel();
            sp.setLoaiHang(x.getLoaihang());
            sp.setMaSP(x.getMaSP());
            sp.setTenSP(x.getTensp());
            sp.setMoTa(x.getMota());
            sp.setSoLuong(x.getSoLuong());
            sp.setGia(x.getGiaBan());
            sp.setGiamgia(x.getGiamgia());
            listsp.add(sp);
        }
        return listsp;
    }

    public List<SanPham> timKiemSDT() {
        Connection cn = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        cn = DBConnection.getConnection();
        try {
            String sql
                    = "select * from KhachHang where Sdt = ?";
            p = cn.prepareStatement(sql);
            rs = p.executeQuery();
            if (rs.next()) {
                String name = rs.getString("HoTenKH");
                String sdt = rs.getString("Sdt");
                String DiaChi = rs.getString("DiaChi");
                System.out.println(name + "\t\t" + sdt + "\t\t" + DiaChi);
            }
        } catch (SQLException e) {

            // Print the exception
            System.out.println(e);
        }
        return null;
    }

    public boolean UpdateSLSP(String Masp, int SoLuong) {
        try {
            String sql = "UPDATE [dbo].[SanPham]\n"
                    + "   SET \n"
                    + "      [SoLuong] = ?\n"
                    + " WHERE MaSP = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, SoLuong);
            pr.setString(2, Masp);
            pr.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Integer DeleteSoLuongSP(String ma) {
        try {
            String sql = "DELETE FROM [dbo].[KhachHang]\n"
                    + "      WHERE MaKH = ?";
            Integer row = JDBCHelper.excuteUpdate(sql, ma);
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

    public List<HoaDon> getlistHD(int trangthai) {
        List<HoaDon> listGD = new ArrayList<>();
        try {
            String sql = "SELECT        dbo.HoaDon.MaHD, dbo.HoaDon.NgayTao, dbo.NhanVien.HoTenNV, dbo.HoaDon.TinhTrang\n"
                    + "FROM            dbo.HoaDon INNER JOIN\n"
                    + "                         dbo.NhanVien ON dbo.HoaDon.MaNV = dbo.NhanVien.MaNV\n"
                    + "						 where TinhTrang = ?";
            Connection cn = DBConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, trangthai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgayTao(rs.getString(3));
                hd.setTinhtrang(rs.getInt(4));
                nhanvien_domainmodel nv = new nhanvien_domainmodel();
                nv.setTennv(rs.getString(2));
                listGD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGD;
    }

    public Integer InsertHD(HoaDon hd) {
        try {
            String sql = "INSERT INTO [dbo].[HoaDon]\n"
                    + "           ([MaHD]\n"
                    + "           ,[MaNV]\n"
                    + "           ,[MaKH]\n"
                    + "           ,[NgayTao]\n"
                    + "           ,[TinhTrang]\n"
                    + "           ,[TenNguoiNhan]\n"
                    + "           ,[DiaChi]\n"
                    + "           ,[Sdt]\n"
                    + "           ,[tongtien]\n"
                    + "           ,[phantramgiamgia])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?,?)";
            Integer row = JDBCHelper.excuteUpdate(sql, hd.getMaHD(),hd.getMaNV(),hd.getMaKH(),hd.getNgayTao(),
                    hd.getTinhtrang(),hd.getTennguoiNhan(),hd.getDiachi(),hd.getSdt(),hd.getTongtien(),
                    hd.getGiamgia());
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }

}
