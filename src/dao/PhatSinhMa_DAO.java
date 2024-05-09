package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connect.ConnectDB;

public class PhatSinhMa_DAO {

	// ma CuaHang
	public String getMaCuaHang() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('CH', RIGHT(CONCAT('000',ISNULL(right(max(maCuaHang),3),0) + 1),3)) from [dbo].[CuaHang] where maCuaHang like 'CH%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Nhan Vien Ky Thuat
	public String getMaNhanVienKyThuat() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('NVKT', RIGHT(CONCAT('000',ISNULL(right(max(maNhanVien),3),0) + 1),3)) from [dbo].[NhanVienKiThuat] where maNhanVien like 'NVKT%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Nhan Vien Hanh Chanh
	public String getMaNhanVienHanhChanh() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('NVHC', RIGHT(CONCAT('000',ISNULL(right(max(maNhanVien),3),0) + 1),3)) from [dbo].[NhanVienHanhChanh] where maNhanVien like 'NVHC%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Hoa Don
	public String getMaHoaDon() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('HD', RIGHT(CONCAT('000',ISNULL(right(max(maHoaDon),3),0) + 1),3)) from [dbo].[HoaDon] where maHoaDon like 'HD%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Loai Xe
	public String getMaLoaiXe() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('LX', RIGHT(CONCAT('000',ISNULL(right(max(maLoaiXe),3),0) + 1),3)) from [dbo].[ThongTinXe] where maLoaiXe like 'LX%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Xe
	public String getMaXe() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('X', RIGHT(CONCAT('000',ISNULL(right(max(maXe),3),0) + 1),3)) from [dbo].[Xe] where maXe like 'X%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Nha Phan Phoi
	public String getMaNhaPhanPhoi() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('NPP', RIGHT(CONCAT('000',ISNULL(right(max(maNhaPhanPhoi),3),0) + 1),3)) from [dbo].[NhaPhanPhoi] where maNhaPhanPhoi like 'NPP%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
	
	// ma Khach Hang
	public String getMaKhachHang() throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("select CONCAT('KH', RIGHT(CONCAT('000',ISNULL(right(max(maKhachHang),3),0) + 1),3)) from [dbo].[KhachHang] where maKhachHang like 'KH%'");
		String maCuaHang = "";
		while (resultSet.next()) {
			maCuaHang = resultSet.getString(1);
		}
		return maCuaHang;
	}
}
