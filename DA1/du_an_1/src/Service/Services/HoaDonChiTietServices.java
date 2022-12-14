/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.HoaDonChiTiet;
import Reponstory.HoaDonChiTietReponstory;
import Services.Services.IPL.HoaDonChiTietServicesIPL;
import java.util.List;

/**
 *
 * @author HP
 */
public class HoaDonChiTietServices implements HoaDonChiTietServicesIPL{
    HoaDonChiTietReponstory hoaDonChiTietReponstory = new HoaDonChiTietReponstory();
    
    @Override
    public List<HoaDonChiTiet> getallHoaDonReponstorys(){
        return hoaDonChiTietReponstory.getallHoaDonReponstorys();
    }
    @Override
     public boolean UpdateSLCTSP(String Masp, int SoLuong){
         return hoaDonChiTietReponstory.UpdateSLCTSP(Masp, SoLuong);
     }
     
     @Override
       public Integer addHoaDonChiTiet(HoaDonChiTiet hdct){
           return hoaDonChiTietReponstory.addHoaDonChiTiet(hdct);
       }
}
