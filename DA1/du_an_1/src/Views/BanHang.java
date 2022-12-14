/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.sql.*;
import Service.Services.BanHangServices;
import Services.Services.IPL.INhanVien;
import Service.Services.nhanvien_services;
import Utilites.DBConnection;
import Viewmodel.SanPham_BanHangViewModel;
import Viewmodel.nhanvien_viewmodel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Services.Services.IPL.BanHangServicesIPL;
import Services.Services.IPL.HoaDonServicesIPL;
import Services.Services.IPL.SanPhamServicesIPL;
import Service.Services.SanphamServices;
import Service.Services.hoaDonServices;
import static Utilites.DBConnection.DBNAME;
import static Utilites.DBConnection.HOSTNAME;
import static Utilites.DBConnection.PORT;
import Utilites.JDBCHelper;
import Viewmodel.GioHangViewModel;
import Viewmodel.HoaDonChiTietViewModel;
import Viewmodel.HoaDonViewModel;
import Viewmodel.TrangThaiHoaDonViewModel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class BanHang extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    Result result = null;
    BufferedImage image = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    BanHangServicesIPL banHang_SanPhamServicesIPL = new BanHangServices();
    INhanVien nhanVienServicesIPL = new nhanvien_services();
    SanPhamServicesIPL sanPhamServicesIPL = new SanphamServices();
    HoaDonServicesIPL hoaDonServicesIPL = new hoaDonServices();
    DefaultTableModel dtm;
    private DecimalFormat format = new DecimalFormat("#.###");
    private List<GioHangViewModel> listGHVM = new ArrayList<>();
    private List<HoaDonChiTietViewModel> listHoaDonCTVM = new ArrayList<>();
    private String id;
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private List<Model.HoaDon> listHD = new ArrayList<>();

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public BanHang() {
        initComponents();
        setLocationRelativeTo(null);
        loaddataSanPham();
        themvaocb();
        loadHoaDon();
        txt_ngaylap.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())));
//        loadataGioHang();
        showWebcam();
//        checkBill();
//loadgiamGia();
//txt_maHD.setText("HÐ" + String.valueOf(hoaDonServicesIPL.getMaHD() + 1));
        inputHD();

    }

    private void showWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        lb_wrbcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 150));

        executor.execute(this);
    }

    private void themvaocb() {
        cb_nhanvien.removeAllItems();
        for (nhanvien_viewmodel cv : nhanVienServicesIPL.getAllnv()) {
            cb_nhanvien.addItem(cv.getTennv());
        }
    }

    private void loadHoaDon() {
        dtm = (DefaultTableModel) tb_hoaDon.getModel();
        dtm.setRowCount(0);
        List<TrangThaiHoaDonViewModel> listHD = banHang_SanPhamServicesIPL.getTrangthaiHD();
        for (TrangThaiHoaDonViewModel x : listHD) {
            dtm.addRow(new Object[]{
                x.getMaHD(),
                x.getNhanVien(),
                x.getNgayTao(),
                x.getTrangthaihoadon() == 0 ? "Đã Thanh Toán" : "Chưa Thanh Toán",});

        }
    }

