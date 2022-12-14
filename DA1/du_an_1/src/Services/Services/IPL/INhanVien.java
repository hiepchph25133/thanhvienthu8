/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.nhanvien_domainmodel;
import Viewmodel.nhanvien_viewmodel;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface INhanVien {

  public ArrayList<nhanvien_viewmodel> getAllnv();

 public String addnv(nhanvien_viewmodel nv);

   public String updatenv(nhanvien_viewmodel nv);

 public String deletenv(String manv) ;
}
