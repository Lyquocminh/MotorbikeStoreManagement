package entity;

import java.util.ArrayList;

public class NhanVienHanhChinh extends NhanVien{
	public ArrayList<HoaDon> dsHoaDon;
	public NhanVienHanhChinh(String ma, String ten, String diaChi, String chucVu, String email, int sdt, 
		String maCuaHang, int namKinhNghiem,TaiKhoan taiKhoan) {
		super(ma, ten, diaChi, chucVu, email, sdt, maCuaHang, namKinhNghiem, taiKhoan);
		dsHoaDon = new ArrayList<HoaDon>();
	}
	public ArrayList<HoaDon> getDsHoaDon() {
		return dsHoaDon;
	}
	public void setDsHoaDon(ArrayList<HoaDon> dsHoaDon) {
		this.dsHoaDon = dsHoaDon;
	}
	
}
