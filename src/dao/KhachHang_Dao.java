package dao;

import connectDB.ConnectDB;
import enity.*;
import gui.ThanhToan_Gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHang_Dao {
    public KhachHang_Dao() {

    }

    public static String getSDT(String khachHang) {
        String sdt = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM KhachHang where MaKH like '"+khachHang+"'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                sdt = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sdt;
    }

    //lấy danh sách khách hàng
    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM KhachHang";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                dsKH.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKH;
    }

    //tạo khách hàng
    public boolean createKhachHang(KhachHang kh) {
        int n = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();


            String sql = "INSERT INTO KhachHang VALUES(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, kh.getMaKH());
            statement.setString(2, kh.getTenKH());
            statement.setString(3, kh.getSdt());
            statement.setString(4, kh.getGioiTinh());
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean remove(KhachHang nv) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("delete from KhachHang where MaKH = ?");
            stmt.setString(1, nv.getMaKH());
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

    //lấy mã khách hàng từ số điện thoại
    public static String getMaKH(String sdt){
        String maKH = "";

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM KhachHang where SDT like '"+sdt+"'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                maKH = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maKH;
    }

    //lấy tên khách hàng theo mã
    public static String getTenKH(String maKH){
        String tenKH = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM KhachHang where MaKH like '"+maKH+"'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                tenKH = rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenKH;
    }
}
