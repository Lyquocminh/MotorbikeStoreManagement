package entity;

import java.util.ArrayList;
import java.util.List;

public class ThongTinXe {
	private String maLoaiXe, tenLoaiXe, moTaXe, phienBan;
	private int giaNiemYet, giaGiam;
	public List<Xe> dsXe;
	
	public ThongTinXe() {
		super();
	}
	
	public ThongTinXe(String maLoaiXe) {
		super();
		this.maLoaiXe = maLoaiXe;
	}
	
	public ThongTinXe(String maLoaiXe, String tenLoaiXe, int giaNiemYet, int giaGiam, String moTaXe, String phienBan) {
		super();
		this.maLoaiXe = maLoaiXe;
		this.tenLoaiXe = tenLoaiXe;
		this.moTaXe = moTaXe;
		this.phienBan = phienBan;
		this.giaNiemYet = giaNiemYet;
		this.giaGiam = giaGiam;
		dsXe = new ArrayList<Xe>();
	}

	public String getMaLoaiXe() {
		return maLoaiXe;
	}

	public void setMaLoaiXe(String maLoaiXe) {
		this.maLoaiXe = maLoaiXe;
	}

	public String getTenLoaiXe() {
		return tenLoaiXe;
	}

	public void setTenLoaiXe(String tenLoaiXe) {
		this.tenLoaiXe = tenLoaiXe;
	}

	public String getMoTaXe() {
		return moTaXe;
	}

	public void setMoTaXe(String moTaXe) {
		this.moTaXe = moTaXe;
	}

	public String getPhienBan() {
		return phienBan;
	}

	public void setPhienBan(String phienBan) {
		this.phienBan = phienBan;
	}

	public int getGiaNiemYet() {
		return giaNiemYet;
	}

	public void setGiaNiemYet(int giaNiemYet) {
		this.giaNiemYet = giaNiemYet;
	}

	public int getGiaGiam() {
		return giaGiam;
	}

	public void setGiaGiam(int giaGiam) {
		this.giaGiam = giaGiam;
	}

	public List<Xe> getDsXe() {
		return dsXe;
	}

	public void setDsXe(ArrayList<Xe> dsXe) {
		this.dsXe = dsXe;
	}
	
	
	
}
