package dao;

import connectDB.ConnectDB;
import enity.ChiTietHoaDon;
import enity.HoaDon;
import enity.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ChiTietHoaDon_Dao {

    ArrayList<ChiTietHoaDon> dsCTHD;

    public ChiTietHoaDon_Dao() {
        dsCTHD = new ArrayList<ChiTietHoaDon>();
    }

    public boolean create(ChiTietHoaDon ct) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("Insert into ChiTietHoaDon values (?, ?, ?)");
            stmt.setString(1, ct.getMaSP());
            stmt.setInt(2, ct.getMaHD());
            stmt.setInt(3, ct.getSoLuong());
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

    public void add(ChiTietHoaDon ct){
        // add to list
        dsCTHD.add(ct);
    }

    public Double getTongTien(int maHD){
        Double tongTien = 0.0;
        for (ChiTietHoaDon ct : dsCTHD){
            if (ct.getMaHD()==maHD){
                tongTien += ct.getSoLuong() * SanPham_Dao.getDonGia(ct.getMaSP());
            }
        }
        return tongTien;
    }

    public void updateSoLuong(String maSP, int soLuong) {
        for (ChiTietHoaDon ct : dsCTHD){
            if (ct.getMaSP().equals(maSP)){
                ct.setSoLuong(soLuong);
            }
        }

    }

    public void removeAll() {
        dsCTHD.clear();
    }

    public ChiTietHoaDon[] getDsCTHD() {
        return dsCTHD.toArray(new ChiTietHoaDon[0]);

    }


    //lấy danh sách chi tiết theo mã hóa đơn
    public ArrayList<ChiTietHoaDon> getDSCT(int maHD){
        ArrayList<ChiTietHoaDon> dsCT = new ArrayList<ChiTietHoaDon>();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();

            String sql = "Select * FROM ChiTietHoaDon where MaHD = "+maHD;
            java.sql.Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String maSP = rs.getString(1);
                int maHD1 = rs.getInt(2);
                int soLuong = rs.getInt(3);
                ChiTietHoaDon sp = new ChiTietHoaDon(maSP, maHD1, soLuong);
                dsCT.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCT;
    }

    // thanhtoan ... sql
}
