package entity;

import java.util.Objects;

public class NhanVien {
	private String ma, ten, diaChi, chucVu, email, maCuaHang;
	private int sdt, namKinhNghiem;
	private TaiKhoan taiKhoan;
	public NhanVien(String ma, String ten, String diaChi, String chucVu, String email, int sdt,
		String maCuaHang, int namKinhNghiem,TaiKhoan taiKhoan) {
		this.ma = ma;
		this.ten = ten;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.email = email;
		this.sdt = sdt;
		this.maCuaHang = maCuaHang;
		this.namKinhNghiem = namKinhNghiem;
		this.taiKhoan=taiKhoan;
	}

	public String getMaCuaHang() {
		return maCuaHang;
	}

	public void setMaCuaHang(String maCuaHang) {
		this.maCuaHang = maCuaHang;
	}

	public int getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(int namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
	}

	public String getMa() {
		return ma;
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
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
		NhanVien other = (NhanVien) obj;
		return Objects.equals(ma, other.ma);
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}
	
	
}
