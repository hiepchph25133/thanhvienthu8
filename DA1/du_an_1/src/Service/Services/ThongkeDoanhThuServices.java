/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Reponstory.ThongkedoanhThuReponStory;
import Services.Services.IPL.ThongKeDoanhThuServicesIPL;
import Viewmodel.ThongKeDoanhThu;
import Viewmodel.Top5ViewModel;
import Viewmodel.loinhuan;
import Viewmodel.tongdoanhthu;
import java.util.List;

/**
 *
 * @author HP
 */
public class ThongkeDoanhThuServices implements ThongKeDoanhThuServicesIPL{
    
    ThongkedoanhThuReponStory doanhthu = new ThongkedoanhThuReponStory();
    
    @Override
     public List<Top5ViewModel> getTop5(){
         return doanhthu.getTop5();
     }
     
     @Override
     public List<ThongKeDoanhThu> getdoanhthu(){
         return doanhthu.getdoanhthu();
     }
   
     @Override
       public List<tongdoanhthu> getTongDoanhThu(){
            return doanhthu.getTongDoanhThu();
        }
//         
//       @Override
//          public List<ThongKeDoanhThu> gettongdoanhthu(){
//                  return doanhthu.getTongDoanhThu();
//          }
       
         @Override
         public List<loinhuan> getloinhuan(){
             return doanhthu.getloinhuan();
         }
}
