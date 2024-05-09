package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.KhachHang;
import entity.Xe;

public class Xe_DAO {

	// them xe
	public boolean themXe(Xe xe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into Xe values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, xe.getMa());
			ps.setString(2, xe.getSoMay());
			ps.setString(3, xe.getSoKhung());
			ps.setDate(4, xe.getNgayNhap());
			ps.setString(5, xe.getMaNPP());
			ps.setString(6, xe.getMaLoaiXe());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	// xoa xe theo ma
	public boolean xoaXeTheoMa(String ma) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from Xe where maXe = '" + ma + "'");
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ps.close();
		return false;

	}

	// xoa xe theo ma loai xe theo số lượng
	public boolean xoaXeTheoMaLoaiXeSL(String ma, int sl) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete top ("+sl+") from Xe where maLoaiXe = '"+ma+"'");
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ps.close();
		return false;

	}

	// sua thong tin xe
	public boolean suaThongTinXe(Xe xe, String ma) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update Xe set soMay = ?, soKhung = ?, ngayNhapXe = ?, maNhaPhanPhoi = ?, maLoaiXe = ? where maXe = '"
							+ ma + "'");
			ps.setString(1, xe.getSoMay());
			ps.setString(2, xe.getSoKhung());
			ps.setDate(3, xe.getNgayNhap());
			ps.setString(4, xe.getMaNPP());
			ps.setString(5, xe.getMaLoaiXe());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		con.close();
		return false;
	}

	// get xe theo ma
	public Xe getXeTheoMa(String ma) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from Xe where maXe = '" + ma + "'");
		Xe xe = new Xe();
		while (resultSet.next()) {
			xe.setMa(ma);
			xe.setSoMay(resultSet.getString(2));
			xe.setSoKhung(resultSet.getString(3));
			xe.setNgayNhap(resultSet.getDate(4));
			xe.setMaNPP(resultSet.getString(5));
			xe.setMaLoaiXe(resultSet.getString(6));
		}
		return xe;
	}

	// get danh sach xe theo nha phan phoi, get tat ca xe theo nha phan phoi
	public List<Xe> getXeTheoMaNhaPhanPhoi(String maNhaPhanPhoi) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("select * from Xe where maNhaPhanPhoi = '" + maNhaPhanPhoi + "'");
			ResultSet resultSet = ps.executeQuery();
			List<Xe> dsXe = new ArrayList<Xe>();
			while (resultSet.next()) {
				dsXe.add(new Xe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6)));
			}
			return dsXe;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Xe>();
	}

	// get danh sach xe theo ma loai xe, get tat ca xe theo ma loai xe
	public List<Xe> getXeTheoMaLoaiXe(String maLoaiXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Xe where maLoaiXe = '" + maLoaiXe + "'");
			ResultSet resultSet = ps.executeQuery();
			List<Xe> dsXe = new ArrayList<Xe>();
			while (resultSet.next()) {
				dsXe.add(new Xe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6)));
			}
			return dsXe;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Xe>();
	}

	// get danh sach xe theo ngay nhap, get tat ca xe theo ngay nhap
	public List<Xe> getXeTheoNgayNhap(Date ngayNhap) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Xe where ngayNhapXe = '" + ngayNhap + "'");
			ResultSet resultSet = ps.executeQuery();
			List<Xe> dsXe = new ArrayList<Xe>();
			while (resultSet.next()) {
				dsXe.add(new Xe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6)));
			}
			return dsXe;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Xe>();
	}

	// get danh sach xe, get tat ca xe
	public List<Xe> getAllXe() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from Xe");
			ResultSet resultSet = ps.executeQuery();
			List<Xe> dsXe = new ArrayList<Xe>();
			while (resultSet.next()) {
				dsXe.add(new Xe(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6)));
			}
			return dsXe;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Xe>();
	}
}
