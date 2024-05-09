package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.NhaPhanPhoi;

public class NhaPhanPhoi_DAO {

	// them nha phan phoi
	public boolean themNhaPhanPhoi(NhaPhanPhoi nhaPhanPhoi) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into NhaPhanPhoi values (?, ?, ?, ?, ?)");
			ps.setString(1, nhaPhanPhoi.getMa());
			ps.setString(2, nhaPhanPhoi.getTenNhaPhanPhoi());
			ps.setString(3, nhaPhanPhoi.getDiaChi());
			ps.setInt(4, nhaPhanPhoi.getSdt());
			ps.setString(5, nhaPhanPhoi.getEmail());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	// xoa nha phan phoi theo ma
	public boolean xoaNhaPhanPhoiTheoMa(String ma) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		PreparedStatement ps = null;
		try {
			Connection con = ConnectDB.getConnection();
			ps = con.prepareStatement("delete from NhaPhanPhoi where maNhaPhanPhoi = '" + ma + "'");
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ps.close();
		return false;

	}

	// sua nha phan phoi theo ma
	public boolean suaNhaPhanPhoi(NhaPhanPhoi nhaPhanPhoi, String maNhaPhanPhoi) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update NhaPhanPhoi set tenNhaPhanPhoi = ?, diaChi = ?, soDienThoai = ?, email = ? where maNhaPhanPhoi = '"
							+ maNhaPhanPhoi + "'");
			ps.setString(1, nhaPhanPhoi.getTenNhaPhanPhoi());
			ps.setString(2, nhaPhanPhoi.getDiaChi());
			ps.setInt(3, nhaPhanPhoi.getSdt());
			ps.setString(4, nhaPhanPhoi.getEmail());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;
	}

	// get NhaPhanPhoi theo ma
	public NhaPhanPhoi getNhaPhanPhoiTheoMa(String ma) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		NhaPhanPhoi nhaPhanPhoi = new NhaPhanPhoi();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from NhaPhanPhoi where maNhaPhanPhoi = '" + ma + "'");
			while (resultSet.next()) {
				nhaPhanPhoi.setMa(ma);
				nhaPhanPhoi.setTenNhaPhanPhoi(resultSet.getString(2));
				nhaPhanPhoi.setDiaChi(resultSet.getString(3));
				nhaPhanPhoi.setSdt(resultSet.getInt(4));
				nhaPhanPhoi.setEmail(resultSet.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return nhaPhanPhoi;
	}
	
	// get NhaPhanPhoi theo so dien thoai
		public NhaPhanPhoi getNhaPhanPhoiTheoSoDienThoai(int soDienThoai) throws SQLException {
			ConnectDB.getInstance();
			ConnectDB.connect();
			Connection con = ConnectDB.getConnection();
			NhaPhanPhoi nhaPhanPhoi = new NhaPhanPhoi();
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement
						.executeQuery("select * from NhaPhanPhoi where soDienThoai = '" + soDienThoai + "'");
				while (resultSet.next()) {
					nhaPhanPhoi.setMa(resultSet.getString(1));
					nhaPhanPhoi.setTenNhaPhanPhoi(resultSet.getString(2));
					nhaPhanPhoi.setDiaChi(resultSet.getString(3));
					nhaPhanPhoi.setSdt(resultSet.getInt(4));
					nhaPhanPhoi.setEmail(resultSet.getString(5));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return nhaPhanPhoi;
		}

	// get all NhaPhanPhoi
	public List<NhaPhanPhoi> getAllNhaPhanPhoi() throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		List<NhaPhanPhoi> dsNhaPhanPhois = null;
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from NhaPhanPhoi");
			dsNhaPhanPhois = new ArrayList<NhaPhanPhoi>();
			while (resultSet.next()) {
				dsNhaPhanPhois.add(new NhaPhanPhoi(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dsNhaPhanPhois;
	}
	
	// get all ten nha phan phoi
		public List<NhaPhanPhoi> getNhaPhanPhoiTheoTen(String tenNhaPhanPhoi) throws SQLException {
			ConnectDB.getInstance();
			ConnectDB.connect();
			Connection con = ConnectDB.getConnection();
			List<NhaPhanPhoi> dsNhaPhanPhois = null;
			try {
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from NhaPhanPhoi where tenNhaPhanPhoi = '"+ tenNhaPhanPhoi +"'");
				dsNhaPhanPhois = new ArrayList<NhaPhanPhoi>();
				while (resultSet.next()) {
					dsNhaPhanPhois.add(new NhaPhanPhoi(resultSet.getString(1), resultSet.getString(2),
							resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return dsNhaPhanPhois;
		}
}
