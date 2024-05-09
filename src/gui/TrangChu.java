package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;

public class TrangChu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar  menuBar;
	
	private JMenu menuTrangChu;
	private JMenu menuCuaHang;
	private JMenu menuNhanVien;
	private JMenu menuHoaDon;
	private JMenu menuXe;
	private JMenu menuKhachHang;
	private JMenu menuThongKe;
	private JMenu menuDangXuat;
	
	private JMenuItem menuitemTrangChu;
	private JMenuItem menuitemNhanVienHanhChinh;
	private JMenuItem menuitemHoaDon;
	private JMenuItem menuitemThongTinXe;
	private JMenuItem menuitemCuaHang;
	private JMenuItem menuitemNhaPhanPhoi;
	private JMenuItem menuitemQuanLyXe;
	private JMenuItem menuitemKhachHang;
	private JMenuItem menuitemNhanVienKyThuat;
	private JMenuItem menuitemThongKe;
	private JMenuItem menuitemDangXuat;
	
	private JPanel contentPane;

	String trangChu = "Trang chủ";
	String quanLyCuaHang = "Quản Lý Cửa Hàng";
	String nhanVienKyThuat = "Nhân Viên Kỹ Thuật";
	String nhanVienHanhChanh = "Nhân Viên Hành Chánh";
	String hoaDon = "Hóa Đơn";
	String thongTinXe = "Thông Tin Xe";
	String quanLyXe = "Quản Lý Xe";
	String nhaPhanPhoi = "Nhà Phân Phối";
	String khachHang = "Khách Hàng";
	String thongKe = "Thống kê";

	/**
	 * Create the frame.
	 */
	public TrangChu() {
		setTitle(trangChu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(391, 21, 1210, 780);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuTrangChu = new JMenu(trangChu);
		menuTrangChu.setIcon(new ImageIcon(TrangChu.class.getResource("/image/menuHome.png")));
		menuTrangChu.setForeground(new Color(0, 163, 163));
		menuTrangChu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuTrangChu);

		menuitemTrangChu = new JMenuItem("Trang chủ");
		menuitemTrangChu.setForeground(new Color(0, 163, 163));
		menuitemTrangChu.setIcon(new ImageIcon(TrangChu.class.getResource("/image/home.png")));
		menuitemTrangChu.setPreferredSize(new Dimension(130, menuitemTrangChu.getPreferredSize().height));
		menuTrangChu.add(menuitemTrangChu);

		menuCuaHang = new JMenu("Cửa hàng");
		menuCuaHang.setIcon(new ImageIcon(TrangChu.class.getResource("/image/MotoShop.png")));
		menuCuaHang.setForeground(new Color(0, 163, 163));
		menuCuaHang.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuCuaHang);

		menuitemCuaHang = new JMenuItem("Quản lý cửa hàng");
		menuitemCuaHang.setForeground(new Color(0, 163, 163));
		menuitemCuaHang.setIcon(new ImageIcon(TrangChu.class.getResource("/image/store.png")));
		menuitemCuaHang.setPreferredSize(new Dimension(124, menuitemTrangChu.getPreferredSize().height));
		menuCuaHang.add(menuitemCuaHang);

		menuNhanVien = new JMenu("Nhân viên");
		menuNhanVien.setIcon(new ImageIcon(TrangChu.class.getResource("/image/menuStaff.png")));
		menuNhanVien.setForeground(new Color(0, 163, 163));
		menuNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuNhanVien);

		menuitemNhanVienKyThuat = new JMenuItem("Nhân viên kỹ thuật");
		menuitemNhanVienKyThuat.setForeground(new Color(0, 163, 163));
		menuitemNhanVienKyThuat.setIcon(new ImageIcon(TrangChu.class.getResource("/image/technical-support.png")));
		menuNhanVien.add(menuitemNhanVienKyThuat);

		menuitemNhanVienHanhChinh = new JMenuItem("Nhân viên hành chính");
		menuitemNhanVienHanhChinh.setForeground(new Color(0, 163, 163));
		menuitemNhanVienHanhChinh.setIcon(new ImageIcon(TrangChu.class.getResource("/image/team-management.png")));
		menuNhanVien.add(menuitemNhanVienHanhChinh);

		menuHoaDon = new JMenu("Hóa đơn");
		menuHoaDon.setIcon(new ImageIcon(TrangChu.class.getResource("/image/invoice.png")));
		menuHoaDon.setForeground(new Color(0, 163, 163));
		menuHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuHoaDon);

		menuitemHoaDon = new JMenuItem("Hóa đơn");
		menuitemHoaDon.setForeground(new Color(0, 163, 163));
		menuitemHoaDon.setIcon(new ImageIcon(TrangChu.class.getResource("/image/bill.png")));
		menuitemHoaDon.setPreferredSize(new Dimension(118, menuitemTrangChu.getPreferredSize().height));
		menuHoaDon.add(menuitemHoaDon);

		menuXe = new JMenu("Xe");
		menuXe.setIcon(new ImageIcon(TrangChu.class.getResource("/image/motorbike.png")));
		menuXe.setForeground(new Color(0, 163, 163));
		menuXe.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuXe);

		menuitemThongTinXe = new JMenuItem("Thông tin xe");
		menuitemThongTinXe.setForeground(new Color(0, 163, 163));
		menuitemThongTinXe.setIcon(new ImageIcon(TrangChu.class.getResource("/image/information-button.png")));
		menuXe.add(menuitemThongTinXe);

		menuitemQuanLyXe = new JMenuItem("Quản lý xe");
		menuitemQuanLyXe.setForeground(new Color(0, 163, 163));
		menuitemQuanLyXe.setIcon(new ImageIcon(TrangChu.class.getResource("/image/project-management.png")));
		menuXe.add(menuitemQuanLyXe);

		menuitemNhaPhanPhoi = new JMenuItem("Nhà phân phối");
		menuitemNhaPhanPhoi.setForeground(new Color(0, 163, 163));
		menuitemNhaPhanPhoi.setIcon(new ImageIcon(TrangChu.class.getResource("/image/distribution.png")));
		menuXe.add(menuitemNhaPhanPhoi);

		menuKhachHang = new JMenu("Khách hàng");
		menuKhachHang.setIcon(new ImageIcon(TrangChu.class.getResource("/image/menuCustomer.png")));
		menuKhachHang.setForeground(new Color(0, 163, 163));
		menuKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuKhachHang);

		menuitemKhachHang = new JMenuItem("Khách hàng");
		menuitemKhachHang.setForeground(new Color(0, 163, 163));
		menuitemKhachHang.setIcon(new ImageIcon(TrangChu.class.getResource("/image/customer.png")));
		menuitemKhachHang.setPreferredSize(new Dimension(144, menuitemTrangChu.getPreferredSize().height));
		menuKhachHang.add(menuitemKhachHang);

		
		menuThongKe = new JMenu("Thống kê");
		menuThongKe.setIcon(new ImageIcon(TrangChu.class.getResource("/image/statistics.png")));
		menuThongKe.setForeground(new Color(0, 163, 163));
		menuThongKe.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuThongKe);
		
		menuitemThongKe = new JMenuItem("Thống kê ");
		menuitemThongKe.setForeground(new Color(0, 163, 163));
		menuitemThongKe.setIcon(new ImageIcon(TrangChu.class.getResource("/image/stats.png")));
		menuitemThongKe.setPreferredSize(new Dimension(108, menuitemTrangChu.getPreferredSize().height));
		menuThongKe.add(menuitemThongKe);
		
		menuDangXuat = new JMenu("Đăng xuất");
		menuDangXuat.setIcon(new ImageIcon(TrangChu.class.getResource("/image/logout.png")));
		menuDangXuat.setForeground(new Color(0, 163, 163));
		menuDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(menuDangXuat);
		
		menuitemDangXuat = new JMenuItem("Đăng xuất");
		menuitemDangXuat.setForeground(new Color(0, 163, 163));
		menuitemDangXuat.setIcon(new ImageIcon(TrangChu.class.getResource("/image/menuitemlogout.png")));
		menuitemDangXuat.setPreferredSize(new Dimension(117, menuitemTrangChu.getPreferredSize().height));
		menuDangXuat.add(menuitemDangXuat);


		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(TrangChu.class.getResource("/image/taka (1).jpg")));
		// lblNewLabel.setIcon(new

		// ImageIcon(TrangChu.class.getResource("/image/TrangChu.jpg")));

		lblNewLabel.setBounds(10, 92, 1184, 600);
