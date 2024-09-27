package enity;

import java.io.Serializable;

public class NhanVien implements Serializable{
	private String maNV, tenNV, vaiTro, ngaySinh, gioiTinh, SDT;

	public NhanVien(String maNV, String tenNV, String vaiTro, String ngaySinh, String gioiTinh, String sDT) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.vaiTro = vaiTro;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		SDT = sDT;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
}
