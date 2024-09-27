package gui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Jmenu extends JFrame{
	private JPanel content;

	public Jmenu() {
		this.setSize(1500, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font fontSize = new Font("Arial", Font.BOLD, 16);
		Font iconFont = new Font("Segoe UI Emoji", Font.PLAIN, 20);

		JMenuBar bar = new JMenuBar();
		//set màu của các menu khi hover vào
		UIManager.put("Menu.selectionBackground",new Color(200, 255, 255));
		bar.setPreferredSize(new Dimension(0, 50));
		bar.setOpaque(true);
		bar.setBackground(new Color(109, 183, 252)); 

		//icon
		JLabel iconcannha = new JLabel("\uD83C\uDFE0 ");
		iconcannha.setFont(iconFont);
		JLabel iconsanpham = new JLabel("\uD83C\uDF7A ");
		iconsanpham.setFont(iconFont);
		//icon khách hàng
		JLabel iconbanhang = new JLabel("\uD83D\uDC64");
		iconbanhang.setFont(iconFont);
		JLabel iconthongke = new JLabel("\uD83D\uDCBA");
		iconthongke.setFont(iconFont);
		JLabel iconnhanvien = new JLabel("\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC66 ");
		iconnhanvien.setFont(iconFont);
		JLabel iconhoadon = new JLabel("\uD83D\uDCB3 ");
		iconhoadon.setFont(iconFont);

		//trang chu
		JMenu home = new JMenu("Trang Chủ");
		home.setFont(fontSize);
		//set border cho menu
		home.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		//set border radius viền có các gốc bo cong cho menu
		JMenuItem homeItem = new JMenuItem("Open"); // MenuItem trong Menu "Trang Chủ"
		//set màu cho menuitem
		homeItem.setBackground(new Color(109, 183, 252));
		home.add(homeItem); // Thêm MenuItem vào Menu "Trang Chủ"

		// khách hàng
		JMenu customer = new JMenu("Khách hàng");
		customer.setFont(fontSize);
		customer.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		JMenuItem thanhToan = new JMenuItem("Quản lý khách hàng");
		thanhToan.setBackground(new Color(109, 183, 252));
		customer.add(thanhToan);
		// sản phẩm
		JMenu product = new JMenu("Sản Phẩm");
		product.setFont(fontSize);
		product.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		JMenuItem quanLySanPham = new JMenuItem("Quản lý sản phẩm");
		quanLySanPham.setBackground(new Color(109, 183, 252));
		product.add(quanLySanPham);
		// Hoa đơn 
		JMenu hoaDon = new JMenu("Hóa đơn");
		hoaDon.setFont(fontSize);
		hoaDon.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		JMenuItem taohoadon = new JMenuItem("Tạo hóa đơn");
		taohoadon.setBackground(new Color(109, 183, 252));
		hoaDon.add(taohoadon);
		JMenuItem hoadoncho = new JMenuItem("Hóa đơn chờ");
		hoadoncho.setBackground(new Color(109, 183, 252));
		hoaDon.add(hoadoncho);
		JMenuItem lichsu = new JMenuItem("Lịch sử hóa đơn");
		lichsu.setBackground(new Color(109, 183, 252));
		hoaDon.add(lichsu);

		// thống kê
		JMenu thongke = new JMenu("Bàn");
		thongke.setFont(fontSize);
		thongke.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		JMenuItem doanhThu = new JMenuItem("Quản lý Bàn");
		doanhThu.setBackground(new Color(109, 183, 252));
		thongke.add(doanhThu);
		// nhân viên
		JMenu nhanvien = new JMenu("Nhân viên");
		nhanvien.setFont(fontSize);
		nhanvien.setBorder(BorderFactory.createLineBorder(new Color(200, 255, 255), 3));
		JMenuItem quanlynhanvien = new JMenuItem("Quản lý nhân viên");
		quanlynhanvien.setBackground(new Color(109, 183, 252));
		nhanvien.add(quanlynhanvien);


		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconcannha);
		bar.add(home);
		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconsanpham);
		bar.add(product);
		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconbanhang);
		bar.add(customer);
		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconhoadon);
		bar.add(hoaDon);
		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconthongke);
		bar.add(thongke);
		bar.add(Box.createHorizontalStrut(20));
		bar.add(iconnhanvien);
		bar.add(nhanvien);
		bar.add(Box.createHorizontalStrut(20));
		this.setJMenuBar(bar);

		content = new JPanel();
		this.add(content, BorderLayout.CENTER);

		Home_Gui newGui = new Home_Gui();
		content.add(newGui);

		this.setVisible(true);

		//add action 
		ActionListener ac = new JmenuACtion(this);
		hoadoncho.addActionListener(ac);
		thanhToan.addActionListener(ac);
		quanLySanPham.addActionListener(ac);
		doanhThu.addActionListener(ac);
		quanlynhanvien.addActionListener(ac);
		lichsu.addActionListener(ac);
		homeItem.addActionListener(ac);
		taohoadon.addActionListener(ac);
}
	public JPanel getContent() {
		return content;
}

	public static void main(String[] args) {
		new Jmenu();
	}
}
