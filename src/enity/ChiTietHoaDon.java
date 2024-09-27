package enity;

import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {
    private String maSP;
    private Integer soLuong,maHD;

    public ChiTietHoaDon(String maSP, int maHD, Integer soLuong) {
        this.maSP = maSP;
        this.maHD = maHD;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon(String maSP, int maHD){
        super();
        this.maSP = maSP;
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
