/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author HP
 */
public interface HoaDonChiTietServicesIPL {

    List<HoaDonChiTiet> getallHoaDonReponstorys();

    boolean UpdateSLCTSP(String Masp, int SoLuong);

    Integer addHoaDonChiTiet(HoaDonChiTiet hdct);
}
