/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.HoaDon;
import Model.SanPham;
import Reponstory.BanHangReponstory;
import Reponstory.HoaDonReponstory;
import Services.Services.IPL.HoaDonServicesIPL;
import Viewmodel.HoaDonChiTietViewModel;
import Viewmodel.HoaDonViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.util.List;

/**
 *
 * @author HP
 */
public class hoaDonServices implements HoaDonServicesIPL {
    private List<HoaDonViewModel> getlisthd;
    BanHangReponstory banHangReponstory = new BanHangReponstory();
    HoaDonReponstory hoaDonReponstory = new HoaDonReponstory();

    @Override
    public Integer DeleteHoaDon(int HD) {
        return hoaDonReponstory.DeleteHoaDon(HD);
    }

    @Override
    public Integer CapNhapTTHD(TrangThaiHoaDonViewModel ma) {
        return hoaDonReponstory.CapNhapTTHD(ma);
    }
    
    @Override
    public Integer saveHD(HoaDonViewModel hoadon,String NV){
        HoaDon hd =new HoaDon();
        hd.setMaHD(hoadon.getMaHD());
        hd.setNgayTao(hoadon.getNgayTao());
        hd.setTinhtrang(0);
        Integer isInsert = hoaDonReponstory.InsertHD(hd, NV);
        return isInsert;
    }
    
    public List<HoaDonViewModel> getlistHD(int trangthai){
        List<HoaDon> list =banHangReponstory.getlistHD(trangthai);
        for (HoaDon x : list) {
            HoaDonViewModel hd = new HoaDonViewModel();
            hd.setMaHD(x.getMaHD());
            hd.setNgayTao(x.getNgayTao());
            hd.setTrangThai(trangthai);
            getlisthd.add(hd);
        }
       return getlisthd;
    }
    
    @Override
      public List<HoaDon> getallHoaDon(){
          return hoaDonReponstory.getallHoaDon();
      }
      
          @Override
      public int getMaHD(){
          return hoaDonReponstory.getMaHD();
      }
}
