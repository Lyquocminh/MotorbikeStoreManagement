package entity;

import java.util.ArrayList;

public class NhanVienKyThuat extends NhanVien{
	private ArrayList<PhieuNhanXet> dsPhieuNhanXet;
	private String bacTho;
	public NhanVienKyThuat(String ma, String ten, String diaChi, String chucVu, String email, int sdt, String maCuaHang,
			int namKinhNghiem, TaiKhoan taiKhoan,String bacTho) {
		super(ma, ten, diaChi, chucVu, email, sdt, maCuaHang, namKinhNghiem,taiKhoan);
		this.bacTho=bacTho;
		dsPhieuNhanXet = new ArrayList<PhieuNhanXet>();
	}
	public ArrayList<PhieuNhanXet> getDsPhieuNhanXet() {
		return dsPhieuNhanXet;
	}
	public void setDsPhieuNhanXet(ArrayList<PhieuNhanXet> dsPhieuNhanXet) {
		this.dsPhieuNhanXet = dsPhieuNhanXet;
	}
	public String getBacTho() {
		return bacTho;
	}
	public void setBacTho(String bacTho) {
		this.bacTho = bacTho;
	}
	
	
}
