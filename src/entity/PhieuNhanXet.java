package entity;

import java.util.Objects;

public class PhieuNhanXet {
	private int maPhieu, giaTien;
	private String liDoBH, loiThuocVe, linhKien, maKH, maNVKT;
	public PhieuNhanXet(int maPhieu, int giaTien, String liDoBH, String loiThuocVe, String linhKien, String maKH,
			String maNVKT) {
		super();
		this.maPhieu = maPhieu;
		this.giaTien = giaTien;
		this.liDoBH = liDoBH;
		this.loiThuocVe = loiThuocVe;
		this.linhKien = linhKien;
		this.maKH = maKH;
		this.maNVKT = maNVKT;
	}
	public int getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}
	public String getLiDoBH() {
		return liDoBH;
	}
	public void setLiDoBH(String liDoBH) {
		this.liDoBH = liDoBH;
	}
	public String getLoiThuocVe() {
		return loiThuocVe;
	}
	public void setLoiThuocVe(String loiThuocVe) {
		this.loiThuocVe = loiThuocVe;
	}
	public String getLinhKien() {
		return linhKien;
	}
	public void setLinhKien(String linhKien) {
		this.linhKien = linhKien;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaNVKT() {
		return maNVKT;
	}
	public void setMaNVKT(String maNVKT) {
		this.maNVKT = maNVKT;
	}
	public int getMaPhieu() {
		return maPhieu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhieu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuNhanXet other = (PhieuNhanXet) obj;
		return maPhieu == other.maPhieu;
	}
	
}
