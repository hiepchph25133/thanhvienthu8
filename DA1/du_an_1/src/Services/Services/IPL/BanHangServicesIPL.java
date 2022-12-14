/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.SanPham;
import Viewmodel.SanPham_BanHangViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.util.List;

/**
 *
 * @author HP
 */
public interface BanHangServicesIPL {
     List<SanPham> getall();
     List<SanPham_BanHangViewModel> getallDB();
   List<SanPham> timKiemSDT();
   List<TrangThaiHoaDonViewModel> getTrangthaiHD();
   boolean  UpdateSLSP(String Masp, int SoLuong);
}
