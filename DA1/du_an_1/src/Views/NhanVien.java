/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Service.Services.chucvu_services;
import Service.Services.nhanvien_services;
import Viewmodel.chucvu_viewmodel;
import Viewmodel.nhanvien_viewmodel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class NhanVien extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    private final nhanvien_services inhanvien;
    private final chucvu_services ichucvu;
    private DefaultComboBoxModel combobox;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String SDT_REGEX = "(0)+([0-9]{9})";
    private ArrayList<nhanvien_viewmodel> listnv;

    public NhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        inhanvien = new nhanvien_services();
        ichucvu = new chucvu_services();
        themvaocb();
        loaddata(listnv);
    }

    private void themvaocb() {
        cb_chucvu.removeAllItems();
        for (chucvu_viewmodel cv : ichucvu.getAllcv()) {
            cb_chucvu.addItem(cv.getTenCV());
        }
    }

    private void loaddata(ArrayList<nhanvien_viewmodel> listnv) {
        tableModel = (DefaultTableModel) tbnhanvien.getModel();
        tableModel.setRowCount(0);
        for (nhanvien_viewmodel nv : inhanvien.getAllnv()) {
            tableModel.addRow(new Object[]{
                nv.getManv(), nv.getTennv(), nv.laygioitinhcuthe(),
                nv.getSdt(), nv.getNgaysinh(), nv.getDiachi(),
                nv.getMatkhau(), nv.getMacv(), nv.laytrangthaicuthe()
            });
        }
    }

    private void mouseClick() {
        int index = tbnhanvien.getSelectedRow();
        txt_manv1.setText(tbnhanvien.getValueAt(index, 0).toString());
        txt_tennv.setText(tbnhanvien.getValueAt(index, 1).toString());
        if (tbnhanvien.getValueAt(index, 2).equals("Nam")) {
            rd_nam.setSelected(true);
        } else {
            rd_nu.setSelected(true);
        }
        txt_sdt.setText(tbnhanvien.getValueAt(index, 3).toString());
        txt_ngSinh.setText(tbnhanvien.getValueAt(index, 4).toString());
        txt_diachi.setText(tbnhanvien.getValueAt(index, 5).toString());
        txt_matkhau.setText(tbnhanvien.getValueAt(index, 6).toString());
        cb_chucvu.setSelectedItem((String) tbnhanvien.getValueAt(index, 7).toString());
        if (tbnhanvien.getValueAt(index, 8).equals("Đang Đi Làm")) {
            rd_dangilam.setSelected(true);
        } else {
            rd_danghi.setSelected(true);
        }
    }

    public boolean validadatefrom(String check) {
        if (txt_manv1.getText().isEmpty() || txt_tennv.getText().isEmpty() || txt_sdt.getText().isEmpty() || txt_ngSinh.getText().isEmpty()
                || txt_diachi.getText().isEmpty() || txt_matkhau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thông Tin Không Đầy Đủ");
            return false;
        } else if (!txt_sdt.getText().trim().matches(SDT_REGEX)) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại phải gồm 10 số");
            return false;
        }
        try {
            Date date = sdf.parse(txt_ngSinh.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Sai Định Dạng Ngày Sinh");
            return false;
        }
        return true;
    }

    private boolean checktrung(String Ma) {
        for (nhanvien_viewmodel nvchecktrung : inhanvien.getAllnv()) {
            if (nvchecktrung.getManv().equals(Ma)) {
                return true;
            }
        }
        return false;
    }

    private nhanvien_viewmodel docfrom() {
        try {
            nhanvien_viewmodel nv = new nhanvien_viewmodel();
            nv.setManv(txt_manv1.getText().trim());
            nv.setTennv(txt_tennv.getText().trim());
            nv.setGioitinh(rd_nam.isSelected() ? 0 : 1);
            nv.setSdt(txt_sdt.getText().trim());
            Date ngaysinh = sdf.parse(txt_ngSinh.getText());
            nv.setNgaysinh(ngaysinh);
            nv.setDiachi(txt_diachi.getText().trim());
            nv.setMatkhau(txt_matkhau.getText().trim());
            nv.setMacv(ichucvu.getAllcv().get(cb_chucvu.getSelectedIndex()).getMaCV());
            nv.setTrangthai(rd_dangilam.isSelected() ? 0 : 1);
            return nv;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addnhanvien() {
        if (checktrung(txt_manv1.getText())) {
            JOptionPane.showMessageDialog(this, "Mã Nhân Viên Đã Tồn Tại!!!");
            return;
        } else {
            if (!validadatefrom("Add Nhân Viên")) {
                return;
            }
            String add = inhanvien.addnv(docfrom());
            listnv = inhanvien.getAllnv();
            loaddata(listnv);
            JOptionPane.showMessageDialog(this, add);
        }
    }

    private void updatenhanvien() {
        if (!validadatefrom("Update nhân viên")) {
            return;
        }
        String update = inhanvien.updatenv(docfrom());
        listnv = inhanvien.getAllnv();
        loaddata(listnv);
        JOptionPane.showMessageDialog(this, update);
    }

    private void deletenhanvien() {
        int index = tbnhanvien.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Chọn Dòng Để Xóa");
            return;
        } else {
            int luachon = JOptionPane.showConfirmDialog(this, "Bạn Có Chắc Chắn Muốn Xóa Không ?", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (luachon == JOptionPane.YES_OPTION) {
                String delete = inhanvien.deletenv(txt_manv1.getText());
                listnv = inhanvien.getAllnv();
                loaddata(listnv);
                JOptionPane.showMessageDialog(this, delete);
            } else {

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPopuMenu1 = new rojerusan.RSPopuMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnhanvien = new rojeru_san.complementos.RSTableMetro();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_matkhau = new app.bolivia.swing.JCTextField();
        btn_Xoa = new rojerusan.RSMaterialButtonCircle();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_them = new rojerusan.RSMaterialButtonCircle();
        btn_sua = new rojerusan.RSMaterialButtonCircle();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rd_nam = new javax.swing.JRadioButton();
        rd_nu = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cb_chucvu = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        rd_dangilam = new javax.swing.JRadioButton();
        rd_danghi = new javax.swing.JRadioButton();
        txt_manv1 = new app.bolivia.swing.JCTextField();
        txt_tennv = new app.bolivia.swing.JCTextField();
        txt_sdt = new app.bolivia.swing.JCTextField();
        txt_ngSinh = new app.bolivia.swing.JCTextField();
        txt_diachi = new app.bolivia.swing.JCTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng Nhập");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Gioi Tinh", "SÐT", "Ngay Sinh", "Ðia Chi", "Mât Khau", "Chuc Vu", "Trạng Thái"
            }
        ));
        tbnhanvien.setColorBackgoundHead(new java.awt.Color(102, 102, 255));
        tbnhanvien.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbnhanvien.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnhanvien);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 680, 130));

        jLabel15.setBackground(new java.awt.Color(255, 51, 51));
        jLabel15.setFont(new java.awt.Font("Sitka Display", 0, 25)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Quản Lý Nhân Viên");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 50, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 690, 640));

        jPanel2.setBackground(new java.awt.Color(102, 102, 250));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giới Tính");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Display", 0, 25)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mã Nhân Viên");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txt_matkhau.setBackground(new java.awt.Color(102, 102, 255));
        txt_matkhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_matkhau.setPlaceholder("Enter Mât Khâu ...");
        txt_matkhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matkhauActionPerformed(evt);
            }
        });
        jPanel2.add(txt_matkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        btn_Xoa.setBackground(new java.awt.Color(255, 51, 51));
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 590, 140, 30));

        jLabel11.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Trạng Thái");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        jLabel12.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tên Nhân Viên");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SÐT");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel14.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Địa Chỉ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        btn_them.setBackground(new java.awt.Color(255, 51, 51));
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel2.add(btn_them, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 140, 30));

        btn_sua.setBackground(new java.awt.Color(255, 51, 51));
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel2.add(btn_sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 590, 140, 30));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));

        jLabel3.setBackground(new java.awt.Color(255, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image da1/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3);

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        buttonGroup1.add(rd_nam);
        rd_nam.setText("Nam");
        jPanel2.add(rd_nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        buttonGroup1.add(rd_nu);
        rd_nu.setText("Nữ");
        jPanel2.add(rd_nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, -1));

        jLabel16.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ngày Sinh");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel17.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Mật Khẩu");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        cb_chucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(cb_chucvu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 210, -1));

        jLabel18.setFont(new java.awt.Font("Sitka Display", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Chức Vụ");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        buttonGroup2.add(rd_dangilam);
        rd_dangilam.setText("Đang đi làm");
        jPanel2.add(rd_dangilam, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, -1, -1));

        buttonGroup2.add(rd_danghi);
        rd_danghi.setText("Đã Nghỉ");
        jPanel2.add(rd_danghi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, -1, -1));

        txt_manv1.setBackground(new java.awt.Color(102, 102, 255));
        txt_manv1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_manv1.setPlaceholder("Enter Mã NV ...");
        txt_manv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_manv1ActionPerformed(evt);
            }
        });
        jPanel2.add(txt_manv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, -1, -1));

        txt_tennv.setBackground(new java.awt.Color(102, 102, 255));
        txt_tennv.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_tennv.setPlaceholder("Enter Tên NV ...");
        txt_tennv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tennvActionPerformed(evt);
            }
        });
        jPanel2.add(txt_tennv, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        txt_sdt.setBackground(new java.awt.Color(102, 102, 255));
        txt_sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_sdt.setPlaceholder("Enter SÐT ...");
        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });
        jPanel2.add(txt_sdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        txt_ngSinh.setBackground(new java.awt.Color(102, 102, 255));
        txt_ngSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_ngSinh.setPlaceholder("Enter NgSinh ...");
        txt_ngSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngSinhActionPerformed(evt);
            }
        });
        jPanel2.add(txt_ngSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        txt_diachi.setBackground(new java.awt.Color(102, 102, 255));
        txt_diachi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_diachi.setPlaceholder("Enter Ðia Chi ...");
        txt_diachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diachiActionPerformed(evt);
            }
        });
        jPanel2.add(txt_diachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 830));

        setSize(new java.awt.Dimension(1169, 642));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_matkhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matkhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_matkhauActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Trangchu trangchu = new Trangchu();
        trangchu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_manv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_manv1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_manv1ActionPerformed

    private void txt_tennvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tennvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tennvActionPerformed

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdtActionPerformed

    private void txt_ngSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngSinhActionPerformed

    private void txt_diachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diachiActionPerformed

    private void tbnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnhanvienMouseClicked
        // TODO add your handling code here:
        mouseClick();
    }//GEN-LAST:event_tbnhanvienMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        addnhanvien();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        updatenhanvien();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
        deletenhanvien();
    }//GEN-LAST:event_btn_XoaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonCircle btn_Xoa;
    private rojerusan.RSMaterialButtonCircle btn_sua;
    private rojerusan.RSMaterialButtonCircle btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_chucvu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    private javax.swing.JRadioButton rd_danghi;
    private javax.swing.JRadioButton rd_dangilam;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private rojeru_san.complementos.RSTableMetro tbnhanvien;
    private app.bolivia.swing.JCTextField txt_diachi;
    private app.bolivia.swing.JCTextField txt_manv1;
    private app.bolivia.swing.JCTextField txt_matkhau;
    private app.bolivia.swing.JCTextField txt_ngSinh;
    private app.bolivia.swing.JCTextField txt_sdt;
    private app.bolivia.swing.JCTextField txt_tennv;
    // End of variables declaration//GEN-END:variables
}
