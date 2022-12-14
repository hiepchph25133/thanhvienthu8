/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.HoaDon;
import Model.SanPham;
import Reponstory.BanHangReponstory;
import Viewmodel.SanPham_BanHangViewModel;
import java.util.List;
import Services.Services.IPL.BanHangServicesIPL;
import Viewmodel.TrangThaiHoaDonViewModel;

/**
 *
 * @author HP
 */
public class BanHangServices implements BanHangServicesIPL{

    BanHangReponstory banHangReponstory = new BanHangReponstory();
    @Override
     public List<SanPham> getall(){
         return banHangReponstory.getall();
     }
         @Override
        public List<SanPham_BanHangViewModel> getallDB(){
            return banHangReponstory.getallDB();
        }
        @Override
  public List<SanPham> timKiemSDT() {
      return banHangReponstory.timKiemSDT();
  }
  @Override
  public List<TrangThaiHoaDonViewModel> getTrangthaiHD() {
      return banHangReponstory.getTrangthaiHD();
  }
  @Override
    public boolean UpdateSLSP(String Masp, int SoLuong){
      return banHangReponstory.UpdateSLSP(Masp, SoLuong);
  }
}
