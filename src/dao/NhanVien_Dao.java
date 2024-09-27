package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import enity.NhanVien;
public class NhanVien_Dao {
	public NhanVien_Dao() {
		
	}
	public ArrayList<NhanVien> getalltbNhanvien(){
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * FROM NhanVien ";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV  = rs.getString(2);
				String vaiTro  = rs.getString(3);
				String ngaySinh  = rs.getString(4);
				String gioiTinh = rs.getString(5);
				String SDT = rs.getString(6);
				NhanVien nv = new NhanVien(maNV, tenNV , vaiTro, ngaySinh, gioiTinh, SDT);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	public  boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into NhanVien values (?,?,?,?,?,?)");
			stmt.setString(1,  nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setString(3, nv.getVaiTro());
			stmt.setString(4, nv.getNgaySinh());
			stmt.setString(5, nv.getGioiTinh());
			stmt.setString(6, nv.getSDT());
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
	public boolean remove(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from NhanVien where MaNV = ?");
			stmt.setString(1, nv.getMaNV());
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
	
}
