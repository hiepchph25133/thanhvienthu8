/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.HoaDon;
import Viewmodel.HoaDonViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.util.List;

/**
 *
 * @author HP
 */
public interface HoaDonServicesIPL {

    Integer DeleteHoaDon(int HD);

    Integer CapNhapTTHD(TrangThaiHoaDonViewModel ma);

    Integer saveHD(HoaDonViewModel hoadon, String NV);
     List<HoaDonViewModel> getlistHD(int trangthai);
      List<HoaDon> getallHoaDon();
      
      int getMaHD();
}
