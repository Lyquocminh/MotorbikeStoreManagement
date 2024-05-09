package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDon_DAO;
import dao.CuaHang_DAO;
import dao.HoaDon_DAO;
import dao.NhanVienHanhChinh_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongKe_GUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtSoluong;
	private JTextField txtCuaHangBanNhieuXe;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model;
	private DefaultTableModel model_1;
	private JTextField txtNhanVienBanDuocNhieuXe;
	private JTextField textXeBanNhieu;
	private JTextField textXeBanIt;
	private HoaDon_DAO hoaDon_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private NhanVienHanhChinh_DAO nhanVienHanhChinh_DAO;
	private CuaHang_DAO cuaHang_DAO;
	private JDateChooser chooserTuNgay;
	private JDateChooser chooserDenNgay;
	private Date d1;
	private Date d2;
//	private LocalDate localDate;

	/**
	 * Create the panel.
	 */
	public ThongKe_GUI() {

		// connectDB

		// khai bao DAO
		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
		hoaDon_DAO = new HoaDon_DAO();
		cuaHang_DAO = new CuaHang_DAO();
		nhanVienHanhChinh_DAO = new NhanVienHanhChinh_DAO();

		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(165, 42, 42));
		panel.setBorder(new LineBorder(Color.CYAN));
		panel.setBounds(10, 148, 802, 545);
		add(panel);
		panel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 782, 243);
		panel.add(scrollPane);

		String[] columns = { "Mã hóa đơn", "Ngày lập", "Thời gian bảo hành", "Mã khách hàng", "Mã cửa hàng",
				"Mã nhân viên" };
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(25);
		table.setToolTipText("Chọn hoá đơn để thực hiện chức năng");
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				model_1.setRowCount(0);
				loadDataChiTietHoaDonTheoMaHoaDon(chiTietHoaDon_DAO, (String) model.getValueAt(row, 0));
			}
		});
		loadDataHoaDon(hoaDon_DAO);
		// set color for header table
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(0, 163, 163));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tableHeader.setToolTipText("Danh sách thông tin hóa đơn");
		// Add a ListSelectionListener to the table
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Check if the current cell selection is not empty
				if (!e.getValueIsAdjusting()) {
					int rowIndex = table.getSelectedRow();
					if (rowIndex >= 0 && rowIndex < table.getRowCount()) {
						table.setSelectionBackground(new Color(138, 255, 255));
						table.setRowSelectionInterval(rowIndex, rowIndex);
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 263, 782, 243);
		panel.add(scrollPane_1);
		String[] columns_1 = { "Mã hóa đơn", "Mã loại xe", "Số lượng", "Đơn giá", "Thành tiền" };
		model_1 = new DefaultTableModel(columns_1, 0);
		table_1 = new JTable(model_1);
		table_1.setFont(new Font("Arial", Font.PLAIN, 16));
		table_1.setDefaultEditor(Object.class, null);
		table_1.setRowHeight(25);
		table_1.setToolTipText("Chọn chi tiết hoá đơn để thực hiện chức năng");
		loadDataChiTietHoaDon(chiTietHoaDon_DAO);
		// set color for header table
		JTableHeader tableHeader_1 = table_1.getTableHeader();
		tableHeader_1.setBackground(new Color(0, 163, 163));
		tableHeader_1.setForeground(Color.white);
		tableHeader_1.setFont(new Font("Arial", Font.BOLD, 14));
		tableHeader_1.setToolTipText("Danh sách thông tin chi tiết hóa đơn");
		// Add a ListSelectionListener to the table
		ListSelectionModel model_1 = table_1.getSelectionModel();
		model_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Check if the current cell selection is not empty
				if (!e.getValueIsAdjusting()) {
					int rowIndex = table_1.getSelectedRow();
					if (rowIndex >= 0 && rowIndex < table_1.getRowCount()) {
						table_1.setSelectionBackground(new Color(138, 255, 255));
						table_1.setRowSelectionInterval(rowIndex, rowIndex);
					}
				}
			}
		});
		scrollPane_1.setViewportView(table_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new LineBorder(Color.CYAN));
		panel_1.setBounds(812, 148, 376, 545);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Từ ngày:");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 140, 34);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Đến ngày:");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(10, 45, 140, 34);
		panel_1.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Số lượng xe bán được:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(10, 90, 176, 34);
		panel_1.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Cửa hàng bán nhiều xe nhất:");
		lblNewLabel_4_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 158, 206, 34);
		panel_1.add(lblNewLabel_4_1_1_1);

