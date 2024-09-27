package gui;

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
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.ChiTietHoaDon_Dao;
import dao.HoaDon_Dao;
import dao.SanPham_Dao;

import enity.ChiTietHoaDon;
import enity.HoaDon;
import enity.KhachHang;
import enity.SanPham;

public class LichSuHoaDon_Gui extends JPanel implements ActionListener, MouseListener {
    private DefaultTableModel modelTable;
    private DefaultTableModel modelHoaDonMiddle;
    private JTable table;
    private JTable tableHoaDonMiddle;
    private JTextField searchField;
    private JLabel lblMaHoaDon;
    private JLabel lblKhachHang;
    private JLabel lblSoDienThoai;
    private JLabel lblThoiGianTao;
    private JLabel lblGhiChu;
    private JLabel lblTrangThai;
    private JLabel lblBan;
    private JLabel lblTongTien;
    
    private JButton btnSearch;

    private HoaDon_Dao hdDao = new HoaDon_Dao();
    private ChiTietHoaDon_Dao cthd = new ChiTietHoaDon_Dao();
    private ArrayList<HoaDon> dsLSHD = new ArrayList<>();
    private ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
    private ArrayList<ChiTietHoaDon> dsCT = new ArrayList<ChiTietHoaDon>();

    public LichSuHoaDon_Gui() {
    	super();
    	try {
    		
            ConnectDB.connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
    	
    	dsLSHD = hdDao.getLichSuHoaDon();

        // Left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // Label header
        JLabel lbHead = new JLabel("LỊCH SỬ HÓA ĐƠN");
        lbHead.setFont(new Font("Arial", Font.BOLD, 24));
        lbHead.setForeground(Color.BLUE);

        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(lbHead);
        leftPanel.add(Box.createVerticalStrut(20));

        JPanel pHead = new JPanel();
        JLabel lblMaHD = new JLabel("Mã HD: ");
        pHead.add(lblMaHD);
        pHead.add(Box.createHorizontalStrut(10));
        pHead.add(searchField = new JTextField(20));
        pHead.add(Box.createHorizontalStrut(10));
        btnSearch = new JButton("Tìm");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 15));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setBackground(new Color(109, 183, 252));
        pHead.add(btnSearch);
        pHead.add(Box.createVerticalStrut(20));

        leftPanel.add(pHead);
        // Add North panel to the main panel
        leftPanel.add(Box.createVerticalStrut(20));

        // Center - Table - Information
        String[] cols = {"Mã HD", "Người tạo", "Khách hàng", "Thời gian tạo", "Tổng tiền", "Trạng thái"};
        modelTable = new DefaultTableModel(cols, 0);
        table = new JTable(modelTable);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JScrollPane scrollPane = new JScrollPane(table);
        leftPanel.add(new JScrollPane(table));
        leftPanel.add(Box.createVerticalStrut(20));

        // Right side
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Right Middle - Table
        JPanel middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        JLabel middleLabel = new JLabel("HÓA ĐƠN CHI TIẾT");
        middleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        middleLabel.setForeground(Color.BLUE);
        middleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.add(Box.createVerticalStrut(20));
        middle.add(middleLabel);
        middle.add(Box.createVerticalStrut(20));

        // Right - table
        String[] colsMiddle = {"Tên SP", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú"};
        modelHoaDonMiddle = new DefaultTableModel(colsMiddle, 0);

        JTable tableHoaDonMiddle = new JTable(modelHoaDonMiddle);
        JScrollPane scrollPaneMiddle = new JScrollPane(tableHoaDonMiddle);
        scrollPaneMiddle.setPreferredSize(new Dimension(450, 300));
        middle.add(scrollPaneMiddle);
        middle.add(Box.createVerticalStrut(20));

        // Add middle to the center of rightPanel
        rightPanel.add(middle, BorderLayout.CENTER);

        // Create 1 panel under the table
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

        lblSoDienThoai = new JLabel("Số điện thoại: ");
        rightLeft.add(lblSoDienThoai);
        rightLeft.add(Box.createVerticalStrut(10));

        lblThoiGianTao = new JLabel("Thời gian tạo: ");
        rightLeft.add(lblThoiGianTao);
        rightLeft.add(Box.createVerticalStrut(10));


        // Right - Right
        JPanel rightRight = new JPanel();
        rightRight.setLayout(new BoxLayout(rightRight, BoxLayout.Y_AXIS));

        lblTrangThai = new JLabel("Trạng thái: ");
        rightRight.add(lblTrangThai);
        rightRight.add(Box.createVerticalStrut(10));

        lblTongTien = new JLabel("Tổng tiền hóa đơn: ");
        rightRight.add(lblTongTien);
        rightRight.add(Box.createVerticalStrut(10));

        // Add rightRight and rightLeft into 1 panel
        rightBottomPanel.add(rightLeft);
        rightBottomPanel.add(rightRight);

        rightPanel.add(rightBottomPanel, BorderLayout.PAGE_END);

        // Add leftPanel to the west and rightPanel to the east
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        
        // Add ActionListener to the button
        btnSearch.addActionListener(this);

        // Add MouseListener to the table
        table.addMouseListener(this);
        docLichSuHoaDon();
    }
    

