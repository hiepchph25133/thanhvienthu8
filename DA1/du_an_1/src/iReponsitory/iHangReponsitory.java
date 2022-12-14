/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iReponsitory;

import Model.Hang;
import java.util.List;

/**
 *
 * @author asus
 */
public interface iHangReponsitory {

    List<Hang> getAllHangs();

    public Integer add(Hang h);
}