//		localDate = LocalDate.now();
//		int ngay = localDate.getDayOfMonth();
//		int thang = localDate.getMonthValue();
//		int nam = localDate.getYear();
//		Date now = new Date(nam - 1900, thang - 1, ngay);
		
		chooserTuNgay = new JDateChooser();
		chooserTuNgay.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserTuNgay.setBounds(91, 15, 183, 24);
		chooserTuNgay.setDateFormatString("dd/MM/yyyy");
		chooserTuNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
		chooserTuNgay.setFont(new Font("Arial", Font.PLAIN, 16));
		chooserTuNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserTuNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
//		chooserTuNgay.setDate(now);
		chooserTuNgay.getCalendarButton().setToolTipText("Chọn ngày nhập xe");
		panel_1.add(chooserTuNgay);

		chooserDenNgay = new JDateChooser();
		chooserDenNgay.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserDenNgay.setBounds(91, 50, 183, 24);
		chooserDenNgay.setDateFormatString("dd/MM/yyyy");
		chooserDenNgay.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
		chooserDenNgay.setFont(new Font("Arial", Font.PLAIN, 16));
		chooserDenNgay.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserDenNgay.getCalendarButton().setBackground(new Color(138, 255, 255));
		chooserDenNgay.getCalendarButton().setToolTipText("Chọn ngày cuối");
//		chooserDenNgay.setDate(now);
		panel_1.add(chooserDenNgay);

		txtSoluong = new JTextField();
		txtSoluong.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(40, 123, 292, 24);
		panel_1.add(txtSoluong);

		txtCuaHangBanNhieuXe = new JTextField();
		txtCuaHangBanNhieuXe.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCuaHangBanNhieuXe.setColumns(10);
		txtCuaHangBanNhieuXe.setBounds(40, 198, 292, 24);
		panel_1.add(txtCuaHangBanNhieuXe);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Nhân viên bán được nhiều xe nhất:");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 415, 257, 34);
		panel_1.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("Số xe bán được ít nhất:");
		lblNewLabel_4_1_1_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2.setBounds(10, 332, 267, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2);

		JLabel lblNewLabel_4_1_1_1_3 = new JLabel("Số xe bán được nhiều nhất:");
		lblNewLabel_4_1_1_1_3.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_3.setBounds(10, 244, 246, 34);
		panel_1.add(lblNewLabel_4_1_1_1_3);

		txtNhanVienBanDuocNhieuXe = new JTextField();
		txtNhanVienBanDuocNhieuXe.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNhanVienBanDuocNhieuXe.setColumns(10);
		txtNhanVienBanDuocNhieuXe.setBounds(40, 460, 292, 24);
		panel_1.add(txtNhanVienBanDuocNhieuXe);

		textXeBanNhieu = new JTextField();
		textXeBanNhieu.setFont(new Font("Arial", Font.PLAIN, 16));
		textXeBanNhieu.setColumns(10);
		textXeBanNhieu.setBounds(40, 298, 292, 24);
		panel_1.add(textXeBanNhieu);

		textXeBanIt = new JTextField();
		textXeBanIt.setFont(new Font("Arial", Font.PLAIN, 16));
		textXeBanIt.setColumns(10);
		textXeBanIt.setBounds(40, 381, 292, 24);
		panel_1.add(textXeBanIt);

		JButton btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();
				java.util.Date d1 = chooserTuNgay.getDate();
				java.util.Date d2 = chooserDenNgay.getDate();
				// Kiểm tra nếu ngày được chọn khác null
				if (d1 != null && d2 != null) {
					if (d2.before(d1)) {
						JOptionPane.showMessageDialog(null, "Phải chọn ngày kết thúc sau ngày bắt đầu");
					}
					ThongKe_GUI.this.model.setRowCount(0);
					ThongKe_GUI.this.model_1.setRowCount(0);
					try {
						for (HoaDon hoaDon : hoaDon_DAO.getHDtheoNgayBdKt(d1, d2)) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
							Object[] objects = { hoaDon.getMa(), dateFormat.format(hoaDon.getNgayLap()),
									hoaDon.getThoiGianBH(), hoaDon.getMaKH(), hoaDon.getMaCH(), hoaDon.getMaNV() };
							loadDataChiTietHoaDonTheoMaHoaDon(chiTietHoaDon_DAO, hoaDon.getMa());
							ThongKe_GUI.this.model.addRow(objects);
							int sum = 0;
							for (int i = 0; i < table_1.getRowCount(); i++) {
								sum += Integer.valueOf(table_1.getValueAt(i, 2).toString());
							}
							String getString;
							txtSoluong.setText(sum + "");
							textXeBanNhieu.setText(hoaDon_DAO.getSoLuongXeBanNhieuNhat(d1, d2));
							textXeBanIt.setText(hoaDon_DAO.getSoLuongXeBanItNhat(d1, d2));
							getString = hoaDon_DAO.getCuaHangBanNhieu(d1, d2);
							txtCuaHangBanNhieuXe.setText("Mã CH: "+
									getString + " Tên CH: "+cuaHang_DAO.getCuaHangTheoMa(getString).getTen());
							getString = hoaDon_DAO.getNhanVienBanNhieu(d1, d2);
							txtNhanVienBanDuocNhieuXe.setText("Tên NV: "+getString + " Tên NV: "
							 + nhanVienHanhChinh_DAO.getNhanVienHanhChinhTheoMa(getString).getTen() );
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn ngày.");
				}
			}
		});
		btnLoc.setBounds(295, 32, 71, 27);
		panel_1.add(btnLoc);
		btnLoc.setHorizontalAlignment(SwingConstants.LEFT);