    //Đọc dữ liệu từ bảng LichSuHoaDon
    public void docLichSuHoaDon() {
    	for (HoaDon hd : dsLSHD) {
    	    Double tongTien = 0.0;
    	    for (ChiTietHoaDon ct : cthd.getDSCT(hd.getMaHD())) {
    	      tongTien += SanPham_Dao.getDonGia(ct.getMaSP()) * ct.getSoLuong();
    	    }
    	    modelTable.addRow(new Object[]{
    	      hd.getMaHD(),
    	      hd.getMaNV(),
    	      hd.getMaKH(),
    	      hd.getNgayTao(),
    	      tongTien,
    	      hd.getTrangThai()
    	    });
    	  }
    }

    //Đọc dũ liệu từ bảng Chi tiết hóa đơn
    public void docChiTiet() {
        String ghiChu = "";
        for(HoaDon hd : dsLSHD) {
            ghiChu = hd.getGhiChu();
        }
        for (ChiTietHoaDon sp: dsCT ) {
            modelHoaDonMiddle.addRow(new Object[] {
                    SanPham_Dao.getTenSP(sp.getMaSP()),
                    SanPham_Dao.getDonGia(sp.getMaSP()),
                    sp.getSoLuong(),
                    SanPham_Dao.getDonGia(sp.getMaSP()) * sp.getSoLuong(),
                    ghiChu
            });
        }
    }

    //hàm get hóa đơn theo mã trong bảng
    public HoaDon getHoaDon(int maHD) {
        for (HoaDon hd : dsLSHD) {
            if (hd.getMaHD()==maHD) {
                return hd;
            }
        }
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnSearch)) {
            String searchInput = searchField.getText().trim();
            if (searchInput.equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn cần tìm");
            } else {
                try {
                    int maHD = Integer.parseInt(searchInput);
                    // Continue with your search logic using 'maHD'
                    HoaDon hd = getHoaDon(maHD);
                    if (hd == null) {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
                    } else {
                        modelTable.setRowCount(0);
                        modelHoaDonMiddle.setRowCount(0);
                        Double tongTien = 0.0;
                        for (ChiTietHoaDon ct : cthd.getDSCT(hd.getMaHD())) {
                            tongTien += SanPham_Dao.getDonGia(ct.getMaSP()) * ct.getSoLuong();
                        }
                        modelTable.addRow(new Object[]{
                                hd.getMaHD(),
                                hd.getMaNV(),
                                hd.getMaKH(),
                                hd.getNgayTao(),
                                tongTien,
                                hd.getTrangThai()
                        });
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên");
                }
            }
        }
    }



        


    @Override
    public void mouseClicked(MouseEvent e) {
    	//reset lại danh sách chi tiết
        dsCT.clear();
        //reset lại model
        modelHoaDonMiddle.setRowCount(0);
    	
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int maHoaDon = (int) table.getValueAt(selectedRow, 0);
            String khachHang = modelTable.getValueAt(selectedRow, 2).toString();
            String thoiGianTao = modelTable.getValueAt(selectedRow, 3).toString();
            String tongTienThanhToan = modelTable.getValueAt(selectedRow, 4).toString();
            String trangThai = modelTable.getValueAt(selectedRow, 5).toString();
            String sdt = KhachHang_Dao.getSDT(khachHang);

            //khi nhấn vào hóa đơn sẽ hiện các chi tiết hóa đơn của hóa đơn đó
            //lấy chi tiết hóa đơn theo mã hóa đơn
            dsCT = cthd.getDSCT(maHoaDon);
            //set lại model cho table bên phải
            docChiTiet();
            
            Double tongTien = 0.0;
            for (ChiTietHoaDon ct: dsCT) {
                tongTien += SanPham_Dao.getDonGia(ct.getMaSP()) * ct.getSoLuong();
            }
            
            // Update labels on the right side
            lblMaHoaDon.setText("Mã hóa đơn: " + maHoaDon);
            lblKhachHang.setText("Khách hàng: " + khachHang);
            lblThoiGianTao.setText("Thời gian tạo: " + thoiGianTao);
            lblTrangThai.setText("Trạng thái: Hoàn Thành"); 
            lblTongTien.setText("Tổng tiền hóa đơn: "+ tongTien); // Set your data here
            lblSoDienThoai.setText("Số điện thoại: " + sdt);
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
