/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Viewmodel.ThongKeDoanhThu;
import Viewmodel.Top5ViewModel;
import Viewmodel.loinhuan;
import Viewmodel.tongdoanhthu;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ThongKeDoanhThuServicesIPL {
    
    List<Top5ViewModel> getTop5();
    
    List<ThongKeDoanhThu> getdoanhthu();
    
   List<tongdoanhthu> getTongDoanhThu();
   
//   List<ThongKeDoanhThu> gettongdoanhthu();
   
   List<loinhuan> getloinhuan();
}
