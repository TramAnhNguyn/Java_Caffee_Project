package enity;

import java.io.Serializable;
import java.util.Objects;

public class SanPham implements Serializable  {
	private String maSP, tenSP, moTa, Loai;
	private boolean trangThai;
	private double DonGia;
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	public SanPham(String maSP, String tenSP, String moTa, boolean trangThai, double donGia, String loai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.trangThai = trangThai;
		DonGia = donGia;
		Loai = loai;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getLoai() {
		return Loai;
	}
	public void setLoai(String loai) {
		Loai = loai;
	}
	public boolean isTrangThai() {
		return trangThai;
	}
	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	public double getDonGia() {
		return DonGia;
	}
	public void setDonGia(double donGia) {
		DonGia = donGia;
	}
	
	
}
