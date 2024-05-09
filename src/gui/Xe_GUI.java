package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connect.ConnectDB;
import dao.NhaPhanPhoi_DAO;
import dao.PhatSinhMa_DAO;
import dao.ThongTinXe_DAO;
import dao.Xe_DAO;
import entity.NhaPhanPhoi;
import entity.ThongTinXe;
import entity.Xe;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;

public class Xe_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField textMaXe;
	private JTextField textSoMay;
	private JTextField textSoKhung;
	private JDateChooser chooserNgayNhapXe;
	private DefaultTableModel model;
	private Xe_DAO xe_DAO;
	private NhaPhanPhoi_DAO nhaPhanPhoi_DAO;
	private ThongTinXe_DAO thongTinXe_DAO;
	private PhatSinhMa_DAO phatSinhMa_DAO;
	private JComboBox<String> cbTim;
	private JComboBox<String> cbNhaPP;
	private JComboBox<String> cbMaLoaiXe;

	/**
	 * Create the panel.
	 */
	public Xe_GUI() throws SQLException {

		// connectDB
		connect();

		// khai bao dao
		phatSinhMa_DAO = new PhatSinhMa_DAO();
		thongTinXe_DAO = new ThongTinXe_DAO();
		nhaPhanPhoi_DAO = new NhaPhanPhoi_DAO();
		xe_DAO = new Xe_DAO();

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
		scrollPane.setBounds(10, 10, 782, 525);
		panel.add(scrollPane);

		String[] columns = { "M\u00E3 xe", "S\u1ED1 m\u00E1y", "S\u1ED1 khung", "Ng\u00E0y nh\u1EADp xe",
				"M\u00E3 nh\u00E0 ph\u00E2n ph\u1ED1i", "M\u00E3 lo\u1EA1i xe" };
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setFont(new Font("SansSerif", Font.PLAIN, 16));
		table.setDefaultEditor(Object.class, null);
		table.setRowHeight(25);
		table.setToolTipText("Chọn xe để thực hiện chức năng");
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
				SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
				try {
					Date dateUtil = format.parse((String) model.getValueAt(row, 3));
					java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
					chooserNgayNhapXe.setDate(dateSql);

				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				textMaXe.setText((String) model.getValueAt(row, 0));
				textSoMay.setText((String) model.getValueAt(row, 1));
				textSoKhung.setText((String) model.getValueAt(row, 2));
				cbNhaPP.setSelectedItem(model.getValueAt(row, 4).toString());
				cbMaLoaiXe.setSelectedItem(model.getValueAt(row, 5).toString());
			}
		});

		// set color for header table
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setBackground(new Color(0, 163, 163));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tbHeader.setToolTipText("Danh sách thông tin xe");

		// set color for table
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {

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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new LineBorder(Color.CYAN));
		panel_1.setBounds(812, 148, 376, 545);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Mã xe:");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 122, 34);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Số máy:");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(10, 45, 122, 34);
		panel_1.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Số khung:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(10, 89, 115, 34);
		panel_1.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Ngày nhập xe:");
		lblNewLabel_4_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 133, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Nhà phân phối:");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 185, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("Mã loại xe:");
		lblNewLabel_4_1_1_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2.setBounds(10, 229, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2);

		textMaXe = new JTextField();
		textMaXe.setFont(new Font("Arial", Font.PLAIN, 16));
		textMaXe.setBounds(160, 18, 206, 24);
		textMaXe.setEditable(false);
		panel_1.add(textMaXe);
		textMaXe.setColumns(10);

		textSoMay = new JTextField();
		textSoMay.setFont(new Font("Arial", Font.PLAIN, 16));
		textSoMay.setColumns(10);
		textSoMay.setBounds(160, 53, 206, 24);
		panel_1.add(textSoMay);

		textSoKhung = new JTextField();
		textSoKhung.setFont(new Font("Arial", Font.PLAIN, 16));
		textSoKhung.setColumns(10);
		textSoKhung.setBounds(160, 97, 206, 24);
		panel_1.add(textSoKhung);

		chooserNgayNhapXe = new JDateChooser();
		chooserNgayNhapXe.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooserNgayNhapXe.setBounds(160, 141, 206, 24);
		chooserNgayNhapXe.setDateFormatString("dd/MM/yyyy");
		chooserNgayNhapXe.setBorder(new LineBorder(new Color(138, 255, 255), 1, true));
		chooserNgayNhapXe.setFont(new Font("Arial", Font.PLAIN, 16));
		chooserNgayNhapXe.getCalendarButton().setPreferredSize(new Dimension(30, 24));
		chooserNgayNhapXe.getCalendarButton().setBackground(new Color(138, 255, 255));
		chooserNgayNhapXe.getCalendarButton().setToolTipText("Chọn ngày nhập xe");
		panel_1.add(chooserNgayNhapXe);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(165, 42, 42)));
		panel_2.setBounds(10, 378, 356, 147);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.LIGHT_GRAY);
		btnThem.setForeground(new Color(165, 42, 42));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 16));
		btnThem.setBounds(56, 32, 112, 27);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String maXe = phatSinhMa_DAO.getMaXe();
					String soMay = textSoMay.getText();
					String soKhung = textSoKhung.getText();
					Date ngayNhap = chooserNgayNhapXe.getDate();
					String nhaPhanPhoi = (String) cbNhaPP.getSelectedItem();
					String maLoaiXe = (String) cbMaLoaiXe.getSelectedItem();
					Xe xe = new Xe(maXe, soMay, soKhung, new java.sql.Date(ngayNhap.getTime()), nhaPhanPhoi, maLoaiXe);
					xe_DAO.themXe(xe);
					JOptionPane.showMessageDialog(null, "Thêm xe '" + maXe + "' thành công!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Thêm xe thất bại!");
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnThem);

		JButton btnXoatrang = new JButton("Xóa Trắng");
		btnXoatrang.setForeground(new Color(165, 42, 42));
		btnXoatrang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoatrang.setBackground(Color.LIGHT_GRAY);
		btnXoatrang.setBounds(212, 32, 112, 27);
		btnXoatrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		panel_2.add(btnXoatrang);

		JButton btnCapnhat = new JButton("Cập Nhật");
		btnCapnhat.setForeground(new Color(165, 42, 42));
		btnCapnhat.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCapnhat.setBackground(Color.LIGHT_GRAY);
		btnCapnhat.setBounds(56, 69, 112, 27);
		btnCapnhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn phải chọn dòng cần cập nhật!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn có muốn cập nhật xe '" + textMaXe.getText() + "'?", "Cập nhật?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						if (isNull()) {
							JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin!");
						} else {
							String maXe = (String) model.getValueAt(row, 0);
							String soMay = textSoMay.getText();
							String soKhung = textSoKhung.getText();
							Date ngayNhapXe = chooserNgayNhapXe.getDate();
							String maNhaPhanPhoi = (String) cbNhaPP.getSelectedItem();
							String maLoaiXe = (String) cbMaLoaiXe.getSelectedItem();
							Xe xe = new Xe(maXe, soMay, soKhung, new java.sql.Date(ngayNhapXe.getTime()), maNhaPhanPhoi,
									maLoaiXe);
							try {
								xe_DAO.suaThongTinXe(xe, maXe);
								JOptionPane.showMessageDialog(null, "Cập nhập thông tin thành công!");
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Cập nhật không thành công!");
							}
						}
					}
				}
			}
		});
		panel_2.add(btnCapnhat);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(165, 42, 42));
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(212, 69, 112, 27);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bạn phải chọn dòng cần xóa!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn chắc chắn muốn xóa xe '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						try {
							xe_DAO.xoaXeTheoMa((model.getValueAt(row, 0).toString()));
							JOptionPane.showMessageDialog(null,
									"Xóa xe '" + model.getValueAt(row, 0) + "' thành công!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Xóa xe thất bại!");
							e1.printStackTrace();
						}
					}
				}
			}
		});
		panel_2.add(btnXoa);

		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.setForeground(new Color(165, 42, 42));
		btnLmMi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLmMi.setBackground(Color.LIGHT_GRAY);
		btnLmMi.setBounds(135, 110, 112, 27);
		btnLmMi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					model.setRowCount(0);
					loadDataXe(xe_DAO);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		panel_2.add(desktopPane);
		panel_2.add(btnLmMi);

		JLabel lblNewLabel_1_1 = new JLabel("Chức năng:");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(10, 352, 322, 27);
		panel_1.add(lblNewLabel_1_1);

		cbNhaPP = new JComboBox<String>();
		cbNhaPP.addItem("");
		for (NhaPhanPhoi nhaPhanPhoi : nhaPhanPhoi_DAO.getAllNhaPhanPhoi()) {
			cbNhaPP.addItem(nhaPhanPhoi.getMa() + "");
		}
		cbNhaPP.setFont(new Font("Arial", Font.PLAIN, 16));
		cbNhaPP.setBounds(160, 192, 206, 21);
		panel_1.add(cbNhaPP);

		cbMaLoaiXe = new JComboBox<String>();
		cbMaLoaiXe.addItem("");
		for (ThongTinXe thongTinXe : thongTinXe_DAO.getAllThongTinXe()) {
			cbMaLoaiXe.addItem(thongTinXe.getMaLoaiXe());
		}
		cbMaLoaiXe.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMaLoaiXe.setBounds(160, 238, 206, 21);
		panel_1.add(cbMaLoaiXe);

		JLabel lblNewLabel = new JLabel("Danh Sách Xe");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 123, 322, 27);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(809, 123, 322, 27);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tìm Kiếm:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 6, 91, 54);
		add(lblNewLabel_2);

		cbTim = new JComboBox<String>();
		cbTim.setForeground(Color.RED);
		cbTim.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTim.setBounds(88, 25, 125, 21);
		cbTim.addItem("Mã xe");
		cbTim.addItem("Ngày nhập xe");
		cbTim.addItem("Mã nhà phân phối");
		cbTim.addItem("Mã loại xe");
		add(cbTim);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(223, 19, 289, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Quản Lý Xe");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 70, 1198, 37);
		add(lblNewLabel_3);

		JButton btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon(Xe_GUI.class.getResource("/image/magnifier.png")));
		btnTim.setForeground(new Color(165, 42, 42));
		btnTim.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTim.setBackground(Color.LIGHT_GRAY);
		btnTim.setBounds(522, 20, 133, 27);
		btnTim.setHorizontalTextPosition(SwingConstants.LEADING);
		btnTim.setVerticalTextPosition(SwingConstants.CENTER);
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cbTim.getSelectedItem().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn thuộc tính để tìm kiếm!");
				} else {
					if (cbTim.getSelectedItem().toString().equals("Mã xe")) {
						try {
							loadDataXeTheoMa(xe_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (cbTim.getSelectedItem().toString().equals("Ngày nhập xe")) {
						try {
							loadDataXeTheoNgayNhap(xe_DAO, textField.getText());
						} catch (SQLException | ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (cbTim.getSelectedItem().toString().equals("Mã nhà phân phối")) {
						try {
							loadDataXeTheoMaNhaPhanPhoi(xe_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							loadDataXeTheoMaLoaiXe(xe_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		add(btnTim);

		// input data from Xe table
		loadDataXe(xe_DAO);

	}

	public void loadDataXe(Xe_DAO xe_DAO) throws SQLException {
		model.setRowCount(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		for (Xe xe : xe_DAO.getAllXe()) {
			Object[] objects = { xe.getMa(), xe.getSoMay(), xe.getSoKhung(), dateFormat.format(xe.getNgayNhap()),
					xe.getMaNPP(), xe.getMaLoaiXe() };
			model.addRow(objects);
		}
	}

	public void loadDataXeTheoMa(Xe_DAO xe_DAO, String maXe) throws SQLException {
		model.setRowCount(0);
		Xe xe = xe_DAO.getXeTheoMa(maXe);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		Object[] objects = { xe.getMa(), xe.getSoMay(), xe.getSoKhung(), dateFormat.format(xe.getNgayNhap()),
				xe.getMaNPP(), xe.getMaLoaiXe() };
		model.addRow(objects);
	}

	public void loadDataXeTheoNgayNhap(Xe_DAO xe_DAO, String ngayNhap) throws SQLException, ParseException {
		model.setRowCount(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		Date ngayNhapUtil = new SimpleDateFormat("dd/MM/yyyy").parse(textField.getText());
		java.sql.Date nhayNhapSql = new java.sql.Date(ngayNhapUtil.getTime());
		for (Xe xe : xe_DAO.getXeTheoNgayNhap(nhayNhapSql)) {
			Object[] objects = { xe.getMa(), xe.getSoMay(), xe.getSoKhung(), dateFormat.format(xe.getNgayNhap()),
					xe.getMaNPP(), xe.getMaLoaiXe() };
			model.addRow(objects);
		}
	}

	public void loadDataXeTheoMaNhaPhanPhoi(Xe_DAO xe_DAO, String maNhaPhanPhoi) throws SQLException {
		model.setRowCount(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		for (Xe xe : xe_DAO.getXeTheoMaNhaPhanPhoi(maNhaPhanPhoi)) {
			Object[] objects = { xe.getMa(), xe.getSoMay(), xe.getSoKhung(), dateFormat.format(xe.getNgayNhap()),
					xe.getMaNPP(), xe.getMaLoaiXe() };
			model.addRow(objects);
		}
	}

	public void loadDataXeTheoMaLoaiXe(Xe_DAO xe_DAO, String maLoaiXe) throws SQLException {
		model.setRowCount(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy");
		for (Xe xe : xe_DAO.getXeTheoMaLoaiXe(maLoaiXe)) {
			Object[] objects = { xe.getMa(), xe.getSoMay(), xe.getSoKhung(), dateFormat.format(xe.getNgayNhap()),
					xe.getMaNPP(), xe.getMaLoaiXe() };
			model.addRow(objects);
		}
	}

	public void connect() throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
	}

	public void xoaTrang() {
		textMaXe.setText("");
		textSoMay.setText("");
		textSoKhung.setText("");
		cbNhaPP.setSelectedItem("");
		cbMaLoaiXe.setSelectedItem("");
		chooserNgayNhapXe.setDate(new Date());
	}

	public boolean isNull() {
		if (textMaXe.equals("") || textSoMay.equals("") || textSoKhung.equals("")
				|| cbNhaPP.getSelectedItem().toString().equals("")
				|| cbMaLoaiXe.getSelectedItem().toString().equals(""))
			return true;
		return false;
	}
}
