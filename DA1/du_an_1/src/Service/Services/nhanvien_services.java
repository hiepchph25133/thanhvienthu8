/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.nhanvien_domainmodel;
import Reponstory.nhanvien_repon;
import Services.Services.IPL.INhanVien;
import Viewmodel.nhanvien_viewmodel;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class nhanvien_services implements INhanVien{
        nhanvien_repon repon = new nhanvien_repon();

    @Override
    public ArrayList<nhanvien_viewmodel> getAllnv() {
        return repon.getAllnv();
    }

    @Override
    public String addnv(nhanvien_viewmodel nv) {
        boolean addnv = repon.addnv(nv);
        if(addnv){
            return "Add dữ liệu thành công";
        }else{
            return "add dữ liệu thất bại";
        }
    }

    @Override
    public String updatenv(nhanvien_viewmodel nv) {
        boolean update = repon.updatenv(nv);
        if(update){
            return "Update dữ liệu thành công";
        }else{
            return "Update dữ liệu thất bại";
        }
    }

    @Override
    public String deletenv(String manv) {
        boolean delete = repon.deleterow(manv);
        if(delete){
            return "Delete dữ liệu thành công";
        }else{
            return "Delete dữ liệu thất bại";
        }
    }
    
}
