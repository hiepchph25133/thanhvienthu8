/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Utilites.DBConnection;
import Utilites.JDBCHelper;
import java.sql.*;

/**
 *
 * @author HP
 */
public class SanPhamReponStory {

    public boolean updateSoLuongSP(String Masp, int SoLuong) {
        try {
            String sql = "UPDATE SanPham SET SoLuong = ? WHERE MaSP = ?";
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

    public Integer DeleteSanPhamGioHang(String ma) {
        try {
            String sql = "DELETE FROM [dbo].[SanPham]\n"
                    + "      WHERE [MaSP] = ?";
            Integer row = JDBCHelper.excuteUpdate(sql, ma);
            return row;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truyu van");
        }
        return null;
    }
    public Integer DeleteGH(String Ma){
         try {
             String sql = "DELETE FROM [dbo].[SanPham]\n"
                    + "      WHERE [MaSP] = ?";
            Integer row = JDBCHelper.excuteUpdate(sql, Ma);
                return row;
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("Lôi");
        }
         return null;
           
     
}
}