//		lblNewLabel.setBounds(54, 123, 830, 476);
		contentPane.add(lblNewLabel);
						
								JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ BÁN XE MÁY", SwingConstants.CENTER);
								lblNewLabel_1.setBounds(0, 0, 1194, 84);
								contentPane.add(lblNewLabel_1);
								lblNewLabel_1.setBackground(new Color(255, 255, 255));
								lblNewLabel_1.setForeground(Color.BLACK);
								lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 24));
		
		menuitemTrangChu.addActionListener(this);
		menuitemNhanVienHanhChinh.addActionListener(this);
		menuitemCuaHang.addActionListener(this);
		menuitemHoaDon.addActionListener(this);
		menuitemKhachHang.addActionListener(this);
		menuitemNhanVienKyThuat.addActionListener(this);
		menuitemNhaPhanPhoi.addActionListener(this);
		menuitemQuanLyXe.addActionListener(this);
		menuitemThongTinXe.addActionListener(this);
		menuitemThongKe.addActionListener(this);
		menuitemDangXuat.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(menuitemNhanVienHanhChinh)) {
			try {
				setTitle(nhanVienHanhChanh);
				showMenu(new NhanVienHanhChinh_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("LOI");
			}
		} else if (o.equals(menuitemCuaHang)) {
			setTitle(quanLyCuaHang);
			try {
				showMenu(new CuaHang_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemKhachHang)) {
			setTitle(khachHang);
			try {
				showMenu(new KhachHang_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemNhanVienKyThuat)) {
			try {
				setTitle(nhanVienKyThuat);
				showMenu(new NhanVienKyThuat_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemHoaDon)) {
			try {
				setTitle(hoaDon);
				showMenu(new HoaDon_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemNhaPhanPhoi)) {
			setTitle(nhaPhanPhoi);
			try {
				showMenu(new NhaPhanPhoi_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemQuanLyXe)) {
			try {
				setTitle(quanLyXe);
				showMenu(new Xe_GUI());
			} catch (SQLException e1) {
				// TODO AutoX-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemThongTinXe)) {
			try {
				setTitle(thongTinXe);
				showMenu(new ThongTinXe_GUI());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(menuitemThongKe)) {
			setTitle(thongKe);
			showMenu(new ThongKe_GUI());
		} else if (o.equals(menuitemTrangChu)) {
			setTitle(trangChu);
			showMenu(new TrangChu_GUI());
		} else if (o.equals(menuitemDangXuat)) {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn đăng xuất?", "Đăng xuất?", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				logout();
			}
		}

	}
	
	public void logout() {
		try {
			DangNhap_GUI dangNhap_GUI = new DangNhap_GUI();
			dangNhap_GUI.setVisible(true);
			this.setVisible(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void showMenu(JPanel cn) {
		setContentPane(cn);
		validate();
		invalidate();
	}
}