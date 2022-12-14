/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reponstory;

import Model.Hang;
import Utilites.JDBCHelper;
import iReponsitory.iHangReponsitory;
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
public class HangReponsitory implements iHangReponsitory {

    @Override
    public List<Hang> getAllHangs() {
        List<Hang> h = new ArrayList<>();
        String sql = "select * from hang";
        ResultSet rs;
        rs = JDBCHelper.excuteQuery(sql);
        try {
            while (rs.next()) {
                h.add(new Hang(rs.getString(1), rs.getString(2)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(HangReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }

    @Override
    public Integer add(Hang h) {
        String sql = "insert into hang(MaHang,tenhang)\n"
                + "values (?,?)";
        int row = 0;
        row = JDBCHelper.excuteUpdate(sql, h.getMaHang(), h.getTenHang());
        return row;
    }

}
