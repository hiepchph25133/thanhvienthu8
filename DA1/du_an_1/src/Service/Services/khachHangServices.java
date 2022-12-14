/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.Khachhang;
import Reponstory.khachhangreponstory;
import Services.Services.IPL.khachhangIPL;
import Viewmodel.KhachHangviewmodel;
import Viewmodel.ThemNhanhKHViewModel;
import java.util.List;
import java.sql.*;

public class khachHangServices implements khachhangIPL {

    Connection con;
    PreparedStatement pst;
    khachhangreponstory khachhangrepo = new khachhangreponstory();

    @Override
    public List<Khachhang> getall() {
        return khachhangrepo.getall();
    }

    @Override
    public Integer add(Khachhang kh) {
        return khachhangrepo.add(kh);
    }

    @Override
    public Integer Delete(String id) {
        return khachhangrepo.Delete(id);
    }

    @Override
    public Integer Update(Khachhang kh) {
        return khachhangrepo.Update(kh);
    }

    @Override
    public List<KhachHangviewmodel> getallDB() {
        return khachhangrepo.getallDB();
    }

    @Override
    public List<ThemNhanhKHViewModel> themNhanhKH() {
        return khachhangrepo.themNhanhKH();
    }
       @Override
     public Integer themnhanhKH(ThemNhanhKHViewModel kh){
         return khachhangrepo.themnhanhKH(kh);
     }
      @Override
       public List<ThemNhanhKHViewModel> tauNhanh(){
           return khachhangrepo.tauNhanh();
       }
}
