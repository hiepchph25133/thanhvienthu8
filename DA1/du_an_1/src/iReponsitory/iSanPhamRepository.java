/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iReponsitory;

import Model.SanPham;
import Viewmodel.SanPhamViewModel;
import java.util.List;

/**
 *
 * @author asus
 */
public interface iSanPhamRepository {

    List<SanPhamViewModel> getallSanPhamViewModels();

    public Integer add(SanPhamViewModel sp);

    public Integer Delete(String Ma);

    public Integer Update(SanPhamViewModel  sp);
}
