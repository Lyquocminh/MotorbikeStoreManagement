package entity;

import java.util.ArrayList;
import java.util.Objects;

public class KhachHang {
	private String ma, ho, ten, diaChi,email;
	private int sdt;
	public ArrayList<HoaDon> dsHoaDon;
	public ArrayList<PhieuNhanXet> dsPhieuNhanXet;
	
	public KhachHang() {
		super();
	}
	
	public KhachHang(String ma) {
		super();
		this.ma = ma;
	}
	
	public KhachHang(String ma, String ho, String ten, String diaChi, int sdt, String email) {
		super();
		this.ma = ma;
		this.ho = ho;
		this.ten = ten;
		this.diaChi = diaChi;
		this.email = email;
		this.sdt = sdt;
		dsHoaDon = new ArrayList<HoaDon>();
		dsPhieuNhanXet = new ArrayList<PhieuNhanXet>();
	}
	
	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getMa() {
		return ma;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSdt() {
		return sdt;
	}
	public void setSdt(int sdt) {
		this.sdt = sdt;
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
		KhachHang other = (KhachHang) obj;
		return Objects.equals(ma, other.ma);
	}
	
}
