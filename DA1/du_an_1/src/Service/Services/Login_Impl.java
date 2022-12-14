/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Services;

import Model.Login_domainmodel;
import Reponstory.Login_Reponsitory;
import ViewModel.Login_ViewModel;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Login_Impl implements Services.Services.IPL.Login_Impl {

    Login_Reponsitory reponsitory = new Login_Reponsitory();

    @Override
    public ArrayList<Login_ViewModel> getAlllogin() {
        return reponsitory.getAlllogin();
    }
    
    @Override
    public String checkLogin(String username, String passworld){
        for (Login_ViewModel loginmodel : getAlllogin()) {
            if(loginmodel.getUsername().equals(username) && loginmodel.getPassworld().equals(passworld)){
                return loginmodel.getRole();
            }
        }
        return null;
    }
}
