package gui;

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import dao.KhachHang_Dao;
import dao.SanPham_Dao;
import enity.ChiTietHoaDon;
import enity.HoaDon;
import enity.SanPham;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDonCho_Gui extends JPanel implements ActionListener, MouseListener {
    private DefaultTableModel modelHoaDonLeft;
    private DefaultTableModel modelHoaDonMiddle;
    private DefaultTableModel tempSelectedRowData;
    private JTable tableHoaDonLeft;
    private JTable tableHoaDonRight;
    private JButton btnXN;
    private JLabel lblMaHoaDon;
    private JLabel lblKhachHang;
    private JLabel lblSoDienThoai;
    private JLabel lblThoiGianTao;
    private JLabel lblGhiChu;
    private JLabel lblTrangThai;
    private JLabel lblTongTien;
    private JLabel lblChiPhiKhac;
    private JLabel lblTongTienHoaDon;

    private HoaDon_Dao hdCho = new HoaDon_Dao();
    private ArrayList<HoaDon> dsHDC = new ArrayList<HoaDon>();
    private ArrayList<ChiTietHoaDon> dsCT = new ArrayList<ChiTietHoaDon>();
    private ChiTietHoaDon_Dao cthd = new ChiTietHoaDon_Dao();

    public HoaDonCho_Gui() {
        super();
        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dsHDC = hdCho.getDSHoaDonCho();

        //Left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel leftTop = new JPanel();
        leftTop.setLayout(new BoxLayout(leftTop, BoxLayout.Y_AXIS));
        JLabel leftLabel = new JLabel("HÓA ĐƠN CHỜ");
        leftLabel.setFont(new Font("Arial", Font.BOLD, 24));
        leftLabel.setForeground(Color.BLACK);
        leftTop.add(Box.createVerticalStrut(20));
        leftTop.add(leftLabel);
        leftTop.add(Box.createVerticalStrut(20));

        String[] colsLeft = {"Mã HD", "Người tạo", "Thời gian tạo", "Trạng thái", "Ghi chú","Bàn","Khách hàng"};
        modelHoaDonLeft = new DefaultTableModel(colsLeft, 0);
        tableHoaDonLeft = new JTable(modelHoaDonLeft);
        leftTop.add(new JScrollPane(tableHoaDonLeft));
        leftTop.add(Box.createVerticalStrut(20));

        //set độ dài rộng cho các cột
        tableHoaDonLeft.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableHoaDonLeft.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableHoaDonLeft.getColumnModel().getColumn(2).setPreferredWidth(160);
        tableHoaDonLeft.getColumnModel().getColumn(3).setPreferredWidth(150);
        tableHoaDonLeft.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableHoaDonLeft.getColumnModel().getColumn(5).setPreferredWidth(100);
        tableHoaDonLeft.getColumnModel().getColumn(6).setPreferredWidth(150);


        btnXN = new JButton("Xác nhận hóa đơn");
        btnXN.setFont(new Font("Arial", Font.BOLD, 24));
        btnXN.setForeground(Color.BLACK);
        btnXN.setBackground(new Color(109, 183, 252));
        leftTop.add(btnXN);
        leftPanel.add(leftTop, BorderLayout.NORTH);


        // Right side
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Right Middle - Table
        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        JLabel middleLabel = new JLabel("HÓA ĐƠN CHI TIẾT");
        middleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        middleLabel.setForeground(Color.BLACK);
        middleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(Box.createVerticalStrut(20));
        middle.add(middleLabel);
        middle.add(Box.createVerticalStrut(20));

        //Right - table
        String[] colsMiddle = {"Tên SP", "Đơn giá", "Số lượng", "Thành tiền"};
        modelHoaDonMiddle = new DefaultTableModel(colsMiddle, 0);

        JTable tableHoaDonMiddle = new JTable(modelHoaDonMiddle);
        JScrollPane scrollPaneMiddle = new JScrollPane(tableHoaDonMiddle);
        scrollPaneMiddle.setPreferredSize(new Dimension(400, 300));
        middle.add(scrollPaneMiddle);
        middle.add(Box.createVerticalStrut(20));

        // Add middle to the center of rightPanel
        rightPanel.add(middle, BorderLayout.CENTER);

        //create 1 panel under the table
        JPanel rightBottomPanel = new JPanel();
        rightBottomPanel.setLayout(new GridLayout(1, 2));

        // Right - Left
        JPanel rightLeft = new JPanel();
        rightLeft.setLayout(new BoxLayout(rightLeft, BoxLayout.Y_AXIS));

        lblMaHoaDon = new JLabel("Mã hóa đơn: ");
        rightLeft.add(lblMaHoaDon);
        rightLeft.add(Box.createVerticalStrut(10));

        lblKhachHang = new JLabel("Khách hàng: ");
        rightLeft.add(lblKhachHang);
        rightLeft.add(Box.createVerticalStrut(10));

        lblThoiGianTao = new JLabel("Thời gian tạo: ");
        rightLeft.add(lblThoiGianTao);
        rightLeft.add(Box.createVerticalStrut(10));

        lblGhiChu = new JLabel("Ghi chú: ");
        rightLeft.add(lblGhiChu);
        rightLeft.add(Box.createVerticalStrut(10));


        // Right - Right
        JPanel rightRight = new JPanel();
        rightRight.setLayout(new BoxLayout(rightRight, BoxLayout.Y_AXIS));

        lblTrangThai = new JLabel("Trạng thái: ");
        rightRight.add(lblTrangThai);
        rightRight.add(Box.createVerticalStrut(10));

        lblTongTien = new JLabel("Bàn: ");
        rightRight.add(lblTongTien);
        rightRight.add(Box.createVerticalStrut(10));


        lblTongTienHoaDon = new JLabel("Tổng tiền hóa đơn: ");
        rightRight.add(lblTongTienHoaDon);
        rightRight.add(Box.createVerticalStrut(10));

        //Add rightRight and rightLeft into 1 panel
        rightBottomPanel.add(rightLeft);
        rightBottomPanel.add(rightRight);

        rightPanel.add(rightBottomPanel, BorderLayout.PAGE_END);

        // Add leftPanel to the west and rightPanel to the east
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        btnXN.addActionListener(this);
        tableHoaDonLeft.addMouseListener(this);
        docSanPham();

    }

    public void docSanPham() {
        for (HoaDon sp: dsHDC ) {
            modelHoaDonLeft.addRow(new Object[] {
                    sp.getMaHD(),
                    sp.getMaNV(),
                    sp.getNgayTao(),
                    sp.getTrangThai(),
                    sp.getGhiChu(),
                    sp.getMaBan(),
                    // lấy tên khách hàng
                    KhachHang_Dao.getTenKH(sp.getMaKH())
            });
        }
    }

    public void docChiTiet() {
        for (ChiTietHoaDon sp: dsCT ) {
            modelHoaDonMiddle.addRow(new Object[] {
                    SanPham_Dao.getTenSP(sp.getMaSP()),
                    SanPham_Dao.getDonGia(sp.getMaSP()),
                    sp.getSoLuong(),
                    SanPham_Dao.getDonGia(sp.getMaSP()) * sp.getSoLuong()
            });
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnXN)) {
            if (tableHoaDonLeft.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xác nhận");
                return;
            }
            //lấy mã hóa đơn
            int selectedRow = tableHoaDonLeft.getSelectedRow();
            int maHoaDon = (int) modelHoaDonLeft.getValueAt(selectedRow, 0);
            //update trạng thái hóa đơn
            if (hdCho.xacNhanHoaDon(maHoaDon)) {
                JOptionPane.showMessageDialog(null, "Xác nhận hóa đơn thành công");
                modelHoaDonLeft.setRowCount(0);
                dsHDC = hdCho.getDSHoaDonCho();
                docSanPham();
            } else {
                JOptionPane.showMessageDialog(null, "Xác nhận hóa đơn thất bại");
            }

            // Display confirmation message
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //reset lại danh sách chi tiết
        dsCT.clear();
        //reset lại model
        modelHoaDonMiddle.setRowCount(0);

        int selectedRow = tableHoaDonLeft.getSelectedRow();
        if (selectedRow != -1) {
            int maHoaDon = (int) modelHoaDonLeft.getValueAt(selectedRow, 0);
            String khachHang = modelHoaDonLeft.getValueAt(selectedRow, 6).toString();
            String ngayTao = modelHoaDonLeft.getValueAt(selectedRow, 2).toString();
            String ghiChu = modelHoaDonLeft.getValueAt(selectedRow, 4).toString();
            String ban = modelHoaDonLeft.getValueAt(selectedRow,5).toString();

            //khi nhấn vào hóa đơn sẽ hiện các chi tiết hóa đơn của hóa đơn đó
            //lấy chi tiết hóa đơn theo mã hóa đơn
            dsCT = cthd.getDSCT(maHoaDon);
            //set lại model cho table bên phải
            docChiTiet();
            if(ban.equals("0")){
                ban = "Mang về";
            }
            else{
                ban = "Bàn " + ban;
            }
            Double tongTien = 0.0;
            for (ChiTietHoaDon ct: dsCT) {
                tongTien += SanPham_Dao.getDonGia(ct.getMaSP()) * ct.getSoLuong();
            }
            // Update labels on the right side
            lblMaHoaDon.setText("Mã hóa đơn: " + maHoaDon);
            lblKhachHang.setText("Khách hàng: " + khachHang);
            lblThoiGianTao.setText("Ngày tạo: " + ngayTao);
            lblGhiChu.setText("Ghi chú: " + ghiChu);
            lblTrangThai.setText("Trạng thái: Chờ xác nhận"); // Set your data here
            lblTongTien.setText("Bàn: "+ ban); // Set your data here
            lblTongTienHoaDon.setText("Tổng tiền hóa đơn: "+ tongTien); // Set your data here
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
