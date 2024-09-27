package gui;

import connectDB.ConnectDB;
import dao.KhachHang_Dao;
import dao.NhanVien_Dao;
import enity.KhachHang;
import enity.NhanVien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuanLyKhachHang_Gui extends JPanel implements MouseListener, ActionListener {
    private ArrayList<KhachHang> dsNV = new ArrayList<KhachHang>();
    private KhachHang_Dao nvdao = new KhachHang_Dao();
    private JTextField txtMaNV, txtTenNV, txtVaiTro, txtNgaySinh, txtGioiTinh, txtSDT;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnTim;
    public QuanLyKhachHang_Gui() {

            try {
                ConnectDB.connect();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            dsNV = nvdao.getAllKhachHang();




            // Tạo panel chính
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Panel chứa các thông tin nhân viên và button
            JPanel infoPanel = new JPanel(new GridBagLayout());
            infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Khách hàng"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Đặt khoảng cách giữa các thành phần

            // Label và TextField cho Mã NV
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            infoPanel.add(new JLabel("Mã KH:"), gbc);
            gbc.gridx = 1;
            txtMaNV = new JTextField(20);
            infoPanel.add(txtMaNV, gbc);

            // Label và TextField cho Tên NV
            gbc.gridx = 0;
            gbc.gridy = 1;
            infoPanel.add(new JLabel("Tên KH:"), gbc);
            gbc.gridx = 1;
            txtTenNV = new JTextField(20);
            infoPanel.add(txtTenNV, gbc);

            // Label và TextField cho Giới tính
            gbc.gridx = 0;
            gbc.gridy = 4;
            infoPanel.add(new JLabel("Giới tính:"), gbc);
            gbc.gridx = 1;
            txtGioiTinh = new JTextField(20);
            infoPanel.add(txtGioiTinh, gbc);

            // Label và TextField cho Số điện thoại
            gbc.gridx = 0;
            gbc.gridy = 5;
            infoPanel.add(new JLabel("Số điện thoại:"), gbc);
            gbc.gridx = 1;
            txtSDT = new JTextField(20);
            infoPanel.add(txtSDT, gbc);

            // Panel chứa các button
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            btnThem = new JButton("Thêm");
            btnXoa = new JButton("Xóa");
            btnTim = new JButton("Tìm");

            // Thêm các button vào buttonPanel
            buttonPanel.add(btnThem);
            buttonPanel.add(btnXoa);
            buttonPanel.add(btnTim);

            // Đặt panel infoPanel và buttonPanel vào mainPanel (bên trái)
            mainPanel.add(infoPanel, BorderLayout.WEST);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Tạo bảng danh sách nhân viên và đặt border có tiêu đề
            String[] columnNames = {"Mã KH", "Tên KH", "Giới tính", "Số điện thoại"};
            tableModel = new DefaultTableModel(columnNames, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            TitledBorder border = BorderFactory.createTitledBorder("Danh Sách Khách hàng");
            border.setTitleColor(Color.BLUE);
            border.setTitleFont(new Font("Arial", Font.BOLD, 30));
            border.setTitleJustification(TitledBorder.CENTER);
            scrollPane.setBorder(border);

            // Đặt bảng vào mainPanel (bên phải)
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // Đặt mainPanel vào JFrame
            add(mainPanel);

            btnThem.addActionListener(this);
            btnXoa.addActionListener(this);
            btnTim.addActionListener(this);
            table.addMouseListener(this);

            docSanPham();

        }
        public void docSanPham() {
            for (KhachHang nv: dsNV ) {
                tableModel.addRow(new Object[] {
                        nv.getMaKH(),
                        nv.getTenKH(),
                        nv.getGioiTinh(),
                        nv.getSdt()
                });
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Object source = e.getSource();
            if(source.equals(btnThem)) {
                // Lấy thông tin từ các JTextField của khách hàng
                String maKH = txtMaNV.getText().trim();
                String tenKH = txtTenNV.getText().trim();
                String gioiTinh = txtGioiTinh.getText().trim();
                String sdt = txtSDT.getText().trim();
                
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String existingMaKH = tableModel.getValueAt(i, 0).toString();
                    if (existingMaKH.equals(maKH)) {
                        JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại trong bảng. Vui lòng nhập mã khách hàng khác");
                        return;
                    }
                }
                
                if (!maKH.matches("^KH\\d+") || maKH.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Mã khách hàng bắt đầu bằng 'KH' và theo sau là số nguyên bất kì");
                    return;
                }
                if (!tenKH.matches("[\\p{L}' ]+")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
                    return;
                }
                if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
                    JOptionPane.showMessageDialog(this, "Giới tính phải là 'Nam' hoặc 'Nữ'.");
                    return;
                }
                if (!sdt.matches("\\d+") || sdt.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại là số nguyên và không được để trống.");
                    return;
                }
                
                //tạo khách hàng
                KhachHang nv = new KhachHang(maKH, tenKH, sdt, gioiTinh);
                if(nvdao.createKhachHang(nv)) {
                    JOptionPane.showMessageDialog(this, "Thành Công");
                    //Thêm khách hàng vào bảng
                    tableModel.addRow(new Object[] {
                            nv.getMaKH(),
                            nv.getTenKH(),
                            nv.getGioiTinh(),
                            nv.getSdt()


                    });
                }else {
                    JOptionPane.showMessageDialog(this, "Không Thành Công");
                }
            }
            else if (source.equals(btnXoa)) {
                int r = table.getSelectedRow();
                if(r == -1) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!!!");
                    return;
                }else if(JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa chứ","Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    KhachHang nv = new KhachHang(txtMaNV.getText(), txtTenNV.getText(), txtGioiTinh.getText(), txtSDT.getText());
                    tableModel.removeRow(r);
                    if(nvdao.remove(nv)) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                    }else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại !!!");
                    }
                }
            }else if (source.equals(btnTim))
            {
                for(int i = 0; i<tableModel.getRowCount(); i++)
                {
                    if(tableModel.getValueAt(i, 0).equals(txtMaNV.getText())) {
                        table.clearSelection();
                        table.setRowSelectionInterval(i, i);
                        table.scrollRectToVisible(table.getCellRect(i, 0, true));
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã Khách hàng nào trùng khớp");
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            int row = table.getSelectedRow();
            txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
            txtTenNV.setText(tableModel.getValueAt(row, 1).toString());
            txtGioiTinh.setText(tableModel.getValueAt(row, 2).toString());
            txtSDT.setText(tableModel.getValueAt(row, 3).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
}
