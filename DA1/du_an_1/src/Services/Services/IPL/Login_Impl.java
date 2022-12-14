/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services.Services.IPL;

import ViewModel.Login_ViewModel;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public interface Login_Impl {

    public ArrayList<Login_ViewModel> getAlllogin();

    public String checkLogin(String username, String passworld);
}
