/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirectoryManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Tung
 */
public class AdminForm extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public AdminForm() {
        initComponents();
        ImageIcon img = new ImageIcon("image//ADMIN-OUTSOURCING.jpg");
        this.setIconImage(img.getImage());
        btnProduct.setSize(190, 190);
        new SetImage().setImageButton(btnProduct, "image//coffee-beans-1.png");
        btnEmp.setSize(190, 190);
        new SetImage().setImageButton(btnEmp, "image//Employee.png");
        btnRevenue.setSize(190, 190);
        new SetImage().setImageButton(btnRevenue, "image//pricing.png");
        btnCustomer.setSize(190, 190);
        new SetImage().setImageButton(btnCustomer, "image//Customer.jpg");
        btnPromotions.setSize(190, 190);
        new SetImage().setImageButton(btnPromotions, "image//Promotions.jpg");
        btnOrder.setSize(190, 190);
        new SetImage().setImageButton(btnOrder, "image//Order.png");
        btnHistory.setSize(190, 190);
        new SetImage().setImageButton(btnHistory, "image//history-icon-68319.png");
        btnLogout.setSize(20, 20);
        new SetImage().setImageButton(btnLogout, "image//logout.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProduct = new javax.swing.JButton();
        btnEmp = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnPromotions = new javax.swing.JButton();
        btnRevenue = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("image//ca-phe-say-lanh.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        btnLogout = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Qu???n l??");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        btnProduct.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnProduct.setForeground(new java.awt.Color(204, 0, 0));
        btnProduct.setToolTipText("Qu???n l?? s???n ph???m");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        btnEmp.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEmp.setForeground(new java.awt.Color(204, 0, 0));
        btnEmp.setToolTipText("Qu???n l?? nh??n vi??n");
        btnEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpActionPerformed(evt);
            }
        });

        btnCustomer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(204, 0, 0));
        btnCustomer.setToolTipText("Qu???n l?? kh??ch h??ng VIP");
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnPromotions.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPromotions.setForeground(new java.awt.Color(204, 0, 0));
        btnPromotions.setToolTipText("Qu???n l?? ch????ng tr??nh khuy???n m??i");
        btnPromotions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromotionsActionPerformed(evt);
            }
        });

        btnRevenue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnRevenue.setForeground(new java.awt.Color(204, 0, 0));
        btnRevenue.setToolTipText("Qu???n l?? doanh thu");
        btnRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevenueActionPerformed(evt);
            }
        });

        btnOrder.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnOrder.setForeground(new java.awt.Color(204, 0, 0));
        btnOrder.setToolTipText("Qu???n l?? h??a ????n");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnLogout.setText("????ng xu???t");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnLogout)
                .addGap(0, 77, Short.MAX_VALUE))
        );

        btnHistory.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHistory.setForeground(new java.awt.Color(204, 0, 0));
        btnHistory.setToolTipText("L???ch s??? b??n h??ng");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnPromotions, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnRevenue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPromotions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int click = JOptionPane.showConfirmDialog(null, "????ng xu???t ngay b??y gi????");
        if (click == 0) {
            this.setVisible(false);
            new LoginForm().setVisible(true);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevenueActionPerformed
        new Revenue().setVisible(true);
    }//GEN-LAST:event_btnRevenueActionPerformed

    private void btnPromotionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromotionsActionPerformed
        new Promotions().setVisible(true);
    }//GEN-LAST:event_btnPromotionsActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        new Customer().setVisible(true);
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        new Order().setVisible(true);
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpActionPerformed
        new EmployeeManager().setVisible(true);
    }//GEN-LAST:event_btnEmpActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
       new History().setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        new Product().setVisible(true);
    }//GEN-LAST:event_btnProductActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnEmp;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnProduct;
    private javax.swing.JButton btnPromotions;
    private javax.swing.JButton btnRevenue;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
