/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.Hang;
import Reponstory.HangReponsitory;
import Services.Services.IPL.iHangService;
import java.util.List;

/**
 *
 * @author asus
 */
public class HangService implements iHangService{
    private HangReponsitory reponstory = new HangReponsitory();
    
    @Override
    public List<Hang> getAllHangs() {
        return reponstory.getAllHangs();
        }

    @Override
    public Integer add(Hang h) {
        return reponstory.add(h);
    }
    
}
