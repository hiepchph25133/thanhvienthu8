/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.SanPham;
import Reponstory.QLSanPhamReponsitory;

import Services.Services.IPL.iSanPhamService;
import Viewmodel.SanPhamViewModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class QLSanPhamService implements iSanPhamService {

    private QLSanPhamReponsitory reponsitory = new QLSanPhamReponsitory();

    @Override
    public List<SanPhamViewModel> getallSanPhamViewModel() {
        return reponsitory.getallSanPhamViewModels();
    }

    @Override
    public Integer add(SanPhamViewModel sp) {
        return reponsitory.add(sp);
    }

    @Override
    public Integer Delete(String Ma) {
        return reponsitory.Delete(Ma);
    }

    @Override
    public Integer Update(SanPhamViewModel sp) {
        return reponsitory.Update(sp);
    }

}
