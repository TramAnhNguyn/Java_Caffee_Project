package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JmenuACtion implements ActionListener  {
	Jmenu menu ;
	
	public JmenuACtion(Jmenu menu) {
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
		System.out.println(str);
		if(str.trim().compareToIgnoreCase("Hóa đơn chờ")==0) {
			System.out.println("hóa đơn chờ");
			HoaDonCho_Gui newGui = new HoaDonCho_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if (str.trim().compareToIgnoreCase("Tạo hóa đơn")==0) {
			System.out.println("Tạo hóa đơn");
			ThanhToan_Gui newGui = new ThanhToan_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if(str.trim().compareToIgnoreCase("Quản lý sản phẩm")==0){
			System.out.println("Quản lý sản phẩm");
			QuanLySanPham_Gui newGui = new QuanLySanPham_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if(str.trim().compareToIgnoreCase("Quản lý nhân viên")==0){
			System.out.println("Quản lý nhân viên");
			QuanLyNhanVien_Gui newGui = new QuanLyNhanVien_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if(str.trim().compareToIgnoreCase("Lịch sử hóa đơn")==0){
			System.out.println("Lịch sử hóa đơn");
			LichSuHoaDon_Gui newGui = new LichSuHoaDon_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if(str.trim().compareToIgnoreCase("Quản lý bàn")==0){
			System.out.println("Bàn");
			QuanLyBan_Gui newGui = new QuanLyBan_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		} else if(str.trim().compareToIgnoreCase("Quản lý khách hàng")==0){
			System.out.println("Quản lý khách hàng");
			QuanLyKhachHang_Gui newGui = new QuanLyKhachHang_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		}
		else if(str.trim().compareToIgnoreCase("Open")==0){
			System.out.println("Home");
			Home_Gui newGui = new Home_Gui();
			JPanel panel = menu.getContent();
			panel.removeAll();
			panel.add(newGui);
			panel.revalidate();
			panel.repaint();
		}
	}
}