//    private void loadTrangThaiHoaDonDa() {
//        dtm = (DefaultTableModel) tb_hoaDon.getModel();
//        dtm.setRowCount(0);
//        List<TrangThaiHoaDonViewModel> listHD = banHang_SanPhamServicesIPL.getTrangthaiHD();
//        for (TrangThaiHoaDonViewModel x : listHD) {
//            dtm.addRow(new Object[]{
//                x.getMaHD(),
//                x.getNhanVien(),
//                x.getTrangthaihoadon() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán"
//            });
//
//        }
//    }
    private void loaddataSanPham() {
        dtm = (DefaultTableModel) tb_sanpham.getModel();
        dtm.setRowCount(0);
        List<SanPham_BanHangViewModel> listSPVM = banHang_SanPhamServicesIPL.getallDB();
        for (SanPham_BanHangViewModel x : listSPVM) {
            dtm.addRow(new Object[]{
                dtm.getRowCount() + 1,
                x.getLoaiHang(),
                x.getMaSP(),
                x.getTenSP(),
                x.getMoTa(),
                x.getSoLuong(),
                x.getGia(), //                x.getGiamgia()
            });
        }
    }

    private void loadataGioHang() {
        dtm = (DefaultTableModel) tb_giohang.getModel();
        dtm.setRowCount(0);
        for (GioHangViewModel x : listGHVM) {
            dtm.addRow(new Object[]{
                x.getMaSP(),
                x.getTenSp(),
                x.getSoLuong(),
                format.format(x.getDonGia()),
                format.format(x.getThanhtien())
            });
        }
    }

    private void search(String str) {
        dtm = (DefaultTableModel) tb_sanpham.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
        tb_sanpham.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(str));
    }

    private void searchHD(String str) {
        dtm = (DefaultTableModel) tb_hoaDon.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
        tb_hoaDon.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(str));
    }

    private HoaDonViewModel inputHD() {
        HoaDonViewModel hdvm = new HoaDonViewModel();
        String ma = "HÐ";
        Random random = new Random();
        hdvm.setMaHD(ma + random.nextInt());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
//        hdvm.setNgayTao(date);
//    txt_maHD.setText(String.valueOf(ma));
        return hdvm;
    }

    private HoaDonChiTietViewModel inputHDCT(Double gia, int SoLuong) {
        HoaDonChiTietViewModel hdctvm = new HoaDonChiTietViewModel();
        hdctvm.setDongia(gia);
        hdctvm.setSoLuong(SoLuong);
        return hdctvm;
    }

    private void checkBill() {
        if (tb_giohang.getRowCount() == 0) {
            txt_tongtien.setText("0");
            txt_khachcantra.setText("0");
            btn_thanhtoan.setEnabled(false);
            btn_inHoaDon.setEnabled(false);
            btn_capnhapHD.setEnabled(false);

        } else {
            btn_taohoaDon.setEnabled(true);
            btn_thanhtoan.setEnabled(true);
            btn_clear.setEnabled(true);
            btn_capnhapHD.setEnabled(true);
        }
    }

    public void qr() {
        int sl = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập Số Lượng"));
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_xoa = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_giohang = new javax.swing.JTable();
        txt_maHD = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_sanpham = new javax.swing.JTable();
        txt_timkiemSP = new app.bolivia.swing.JCTextField();
        btn_capnhapHD = new javax.swing.JButton();
        btn_taohoaDon = new javax.swing.JButton();
        btn_inHoaDon = new javax.swing.JButton();
        lb_wrbcam = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cb_nhanvien = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_ngaylap = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_timkiemKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_khachhang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_sdtkh = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_diachiKH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_khachthanhtoan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rd_tienmat = new javax.swing.JRadioButton();
        rd_chuyenkhoan = new javax.swing.JRadioButton();
        txt_giamgia = new javax.swing.JTextField();
        txt_tongtien = new javax.swing.JLabel();
        txt_khachcantra = new javax.swing.JLabel();
        txt_tienthua = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_hoaDon = new javax.swing.JTable();
        btn_thanhtoan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        txt_qr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Quản lý bán hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_clear.setText("Làm mới giỏ hàng");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        tb_giohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá"
            }
        ));
        jScrollPane1.setViewportView(tb_giohang);

        txt_maHD.setPlaceholder("Mã Hóa Đơn");
        txt_maHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maHDActionPerformed(evt);
            }
        });
        txt_maHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_maHDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btn_xoa)
                .addGap(47, 47, 47)
                .addComponent(btn_clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maHD, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txt_maHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoa)
                    .addComponent(btn_clear))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image da1/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel2.setText("Giỏ hàng");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tb_sanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Loai hang", "Mã SP", "Tên SP", "Thông tin SP", "Số Lượng", "Giá"
            }
        ));
        tb_sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_sanphamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_sanpham);

        txt_timkiemSP.setPlaceholder("Nhập sản phẩm cần tìm kiếm");
        txt_timkiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemSPActionPerformed(evt);
            }
        });
        txt_timkiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_timkiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_timkiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        btn_capnhapHD.setText("Cập nhập hóa đơn");
        btn_capnhapHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhapHDActionPerformed(evt);
            }
        });

        btn_taohoaDon.setText("Tạo hóa đơn");
        btn_taohoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taohoaDonActionPerformed(evt);
            }
        });

        btn_inHoaDon.setText("In hóa đơn");
        btn_inHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inHoaDonActionPerformed(evt);
            }
        });

        lb_wrbcam.setBackground(new java.awt.Color(250, 250, 250));
        lb_wrbcam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        lb_wrbcam.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lb_wrbcamInputMethodTextChanged(evt);
            }
        });
        lb_wrbcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Nhân Viên");

        cb_nhanvien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Ngày Lập");

        jLabel12.setText("Tìm Kiếm KH");

        txt_timkiemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemKHActionPerformed(evt);
            }
        });
        txt_timkiemKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timkiemKHKeyPressed(evt);
            }
        });

        jLabel13.setText("Khách Hàng");

        jLabel14.setText("SĐT KH");

        jLabel15.setText("Địa Chỉ KH");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_nhanvien, 0, 128, Short.MAX_VALUE)
                    .addComponent(txt_ngaylap)
                    .addComponent(txt_timkiemKH)
                    .addComponent(txt_khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txt_sdtkh)
                    .addComponent(txt_diachiKH))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cb_nhanvien, txt_khachhang, txt_ngaylap, txt_timkiemKH});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_ngaylap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_timkiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txt_sdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_diachiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cb_nhanvien, txt_khachhang, txt_ngaylap, txt_timkiemKH});

        jTabbedPane1.addTab("Mua hàng", jPanel6);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Tổng tiền ");

        jLabel5.setText("Giảm giá");

        jLabel6.setText("Khách cần trả");

        jLabel7.setText("Khách thanh toán");

        jLabel8.setText("Tiền thừa");

        jLabel9.setText("Phương thức thanh toán");

        buttonGroup2.add(rd_tienmat);
        rd_tienmat.setText("Tiền mặt");

        buttonGroup2.add(rd_chuyenkhoan);
        rd_chuyenkhoan.setText("Chuyển khoản");

        txt_giamgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giamgiaActionPerformed(evt);
            }
        });
        txt_giamgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_giamgiaKeyReleased(evt);
            }
        });

        txt_tongtien.setText("jLabel3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_khachthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(txt_giamgia)
                            .addComponent(txt_tongtien)
                            .addComponent(txt_khachcantra)
                            .addComponent(txt_tienthua)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(rd_tienmat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_chuyenkhoan)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tongtien))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_giamgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_khachcantra))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_khachthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_tienthua))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rd_tienmat)
                    .addComponent(rd_chuyenkhoan))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel5);

        tb_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HÐ", "Nhân Viên", "Ngày tạo", "Trạng Thái HĐ"
            }
        ));
        tb_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tb_hoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        btn_thanhtoan.setText("Thanh Toán");
        btn_thanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhtoanActionPerformed(evt);
            }
        });

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane3.setViewportView(bill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(208, 208, 208)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lb_wrbcam, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(157, 157, 157)
                                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(btn_capnhapHD)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_taohoaDon)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_inHoaDon)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_thanhtoan))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txt_qr)
                        .addGap(619, 619, 619)
                        .addComponent(jLabel1)))
                .addContainerGap(728, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_qr, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_wrbcam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_capnhapHD)
                            .addComponent(btn_taohoaDon)
                            .addComponent(btn_inHoaDon)
                            .addComponent(btn_thanhtoan))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(375, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_timkiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemSPActionPerformed

    }//GEN-LAST:event_txt_timkiemSPActionPerformed

    private void tb_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_sanphamMouseClicked

        int row = tb_sanpham.getSelectedRow();
        int rowGH = tb_giohang.getSelectedRow();
        int hoaDonCho = tb_hoaDon.getSelectedRow();

        Double tongPT = 0.0;
        Double tongVN = 0.0;
        Double tongTien = 0.0;
        int count = 0;

        if (row < 0) {
            return;
        }
        try {

            int sl = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập Số Lượng"));
            String maSp = tb_sanpham.getValueAt(row, 2).toString();
            String tenSP = tb_sanpham.getValueAt(row, 3).toString();
            int SoLuong = Integer.parseInt(tb_sanpham.getValueAt(row, 5).toString());
            Double donGia = Double.parseDouble(tb_sanpham.getValueAt(row, 6).toString());

            if (SoLuong >= sl) {
                int themsoLuong = SoLuong + sl;
                for (GioHangViewModel x : listGHVM) {
                    if (maSp.equals(x.getMaSP())) {
                        banHang_SanPhamServicesIPL.UpdateSLSP(maSp, themsoLuong);
                        JOptionPane.showMessageDialog(this, "Sản Phẩm đã tồn tại");
                        return;
                    } else if (maSp.equals(x.getMaSP())) {

                    }
                }
                listGHVM.add(new GioHangViewModel(maSp, tenSP, sl, donGia));
//                listHoaDonCTVM.add(new HoaDonChiTietViewModel( maSp, 0, donGia, donGia, tenSP, tongTien);
                loadataGioHang();
                int kq = SoLuong - sl;
                banHang_SanPhamServicesIPL.UpdateSLSP(maSp, kq);

                List<SanPham_BanHangViewModel> list = banHang_SanPhamServicesIPL.getallDB();
                list.clear();
                loaddataSanPham();
                int index = tb_giohang.getRowCount();
                double gia = 0;

//                for (int i = 0; i < index; i++) {
//                    double tien = Double.valueOf(tb_giohang.getValueAt(i, 4).toString());
//                    gia += tien;
//                }
//                txt_tontien.setText(String.valueOf(format.format(gia)));
//                txt_khachcantra.setText(String.valueOf(format.format(gia)));
//                if (!txt_giamgia.getText().trim().equals("")) {    
//                    Double giam = Double.valueOf(txt_giamgia.getText());
                for (GioHangViewModel x : listGHVM) {
                    tongTien = tongTien + x.getThanhtien();
                    txt_tongtien.setText(String.valueOf(format.format(tongTien)));
//                    txt_khachcantra.setText(String.valueOf(format.format(tongTien)));
                    if (tb_hoaDon.getValueAt(count, 0).equals(maSp) && x.getGiamGia().equals("%")) {
                        if (tb_hoaDon.getValueAt(count, 0).equals(maSp)) {
                            tongPT = x.getThanhtien() * x.getGiamGia() / 100;
//                            txt_giamgia.setText(String.valueOf(giam += tongPT));
//                            txt_giamgia.setText(String.valueOf(giam));
                        } else {
                            tongVN = x.getGiamGia();
//                            txt_giamgia.setText(String.valueOf(giam + tongVN));
                        }
                        count++;
                    }
//                    Double ThanhTien = Double.parseDouble(txt_tontien.getText()) - Double.parseDouble(txt_giamgia.getText());
//                    txt_khachcantra.setText(String.valueOf(format.format(ThanhTien)));
                }

            } else if (SoLuong < sl) {
                JOptionPane.showMessageDialog(this, "san pham không đủ ");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro");
        }

//
//        List<TrangThaiHoaDonViewModel> listHD = banHang_SanPhamServicesIPL.getTrangthaiHD();
//        dtm = (DefaultTableModel) tb_chuaThanhToan.getModel();
//        for (TrangThaiHoaDonViewModel x : listHD) {
//            if (tb_chuaThanhToan.getValueAt(hoaDonCho, 0).toString().equals(x.getMaHD())) {
//                HoaDonChiTietViewModel hdct = inputHDCT(donGia, sl);
//
//            }
//
//        }

    }//GEN-LAST:event_tb_sanphamMouseClicked

    private void txt_maHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maHDActionPerformed

    private void txt_timkiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemSPKeyReleased

        String searchString = txt_timkiemSP.getText();
        search(searchString);
    }//GEN-LAST:event_txt_timkiemSPKeyReleased

    private void btn_taohoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taohoaDonActionPerformed
        HoaDonViewModel hd = inputHD();
        Integer add = hoaDonServicesIPL.saveHD(hd, id);
        if (add > 0) {
            System.out.println("thêm thành công");
            List<HoaDonViewModel> list = hoaDonServicesIPL.getlistHD(1);
            list.clear();
            loadHoaDon();
        } else {
            System.out.println("that bai");
        }

