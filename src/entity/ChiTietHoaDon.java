package entity;

public class ChiTietHoaDon {
	private String ma, maLoaiXe;
	private ThongTinXe ttXE;
	private int soLuong,thanhTien, donGia;
	
	public ChiTietHoaDon() {
		super();
	}
	
	public ChiTietHoaDon(String ma) {
		super();
		this.ma = ma;
		this.maLoaiXe = ttXE.getMaLoaiXe();
	}
	
	public ChiTietHoaDon(String ma, ThongTinXe ttXE, int soLuong, int donGia) {
		super();
		this.ma = ma;
		this.maLoaiXe = ttXE.getMaLoaiXe();
		this.donGia = ttXE.getGiaNiemYet();
		this.soLuong = soLuong;
		this.thanhTien = soLuong * donGia;
	}
	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getMaLoaiXe() {
		return maLoaiXe;
	}

	public void setMaLoaiXe(String maLoaiXe) {
		this.maLoaiXe = maLoaiXe;
	}

	public ThongTinXe getTtXE() {
		return ttXE;
	}

	public void setTtXE(ThongTinXe ttXE) {
		this.ttXE = ttXE;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}


	
}
