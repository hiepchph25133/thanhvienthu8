/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Reponstory.SanPhamReponStory;
import Services.Services.IPL.SanPhamServicesIPL;

/**
 *
 * @author HP
 */
public class SanphamServices implements SanPhamServicesIPL{
    SanPhamReponStory sanPhamReponStory = new SanPhamReponStory();
    @Override
     public boolean updateSoLuongSP(String Masp, int SoLuong){
        return sanPhamReponStory.updateSoLuongSP(Masp, SoLuong);
     }
      @Override
        public Integer DeleteSanPhamGioHang(String ma){
          return sanPhamReponStory.DeleteSanPhamGioHang(ma);
        }
        
        
    public Integer DeleteGH(String Ma){
        return sanPhamReponStory.DeleteGH(Ma);
    }
    
}
