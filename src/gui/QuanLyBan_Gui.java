package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.Ban_Dao;
import enity.Ban;
import enity.SanPham;
import enity.Ban;

public class QuanLyBan_Gui extends JPanel implements MouseListener, ActionListener {
    private ArrayList<Ban> dsBan = new ArrayList<Ban>();
    private Ban_Dao banDao = new Ban_Dao();
    private JTextField txtMaBan, txtSoGhe;
    private JComboBox<String> txtTrangThai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnXoa, btnTim;

    public QuanLyBan_Gui() {
        super();

        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dsBan = banDao.getalltbNhabanien();

        // Tạo panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel chứa các thông tin bàn và button
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Bàn"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

        // Label và TextField cho Mã Bàn
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        infoPanel.add(new JLabel("Mã Bàn:"), gbc);
        gbc.gridx = 1;
        txtMaBan = new JTextField(20);
        infoPanel.add(txtMaBan, gbc);

        // Label và TextField cho Số Ghế
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Số Ghế:"), gbc);
        gbc.gridx = 1;
        txtSoGhe = new JTextField(20);
        infoPanel.add(txtSoGhe, gbc);

        // Label và ComboBox cho Trạng Thái
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Trạng Thái:"), gbc);
        gbc.gridx = 1;
        String[] trangThaiArr = {"Mang về", "Trống"};
        txtTrangThai = new JComboBox<>(trangThaiArr);
        infoPanel.add(txtTrangThai, gbc);

        // Panel chứa các button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Cập nhật");
        btnTim = new JButton("Tìm");

        // Thêm các button vào buttonPanel
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnTim);

        // Đặt panel infoPanel và buttonPanel vào mainPanel (bên trái)
        mainPanel.add(infoPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tạo bảng danh sách bàn và đặt border có tiêu đề
        String[] columnNames = {"Mã Bàn", "Số Ghế", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        TitledBorder border = BorderFactory.createTitledBorder("Danh Sách Bàn");
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

        docBan();
    }

    public void docBan() {
        for (Ban ban : dsBan) {
            tableModel.addRow(new Object[]{
                    ban.getMaBan(),
                    ban.getSoGhe(),
                    ban.getTrangThai()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
        if (source.equals(btnThem)) {
            try {
            	String maBan = txtMaBan.getText().trim();
            	if(maBan.isEmpty()) {
            		JOptionPane.showMessageDialog(this, "Mã bàn không rỗng. Vui lòng nhập mã bàn");
            		return;
            	}
                // Check if the table ID already exists in the table
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(maBan)) {
                        JOptionPane.showMessageDialog(this, "Mã bàn đã tồn tại trong bảng. Vui lòng nhập mã bàn khác.");
                        return;
                    }
                }
            	
                Ban ban = new Ban(
                        txtMaBan.getText(),
                        txtSoGhe.getText(),
                        txtTrangThai.getSelectedItem().toString()
                );
                tableModel.addRow(new Object[]{
                        ban.getMaBan(),
                        ban.getSoGhe(),
                        ban.getTrangThai()
                });
                if (banDao.create(ban)) {
                    JOptionPane.showMessageDialog(this, "Thành Công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không Thành Công");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ");
            }
        } else if (source.equals(btnXoa)) {
            //cập nhật bàn
            int row = table.getSelectedRow();
            Ban ban = new Ban(
                    txtMaBan.getText(),
                    txtSoGhe.getText(),
                    txtTrangThai.getSelectedItem().toString()
            );
            if (banDao.update(ban)) {
                tableModel.setValueAt(ban.getMaBan(), row, 0);
                tableModel.setValueAt(ban.getSoGhe(), row, 1);
                tableModel.setValueAt(ban.getTrangThai(), row, 2);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
            }
        } 
        else if (source.equals(btnTim)) {
            String maBanTim = txtMaBan.getText();
            boolean found = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(maBanTim)) {
                    table.clearSelection();
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã bàn nào trùng khớp");
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
        txtMaBan.setText(tableModel.getValueAt(row, 0).toString());
        txtSoGhe.setText(tableModel.getValueAt(row, 1).toString());
        if(tableModel.getValueAt(row, 2).toString().equals("Mang về")) {
        	txtTrangThai.setSelectedIndex(0);
        }
        else if(tableModel.getValueAt(row, 2).toString().equals("trống")) {
        	txtTrangThai.setSelectedIndex(1);
        }
        
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

    
