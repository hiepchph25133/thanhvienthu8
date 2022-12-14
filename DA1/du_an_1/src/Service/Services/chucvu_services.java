/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Reponstory.chucvu_respon;
import Services.Services.IPL.ichucvu;
import Viewmodel.chucvu_viewmodel;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class chucvu_services implements ichucvu{
    chucvu_respon repon = new chucvu_respon();

    @Override
    public ArrayList<chucvu_viewmodel> getAllcv() {
        return repon.getAllcv();
    }
    
}