//        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo 1 hóa đơn bán hàng mới hay không?", "Thông Báo", 2);
//        if (Click == JOptionPane.YES_OPTION) {
//            List<HoaDonViewModel> list = hoaDonServicesIPL.getlistHD(1);
//            list.clear();
//            btn_thanhtoan.setEnabled(true);
//            btn_inHoaDon.setEnabled(true);
//            btn_capnhapHD.setEnabled(true);
//
//        }
    }//GEN-LAST:event_btn_taohoaDonActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed

        int row = tb_giohang.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm khỏi giỏ hàng không");
            if (confirm == 0) {
                listGHVM.remove(row);
                loadataGioHang();
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
//        int rowHD = tb_hoaDon.getSelectedRow();
//        int rowGH = tb_giohang.getSelectedRow();
//        if (rowHD < 0) {
//            return;
//        }
//        if (rowGH < 0) {
//            return;
//        }
////        String MaHD = tb_hoaDon.getValueAt(rowHD, 0).toString();
//        String id = tb_giohang.getValueAt(rowGH, 0).toString();
//        sanPhamServicesIPL.DeleteSanPhamGioHang(id);
//        List<SanPham_BanHangViewModel> list = banHang_SanPhamServicesIPL.getallDB();
//        for (SanPham_BanHangViewModel x : list) {
//            for (GioHangViewModel giohang : listGHVM) {
//                if (giohang.getMaSP().equals(x.getMaSP())) {
//                    banHang_SanPhamServicesIPL.UpdateSLSP(giohang.getMaSP(), x.getSoLuong() + giohang.getSoLuong());
//                    list.clear();
//                    loaddataSanPham();
//                    return;
//                }
//                if (tb_giohang.getValueAt(rowGH, 0).equals(giohang.getMaSP())) {
//                    listGHVM.remove(x);
//                    loadataGioHang();
//                    return;
//                }
//            }
//        }
        int row = tb_giohang.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn làm mới giỏ hàng ?");
            if (confirm == 0) {
                listGHVM.clear();
                loadataGioHang();
            }
        }
    }//GEN-LAST:event_btn_clearActionPerformed

    private void tb_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hoaDonMouseClicked
