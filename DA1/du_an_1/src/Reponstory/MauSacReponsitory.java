/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.MauSac;
import Utilites.JDBCHelper;
import iReponsitory.iMauSacReponsitory;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class MauSacReponsitory implements iMauSacReponsitory {

    @Override
    public List<MauSac> getAllMauSacs() {
        List<MauSac> ms = new ArrayList<>();
        String sql = "select * from MauSac";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                ms.add(new MauSac(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ms;
    }

    @Override
    public Integer add(MauSac ms) {
        String sql = "insert into MauSac(MaMS,TenMS)\n"
                + "values (?,?)";
        int row = 0;
        row = JDBCHelper.excuteUpdate(sql, ms.getMaMS(), ms.getTenMS());
        return row;
    }

}
