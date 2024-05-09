package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import connect.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.CuaHang_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVienHanhChinh_DAO;
import dao.PhatSinhMa_DAO;
import dao.ThongTinXe_DAO;
import dao.XeTrongKho_DAO;
import dao.Xe_DAO;
import entity.ChiTietHoaDon;
import entity.CuaHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVienHanhChinh;
import entity.ThongTinXe;
import entity.XeTrongKho;
import HoaDonPDF.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntToDoubleFunction;
import java.util.Date;
import java.util.Iterator;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.DropMode;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class HoaDon_GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textTim;
	private JTable tableHd;
	private DefaultTableModel modelHd;
	private DefaultTableModel modelHdDetail;
	private JTextField textMaHD;
	private JTable tableHddetail;
	private JTextField textNgaylap;
	private JTextField textThoigianbaohanh;
	private JTextField textTongTien;
	private HoaDon_DAO hoaDon_DAO;
	private ChiTietHoaDon_DAO chiTietHoaDon_DAO;
	private CuaHang_DAO cuaHang_DAO;
	private KhachHang_DAO khachHang_DAO;
	private XeTrongKho_DAO xeTrongKho_DAO;
	private NhanVienHanhChinh_DAO nhanVienHanhChinh_DAO;
	private ThongTinXe_DAO thongTinXe_DAO;
	private PhatSinhMa_DAO phatSinhMa_DAO;
	private Xe_DAO xe_DAO;
	private List<CuaHang> dsCuaHang;
	// xetrongkhodao
	private JComboBox<String> cbMakhachhang;
	private JComboBox<String> cbMacuahang;
	private JComboBox<String> cbManhanvien;
	private JButton btnTim;
	private JComboBox<Object> cbTimTheo;
	private SimpleDateFormat dateFormat;
	private JTextField textNhap;
	private boolean isEditable;
	private JComboBox<String> cbLoaiXe;
	private JButton btnThemDong;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public HoaDon_GUI() throws SQLException {
		// bat buoc
		ConnectDB.getInstance().connect();
//		ở trên
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		// chang
		dsCuaHang = new ArrayList<CuaHang>();
		thongTinXe_DAO = new ThongTinXe_DAO();
		xeTrongKho_DAO = new XeTrongKho_DAO();
		cuaHang_DAO = new CuaHang_DAO();
		phatSinhMa_DAO = new PhatSinhMa_DAO();
		xe_DAO = new Xe_DAO();
		dsCuaHang = cuaHang_DAO.getAllCuaHang();

		// ========== cb nhan vien hành chánh
		nhanVienHanhChinh_DAO = new NhanVienHanhChinh_DAO();
		int size = dsCuaHang.size();
		for (int i = 0; i < size; i++) {
			dsCuaHang.get(i).setDsNhanVienHc(new ArrayList<NhanVienHanhChinh>(
					nhanVienHanhChinh_DAO.getNhanVienHanhChinhTheoMaCh(dsCuaHang.get(i).getMa())));
			dsCuaHang.get(i).setDsXe(
					new ArrayList<XeTrongKho>(xeTrongKho_DAO.getXeTrongKhoTheoMaCuaHang(dsCuaHang.get(i).getMa())));
		}

		JPanel panel = new JPanel();
		panel.setBackground(new Color(165, 42, 42));
		panel.setBorder(new LineBorder(Color.CYAN));
		panel.setBounds(442, 299, 748, 401);
		add(panel);
		panel.setLayout(null);
		// table
		String[] column = { "Mã hóa đơn", "Ngày lập", "Thời gian bảo hành", "Mã khách hàng", "Mã cửa hàng",
				"Mã nhân viên" };
		modelHd = new DefaultTableModel(column, 0);
		tableHd = new JTable(modelHd);
		tableHd.setDefaultEditor(Object.class, null);
		tableHd.setRowHeight(25);
		tableHd.setToolTipText("Chọn hoá đơn để thực hiện chức năng");
		// set color for header table
		JTableHeader tableHdHeader = tableHd.getTableHeader();
		tableHdHeader.setBackground(new Color(0, 163, 163));
		tableHdHeader.setForeground(Color.white);
		tableHdHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tableHdHeader.setToolTipText("Danh sách thông tin hóa đơn");
		// Add a ListSelectionListener to the table
		ListSelectionModel model = tableHd.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Check if the current cell selection is not empty
				if (!e.getValueIsAdjusting()) {
					int rowIndex = tableHd.getSelectedRow();
					if (rowIndex >= 0 && rowIndex < tableHd.getRowCount()) {
						tableHd.setSelectionBackground(new Color(138, 255, 255));
						tableHd.setRowSelectionInterval(rowIndex, rowIndex);
					}
				}
			}
		});
		tableHd.setFont(new Font("Arial", Font.PLAIN, 16));
		JScrollPane scrollPane = new JScrollPane(tableHd, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 10, 728, 381);
		panel.add(scrollPane);

		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(SystemColor.text);
		panelThongTin.setBorder(new LineBorder(Color.CYAN));
		panelThongTin.setBounds(442, 39, 748, 236);
		add(panelThongTin);
		panelThongTin.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Mã Hóa Đơn:\r\n");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 105, 34);
		panelThongTin.add(lblNewLabel_4);

		textMaHD = new JTextField();
		textMaHD.setEditable(false);
		textMaHD.setFont(new Font("Arial", Font.PLAIN, 16));
		textMaHD.setBounds(160, 18, 206, 24);
		panelThongTin.add(textMaHD);
		textMaHD.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(165, 42, 42)));
		panel_2.setBounds(10, 378, 356, 147);
		panelThongTin.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4_1 = new JLabel("Ngày Lập:\r\n");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBackground(Color.WHITE);
		lblNewLabel_4_1.setBounds(10, 55, 105, 34);
		panelThongTin.add(lblNewLabel_4_1);

		textNgaylap = new JTextField();
		textNgaylap.setFont(new Font("Arial", Font.PLAIN, 16));
		textNgaylap.setColumns(10);
		textNgaylap.setBounds(160, 65, 206, 24);
		panelThongTin.add(textNgaylap);

		JLabel lblNewLabel_4_1_1 = new JLabel("Thời gian bảo hành:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBackground(Color.WHITE);
		lblNewLabel_4_1_1.setBounds(10, 99, 157, 34);
		panelThongTin.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_2 = new JLabel("Mã khách hàng:\r\n");
		lblNewLabel_4_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_2.setBackground(Color.WHITE);
		lblNewLabel_4_1_2.setBounds(10, 143, 132, 34);
		panelThongTin.add(lblNewLabel_4_1_2);

		JLabel lblNewLabel_4_1_3 = new JLabel("Mã cửa hàng:");
		lblNewLabel_4_1_3.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_3.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_3.setBackground(Color.WHITE);
		lblNewLabel_4_1_3.setBounds(10, 187, 105, 34);
		panelThongTin.add(lblNewLabel_4_1_3);

		JLabel lblNewLabel_4_1_4 = new JLabel("Mã nhân viên:");
		lblNewLabel_4_1_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_4.setBackground(Color.WHITE);
		lblNewLabel_4_1_4.setBounds(393, 10, 105, 34);
		panelThongTin.add(lblNewLabel_4_1_4);
		// ======== cb mã kh
		khachHang_DAO = new KhachHang_DAO();
		cbMakhachhang = new JComboBox<String>();
		cbMakhachhang.addItem(" ");
		for (KhachHang kHang : khachHang_DAO.getAllKhachHang()) {
			cbMakhachhang.addItem(kHang.getMa());
		}
		cbMakhachhang.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMakhachhang.setBounds(160, 152, 206, 21);
		panelThongTin.add(cbMakhachhang);
		// ======== cb mã cửa hàng
		cbMacuahang = new JComboBox<String>();
		cbMacuahang.addItem(" ");
		for (CuaHang cHang : dsCuaHang) {
			cbMacuahang.addItem(cHang.getMa());
		}
		cbMacuahang.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMacuahang.setBounds(160, 196, 206, 21);
		panelThongTin.add(cbMacuahang);

		cbManhanvien = new JComboBox<String>();
		cbManhanvien.addItem(" ");
		for (NhanVienHanhChinh nv : dsCuaHang.get(0).getDsNhanVienHc()) {
			cbManhanvien.addItem(nv.getMa());
		}
		cbManhanvien.setFont(new Font("Arial", Font.PLAIN, 16));
		cbManhanvien.setBounds(508, 17, 206, 21);
		panelThongTin.add(cbManhanvien);
		// skien khi chon ma cua hang
		cbMacuahang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbManhanvien.removeAllItems();
				cbLoaiXe.removeAllItems();
				for (CuaHang ch : dsCuaHang) {
					if (cbMacuahang.getSelectedItem().equals(ch.getMa())) {
						for (NhanVienHanhChinh nv : ch.getDsNhanVienHc()) {
							cbManhanvien.addItem(nv.getMa());
						}
						for (XeTrongKho xe : ch.getDsXe()) {
							cbLoaiXe.addItem(xe.getMaXe());
						}
						break;
					}
				}
			}
		});

		textThoigianbaohanh = new JTextField();
		textThoigianbaohanh.setFont(new Font("Arial", Font.PLAIN, 16));
		textThoigianbaohanh.setColumns(10);
		textThoigianbaohanh.setBounds(160, 104, 206, 24);
		panelThongTin.add(textThoigianbaohanh);

		JLabel lblNewLabel_4_1_4_1 = new JLabel("Tổng tiền:");
		lblNewLabel_4_1_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_4_1.setBackground(Color.WHITE);
		lblNewLabel_4_1_4_1.setBounds(393, 55, 105, 34);
		panelThongTin.add(lblNewLabel_4_1_4_1);

		textTongTien = new JTextField();
		textTongTien.setEditable(false);
		textTongTien.setFont(new Font("Arial", Font.PLAIN, 16));
		textTongTien.setColumns(10);
		textTongTien.setBounds(508, 60, 206, 24);
		panelThongTin.add(textTongTien);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new LineBorder(new Color(165, 42, 42)));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(393, 89, 338, 137);
		panelThongTin.add(panel_2_1);

		textNhap = new JTextField();
		textNhap.setFont(new Font("Arial", Font.PLAIN, 16));
		cbLoaiXe = new JComboBox<String>();
		cbLoaiXe.setFont(new Font("Arial", Font.PLAIN, 16));
		isEditable = false;
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEditable = !isEditable;
				TableColumn column = tableHddetail.getColumnModel().getColumn(1); // Lấy cột tương ứng trong bảng
				TableColumn column1 = tableHddetail.getColumnModel().getColumn(2); // Lấy cột tương ứng trong bảng
				if (isEditable) {
					xoaTrang();

					btnThemDong.setEnabled(true);
					btnThem.setText("Xác Nhận");
					// set lại dòng trong table hd detail
					modelHdDetail.setRowCount(0);
					modelHd.setRowCount(0);
					// Đưa combobox vào bảng
					column.setCellEditor(new DefaultCellEditor(cbLoaiXe));
					// Bỏ cờ chỉ đọc để cho phép người dùng sửa đổi bảng JTable
					column1.setCellEditor(new DefaultCellEditor(textNhap));
					Date currentDate = new Date();
					textNgaylap.setText(dateFormat.format(currentDate));
				} else {
					btnThem.setText("Thêm");
					btnThemDong.setEnabled(false);
					int sum = 0;
					// Thiết lập lại bảng JTable của bạn với giá trị chỉ đọc mặc định
					column.setCellEditor(new DefaultCellEditor(new JTextField()));
					column1.setCellEditor(null);
					column.setCellEditor(null);
					if (tableHddetail.getRowCount() >= 0) {
						for (int i = 0; i < tableHddetail.getRowCount(); i++) {
							int value1 = Integer.valueOf(tableHddetail.getValueAt(i, 2).toString());
							int value2 = Integer.valueOf(tableHddetail.getValueAt(i, 3).toString());
							// sử dụng giá trị đó ở đây
							tableHddetail.setValueAt(value1 * value2, i, 4);
						}
						for (int i = 0; i < tableHddetail.getRowCount(); i++) {
							sum += Integer.valueOf(tableHddetail.getValueAt(i, 4).toString());
						}
						textTongTien.setText(sum + "");
					}
					if (textNgaylap.getText().isEmpty() || textThoigianbaohanh.getText().isEmpty()
							|| cbMacuahang.getSelectedItem().equals(null) || cbManhanvien.getSelectedItem().equals(null)
							|| cbManhanvien.getSelectedItem().equals(null)) {
						JOptionPane.showMessageDialog(null, "Thêm Thất bại");
					} else if(!kiemTraDuLieu()){
						JOptionPane.showMessageDialog(null, "Ngày lập đúng định dạng dd/MM/yyyy và phải đúng ngày hiện tại");
					} 
					else {
						modelHd.addRow(layDuLieu());
						themSql();
						for (int j = 0; j < tableHddetail.getRowCount(); j++) {
							int value = Integer.valueOf(tableHddetail.getValueAt(j, 2).toString());
							String maLx = tableHddetail.getValueAt(j, 1).toString();
							try {
								xe_DAO.xoaXeTheoMaLoaiXeSL(maLx, value);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						JOptionPane.showMessageDialog(null, "Thêm thành công");
					}
				}
			}
		});
		btnThem.setForeground(new Color(165, 42, 42));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 16));
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setBounds(35, 11, 112, 27);
		panel_2_1.add(btnThem);

		JButton btnXoatrang = new JButton("Xóa Trắng");
		btnXoatrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaTrang();

			}
		});
		btnXoatrang.setForeground(new Color(165, 42, 42));
		btnXoatrang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoatrang.setBackground(Color.LIGHT_GRAY);
		btnXoatrang.setBounds(193, 11, 112, 27);
		panel_2_1.add(btnXoatrang);

		JButton btnCapnhat = new JButton("Cập Nhật");
		btnCapnhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEditable = !isEditable;
				TableColumn column = tableHddetail.getColumnModel().getColumn(1); // Lấy cột tương ứng trong bảng
				TableColumn column1 = tableHddetail.getColumnModel().getColumn(2); // Lấy cột tương ứng trong bảng
				if (isEditable) {
					cbMacuahang.setEnabled(false);
					btnThemDong.setEnabled(true);
					btnCapnhat.setText("Xác Nhận");
					// Đưa combobox vào bảng
					column.setCellEditor(new DefaultCellEditor(cbLoaiXe));
					// Bỏ cờ chỉ đọc để cho phép người dùng sửa đổi bảng JTable
					column1.setCellEditor(new DefaultCellEditor(textNhap));
				} else {
					cbMacuahang.setEnabled(true);
					btnCapnhat.setText("Cập Nhật");
					btnThemDong.setEnabled(false);
					int sum = 0;
					// Thiết lập lại bảng JTable của bạn với giá trị chỉ đọc mặc định
					column.setCellEditor(new DefaultCellEditor(new JTextField()));
					column1.setCellEditor(null);
					column.setCellEditor(null);
					if (tableHddetail.getRowCount() >= 0) {
						for (int i = 0; i < tableHddetail.getRowCount(); i++) {
							int value1 = Integer.valueOf(tableHddetail.getValueAt(i, 2).toString());
							int value2 = Integer.valueOf(tableHddetail.getValueAt(i, 3).toString());
							// sử dụng giá trị đó ở đây
							tableHddetail.setValueAt(value1 * value2, i, 4);
						}
						for (int i = 0; i < tableHddetail.getRowCount(); i++) {
							sum += Integer.valueOf(tableHddetail.getValueAt(i, 4).toString());
						}
						textTongTien.setText(sum + "");
					}
					try {
						if (capNhatDuLieu()) {
							capNhatSql();
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						} else {
							JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnCapnhat.setForeground(new Color(165, 42, 42));
		btnCapnhat.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCapnhat.setBackground(Color.LIGHT_GRAY);
		btnCapnhat.setBounds(35, 49, 112, 27);
		panel_2_1.add(btnCapnhat);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j;
				int i = tableHd.getSelectedRow();
				int k = tableHddetail.getSelectedRow();
				if (k >= 0 && k < tableHddetail.getRowCount()) {
					j = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (j == JOptionPane.YES_OPTION) {
						chiTietHoaDon_DAO.xoaChiHoaDon(tableHddetail.getValueAt(k, 0).toString(),
								tableHddetail.getValueAt(k, 1).toString(), tableHddetail.getValueAt(k, 2).toString());
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					}
				} else if (i >= 0 && i < tableHd.getRowCount() && k == -1) {
					j = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
					if (j == JOptionPane.YES_OPTION) {
						chiTietHoaDon_DAO.xoaChiHoaDon(tableHd.getValueAt(i, 0).toString());
						hoaDon_DAO.xoaHoaDon(tableHd.getValueAt(i, 0).toString());
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					}
				}
			}
		});
		btnXoa.setForeground(new Color(165, 42, 42));
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(193, 49, 112, 27);
		panel_2_1.add(btnXoa);

		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelHd.setRowCount(0);
				modelHdDetail.setRowCount(0);
				doDuLieu(hoaDon_DAO);
				doDuLieuChiTiet(chiTietHoaDon_DAO);
			}
		});
		btnLamMoi.setForeground(new Color(165, 42, 42));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setBounds(35, 87, 112, 27);
		panel_2_1.add(btnLamMoi);

		JButton btnInHoaDon = new JButton("Xuất HD");
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int k = tableHd.getSelectedRow();
//				if (k >= 0 && k < tableHd.getRowCount()) {
					String time = tableHd.getValueAt(k, 1).toString();
					String maHoaDon = tableHd.getValueAt(k, 0).toString();
					String maNV = tableHd.getValueAt(k, 5).toString();
					InHoaDon IHD = new InHoaDon();
					try {
						IHD.addOrderInfo(time, maHoaDon, maNV);
					} catch (com.itextpdf.text.DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
					for (int i = 0; i < tableHddetail.getRowCount(); i++) {
						String maSP = tableHddetail.getValueAt(i, 0).toString();
						String tenSP = tableHddetail.getValueAt(i, 1).toString();
						String soLuong = tableHddetail.getValueAt(i, 2).toString();
						String donGia = tableHddetail.getValueAt(i, 3).toString();
						String thanhTien = tableHddetail.getValueAt(i, 4).toString();

						// Insert Order Detail
						IHD.addCellOrderDetail(tenSP, maSP, soLuong, donGia, thanhTien);
					}
					// Insert Order Purchase Info
					try {
						IHD.addPurchaseInfo(textTongTien.getText());
					} catch (com.itextpdf.text.DocumentException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					// Create Order
					IHD.printOrder(maHoaDon + "");

					// Display Order File
					try {
						Thread.sleep(500);
						String url = "D:\\Study\\Download\\InHoaDon\\" + maHoaDon + ".pdf";
						JOptionPane.showMessageDialog(null, "Tạo HD thành công");
						Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "
								+ new File("D:\\Study\\Download\\InHoaDon\\" + maHoaDon + ".pdf").getAbsolutePath());
					} catch (Exception e1) {
						// TODO: handle exception

					}
				}
