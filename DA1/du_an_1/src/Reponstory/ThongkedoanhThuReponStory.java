/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Utilites.DBConnection;
import Utilites.JDBCHelper;
import Viewmodel.ThongKeDoanhThu;
import Viewmodel.Top5ViewModel;
import Viewmodel.loinhuan;
import Viewmodel.tongdoanhthu;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author HP
 */
public class ThongkedoanhThuReponStory {
    public List<Top5ViewModel> getTop5(){
        List<Top5ViewModel> listTop5 = new ArrayList<>();
        try {
            String sql = "Select top 5 MaSP,TenSP,MoTa,SanPham.SoLuong From SanPham  ";
            ResultSet rs= JDBCHelper.excuteQuery(sql);
            while (rs.next()) {                
             listTop5.add(new Top5ViewModel(rs.getString("MaSP"),rs.getString("TenSP"),
             rs.getString("MoTa"),rs.getInt("SoLuong")));
            }
            return listTop5;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    } 
    
    public List<ThongKeDoanhThu> getdoanhthu(){
        List<ThongKeDoanhThu> listdoanhthu = new ArrayList<>();
        try {
            String sql = "Select Datename(Month,ngaytao) , tongdoanhthu,loinhuan  from doanhthu";
              Connection cn = DBConnection.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                listdoanhthu.add(new ThongKeDoanhThu(rs.getString(1),rs.getDouble(2),
                rs.getDouble(3)));
            }
            return listdoanhthu;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
        return null;
    }
    
//    public List<ThongKeDoanhThu> getTongDoanhThu(){
//        List<ThongKeDoanhThu> list = new ArrayList<>();
//        try {
//            String sql ="select Sum(tongdoanhthu )from doanhthu";
//            ResultSet rs = JDBCHelper.excuteQuery(sql);
//            while (rs.next()) {                
//                list.add(new ThongKeDoanhThu(rs.getString(""),rs.getDouble("tongdoanhthu"),rs.getDouble("")));
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Loi truy van");
//        }
//          return null;
//    }
    
    
     public List<tongdoanhthu> getTongDoanhThu(){
        List<tongdoanhthu> list = new ArrayList<>();
        try {
            String sql ="select Sum(tongdoanhthu )from doanhthu";
            ResultSet rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {                
                list.add(new tongdoanhthu(rs.getDouble("tongdoanhthu")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
          return null;
    }
     
     public List<loinhuan> getloinhuan(){
         List<loinhuan> list = new ArrayList<>();
         try {
             String sql = "select Sum(loinhuan) as N'Lợi Nhuận' from doanhthu ";
             ResultSet rs = JDBCHelper.excuteQuery(sql);
             while (rs.next()) {                 
                 list.add(new loinhuan(rs.getDouble(1)));
             }
             return list;
         } catch (Exception e) {
              e.printStackTrace();
            System.out.println("Loi truy van");
         }
                 return null;
     }
}
