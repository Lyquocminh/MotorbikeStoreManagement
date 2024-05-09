package entity;

public class XeTrongKho {
	private String maCuaHang, maLoaiXe;
	private int soLuong;
	public XeTrongKho() {
		super();
	}
	public XeTrongKho(String maCuaHang, String maXe) { 
		super();
		this.maCuaHang = maCuaHang;
		this.maLoaiXe = maXe;
	}
	public XeTrongKho(String maCuaHang, String maXe, int soLuong) {
		super();
		this.maCuaHang = maCuaHang;
		this.maLoaiXe = maXe;
		this.soLuong = soLuong;
	}
	public String getMaCuaHang() {
		return maCuaHang;
	}
	public void setMaCuaHang(String maCuaHang) {
		this.maCuaHang = maCuaHang;
	}
	public String getMaXe() {
		return maLoaiXe;
	}
	public void setMaXe(String maXe) {
		this.maLoaiXe = maXe;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
}
