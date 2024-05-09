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
import entity.CuaHang;
import entity.XeTrongKho;

public class XeTrongKho_DAO {

	public List<XeTrongKho> getXeTrongKhoTheoMaCuaHang(String maCuaHang) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from XeTrongKho where maCuaHang = '" + maCuaHang + "'");
		List<XeTrongKho> dsXeTrongKho = new ArrayList<XeTrongKho>();
		while (resultSet.next()) {
			dsXeTrongKho.add(new XeTrongKho(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
		}
		return dsXeTrongKho;
	}

	// get xe trong kho theo ma cua hang va ma xe
	public XeTrongKho getXeTrongKhoTheoMaCuaHangVaMaXe(String maCuaHang, String maXe) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"select * from XeTrongKho where maCuaHang = '" + maCuaHang + "' and maLoaiXe = '" + maXe + "'");
		XeTrongKho xeTrongKho = new XeTrongKho();
		while (resultSet.next()) {
			xeTrongKho.setMaCuaHang(maCuaHang);
			xeTrongKho.setMaXe(maXe);
			xeTrongKho.setSoLuong(resultSet.getInt(3));
		}
		return xeTrongKho;
	}

	// them xe trong kho
	public boolean themXeTrongKho(XeTrongKho xeTrongKho) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps;
		try {
			ConnectDB.connect();
			ps = con.prepareStatement("insert into XeTrongKho values (?, ?, ?)");
			ps.setString(1, xeTrongKho.getMaCuaHang());
			ps.setString(2, xeTrongKho.getMaXe());
			ps.setInt(3, xeTrongKho.getSoLuong());
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// xoa xe trong kho
	public boolean xoaXeTrongKhoTheoMaCuaHang(String mach) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("delete from XeTrongKho where maCuaHang = '" + mach + "'");
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Lỗi xóa '" + mach + "'");
			e.printStackTrace();
		}
		return false;

	}

	// xoa xe trong kho
	public boolean xoaXeTrongKhoTheoMaChMaLx(String mch, String maLoaiXe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"delete from XeTrongKho where maLoaiXe = '" + maLoaiXe + "'and maCuaHang = '" + maLoaiXe + "'");
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Lỗi xóa '" + maLoaiXe + "' va '" + maLoaiXe + "'");
			e.printStackTrace();
		}
		return false;

	}

	// get xe trong kho theo ma xe
	public List<XeTrongKho> getXeTrongKhoTheoMaLx(String maxe) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from XeTrongKho where maLoaiXe = '" + maxe + "'");
		List<XeTrongKho> dsXeTrongKho = new ArrayList<XeTrongKho>();
		while (resultSet.next()) {
			dsXeTrongKho.add(new XeTrongKho(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
		}
		return dsXeTrongKho;
	}

	// get xe trong kho theo so luong
	public List<XeTrongKho> getXeTrongKhoTheoSoLuong(String sl) throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from XeTrongKho where soLuong = '" + sl + "'");
		List<XeTrongKho> dsXeTrongKho = new ArrayList<XeTrongKho>();
		while (resultSet.next()) {
			dsXeTrongKho.add(new XeTrongKho(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
		}
		return dsXeTrongKho;
	}

	// get danh sach xe trong kho, get tat ca xe trong kho
	public List<XeTrongKho> getAllXeTrongKho() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from XeTrongKho");
		List<XeTrongKho> dsXe = new ArrayList<XeTrongKho>();
		while (resultSet.next()) {
			dsXe.add(new XeTrongKho(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));

		}

		return dsXe;
	}

}
