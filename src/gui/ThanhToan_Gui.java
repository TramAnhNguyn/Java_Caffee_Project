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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ThanhToan_Gui extends JPanel implements ActionListener, MouseListener {

    private JTextField txtChonKH;
    private JComboBox<Object> cbChonBan;
    private JButton btnOK;
    private  JButton btnTim;
    private  JButton btnThanhToan;
    private  JButton btnHuy;
    private  JTextField txtGhiChu;
    private  JTextField txtTienTraLai;
    private  JTextField txtTienKhachDua;
    private  JTextField txtTienHoaDon;
    private  JComboBox<Integer> cbSoLuong;
    private  JTextField txtTienSanPham;
    private  JTable tableHoaDon;
    private  DefaultTableModel modelHoaDon;
    private  JTextField timText;
    private  JLabel timLabel;
    private  JButton btnAdd;
    private  JTable tableSanPham;
    private DefaultTableModel modelSanPham;
    private ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
    private SanPham_Dao spdao = new SanPham_Dao();
    private ChiTietHoaDon_Dao dsCT = new ChiTietHoaDon_Dao();
    private HoaDon_Dao hdDao = new HoaDon_Dao();
    Font fontSize = new Font("Arial", Font.BOLD, 16);

    public ThanhToan_Gui() {
        super();
        try {
            ConnectDB.connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dsSP = spdao.getalltbNhaspien();

        this.setLayout(new BorderLayout());

        //title
        JLabel title = new JLabel("Thanh Toán Hóa Đơn", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.BLUE);
        this.add(title, BorderLayout.NORTH);

        //center
        JPanel cenPanel = new JPanel();
        cenPanel.setLayout(new BorderLayout());
        this.add(cenPanel, BorderLayout.CENTER);
            //left
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        //set margin cho panel
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,50));
        cenPanel.add(leftPanel,BorderLayout.WEST);
        TitledBorder border = BorderFactory.createTitledBorder("Menu của quán");
        border.setTitleColor(new Color(10, 200, 200));
        border.setTitleFont(new Font("Arial", Font.BOLD, 30));
        border.setTitleJustification(TitledBorder.CENTER);
        leftPanel.setBorder(border);
                //form
        String[] cols = {"Mã Món","Tên Món","Đơn giá"};
        modelSanPham = new DefaultTableModel(cols,0);
        tableSanPham = new JTable(modelSanPham);
        leftPanel.add(new JScrollPane(tableSanPham),BorderLayout.NORTH);
                //button
        JPanel btnPanel = new JPanel();
        leftPanel.add(btnPanel,BorderLayout.SOUTH);
        btnAdd = new JButton("Thêm");
        btnAdd.setFont(fontSize);
        btnAdd.setBackground(new Color(109, 183, 252));
        btnPanel.add(btnAdd);
        timLabel = new JLabel("Tìm kiếm bằng tên: ");
        timLabel.setFont(fontSize);
        btnPanel.add(timLabel);
        timText = new JTextField(10);
        btnPanel.add(timText);
        btnTim = new JButton("Tìm");
        btnTim.setFont(fontSize);
        btnTim.setBackground(new Color(109, 183, 252));
        btnPanel.add(btnTim);
            //right
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        cenPanel.add(rightPanel);
                //chitiethoadon
        TitledBorder borderchitiet = BorderFactory.createTitledBorder("Chi Tiết Hóa Đơn");
        borderchitiet.setTitleColor(new Color(10, 200, 200));
        borderchitiet.setTitleFont(new Font("Arial", Font.BOLD, 30));
        borderchitiet.setTitleJustification(TitledBorder.CENTER);
        rightPanel.setBorder(borderchitiet);
        String[] colhoadon = {"Mã HD","Mã món","Tên Món","Đơn giá","Số lượng","Thành tiền"};
        modelHoaDon = new DefaultTableModel(colhoadon,0);
        tableHoaDon = new JTable(modelHoaDon);
        rightPanel.add(new JScrollPane(tableHoaDon),BorderLayout.NORTH);
        //làm ngắn bảng lại một nữa
        tableHoaDon.setPreferredScrollableViewportSize(new Dimension(500, 200));
                //Hoa đơn
        JPanel hoadon = new JPanel(new BorderLayout());
        rightPanel.add(hoadon,BorderLayout.CENTER);
        hoadon.setBackground(Color.LIGHT_GRAY);
                    //right
        JPanel tiensanpham = new JPanel();
        tiensanpham.setLayout(new BoxLayout(tiensanpham,BoxLayout.Y_AXIS));
        hoadon.add(tiensanpham,BorderLayout.EAST);
        //margin
        tiensanpham.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel tongtiensanpham = new JPanel();
        JLabel lblTienSanPham = new JLabel("Tổng tiền sản phẩm: ");
        //set font cho label
        lblTienSanPham.setFont(fontSize);
        tongtiensanpham.add(lblTienSanPham);
        txtTienSanPham = new JTextField(10);
        txtTienSanPham.setEditable(false);
        tongtiensanpham.add(txtTienSanPham);
        tiensanpham.add(tongtiensanpham);


        JPanel chonsoluongPanel = new JPanel();
        JLabel chonsoluong = new JLabel("Chọn số lượng: ");
        //set font cho label
        chonsoluong.setFont(fontSize);
        chonsoluongPanel.add(chonsoluong);
        cbSoLuong = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            cbSoLuong.addItem(i);
        }
        chonsoluongPanel.add(cbSoLuong);
        //btn ok
        btnOK = new JButton("OK");
        btnOK.setBackground(new Color(80, 183, 252));
        chonsoluongPanel.add(btnOK);
        //set do dai của combobox
        cbSoLuong.setPreferredSize(new Dimension(50, 30));
        tiensanpham.add(chonsoluongPanel);

                    //left
        JPanel tienhoadon = new JPanel();
        tienhoadon.setLayout(new BoxLayout(tienhoadon,BoxLayout.Y_AXIS));
        hoadon.add(tienhoadon,BorderLayout.WEST);
        //margin
        tienhoadon.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel tienhoadonPanel = new JPanel();
        JLabel lblTienHoaDon = new JLabel("Tổng tiền hóa đơn: ");
        //set font cho label
        lblTienHoaDon.setFont(fontSize);
        tienhoadonPanel.add(lblTienHoaDon);
        txtTienHoaDon = new JTextField(20);
        txtTienHoaDon.setEditable(false);
        tienhoadonPanel.add(txtTienHoaDon);
        tienhoadon.add(tienhoadonPanel);


        JPanel tienKhach = new JPanel();
        tienhoadon.add(tienKhach);

        JLabel lblTienKhachDua = new JLabel("Tiền khách đưa: ");
        //set font cho label
        lblTienKhachDua.setFont(fontSize);
        tienKhach.add(lblTienKhachDua);
         txtTienKhachDua = new JTextField(5);
        tienKhach.add(txtTienKhachDua);
        JLabel lblTienTraLai = new JLabel("Tiền trả lại: ");
        //set font cho label
        lblTienTraLai.setFont(fontSize);
        tienKhach.add(lblTienTraLai);
         txtTienTraLai = new JTextField(6);
        txtTienTraLai.setEditable(false);
        tienKhach.add(txtTienTraLai);


        JPanel ghiChuPanel = new JPanel();
        JLabel lblGhiChu = new JLabel("Ghi chú:                 ");
        //set font cho label
        lblGhiChu.setFont(fontSize);
        ghiChuPanel.add(lblGhiChu);
         txtGhiChu = new JTextField(20);
        ghiChuPanel.add(txtGhiChu);
        tienhoadon.add(ghiChuPanel);

        //ngày tạo hóa đơn
        JPanel ngayTaoHD = new JPanel();
        JLabel lblNgayTaoHD = new JLabel("Ngày tạo hóa đơn: ");
        //set font cho label
        lblNgayTaoHD.setFont(fontSize);
        ngayTaoHD.add(lblNgayTaoHD);
        JTextField txtNgayTaoHD = new JTextField(20);
        txtNgayTaoHD.setEditable(false);
        ngayTaoHD.add(txtNgayTaoHD);
        tienhoadon.add(ngayTaoHD);
        //set ngày tạo là ngày hôm nay bằng hàm curentday hoặc hàm nào khác
        txtNgayTaoHD.setText(getCurentDay());

        //Chọn bàn
        JPanel chonBan = new JPanel();
        JLabel lblChonBan = new JLabel("Chọn bàn:               ");
        //set font cho label
        lblChonBan.setFont(fontSize);
        chonBan.add(lblChonBan);
        cbChonBan = new JComboBox<>();
        cbChonBan.addItem("mang về");
        cbChonBan.addItem("1");
        cbChonBan.addItem("2");
        cbChonBan.addItem("3");
        cbChonBan.addItem("4");
        cbChonBan.addItem("5");
        cbChonBan.addItem("6");
        chonBan.add(cbChonBan);
        tienhoadon.add(chonBan);
        //set size cho combobox
        cbChonBan.setPreferredSize(new Dimension(182, 30));

        //nhập số điện thoại khách hàng
        JPanel chonKH = new JPanel();
        JLabel lblChonKH = new JLabel("Số điện thoại KH:    ");
        //set font cho label
        lblChonKH.setFont(fontSize);
        chonKH.add(lblChonKH);
        txtChonKH = new JTextField(20);
        chonKH.add(txtChonKH);
        tienhoadon.add(chonKH);

        JPanel btnHoaDon = new JPanel();
        hoadon.add(btnHoaDon,BorderLayout.SOUTH);
         btnHuy = new JButton("Hủy");
        btnHuy.setFont(fontSize);
        btnHuy.setBackground(new Color(109, 183, 252));
        btnHoaDon.add(btnHuy);
        //margin cho btn hủy
        btnHuy.setBorder(BorderFactory.createEmptyBorder(28,20,28,20));
        btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setFont(new Font("Arial", Font.BOLD, 30));
        btnThanhToan.setBackground(new Color(109, 183, 252));
        btnHoaDon.add(btnThanhToan);
        //margin cho btn thanh toán
        btnThanhToan.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //đọc dữ liệu vào table
        docSanPham();

        //set action cho button
        btnAdd.addActionListener(this);
        tableHoaDon.addMouseListener(this);
        btnTim.addActionListener(this);
        btnOK.addActionListener(this);
        btnThanhToan.addActionListener(this);
        btnHuy.addActionListener(this);
        txtTienKhachDua.addActionListener(this);
    }

    public static String getCurentDay() {
        //format ngày tháng năm
        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }

    public void docSanPham() {
        for (SanPham sp: dsSP ) {
            modelSanPham.addRow(new Object[] {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getDonGia(),
            });
        }
    }

    public void themChiTietHoaDon(){
        //sửa lại hàm này để khi add cột mới thì cột cũ sẽ không bị thay đổi

        int row = tableSanPham.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm vào hóa đơn");
            return;
        }
        String maSP = (String) tableSanPham.getValueAt(row, 0);
        String tenSP = (String) tableSanPham.getValueAt(row, 1);
        double donGia = (double) tableSanPham.getValueAt(row, 2);
        int soLuong = 1;
        double thanhTien = donGia * soLuong;
        //thêm vào dsCT
        dsCT.add(new ChiTietHoaDon(maSP, HoaDon_Dao.getNewIDHD(), soLuong));
        //thêm vào table hóa đơn
        modelHoaDon.addRow(new Object[] {
                HoaDon_Dao.getNewIDHD(),
                maSP,
                tenSP,
                donGia,
                soLuong,
                thanhTien
        });

        //set lại giá trị cho txtTienSanPham
        txtTienSanPham.setText(String.valueOf(thanhTien));
        cbSoLuong.setSelectedItem(soLuong);
    }

    public void tinhTongTienHoaDon(){
        double tongTien = 0;
        for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
            tongTien += (double) modelHoaDon.getValueAt(i, 5);
        }
        txtTienHoaDon.setText(String.valueOf(tongTien));
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd){
        
            themChiTietHoaDon();
            //tính tổng tiền hóa đơn
            tinhTongTienHoaDon();
        }
        if(e.getSource() == btnTim){
            String tenSP = timText.getText();
            if (tenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên món.");
                return;
            }
            
            modelSanPham.setRowCount(0);
            for (SanPham sp: dsSP ) {
                if(sp.getTenSP().toLowerCase().contains(tenSP.toLowerCase())){
                    modelSanPham.addRow(new Object[] {
                            sp.getMaSP(),
                            sp.getTenSP(),
                            sp.getDonGia(),
                    });
                }
            }
        }
        if(e.getSource() == btnOK){
            //lấy số lượng từ combobox
            int soLuong = (int) cbSoLuong.getSelectedItem();
            //nếu chưa chọn sản phẩm thì báo lỗi
            if(tableHoaDon.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thay đổi số lượng");
                return;
            }
            //chọn sản phẩm
            int row = tableHoaDon.getSelectedRow();
            //Lấy đơn giá của sản phẩm được chọn
            double donGia = (double) modelHoaDon.getValueAt(row, 3);
            //tính thành tiền
            double thanhTien = donGia * soLuong;
            //thay đổi số lượng và thành tiền của sản phẩm được chọn
            modelHoaDon.setValueAt(soLuong, row, 4);
            modelHoaDon.setValueAt(thanhTien, row, 5);
            //tính lại tổng tiền hóa đơn
            tinhTongTienHoaDon();
            //lưu lại số lượng vào dsCT
            String maSP = (String) modelHoaDon.getValueAt(row, 1);
            dsCT.updateSoLuong(maSP, soLuong);
        }
        if(e.getSource() == txtTienKhachDua){
            //nếu chưa tổng tiền chưa có dữ liệu thì báo lỗi
            if(txtTienHoaDon.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm vào hóa đơn");
                return;
            }
            //nếu tiền khách đưa rỗng hoặc không phải là số thì báo lỗi
            if(txtTienKhachDua.getText().equals("") || !txtTienKhachDua.getText().matches("[0-9]+")){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số tiền khách đưa");
                return;
            }
            //tiền khách đưa không đựa bé hơn tổng tiền hóa đơn
            if(Double.parseDouble(txtTienKhachDua.getText()) < Double.parseDouble(txtTienHoaDon.getText())){
                JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ để thanh toán");
                return;
            }
            double tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
            double tongTien = Double.parseDouble(txtTienHoaDon.getText());
            double tienTraLai = tienKhachDua - tongTien;
            txtTienTraLai.setText(String.valueOf(tienTraLai));
        }
        if(e.getSource() == btnHuy){
            modelHoaDon.setRowCount(0);
            txtTienHoaDon.setText("");
            txtTienKhachDua.setText("");
            txtTienTraLai.setText("");
            txtGhiChu.setText("");
            txtTienSanPham.setText("");
            cbSoLuong.setSelectedItem(1);
            //xóa dữ liệu trong dsCT
            dsCT.removeAll();
        }
        //thanh toán
        if(e.getSource() == btnThanhToan ){
        	if(modelHoaDon.getRowCount()==0) {
        		JOptionPane.showMessageDialog(this, "Chưa thêm sản phẩm");
        		return;
        	}
        	
            //lấy mã bàn
            int maBan = 0;
            if(cbChonBan.getSelectedItem().equals("mang về")){
                maBan = 0;
            }else{
                maBan = Integer.parseInt(cbChonBan.getSelectedItem().toString());
            }
            //lấy mã khách hàng từ số điện thoại
            String maKH = "";
            if(!txtChonKH.getText().equals("")){
                maKH = KhachHang_Dao.getMaKH(txtChonKH.getText());
            }else {
                maKH = "KH";
            }
            //tạo hóa đơn mới với mã mới
            HoaDon hd = new HoaDon(HoaDon_Dao.getNewIDHD(), maKH,"NV01","Chờ xác nhận", txtGhiChu.getText(),maBan, getCurentDay());
            if(hdDao.create(hd)){
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            }else{
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại");
                return;
            }
            //thêm chi tiết hóa đơn vào csdl
            for (ChiTietHoaDon ct : dsCT.getDsCTHD()){
                if(!dsCT.create(ct)){
                    JOptionPane.showMessageDialog(this, "Tạo chi tiết hóa đơn thất bại");
                    return;
                }
            }
            //xóa dữ liệu trong dsCT
            dsCT.removeAll();
            //xóa dữ liệu trong table
            modelHoaDon.setRowCount(0);
            txtTienHoaDon.setText("");
            txtTienKhachDua.setText("");
            txtTienTraLai.setText("");
            txtGhiChu.setText("");
            txtTienSanPham.setText("");
            cbSoLuong.setSelectedItem(1);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //mouse click event cho chi tiết sản phẩm, khi ấn vào sẽ hiển thành tiền ở ô txtTienSanPham và số lượng ở combobox
        int row = tableHoaDon.getSelectedRow();
        txtTienSanPham.setText(tableHoaDon.getValueAt(row, 5).toString());
        cbSoLuong.setSelectedItem(modelHoaDon.getValueAt(row, 4));
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
