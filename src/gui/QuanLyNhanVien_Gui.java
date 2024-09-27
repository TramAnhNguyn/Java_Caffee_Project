package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_Dao;
import enity.NhanVien;
import enity.SanPham;


public class QuanLyNhanVien_Gui extends JPanel implements MouseListener, ActionListener {
	private ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
	private NhanVien_Dao nvdao = new NhanVien_Dao();
	 private JTextField txtMaNV, txtTenNV, txtVaiTro, txtNgaySinh, txtGioiTinh, txtSDT;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnTim;
    public QuanLyNhanVien_Gui() {
    	super();
    	
    	try {
			ConnectDB.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	dsNV = nvdao.getalltbNhanvien();
       
        
        

     // Tạo panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel chứa các thông tin nhân viên và button
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Nhân viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Đặt khoảng cách giữa các thành phần

        // Label và TextField cho Mã NV
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        infoPanel.add(new JLabel("Mã NV:"), gbc);
        gbc.gridx = 1;
        txtMaNV = new JTextField(20);
        infoPanel.add(txtMaNV, gbc);

        // Label và TextField cho Tên NV
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Tên NV:"), gbc);
        gbc.gridx = 1;
        txtTenNV = new JTextField(20);
        infoPanel.add(txtTenNV, gbc);

        // Label và TextField cho Vai trò
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Vai trò:"), gbc);
        gbc.gridx = 1;
        txtVaiTro = new JTextField(20);
        infoPanel.add(txtVaiTro, gbc);

        // Label và TextField cho Ngày sinh
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        txtNgaySinh = new JTextField(20);
        infoPanel.add(txtNgaySinh, gbc);

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
        String[] columnNames = {"Mã NV", "Tên NV", "Vai trò", "Ngày sinh", "Giới tính", "Số điện thoại"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        TitledBorder border = BorderFactory.createTitledBorder("Danh Sách Nhân Viên");
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
    	for (NhanVien nv: dsNV ) {
    		tableModel.addRow(new Object[] {
    				nv.getMaNV(),
    				nv.getTenNV(),
    				nv.getVaiTro(),
    				nv.getNgaySinh(),
    				nv.getGioiTinh(),
    				nv.getSDT()
    				
    				
    		});
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnThem)) {
			
			String maNV = txtMaNV.getText().trim();
			String tenNV = txtTenNV.getText().trim();
			String vaiTro = txtVaiTro.getText().trim();
			String ngaySinh = txtNgaySinh.getText().trim();
			String gioiTinh = txtGioiTinh.getText().trim();
			String sDT = txtSDT.getText().trim();
			
			for (int i = 0; i < tableModel.getRowCount(); i++) {
		        if (tableModel.getValueAt(i, 0).equals(maNV)) {
		            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại trong bảng. Vui lòng nhập mã nhân viên khác.");
		            return;
		        }
		    }
	
			
            if (!maNV.matches("^NV\\d+")) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên bắt đầu là 'NV' và theo sau là số nguyên bất kì");
                return; 
            }
            if (!tenNV.matches("[\\p{L}0-9' ]+")) {
                JOptionPane.showMessageDialog(this, "Tên nhân viên gồm nhiều từ ngăn cách bởi khoảng trắng");
                return; 
            }
            if (!vaiTro.matches("[\\p{L}0-9' ]+")) {
                JOptionPane.showMessageDialog(this, "vai trò gồm nhiều từ ngăn cách bởi khoảng trắng");
                return; 
            }
            if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
                JOptionPane.showMessageDialog(this, "Giới tính phải là 'Nam' hoặc 'Nữ'.");
                return;
            }
            if (!sDT.matches("\\d+") || sDT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại là số nguyên và không được để trống.");
                return;
            }
            if (!ngaySinh.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng YYYY-MM-DD.");
                return;
            }
            
            try {
                // Attempt to parse the date of birth
                LocalDate.parse(ngaySinh);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng YYYY-MM-DD.");
                return;
            }
            
			NhanVien nv = new NhanVien(txtMaNV.getText(),txtTenNV.getText(),txtVaiTro.getText(), txtNgaySinh.getText(), txtGioiTinh.getText(), txtSDT.getText() );
			tableModel.addRow(new Object[] {
					nv.getMaNV(),
    				nv.getTenNV(),
    				nv.getVaiTro(),
    				nv.getNgaySinh(),
    				nv.getGioiTinh(),
    				nv.getSDT()
    				
    		});
			if(nvdao.create(nv)) {
				JOptionPane.showMessageDialog(this, "Thành Công");
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
				NhanVien nv = new NhanVien(txtMaNV.getText());
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
			JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên nào trùng khớp");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
    	txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
    	txtTenNV.setText(tableModel.getValueAt(row, 1).toString());
    	txtVaiTro.setText(tableModel.getValueAt(row, 2).toString());
    	txtNgaySinh.setText(tableModel.getValueAt(row, 3).toString());
    	txtGioiTinh.setText(tableModel.getValueAt(row, 4).toString());
    	txtSDT.setText(tableModel.getValueAt(row, 5).toString());
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
