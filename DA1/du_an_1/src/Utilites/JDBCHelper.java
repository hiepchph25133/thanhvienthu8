package Utilites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

    public static ResultSet excuteQuery(String sql, Object... args) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        cn = DBConnection.getConnection();
        if (cn != null) {
            try {
                pstm = cn.prepareStatement(sql);
                for (int i = 0; i < args.length; i++) {
                    pstm.setObject(i + 1, args[i]);
                }
                rs = pstm.executeQuery();
            } catch (SQLException ex) {
                System.out.println("Lỗi Tại Câu Lệnh: " + sql);
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return rs;
    }

    public static Integer excuteUpdate(String sql, Object... args) {
        Connection cn = null;
        PreparedStatement pstm = null;
        Integer row = 0;
        cn = DBConnection.getConnection();
        if (cn != null) {
            try {
                pstm = cn.prepareStatement(sql);
                for (int i = 0; i < args.length; i++) {
                    pstm.setObject(i + 1, args[i]);
                }
                row = pstm.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Lỗi Tại Câu Lệnh: " + sql);
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return row;
    }
}