//        int row = tb_hoaDon.getSelectedRow();
//        int rowGH = tb_giohang.getSelectedRow();
//        if (row < 0) {
//            return;
//        }
//        String id = tb_hoaDon.getValueAt(row, 0).toString();
//        sanPhamServicesIPL.DeleteSanPhamGioHang(id);
//        List<SanPham_BanHangViewModel> list = banHang_SanPhamServicesIPL.getallDB();
//        for (SanPham_BanHangViewModel x : list) {
//            for (GioHangViewModel giohang : listGHVM) {
//                if (giohang.getMaSP().equals(x.getMaSP())) {
//                    banHang_SanPhamServicesIPL.UpdateSLSP(giohang.getMaSP(), x.getSoLuong() + giohang.getSoLuong());
//                    list.clear();
//                    loaddataSanPham();
//                    return;
//                }
//                if (tb_giohang.getValueAt(row, 0).equals(giohang.getMaSP())) {
//                    listGHVM.remove(x);
//                    loadataGioHang();
//                    return;
//                }
//            }
//        }

    }//GEN-LAST:event_tb_hoaDonMouseClicked

    private void txt_timkiemKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemKHKeyPressed

    }//GEN-LAST:event_txt_timkiemKHKeyPressed

    private void txt_timkiemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemKHActionPerformed
        try {
            Connection cn = DBConnection.getConnection();
            String sql = "select * from KhachHang where Sdt = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, txt_timkiemKH.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("HoTenKH");
                String sdt = rs.getString("Sdt");
                String diachi = rs.getString("DiaChi");

                txt_khachhang.setText(ten);
                txt_sdtkh.setText(sdt);
                txt_diachiKH.setText(diachi);
            } else {
                JOptionPane.showMessageDialog(this, "SÐT khách hàng không tồn tại");
                int ok = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm nhanh khách hàng", "thêm", JOptionPane.YES_NO_CANCEL_OPTION);
                if (ok == JOptionPane.YES_OPTION) {
                    themNhanhKH_BanHang theBanHang = new themNhanhKH_BanHang();
                    theBanHang.setVisible(true);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_timkiemKHActionPerformed

    private void btn_thanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhtoanActionPerformed

        Double a = Double.parseDouble(txt_tongtien.getText());
        Double b = Double.parseDouble(txt_giamgia.getText());
        Double c = Double.parseDouble(txt_khachthanhtoan.getText());
        Double d = Double.parseDouble(txt_khachcantra.getText());
        Double khachTT = Double.parseDouble(txt_khachthanhtoan.getText());
        if (d > khachTT) {
            JOptionPane.showMessageDialog(this, "Số tiền khách cần trả > tiền thanh toán");

        } else {

            Double tienthua = Double.parseDouble(txt_khachthanhtoan.getText()) - Double.parseDouble(txt_khachcantra.getText());

            txt_tienthua.setText((format.format(tienthua)));

        }


    }//GEN-LAST:event_btn_thanhtoanActionPerformed

    private void txt_maHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maHDKeyReleased
        String searchString = txt_maHD.getText();
        searchHD(searchString);
    }//GEN-LAST:event_txt_maHDKeyReleased

    private void btn_inHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inHoaDonActionPerformed