//			}
		});
		btnInHoaDon.setForeground(new Color(165, 42, 42));
		btnInHoaDon.setFont(new Font("Arial", Font.PLAIN, 16));
		btnInHoaDon.setBackground(Color.LIGHT_GRAY);
		btnInHoaDon.setBounds(193, 87, 112, 27);
		panel_2_1.add(btnInHoaDon);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(442, 10, 322, 27);
		add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Quản Lí Hóa Đơn");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(10, 3, 1188, 37);
		add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("Danh Sách Hóa Đơn:");
		lblNewLabel.setBounds(452, 275, 322, 27);
		add(lblNewLabel);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(Color.CYAN));
		panel_3.setBackground(new Color(165, 42, 42));
		panel_3.setBounds(10, 39, 422, 661);
		add(panel_3);
//==================================table chi tiết hóa đơn
		String[] column_1 = { "Mã hóa đơn", "Mã loại xe", "Số lượng", "Đơn giá", "Thành tiền" };
		modelHdDetail = new DefaultTableModel(column_1, 0);
		tableHddetail = new JTable(modelHdDetail);
		tableHddetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnCapnhat.getText().equals("Xác Nhận") || btnThem.getText().equals("Xác Nhận")) {
					int rowIndex = tableHddetail.getSelectedRow();
					if (rowIndex >= 0 && rowIndex < tableHddetail.getRowCount()) {
						String string = tableHddetail.getValueAt(rowIndex, 1).toString();
						if (!string.isEmpty()) {
							try {
								tableHddetail.setValueAt(thongTinXe_DAO.getThongTinXeTheoMa(string).getGiaNiemYet(),
										rowIndex, 3);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});

		tableHddetail.setRowHeight(25);
		tableHddetail.setFont(new Font("Arial", Font.PLAIN, 16));
		tableHddetail.setDefaultEditor(Object.class, null);
		tableHddetail.setToolTipText("Chọn chi tiết hóa đơn để thực hiện chức năng");
		JScrollPane scrollPane_1 = new JScrollPane(tableHddetail, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(10, 125, 402, 489);
		panel_3.add(scrollPane_1);

		JLabel lblNewLabel_2 = new JLabel("Tìm Kiếm:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(10, 10, 91, 54);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));

		textTim = new JTextField();
		textTim.setFont(new Font("Arial", Font.PLAIN, 16));
		textTim.setBounds(10, 56, 402, 27);
		panel_3.add(textTim);
		textTim.setColumns(10);

		JComboBox<String> cbTim = new JComboBox<String>();
		cbTim.setBounds(10, 91, 198, 24);
		panel_3.add(cbTim);
		cbTim.setForeground(Color.RED);
		cbTim.setFont(new Font("Arial", Font.PLAIN, 16));

		btnTim = new JButton("Tìm Kiếm");
		btnTim.addActionListener(new ActionListener() {
			// Tìm kiếm theo
			public void actionPerformed(ActionEvent e) {
				if (textTim.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập dữ liệu");					
				} else {
					boolean check = false;
					if (cbTimTheo.getSelectedItem().equals("Hóa Đơn")) {
						modelHd.setRowCount(0);
						if (cbTim.getSelectedItem().equals("Mã hóa đơn")) {
							HoaDon hd;
							try {
								// lấy du liệu từ sql
								hd = hoaDon_DAO.getHDTheoMa(textTim.getText());
								Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()), hd.getThoiGianBH(),
										hd.getMaKH(), hd.getMaCH(), hd.getMaNV() };
								modelHd.addRow(objects);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Ngày lập")) {
							try {
								Date date = dateFormat.parse(textTim.getText());
								for (HoaDon hd : hoaDon_DAO.getHDtheoNgay(date)) {
									Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()),
											hd.getThoiGianBH(), hd.getMaKH(), hd.getMaCH(), hd.getMaNV() };
									modelHd.addRow(objects);
									check = true;
								}
							} catch (ParseException | SQLException e1) {
								check = true;
								JOptionPane.showMessageDialog(null, "Định dạng không hợp lệ");
							}

							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy ngày trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Tên khách hàng")) {
							try {
								for (HoaDon hd : hoaDon_DAO.getHDTheoTenKH(textTim.getText())) {
									Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()),
											hd.getThoiGianBH(), hd.getMaKH(), hd.getMaCH(), hd.getMaNV() };
									modelHd.addRow(objects);
									check = true;
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy tên KH trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Mã cửa hàng")) {
							try {
								for (HoaDon hd : hoaDon_DAO.getHDTheoMaCH(textTim.getText())) {
									Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()),
											hd.getThoiGianBH(), hd.getMaKH(), hd.getMaCH(), hd.getMaNV() };
									modelHd.addRow(objects);
									check = true;
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã CH trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Mã nhân viên")) {
							try {
								for (HoaDon hd : hoaDon_DAO.getHDTheoMaNV(textTim.getText())) {
									Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()),
											hd.getThoiGianBH(), hd.getMaKH(), hd.getMaCH(), hd.getMaNV() };
									modelHd.addRow(objects);
									check = true;
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã NV trong bảng");
							}
						}
					} else {
						modelHdDetail.setRowCount(0);
						if (cbTim.getSelectedItem().equals("Mã hóa đơn")) {
							try {
								for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO
										.getChiTietHoaDonTheoMa(textTim.getText())) {
									Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(),
											chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia(),
											chiTietHoaDon.getThanhTien() };
									modelHdDetail.addRow(objects);
									check = true;
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã hóa đơn trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Mã loại xe")) {
							try {
								for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO
										.getChiTietHoaDonTheoMaLoaiXe(textTim.getText())) {
									Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(),
											chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia(),
											chiTietHoaDon.getThanhTien() };
									modelHdDetail.addRow(objects);
									check = true;
								}

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy mã loại trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Số lượng")) {
							try {
								for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO
										.getChiTietHoaDonTheoSoLuong(textTim.getText())) {
									Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(),
											chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia(),
											chiTietHoaDon.getThanhTien() };
									modelHdDetail.addRow(objects);
									check = true;
								}

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy số lượng phù hợp trong bảng");
							}
						} else if (cbTim.getSelectedItem().equals("Đơn giá")) {
							try {
								for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO
										.getChiTietHoaDonTheoDonGia(textTim.getText())) {
									Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(),
											chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia(),
											chiTietHoaDon.getThanhTien() };
									modelHdDetail.addRow(objects);
									check = true;
								}

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (check == false) {
								JOptionPane.showMessageDialog(null, "Không tìm thấy đơn giá phù hợp trong bảng");
							}
						}
					}
				}
			}
		});
		btnTim.setHorizontalTextPosition(SwingConstants.LEADING);
		btnTim.setIcon(new ImageIcon(HoaDon_GUI.class.getResource("/image/magnifier.png")));
		cbTim.addItem("Mã hóa đơn");
		cbTim.addItem("Ngày lập");
		cbTim.addItem("Tên khách hàng");
		cbTim.addItem("Mã cửa hàng");
		cbTim.addItem("Mã nhân viên");
		btnTim.setBounds(218, 90, 133, 27);
		panel_3.add(btnTim);
		btnTim.setHorizontalAlignment(SwingConstants.LEFT);
		btnTim.setForeground(new Color(165, 42, 42));
		btnTim.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTim.setBackground(Color.LIGHT_GRAY);
		btnTim.setVerticalTextPosition(SwingConstants.CENTER);
		btnTim.setHorizontalAlignment(SwingConstants.LEFT);

		cbTimTheo = new JComboBox<Object>();
		cbTimTheo.setForeground(Color.RED);
		cbTimTheo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cbTim.removeAllItems();
				if (cbTimTheo.getSelectedItem().equals("Hóa Đơn")) {
					cbTim.addItem("Mã hóa đơn");
					cbTim.addItem("Ngày lập");
					cbTim.addItem("Tên khách hàng");
					cbTim.addItem("Mã cửa hàng");
					cbTim.addItem("Mã nhân viên");
				} else {
					cbTim.addItem("Mã hóa đơn");
					cbTim.addItem("Mã loại xe");
					cbTim.addItem("Số lượng");
					cbTim.addItem("Đơn giá");
				}
			}
		});
		cbTimTheo.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTimTheo.setModel(new DefaultComboBoxModel(new String[] { "Hóa Đơn", "Chi Tiết Hóa Đơn" }));
		cbTimTheo.setBounds(90, 23, 133, 27);
		panel_3.add(cbTimTheo);

		// thêm dòng khi để thêm hóa đơn
		btnThemDong = new JButton("Thêm Dòng");
		btnThemDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelHdDetail.addRow(new Object[] {});
			}
		});
		btnThemDong.setEnabled(false);
		btnThemDong.setForeground(new Color(165, 42, 42));
		btnThemDong.setFont(new Font("Arial", Font.PLAIN, 16));
		btnThemDong.setBackground(Color.LIGHT_GRAY);
		btnThemDong.setBounds(10, 624, 125, 27);
		panel_3.add(btnThemDong);

		// đổ dữ liệu vào table hóa đơn
		hoaDon_DAO = new HoaDon_DAO();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		doDuLieu(hoaDon_DAO);
		// Khai báo chi tiết hd dao
		chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
		doDuLieuChiTiet(chiTietHoaDon_DAO);
		// set color for header table
		JTableHeader tableHddetailHeader = tableHddetail.getTableHeader();
		tableHddetailHeader.setBackground(new Color(0, 163, 163));
		tableHddetailHeader.setForeground(Color.white);
		tableHddetailHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tableHddetailHeader.setToolTipText("Danh sách thông tin chi tiết hóa đơn");
		// Add a ListSelectionListener to the table
		ListSelectionModel modelCthd = tableHddetail.getSelectionModel();
		modelCthd.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Check if the current cell selection is not empty
				if (!e.getValueIsAdjusting()) {
					int rowIndex = tableHddetail.getSelectedRow();
					if (rowIndex >= 0 && rowIndex < tableHddetail.getRowCount()) {
						tableHddetail.setSelectionBackground(new Color(138, 255, 255));
						tableHddetail.setRowSelectionInterval(rowIndex, rowIndex);
					}
				}
			}
		});
		// click chuột vào bảng
		tableHd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int sum = 0;
				int row = tableHd.getSelectedRow();
				textMaHD.setText(tableHd.getValueAt(row, 0).toString());
				textNgaylap.setText(tableHd.getValueAt(row, 1).toString());
				textThoigianbaohanh.setText(tableHd.getValueAt(row, 2).toString());
				cbMakhachhang.setSelectedItem(tableHd.getValueAt(row, 3).toString());
				cbMacuahang.setSelectedItem(tableHd.getValueAt(row, 4).toString());
				// đo dữ liệu lại chi tiết hóa đơn
				try {
					modelHdDetail.setRowCount(0);
					for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO
							.getChiTietHoaDonTheoMa(tableHd.getValueAt(row, 0).toString())) {
						Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(),
								chiTietHoaDon.getSoLuong(), chiTietHoaDon.getDonGia(), chiTietHoaDon.getThanhTien() };
						modelHdDetail.addRow(objects);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < tableHddetail.getRowCount(); i++) {
					sum += Integer.valueOf(tableHddetail.getValueAt(i, 4).toString());
				}
				textTongTien.setText(sum + "");
			}
		});
	}

	public void doDuLieu(HoaDon_DAO hoaDon_DAO) {
		for (HoaDon hd : hoaDon_DAO.getAllHoaDon()) {
			Object[] objects = { hd.getMa(), dateFormat.format(hd.getNgayLap()), hd.getThoiGianBH(), hd.getMaKH(),
					hd.getMaCH(), hd.getMaNV() };
			modelHd.addRow(objects);
		}
	}

	public void doDuLieuChiTiet(ChiTietHoaDon_DAO chiTietHoaDon_DAO) {
		// đổ dữ liệu vào
		for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDon_DAO.getAllChiTietHoaDon()) {
			Object[] objects = { chiTietHoaDon.getMa(), chiTietHoaDon.getMaLoaiXe(), chiTietHoaDon.getSoLuong(),
					chiTietHoaDon.getDonGia(), chiTietHoaDon.getThanhTien() };
			modelHdDetail.addRow(objects);
		}
	}

	public void xoaTrang() {
		textMaHD.setText("");
		textNgaylap.setText("");
		textThoigianbaohanh.setText("");
		cbMakhachhang.setSelectedItem(" ");
		cbMacuahang.setSelectedItem(" ");
		cbMakhachhang.setSelectedItem(" ");
		textTim.setText("");
		textTongTien.setText("");
	}

	public Object[] layDuLieu() {
		try {
			Object[] objects = { phatSinhMa_DAO.getMaHoaDon(), textNgaylap.getText(), textThoigianbaohanh.getText(),
					cbMakhachhang.getSelectedItem(), cbMacuahang.getSelectedItem(), cbManhanvien.getSelectedItem() };
			return objects;
		} catch (SQLException e) {// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//Cap nhật
	public boolean capNhatDuLieu() throws SQLException {
		int i = tableHd.getSelectedRow();
		if (i >= 0) {
			modelHd.setValueAt(textMaHD.getText(), i, 0);
			modelHd.setValueAt(textNgaylap.getText(), i, 1);
			modelHd.setValueAt(textThoigianbaohanh.getText(), i, 2);
			modelHd.setValueAt(cbMakhachhang.getSelectedItem(), i, 3);
			modelHd.setValueAt(cbMacuahang.getSelectedItem(), i, 4);
			modelHd.setValueAt(cbManhanvien.getSelectedItem(), i, 5);
			return true;
		}
		return false;
	}

	public void themSql() {
		if (tableHd.getRowCount() > 0) {
			Date date;
			try {
				date = dateFormat.parse(tableHd.getValueAt(0, 1).toString());
				HoaDon hDon = new HoaDon(tableHd.getValueAt(0, 0).toString(), date, tableHd.getValueAt(0, 2).toString(),
						tableHd.getValueAt(0, 3).toString(), tableHd.getValueAt(0, 4).toString(),
						tableHd.getValueAt(0, 5).toString());
				hoaDon_DAO.themHoaDon(hDon);

				for (int i = 0; i < tableHddetail.getRowCount(); i++) {
					chiTietHoaDon_DAO.themChiTietHoaDon(new ChiTietHoaDon(tableHd.getValueAt(0, 0).toString(),
							thongTinXe_DAO.getThongTinXeTheoMa(tableHddetail.getValueAt(i, 1).toString()),
							Integer.valueOf(tableHddetail.getValueAt(i, 2).toString()),
							Integer.valueOf(tableHddetail.getValueAt(i, 3).toString())));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void capNhatSql() {
		int i = tableHd.getSelectedRow();
		if (i >= 0 && i < tableHd.getRowCount()) {
			if (tableHddetail.getRowCount() >= 0 && tableHd.getRowCount() > 0) {
				Date date;
				try {
					date = dateFormat.parse(textNgaylap.getText());
					HoaDon hDon = new HoaDon(tableHd.getValueAt(i, 0).toString(), date,
							tableHd.getValueAt(i, 2).toString(), tableHd.getValueAt(i, 3).toString(),
							tableHd.getValueAt(i, 4).toString(), tableHd.getValueAt(i, 5).toString());
					hoaDon_DAO.capNhatHoaDon(hDon);
					chiTietHoaDon_DAO.xoaChiHoaDon(hDon.getMa());
					for (int i1 = 0; i1 < tableHddetail.getRowCount(); i1++) {
						chiTietHoaDon_DAO.themChiTietHoaDon(new ChiTietHoaDon(tableHd.getValueAt(i, 0).toString(),
								thongTinXe_DAO.getThongTinXeTheoMa(tableHddetail.getValueAt(i1, 1).toString()),
								Integer.valueOf(tableHddetail.getValueAt(i1, 2).toString()),
								Integer.valueOf(tableHddetail.getValueAt(i1, 3).toString())));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public boolean kiemTraDuLieu() {
		String ngaylap = textNgaylap.getText();
		String tgbh = textThoigianbaohanh.getText();
		return  Regex.ktDateFormat(ngaylap) && Regex.ktTruocNgayHT(ngaylap);		
	}
}
