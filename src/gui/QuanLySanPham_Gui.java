package gui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.SanPham_Dao;
import enity.SanPham;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuanLySanPham_Gui extends JPanel implements MouseListener, ActionListener {

    private DefaultTableModel tableModel;
    private JTable productTable;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTextField txtProductCode;
    private JTextField txtProductName;
    private JTextField txtUnitPrice;
    private JTextField txtDescription;
    private JComboBox<String> cmbProductType;
    private JRadioButton radCompleted;
    private JRadioButton radInProgress;
    private ArrayList<SanPham> dsSP = new ArrayList<>();
    private SanPham_Dao spdao = new SanPham_Dao();

    public QuanLySanPham_Gui() {
        super();
        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dsSP = spdao.getalltbNhaspien();

        this.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Mã sản phẩm (chỉ nhập mã để tìm):"), gbc);
        txtProductCode = new JTextField(15);
        txtProductCode.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        gbc.gridx = 1;
        inputPanel.add(txtProductCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Tên sản phẩm:"), gbc);
        txtProductName = new JTextField(15);
        txtProductName.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        gbc.gridx = 1;
        inputPanel.add(txtProductName, gbc);

        String[] productTypes = {"Cà phê", "Đóng chai", "Nước Ép", "Đá chanh", "Trà"};
        cmbProductType = new JComboBox<>(productTypes);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Loại sản phẩm:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(cmbProductType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Trạng thái:"), gbc);
        radCompleted = new JRadioButton("Đang bán");
        radInProgress = new JRadioButton("Ngưng bán");
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(radCompleted);
        statusGroup.add(radInProgress);
        JPanel statusPanel = new JPanel();
        statusPanel.add(radCompleted);
        statusPanel.add(radInProgress);
        gbc.gridx = 1;
        inputPanel.add(statusPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Đơn giá:"), gbc);
        txtUnitPrice = new JTextField(15);
        txtUnitPrice.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        gbc.gridx = 1;
        inputPanel.add(txtUnitPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Mô tả:"), gbc);
        txtDescription = new JTextField(15);
        txtDescription.setBorder(BorderFactory.createCompoundBorder(
                new RoundedCornerBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        gbc.gridx = 1;
        inputPanel.add(txtDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAdd = new JButton("Thêm");
        btnDelete = new JButton("Xóa");
        btnSearch = new JButton("Tìm");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        inputPanel.add(buttonPanel, gbc);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên sản phẩm");
        tableModel.addColumn("Trạng thái");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Mô tả");
        tableModel.addColumn("Loại ");

        productTable = new JTable(tableModel);
        productTable.setBackground(new Color(255, 220, 255));

        JScrollPane tableScrollPane = new JScrollPane(productTable);

        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);

        TitledBorder border = BorderFactory.createTitledBorder("Menu của quán");
        border.setTitleColor(Color.BLUE);
        border.setTitleFont(new Font("Arial", Font.BOLD, 30));
        border.setTitleJustification(TitledBorder.CENTER);
        tableScrollPane.setBorder(border);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, tableScrollPane);
        splitPane.setResizeWeight(0.3);
        docSanPham();
        productTable.addMouseListener(this);
        add(splitPane, BorderLayout.CENTER);
    }

    public void docSanPham() {
        for (SanPham sp : dsSP) {
            tableModel.addRow(new Object[]{
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.isTrangThai() ? "Đang bán" : "Ngừng bán",
                    sp.getDonGia(),
                    sp.getMoTa(),
                    sp.getLoai()
            });
        }
    }

    class RoundedCornerBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(c.getBackground().darker());
            g2d.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
            g2d.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(btnAdd)) {
            try {
            	
            	String maSP = txtProductCode.getText().trim();
            	String tenSP = txtProductName.getText().trim();
            	for (int i = 0; i < tableModel.getRowCount(); i++) {
    		        if (tableModel.getValueAt(i, 0).equals(maSP)) {
    		            JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại trong bảng. Vui lòng nhập mã sản phẩm khác.");
    		            return;
    		        }
    		    }
                if (!maSP.matches("^SP\\d+")) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm bắt đầu bằng 'SP' và theo sau là số nguyên bất kì");
                    return; 
                }
                if (!tenSP.matches("[\\p{L}0-9' ]+")) {
                    JOptionPane.showMessageDialog(this, "Tên sản phẩm gồm nhiều từ ngăn cách bởi khoảng trắng");
                    return; 
                }
            	
                SanPham sp = new SanPham(
                        txtProductCode.getText(),
                        txtProductName.getText(),
                        txtDescription.getText(),
                        radCompleted.isSelected(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        cmbProductType.getSelectedItem().toString()
                );
                tableModel.addRow(new Object[]{
                        sp.getMaSP(),
                        sp.getTenSP(),
                        sp.isTrangThai() ? "Đang bán" : "Ngừng bán",
                        sp.getDonGia(),
                        sp.getMoTa(),
                        sp.getLoai()
                });
                if (spdao.create(sp)) {
                    JOptionPane.showMessageDialog(this, "Thành Công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không Thành Công");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Đơn giá không hợp lệ");
            }
        } else if (source.equals(btnDelete)) {
            int r = productTable.getSelectedRow();
            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!!!");
                return;
            } else if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String productCode = tableModel.getValueAt(r, 0).toString();
                if (spdao.remove(new SanPham(productCode))) {
                    tableModel.removeRow(r);
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        } else if (source.equals(btnSearch)) {
            String searchCode = txtProductCode.getText().trim();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(searchCode)) {
                    productTable.clearSelection();
                    productTable.setRowSelectionInterval(i, i);
                    productTable.scrollRectToVisible(productTable.getCellRect(i, 0, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm nào trùng khớp");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = productTable.getSelectedRow();
        txtProductCode.setText(tableModel.getValueAt(row, 0).toString());
        txtProductName.setText(tableModel.getValueAt(row, 1).toString());
        if (tableModel.getValueAt(row, 2).toString().equals("Đang bán")) {
            radCompleted.setSelected(true);
        } else {
            radInProgress.setSelected(true);
        }
        txtUnitPrice.setText(tableModel.getValueAt(row, 3).toString());
        txtDescription.setText(tableModel.getValueAt(row, 4).toString());
        if(tableModel.getValueAt(row, 5).toString().equals("Cà phê")) {
        	cmbProductType.setSelectedIndex(0);
        }
        else if(tableModel.getValueAt(row, 5).toString().equals("Đóng chai")) {
        	cmbProductType.setSelectedIndex(1);
        }
        else if(tableModel.getValueAt(row, 5).toString().equals("Nước Ép")) {
        	cmbProductType.setSelectedIndex(2);
        }
        else if(tableModel.getValueAt(row, 5).toString().equals("Đá chanh")) {
        	cmbProductType.setSelectedIndex(3);
        }
        else if(tableModel.getValueAt(row, 5).toString().equals("Trà")) {
        	cmbProductType.setSelectedIndex(4);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void removeAllRows() {
        int rowCount = productTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Sản Phẩm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new QuanLySanPham_Gui());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
