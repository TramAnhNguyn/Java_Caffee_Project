package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import enity.SanPham;

public class SanPham_Dao {
	public SanPham_Dao() {
		
	}
	public ArrayList<SanPham> getalltbNhaspien(){
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * FROM SanPham ";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP  = rs.getString(2);
				boolean trangThai= true;
				if (rs.getString(3).equals("Ðang bán")) {
					trangThai = true;
				}
				else {
					trangThai =	false;
				}
				double donGia  = rs.getDouble(4);
				String moTa = rs.getString(5);
				String loai = rs.getString(6);
				SanPham sp = new SanPham(maSP, tenSP, moTa, trangThai, donGia, loai);
				dsSP.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsSP;
	}
	public  boolean create(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into SanPham values (?,?,?,?,?,?)");
			stmt.setString(1,  sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setString(3, sp.isTrangThai() ?"Ðang bán" : "Ngừng bán");
			stmt.setDouble(4, sp.getDonGia());
			stmt.setString(5, sp.getMoTa());
			stmt.setString(6, sp.getLoai());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean remove(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from SanPham where MaSP = ?");
			stmt.setString(1, sp.getMaSP());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public static Double getDonGia(String maSP) {
		Double donGia = 0.0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * FROM SanPham where MaSP = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				donGia = rs.getDouble(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donGia;
	}
	public static String getTenSP(String maSP) {
		String tenSP = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * FROM SanPham where MaSP = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tenSP = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenSP;
	}
}
