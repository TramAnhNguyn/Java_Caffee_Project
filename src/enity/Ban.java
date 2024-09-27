package enity;

import java.io.Serializable;

public class Ban implements Serializable {
    private String maBan,soGhe;
    private String trangThai;
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(String soGhe) {
		this.soGhe = soGhe;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public Ban(String maBan, String soGhe, String trangThai) {
		super();
		this.maBan = maBan;
		this.soGhe = soGhe;
		this.trangThai = trangThai;
	}
	public Ban(String maBan) {
		super();
		this.maBan = maBan;
	}
	
	

    



}
