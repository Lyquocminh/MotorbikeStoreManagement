package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	// get all khach hang
	public List<KhachHang> getAllKhachHang() {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from KhachHang");
			ResultSet rsResultSet = ps.executeQuery();
			while (rsResultSet.next()) {
				dsKhachHang.add(new KhachHang(rsResultSet.getString(1), rsResultSet.getString(2),
						rsResultSet.getString(3), rsResultSet.getString(4), Integer.valueOf(rsResultSet.getString(5)),
						rsResultSet.getString(6)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}
	
	// get khach hang theo ten
	public List<KhachHang> getKhachHangTheoTen(String tenKhachHang) {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
		try {
			String sqlString = "select * from KhachHang where tenKhachHang = N'"+ tenKhachHang +"'";
			Statement statement = connection.createStatement();
			ResultSet rsResultSet = statement.executeQuery(sqlString);
			while (rsResultSet.next()) {
				dsKhachHang.add(new KhachHang(rsResultSet.getString(1), rsResultSet.getString(2),
						rsResultSet.getString(3), rsResultSet.getString(4), Integer.valueOf(rsResultSet.getString(5)),
						rsResultSet.getString(6)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	// them khach hang
	public boolean themKhachHang(KhachHang khachHang) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into KhachHang values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, khachHang.getMa());
			ps.setString(2, khachHang.getHo());
			ps.setString(3, khachHang.getTen());
			ps.setString(4, khachHang.getDiaChi());
			ps.setInt(5, khachHang.getSdt());
			ps.setString(6, khachHang.getEmail());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;

	}

	// xoa khach hang theo ma
	public boolean xoaKhachHangTheoMa(String maKH) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from KhachHang where maKhachHang = '" + maKH + "'");
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ps.close();
		return false;
	}

	// sua thong tin khach hang
	public boolean suaThongTinKhachHang(KhachHang kh, String maKhachHang) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update KhachHang set hoKhachHang = ?, tenKhachHang = ?, diaChi = ?, soDienThoai = ?, email = ? where maKhachHang = ?");
			ps.setString(1, kh.getHo());
			ps.setString(2, kh.getTen());
			ps.setString(3, kh.getDiaChi());
			ps.setInt(4, kh.getSdt());
			ps.setString(5, kh.getEmail());
			ps.setString(6, kh.getMa());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	// get KhachHang theo ma
	public KhachHang getKhachHangTheoMa(String ma) throws SQLException {
		ConnectDB.getInstance();
		KhachHang kh = new KhachHang();
		Connection con = ConnectDB.getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from KhachHang where maKhachHang = '" + ma + "'");
			while (resultSet.next()) {
				kh.setMa(ma);
				kh.setHo(resultSet.getString(2));
				kh.setTen(resultSet.getString(3));
				kh.setDiaChi(resultSet.getString(4));
				kh.setSdt(resultSet.getInt(5));
				kh.setEmail(resultSet.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return kh;
	}
	
	// get KhachHang theo so dien thoai
		public KhachHang getKhachHangTheoSoDienThoai(int soDienThoai) throws SQLException {
			ConnectDB.getInstance();
			KhachHang kh = new KhachHang();
			Connection con = ConnectDB.getConnection();
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from KhachHang where soDienThoai = '" + soDienThoai + "'");
				while (resultSet.next()) {
					kh.setMa(resultSet.getString(1));
					kh.setHo(resultSet.getString(2));
					kh.setTen(resultSet.getString(3));
					kh.setDiaChi(resultSet.getString(4));
					kh.setSdt(resultSet.getInt(5));
					kh.setEmail(resultSet.getString(6));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return kh;
		}
}
