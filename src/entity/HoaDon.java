package entity;

import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String ma, maKH, maNV, maCH, thoiGianBH;
	private Date ngayLap;
	
	public HoaDon() {
		super();
	}
	
	public HoaDon(String ma) {
		super();
		this.ma = ma;
	}
	
	public HoaDon(String ma, Date ngayLap, String thoiGianBH, String maKH, String maCH, String maNV) {
		super();
		this.ma = ma;
		this.maKH = maKH;
		this.maNV = maNV;
		this.maCH = maCH;
		this.thoiGianBH = thoiGianBH;
		this.ngayLap = ngayLap;
	}
	
	public void setMa(String ma) {
		this.ma = ma;
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
	public String getMaCH() {
		return maCH;
	}
	public void setMaCH(String maCH) {
		this.maCH = maCH;
	}
	public String getThoiGianBH() {
		return thoiGianBH;
	}
	public void setThoiGianBH(String thoiGianBH) {
		this.thoiGianBH = thoiGianBH;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getMa() {
		return ma;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ma);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(ma, other.ma);
	}
	
	
}
