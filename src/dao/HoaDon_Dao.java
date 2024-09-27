package dao;

import connectDB.ConnectDB;
import enity.ChiTietHoaDon;
import enity.HoaDon;
import enity.SanPham;
import gui.ThanhToan_Gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDon_Dao {
    public HoaDon_Dao() {

    }

    public static int getNewIDHD(){
        ArrayList<HoaDon> dsSP = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM HoaDon ";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int maHD = rs.getInt(1);
                String  maKH = rs.getString(2);
                String trangThai= rs.getString(3);
                String ghiChu = rs.getString(5);
                String maNV = rs.getString(6);
                String ngayTao = rs.getString(7);
                int maBan = rs.getInt(4);
                HoaDon sp = new HoaDon(maHD, maKH, maNV, trangThai, ghiChu, maBan, ngayTao);
                dsSP.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return dsSP.size()+1;
    }

    //tạo hóa đơn mới với trạng thái mặc định là "Chờ xác nhận"
    public static boolean create(HoaDon hd) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("Insert into HoaDon values (?,?,?,?,?,?,?)");
            stmt.setInt(1, hd.getMaHD());
            stmt.setString(2, hd.getMaKH());
            stmt.setString(3, "NV01");
            stmt.setInt(4, hd.getMaBan());
            stmt.setString(5, hd.getNgayTao());
            stmt.setString(6, "Chờ xác nhận");
            stmt.setString(7, hd.getGhiChu());
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

    public ArrayList<HoaDon> getDSHoaDonCho(){
            ArrayList<HoaDon> dsHDC = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from HoaDon where TrangThai like N'Chờ xác nhận'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int maHD = rs.getInt(1);
                String maKH  = rs.getString(2);
                String maNV = rs.getString(3);
                int maBan = rs.getInt(4);
                String ngayTao = rs.getString(5);
                String trangThai = rs.getString(6);
                String ghiChu = rs.getString(7);
                HoaDon sp = new HoaDon(maHD, maKH, maNV, trangThai, ghiChu,maBan,ngayTao);
                dsHDC.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHDC;
    }

    //xác nhận hóa đơn -> chuyển trạng thái thành "Hoàn thành"
    public boolean xacNhanHoaDon(int maHD) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("Update HoaDon set TrangThai = N'Hoàn thành' where MaHD = ?");
            stmt.setInt(1, maHD);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public ArrayList<HoaDon> getLichSuHoaDon(){
        ArrayList<HoaDon> dsHDC = new ArrayList<HoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "select * from HoaDon where TrangThai like N'Hoàn thành'";
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int maHD = rs.getInt(1);
                String maKH  = rs.getString(2);
                String maNV = rs.getString(3);
                int maBan = rs.getInt(4);
                String ngayTao = rs.getString(5);
                String trangThai = rs.getString(6);
                String ghiChu = rs.getString(7);
                HoaDon sp = new HoaDon(maHD, maKH, maNV, trangThai, ghiChu,maBan,ngayTao);
                dsHDC.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsHDC;
    }
}
