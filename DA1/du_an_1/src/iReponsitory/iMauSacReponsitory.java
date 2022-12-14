/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iReponsitory;

import Model.Hang;
import Model.MauSac;
import java.util.List;

/**
 *
 * @author asus
 */
public interface iMauSacReponsitory {

    List<MauSac> getAllMauSacs();

    public Integer add(MauSac ms);
}
