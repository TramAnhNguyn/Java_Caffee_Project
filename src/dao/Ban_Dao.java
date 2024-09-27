package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import enity.Ban;


public class Ban_Dao {
	public Ban_Dao(){}
	public ArrayList<Ban> getalltbNhabanien(){
		ArrayList<Ban> dsban = new ArrayList<Ban>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * FROM Ban";
			java.sql.Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maBan = rs.getString(1);
				String soGhe  = rs.getString(2);
				String trangThai = rs.getString(3);
				
				Ban ban = new Ban(maBan, soGhe, trangThai);
				dsban.add(ban);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsban;
	}
	public  boolean create(Ban ban) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into Ban values (?,?,?)");
			stmt.setString(1,  ban.getMaBan());
			stmt.setString(2, ban.getSoGhe());
			stmt.setString(3, ban.getTrangThai());
			
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
	public boolean remove(Ban ban) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from Ban where Maban = ?");
			stmt.setString(1, ban.getMaBan());
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

	
	public static String getTenban(String maban) {
		String tenban = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * FROM Ban where Maban = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, maban);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tenban = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenban;
	}

	//cập nhật bàn
	public boolean update(Ban ban) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update Ban set SoGhe = ?, TrangThai = ? where MaBan = ?");
			stmt.setString(1, ban.getSoGhe());
			stmt.setString(2, ban.getTrangThai());
			stmt.setString(3, ban.getMaBan());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}
