/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.MauSac;
import java.util.List;

/**
 *
 * @author asus
 */
public interface iMauSacService1 {
    List<MauSac> getAlMauSacs();
    
    public Integer add(MauSac ms);
}
