package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connect.ConnectDB;
import entity.ThongTinXe;

public class ThongTinXe_DAO {

	// them thong tin xe
	public boolean themThongTinXe(ThongTinXe thongTinXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {

			PreparedStatement ps = con.prepareStatement("insert into ThongTinXe values (?, ?, ?, ?, ?, ?)");
			ps.setString(1, thongTinXe.getMaLoaiXe());
			ps.setString(2, thongTinXe.getTenLoaiXe());
			ps.setInt(3, thongTinXe.getGiaNiemYet());
			ps.setInt(4, thongTinXe.getGiaGiam());
			ps.setString(5, thongTinXe.getMoTaXe());
			ps.setString(6, thongTinXe.getPhienBan());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();

		return false;
	}

	// xoa thong tin xe theo ma
	public boolean xoaThongTinXeTheoMaLoaiXe(String maLoaiXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from ThongTinXe where maLoaiXe = '" + maLoaiXe + "'");
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps.close();
		return false;

	}

	// sua thong tin xe
	public boolean suaThongTinXe(ThongTinXe thongTinXe, String maLoaiXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update ThongTinXe set tenLoaiXe = ?, giaNiemYet = ?, giaGiam = ?, moTaXe = ?, phienBan = ? where maLoaiXe = ?");
			ps.setString(1, thongTinXe.getTenLoaiXe());
			ps.setInt(2, thongTinXe.getGiaNiemYet());
			ps.setInt(3, thongTinXe.getGiaGiam());
			ps.setString(4, thongTinXe.getMoTaXe());
			ps.setString(5, thongTinXe.getPhienBan());
			ps.setString(6, maLoaiXe);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		con.close();
		return false;

	}

	// get thong tin xe theo ma
	public ThongTinXe getThongTinXeTheoMa(String maLoaiXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ThongTinXe thongTinXe = new ThongTinXe();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from ThongTinXe where maLoaiXe = '" + maLoaiXe + "'");

			while (resultSet.next()) {

				thongTinXe.setMaLoaiXe(maLoaiXe);
				thongTinXe.setTenLoaiXe(resultSet.getString(2));
				thongTinXe.setGiaNiemYet(resultSet.getInt(3));
				thongTinXe.setGiaGiam(resultSet.getInt(4));
				thongTinXe.setMoTaXe(resultSet.getString(5));
				thongTinXe.setPhienBan(resultSet.getString(6));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongTinXe;
	}

	// get thong tin xe theo ten
	public ThongTinXe getThongTinXeTheoTen(String tenLoaiXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ThongTinXe thongTinXe = new ThongTinXe();
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from ThongTinXe where tenLoaiXe = '" + tenLoaiXe + "'");

			while (resultSet.next()) {

				thongTinXe.setMaLoaiXe(resultSet.getString(1));
				thongTinXe.setTenLoaiXe(resultSet.getString(2));
				thongTinXe.setGiaNiemYet(resultSet.getInt(3));
				thongTinXe.setGiaGiam(resultSet.getInt(4));
				thongTinXe.setMoTaXe(resultSet.getString(5));
				thongTinXe.setPhienBan(resultSet.getString(6));

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongTinXe;
	}

	// get danh sach thong tin xe
	public List<ThongTinXe> getAllThongTinXe() {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		List<ThongTinXe> dsThongTinXe = new ArrayList<ThongTinXe>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from ThongTinXe");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				dsThongTinXe.add(new ThongTinXe(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return dsThongTinXe;
	}

}
