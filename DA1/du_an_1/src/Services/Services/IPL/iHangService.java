/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import Model.Hang;
import java.util.List;

/**
 *
 * @author asus
 */
public interface iHangService {

    List<Hang> getAllHangs();

    public Integer add(Hang h);
}
