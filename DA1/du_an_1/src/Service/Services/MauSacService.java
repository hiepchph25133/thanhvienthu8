/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.MauSac;
import Reponstory.MauSacReponsitory;
import Services.Services.IPL.iMauSacService;
import java.util.List;

/**
 *
 * @author asus
 */
public class MauSacService implements iMauSacService{

    private MauSacReponsitory Reponstory = new MauSacReponsitory();
    @Override
    public List<MauSac> getAlMauSacs() {
        return Reponstory.getAllMauSacs();
    }

    @Override
    public Integer add(MauSac ms) {
        return Reponstory.add(ms);
    }
    
}
