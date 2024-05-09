package dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.ChiTietHoaDon;
import entity.ThongTinXe;

public class ChiTietHoaDon_DAO {
	// trả về chi tiết hóa đơn
	public List<ChiTietHoaDon> getAllChiTietHoaDon() {
		ConnectDB.getInstance();
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		Connection connection = ConnectDB.getConnection();
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		Statement statement;
		try {
			String sqlString = "select * from ChiTietHoaDon";
			statement = connection.createStatement();
			ResultSet rsResultSet = statement.executeQuery(sqlString);
			while (rsResultSet.next()) {
				thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(rsResultSet.getString(2));
				dsChiTietHoaDon.add(new ChiTietHoaDon(rsResultSet.getString(1), thongTinXe,
						Integer.valueOf(rsResultSet.getString(3)), Integer.valueOf(rsResultSet.getString(4))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return dsChiTietHoaDon;
	}

	// thêm chi tiết hóa đơn
	public void themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement stmPreparedStatement = null;
		try {
			stmPreparedStatement = connection.prepareStatement("insert into ChiTietHoaDon values(?,?,?,?,?)");
			stmPreparedStatement.setString(1, chiTietHoaDon.getMa());
			stmPreparedStatement.setString(2, chiTietHoaDon.getMaLoaiXe());
			stmPreparedStatement.setInt(3, chiTietHoaDon.getSoLuong());
			stmPreparedStatement.setInt(4, chiTietHoaDon.getDonGia());
			stmPreparedStatement.setInt(5, chiTietHoaDon.getThanhTien());
			stmPreparedStatement.executeUpdate();
			stmPreparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	// xoa chi tiet hoa don
	public void xoaChiHoaDon(String id) {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement stmPreparedStatement = null;
		String sqlString = "delete from ChiTietHoaDon where maHoaDon = ?";
		try {
			stmPreparedStatement = connection.prepareStatement(sqlString);
			stmPreparedStatement.setString(1, id);
			stmPreparedStatement.executeUpdate();
			stmPreparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// xoa chi tiet hoa don
	public void xoaChiHoaDon(String id, String maXe, String sl) {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		PreparedStatement stmPreparedStatement = null;
		String sqlString = "delete from ChiTietHoaDon where maHoaDon = ? and maLoaiXe = ? and soLuong = ?";
		try {
			stmPreparedStatement = connection.prepareStatement(sqlString);
			stmPreparedStatement.setString(1, id);
			stmPreparedStatement.setString(2, maXe);
			stmPreparedStatement.setString(3, sl);
			stmPreparedStatement.executeUpdate();
			stmPreparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// get chi tiết hóa đơn theo mã hóa đơn
	public List<ChiTietHoaDon> getChiTietHoaDonTheoMa(String maString) throws SQLException {
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		String sqlString = "SELECT * FROM [dbo].[ChiTietHoaDon] where maHoaDon = '" + maString + "'";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		while (resultSet.next()) {
			thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(resultSet.getString(2));
			dsChiTietHoaDon.add(new ChiTietHoaDon(resultSet.getString(1), thongTinXe,
					Integer.valueOf(resultSet.getString(3)), Integer.valueOf(resultSet.getString(4))));
		}
		return dsChiTietHoaDon;
	}

	// get theo mã loại xe
	public List<ChiTietHoaDon> getChiTietHoaDonTheoMaLoaiXe(String maString) throws SQLException {
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		String sqlString = "SELECT * FROM [dbo].[ChiTietHoaDon] where maLoaiXe = '" + maString + "'";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		while (resultSet.next()) {
			thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(resultSet.getString(2));
			dsChiTietHoaDon.add(new ChiTietHoaDon(resultSet.getString(1), thongTinXe,
					Integer.valueOf(resultSet.getString(3)), Integer.valueOf(resultSet.getString(4))));
		}
		return dsChiTietHoaDon;
	}

	// get theo số lương
	public List<ChiTietHoaDon> getChiTietHoaDonTheoSoLuong(String maString) throws SQLException {
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		String sqlString = "SELECT * FROM [dbo].[ChiTietHoaDon] where soLuong = '" + maString + "'";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		while (resultSet.next()) {
			thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(resultSet.getString(2));
			dsChiTietHoaDon.add(new ChiTietHoaDon(resultSet.getString(1), thongTinXe,
					Integer.valueOf(resultSet.getString(3)), Integer.valueOf(resultSet.getString(4))));
		}
		return dsChiTietHoaDon;
	}

	// get theo don gia
	public List<ChiTietHoaDon> getChiTietHoaDonTheoDonGia(String maString) throws SQLException {
		List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		String sqlString = "SELECT * FROM [dbo].[ChiTietHoaDon] where donGia = '" + maString + "'";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		while (resultSet.next()) {
			thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(resultSet.getString(2));
			dsChiTietHoaDon.add(new ChiTietHoaDon(resultSet.getString(1), thongTinXe,
					Integer.valueOf(resultSet.getString(3)), Integer.valueOf(resultSet.getString(4))));
		}
		return dsChiTietHoaDon;
	}

	// get tong so luong
	public int getTongSoLuong(String maString) throws SQLException {
		ConnectDB.getInstance();
		Connection connection = ConnectDB.getConnection();
		String sqlString = "SELECT SUM(soLuong) AS tongSl FROM ChiTietHoaDon WHERE maHoaDon = '"+maString+"' ";

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sqlString);
		ThongTinXe_DAO thongTinXe_DAO = new ThongTinXe_DAO();
		ThongTinXe thongTinXe = new ThongTinXe();
		int i = 0;
		while (resultSet.next()) {
			i = resultSet.getInt(1);
		}
		return i;
	}

}
