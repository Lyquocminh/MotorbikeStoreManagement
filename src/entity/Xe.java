package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Xe {
	private String ma, soMay, soKhung, maLoaiXe, maNPP;
	private Date ngayNhap;
	public List<XeTrongKho> dsKho;

	public Xe() {
	}

	public List<XeTrongKho> getDsKho() {
		return dsKho;
	}

	public void setDsKho(List<XeTrongKho> dsKho) {
		this.dsKho = dsKho;
	}

	public Xe(String ma) {
		super();
		this.ma = ma;
	}

	public Xe(String ma, String soMay, String soKhung, Date ngayNhap, String maNPP, String maLoaiXe) {
		super();
		this.ma = ma;
		this.soMay = soMay;
		this.soKhung = soKhung;
		this.ngayNhap = ngayNhap;
		this.maNPP = maNPP;
		this.maLoaiXe = maLoaiXe;
		dsKho = new ArrayList<XeTrongKho>();
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getSoMay() {
		return soMay;
	}

	public void setSoMay(String soMay) {
		this.soMay = soMay;
	}

	public String getSoKhung() {
		return soKhung;
	}

	public void setSoKhung(String soKhung) {
		this.soKhung = soKhung;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public String getMa() {
		return ma;
	}

	public String getMaLoaiXe() {
		return maLoaiXe;
	}

	public void setMaLoaiXe(String maLoaiXe) {
		this.maLoaiXe = maLoaiXe;
	}

	public String getMaNPP() {
		return maNPP;
	}

	public void setMaNPP(String maNPP) {
		this.maNPP = maNPP;
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
		Xe other = (Xe) obj;
		return Objects.equals(ma, other.ma);
	}

}
