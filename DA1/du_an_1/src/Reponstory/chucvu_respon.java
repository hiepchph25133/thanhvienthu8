/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Utilites.JDBCHelper;
import Viewmodel.chucvu_viewmodel;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class chucvu_respon {

    JDBCHelper helper;

    public ArrayList<chucvu_viewmodel> getAllcv() {
        ArrayList<chucvu_viewmodel> listcv = new ArrayList<>();
        helper = new JDBCHelper();
        String sql = "select * from ChucVu";
        ResultSet rs = helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                chucvu_viewmodel cv = new chucvu_viewmodel(rs.getString(1), rs.getString(2), rs.getString(3));
                listcv.add(cv);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi Tại Câu Lệnh Sql: " + sql);
            ex.printStackTrace();
        }
        return listcv;
    }
}