//		btnLoc.setIcon(new ImageIcon(
//				"D:\\Study\\OOPJava\\21091031_TrinhMinhKhaa\\Motorbike-Store-Project\\data\\image\\icons8-search-30.png"));
		btnLoc.setForeground(new Color(165, 42, 42));
		btnLoc.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLoc.setBackground(Color.LIGHT_GRAY);
		btnLoc.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnLoc.setHorizontalAlignment(SwingConstants.LEFT);
//		btnLoc.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				java.util.Date utilTuNgay = chooserTuNgay.getDate();
//				java.util.Date utilDenNgay = chooserDenNgay.getDate();
//
//				@SuppressWarnings("deprecation")
//				Date ngayBatDau = new Date(utilTuNgay.getYear(), utilTuNgay.getMonth(), utilTuNgay.getDate());
//				@SuppressWarnings("deprecation")
//				Date ngayKetThuc = new Date(utilDenNgay.getYear(), utilDenNgay.getMonth(), utilDenNgay.getDate());
//				if (ngayBatDau.after(ngayKetThuc)) {
//					JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!");
//				}
//			}
//		});

		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe_GUI.this.model.setRowCount(0);
				ThongKe_GUI.this.model_1.setRowCount(0);
				loadDataHoaDon(hoaDon_DAO);
				loadDataChiTietHoaDon(chiTietHoaDon_DAO);
				xoaTrang();
			}
		});
		btnLamMoi.setForeground(new Color(165, 42, 42));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setBounds(144, 508, 112, 27);
		panel_1.add(btnLamMoi);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(809, 123, 322, 27);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Thống kê:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(15, 109, 91, 54);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("THỐNG KÊ");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 70, 1198, 37);
		add(lblNewLabel_3);
	}

	public void loadDataHoaDon(HoaDon_DAO hoaDon_DAO) {
		for (HoaDon hoaDon : hoaDon_DAO.getAllHoaDon()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
			Object[] objects = { hoaDon.getMa(), dateFormat.format(hoaDon.getNgayLap()), hoaDon.getThoiGianBH(),
					hoaDon.getMaKH(), hoaDon.getMaCH(), hoaDon.getMaNV() };
			model.addRow(objects);
		}
	}

	public void loadDataChiTietHoaDon(ChiTietHoaDon_DAO chiTietHoaDon_DAO) {
		for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO.getAllChiTietHoaDon()) {
			Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(), chiTietHoaDon.getSoLuong(),
					chiTietHoaDon.getDonGia(), chiTietHoaDon.getThanhTien() };
			model_1.addRow(objects);
		}
	}

	public void loadDataChiTietHoaDonTheoMaHoaDon(ChiTietHoaDon_DAO chiTietHoaDon_DAO, String maHoaDon) {
		try {
			for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO.getChiTietHoaDonTheoMa(maHoaDon)) {
				Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(), chiTietHoaDon.getSoLuong(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getThanhTien() };
				model_1.addRow(objects);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xoaTrang() {
		textXeBanIt.setText("");
		textXeBanNhieu.setText("");
		txtSoluong.setText("");
		txtCuaHangBanNhieuXe.setText("");
		txtNhanVienBanDuocNhieuXe.setText("");
	}
}
