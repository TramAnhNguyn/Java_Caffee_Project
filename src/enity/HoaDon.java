package enity;

import java.io.Serializable;

public class HoaDon implements Serializable {
    private String maKH,maNV,trangThai,ghiChu;
    private Integer maBan,maHD;
    private String ngayTao;

    public HoaDon(Integer maHD, String maKH, String maNV, String trangThai, String ghiChu, Integer maBan, String ngayTao) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.maBan = maBan;
        this.ngayTao = ngayTao;
    }

    public HoaDon(Integer maHD){
        super();
        this.maHD = maHD;
    }


    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
}