//        int row = tb_giohang.getSelectedRow();
//        InHoaDon in = new InHoaDon();
//        in.setVisible(true);
//        if (row == -1) {
//            JOptionPane.showMessageDialog(this, "hãy chọn 1 dòng rồi ấn In");
//        } else {
//
//            PrinterJob job = PrinterJob.getPrinterJob();
//            job.setJobName("Print Data");
//
//            job.setPrintable(new Printable() {
//                public int print(Graphics pg, PageFormat pf, int pageNum) {
//                    pf.setOrientation(PageFormat.LANDSCAPE);
//                    if (pageNum > 0) {
//                        return Printable.NO_SUCH_PAGE;
//                    }
//
//                    Graphics2D g2 = (Graphics2D) pg;
//                    g2.translate(pf.getImageableX(), pf.getImageableY());
//                    g2.scale(0.47, 0.47);
//
//                    tb_giohang.print(g2);
//
//                    return Printable.PAGE_EXISTS;
//
//                }
//            });
//            boolean ok = job.printDialog();
//            if (ok) {
//                try {
//
//                    job.print();
//                } catch (PrinterException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }

        try {
            bill.setText("                         FourGlasses Team   \n");
            bill.setText(bill.getText() + "\tĐịa Chỉ:Cao Đẳng FPT Hà Nội \n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + " Sản Phẩm             \tSố Lượng         \tGiá \n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");

            DefaultTableModel df = (DefaultTableModel) tb_giohang.getModel();
            for (int i = 0; i < tb_giohang.getRowCount(); i++) {

                String name = df.getValueAt(i, 1).toString();
                String qt = df.getValueAt(i, 2).toString();
                String prc = df.getValueAt(i, 3).toString();

                bill.setText(bill.getText() + name + "\t" + qt + "\t" + prc + " \n");

            }
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + "Tổng Tiền: \t" + txt_khachcantra.getText() + "\n");
            bill.setText(bill.getText() + "Tiền Khách Trả:\t" + txt_khachthanhtoan.getText() + "\n");
            bill.setText(bill.getText() + "Dư trả lại :\t" + txt_tienthua.getText() + "\n");
            bill.setText(bill.getText() + "====================================\n");
            bill.setText(bill.getText() + "                       Cảm ơn và hẹn gặp lại...!" + "\n");
            bill.setText(bill.getText() + "----------------------------------------------------------------\n");
            bill.setText(bill.getText() + "                    Cảm ơn vì đã lựa chọn dịch vụ cửa hàng" + "\n");

            bill.print();

        } catch (PrinterException ex) {

            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_inHoaDonActionPerformed

    private void btn_capnhapHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhapHDActionPerformed
        int row = tb_hoaDon.getSelectedRow();
        TrangThaiHoaDonViewModel hd = new TrangThaiHoaDonViewModel();

        try {
            String maHD = tb_hoaDon.getValueAt(row, 0).toString();
            String trangthaiHD = tb_hoaDon.getValueAt(row, 2).toString();
            String[] opption = {"Đã Thanh Toán", "Chưa Thanh Toán", "Hủy"};
            int x = JOptionPane.showOptionDialog(null, "Mời bạn chọn trạng thái đơn hàng",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opption, opption[0]);

            hoaDonServicesIPL.CapNhapTTHD(hd);
            loadHoaDon();
        } catch (Exception e) {

        }

    }//GEN-LAST:event_btn_capnhapHDActionPerformed

    private void txt_giamgiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_giamgiaKeyReleased

    }//GEN-LAST:event_txt_giamgiaKeyReleased

    private void txt_giamgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giamgiaActionPerformed
        Double discount;
        Double a = Double.parseDouble(txt_tongtien.getText());
        Double b = Double.parseDouble(txt_giamgia.getText());

        Double giamgia = 100 - b;
        Double giam = giamgia / 100;

        Double khachtra = a * giam;
        txt_khachcantra.setText((format.format(khachtra)));
    }//GEN-LAST:event_txt_giamgiaActionPerformed

    private void lb_wrbcamInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lb_wrbcamInputMethodTextChanged
        GetQR();
    }//GEN-LAST:event_lb_wrbcamInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bill;
    private javax.swing.JButton btn_capnhapHD;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_inHoaDon;
    private javax.swing.JButton btn_taohoaDon;
    private javax.swing.JButton btn_thanhtoan;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_nhanvien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel lb_wrbcam;
    private javax.swing.JRadioButton rd_chuyenkhoan;
    private javax.swing.JRadioButton rd_tienmat;
    public javax.swing.JTable tb_giohang;
    private javax.swing.JTable tb_hoaDon;
    private javax.swing.JTable tb_sanpham;
    public javax.swing.JTextField txt_diachiKH;
    private javax.swing.JTextField txt_giamgia;
    private javax.swing.JLabel txt_khachcantra;
    public javax.swing.JTextField txt_khachhang;
    private javax.swing.JTextField txt_khachthanhtoan;
    private app.bolivia.swing.JCTextField txt_maHD;
    private javax.swing.JTextField txt_ngaylap;
    private javax.swing.JLabel txt_qr;
    public javax.swing.JTextField txt_sdtkh;
    private javax.swing.JLabel txt_tienthua;
    private javax.swing.JTextField txt_timkiemKH;
    private app.bolivia.swing.JCTextField txt_timkiemSP;
    private javax.swing.JLabel txt_tongtien;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("STT");
        dtm.addColumn("Loai hang");
        dtm.addColumn("Ma SP");
        dtm.addColumn("Tên SP");
        dtm.addColumn("Thong Tin ");
        dtm.addColumn("So Luong");
        dtm.addColumn("Gia");
        do {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            Result result = null;
            BufferedImage imagle = null;
            if (webcam.isOpen()) {
                if ((imagle = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(imagle);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            int Count = 0;
            
            try {
                String sql = "Select loaihang,maSP,tenSP,mota,soluong,giaban from SanPham WHERE QRCode = '" + result + "'";
                Statement st = DBConnection.getConnection().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    dtm.addRow(new Object[]{
                        1,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)

                    });

                    tb_sanpham.setModel(dtm);
                    
                  

                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Loi truy van data");
            }
            if (result != null) {
                txt_qr.setText(result.getText());
//                int input = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập Số Lượng"));

            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void GetQR() {
        DefaultTableModel dtm = new DefaultTableModel();
        try {
            String sql = "Select * from SanPham WHERE QRCode = ?";
            Statement st = DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getDouble(6)
                });
                tb_sanpham.setModel(dtm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi truy van");
        }
    }
}
