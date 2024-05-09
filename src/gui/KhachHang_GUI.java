package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.sound.sampled.AudioFileFormat;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connect.ConnectDB;
import dao.KhachHang_DAO;
import dao.PhatSinhMa_DAO;
import entity.KhachHang;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class KhachHang_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField textMaKH;
	private JTextField textHoKH;
	private JTextField textTenKH;
	private JTextField textDiaChi;
	private JTextField textSdt;
	private JTextField textEmail;
	private DefaultTableModel model;
	private KhachHang_DAO khachHang_DAO;
	private PhatSinhMa_DAO phatSinhMa_DAO;

	/**
	 * Create the panel.
	 */
	public KhachHang_GUI() throws SQLException {

		// connectDB
		connect();

		// khai bao dao
		khachHang_DAO = new KhachHang_DAO();
		phatSinhMa_DAO = new PhatSinhMa_DAO();

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

		String[] columns = { "M\u00E3 kh\u00E1ch h\u00E0ng", "H\u1ECD kh\u00E1ch h\u00E0ng",
				"T\u00EAn kh\u00E1ch h\u00E0ng", "\u0110\u1ECBa ch\u1EC9", "SDT", "Email" };
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		loadDataKhachHang();
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setRowHeight(25);
		table.setDefaultEditor(Object.class, null);
		table.setToolTipText("Chọn khách hàng để thực hiện chức năng");
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				textMaKH.setText(table.getValueAt(row, 0).toString());
				textHoKH.setText(table.getValueAt(row, 1).toString());
				textTenKH.setText(table.getValueAt(row, 2).toString());
				textDiaChi.setText(table.getValueAt(row, 3).toString());
				textSdt.setText(table.getValueAt(row, 4).toString());
				textEmail.setText(table.getValueAt(row, 5).toString());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		// set color for header table
		JTableHeader tbHeader = table.getTableHeader();
		tbHeader.setBackground(new Color(0, 163, 163));
		tbHeader.setForeground(Color.white);
		tbHeader.setFont(new Font("Arial", Font.BOLD, 14));
		tbHeader.setToolTipText("Danh sách thông tin khách hàng");
		// set color for table
		ListSelectionModel model2 = table.getSelectionModel();
		model2.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Check if the current cell selection is not empty
				if (!e.getValueIsAdjusting()) {
					// Check if the current cell selection is not empty
					if (!e.getValueIsAdjusting()) {
						int rowIndex = table.getSelectedRow();
						if (rowIndex >= 0 && rowIndex < table.getRowCount()) {
							table.setSelectionBackground(new Color(138, 255, 255));
							table.setRowSelectionInterval(rowIndex, rowIndex);
						}
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

		JLabel lblNewLabel_4 = new JLabel("Mã khách hàng:");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 122, 34);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Họ khách hàng:");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(10, 45, 122, 34);
		panel_1.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(10, 89, 115, 34);
		panel_1.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_4_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 133, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 185, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("Email:");
		lblNewLabel_4_1_1_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2.setBounds(10, 229, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2);

		textMaKH = new JTextField();
		textMaKH.setFont(new Font("Arial", Font.PLAIN, 16));
		textMaKH.setBounds(160, 18, 206, 24);
		textMaKH.setEditable(false);
		panel_1.add(textMaKH);
		textMaKH.setColumns(10);

		textHoKH = new JTextField();
		textHoKH.setFont(new Font("Arial", Font.PLAIN, 16));
		textHoKH.setColumns(10);
		textHoKH.setBounds(160, 53, 206, 24);
		panel_1.add(textHoKH);

		textTenKH = new JTextField();
		textTenKH.setFont(new Font("Arial", Font.PLAIN, 16));
		textTenKH.setColumns(10);
		textTenKH.setBounds(160, 97, 206, 24);
		panel_1.add(textTenKH);

		textDiaChi = new JTextField();
		textDiaChi.setFont(new Font("Arial", Font.PLAIN, 16));
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(160, 141, 206, 24);
		panel_1.add(textDiaChi);

		textSdt = new JTextField();
		textSdt.setFont(new Font("Arial", Font.PLAIN, 16));
		textSdt.setColumns(10);
		textSdt.setBounds(160, 185, 206, 24);
		panel_1.add(textSdt);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(160, 229, 206, 24);
		panel_1.add(textEmail);

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
					if (Regex.ktSDT(textSdt.getText()) && Regex.ktEmail(textEmail.getText())) {
						String maKhachHang = phatSinhMa_DAO.getMaKhachHang();
						String hoKhachHang = textHoKH.getText();
						String tenKhachHang = textTenKH.getText();
						String diaChi = textDiaChi.getText();
						int soDienThoai = Integer.parseInt(textSdt.getText());
						String email = textEmail.getText();
						KhachHang khachHang = new KhachHang(maKhachHang, hoKhachHang, tenKhachHang, diaChi, soDienThoai,
								email);
						khachHang_DAO.themKhachHang(khachHang);
						JOptionPane.showMessageDialog(null, "Thêm khách hàng '" + maKhachHang + "' thành công!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Số điện thoại phải là số!\nEmail chỉ cho phép @gmail.com");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Thêm thất bại!");
				}

			}
		});
		panel_2.add(btnThem);

		JButton btnXoatrang = new JButton("Xóa Trắng");
		btnXoatrang.setForeground(new Color(165, 42, 42));
		btnXoatrang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoatrang.setBackground(Color.LIGHT_GRAY);
		btnXoatrang.setBounds(212, 32, 112, 27);
		panel_2.add(btnXoatrang);
		btnXoatrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textMaKH.setText("");
				textHoKH.setText("");
				textTenKH.setText("");
				textDiaChi.setText("");
				textSdt.setText("");
				textEmail.setText("");
			}
		});

		JButton btnCapnhat = new JButton("Cập Nhật");
		btnCapnhat.setForeground(new Color(165, 42, 42));
		btnCapnhat.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCapnhat.setBackground(Color.LIGHT_GRAY);
		btnCapnhat.setBounds(56, 69, 112, 27);
		panel_2.add(btnCapnhat);
		btnCapnhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn dòng cần cập nhật!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn cập nhật khách hàng '" + model.getValueAt(row, 0) + "' chứ?", "Cập nhật?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						try {
							if (Regex.ktSDT(textSdt.getText()) && Regex.ktEmail(textEmail.getText())) {
								KhachHang khachHang = new KhachHang((String) textMaKH.getText(),
										(String) textHoKH.getText(), (String) textTenKH.getText(),
										(String) textDiaChi.getText(), Integer.parseInt((String) textSdt.getText()),
										(String) textEmail.getText());
								khachHang_DAO.suaThongTinKhachHang(khachHang, (String) textMaKH.getText());
								JOptionPane.showMessageDialog(null,
										"Cập nhật thành công khách hàng '" + model.getValueAt(row, 0) + "'!");
							}
							else {
								JOptionPane.showMessageDialog(null, "Số điện thoại phải là số!\nEmail chỉ cho phép @gmail.com");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Cập nhật khách hàng '" + model.getValueAt(row, 0) + "' không thành công!");

						}
					}
				}
			}
		});

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(165, 42, 42));
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(212, 69, 112, 27);
		panel_2.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn dòng cần xóa!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn xóa khách hàng '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						try {
							khachHang_DAO.xoaKhachHangTheoMa((String) model.getValueAt(row, 0));
							JOptionPane.showMessageDialog(null,
									"Xóa thành công khách hàng '" + model.getValueAt(row, 0) + "'!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Xóa khách hàng '\" + model.getValueAt(row, 0) + \"' không thành công!");

						}
					}
				}
			}
		});

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(165, 42, 42));
		btnLamMoi.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLamMoi.setBackground(Color.LIGHT_GRAY);
		btnLamMoi.setBounds(136, 106, 112, 27);
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadDataKhachHang();
			}
		});
		panel_2.add(btnLamMoi);

		JLabel lblNewLabel_1_1 = new JLabel("Chức năng:");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(10, 352, 322, 27);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Danh Sách Khách Hàng:");
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

		JComboBox<String> cbTim = new JComboBox<String>();
		cbTim.setForeground(Color.RED);
		cbTim.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTim.setBounds(88, 25, 125, 21);
		add(cbTim);
		cbTim.addItem("");
		cbTim.addItem("Mã khách hàng");
		cbTim.addItem("Tên khách hàng");
		cbTim.addItem("Số điện thoại");

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(223, 19, 289, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Quản Lí Khách hàng\r\n");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 70, 1198, 37);
		add(lblNewLabel_3);

		JButton btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon(KhachHang_GUI.class.getResource("/image/magnifier.png")));
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
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn chức nằng để tìm kiếm!");
				} else {
					if (cbTim.getSelectedItem().toString().equals("Mã khách hàng")) {
						try {
							loadDataKhachHangTheoMa(khachHang_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();    
						}
					} else if (cbTim.getSelectedItem().toString().equals("Tên khách hàng")) {
						loadDataKhachHangTheoTen(khachHang_DAO, textField.getText());
					} else if (cbTim.getSelectedItem().toString().equals("Số điện thoại")) {
						try {
							loadDataKhachHangTheoSoDienThoai(khachHang_DAO, Integer.parseInt(textField.getText()));
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		add(btnTim);
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	public void connect() throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
	}

	public void loadDataKhachHang() {
		model.setRowCount(0);
		for (KhachHang khachHang : khachHang_DAO.getAllKhachHang()) {
			Object[] objects = { khachHang.getMa(), khachHang.getHo(), khachHang.getTen(), khachHang.getDiaChi(),
					khachHang.getSdt(), khachHang.getEmail() };
			model.addRow(objects);
		}
	}

	public void loadDataKhachHangTheoTen(KhachHang_DAO khachHang_DAO, String ten) {
		model.setRowCount(0);
		for (KhachHang khachHang : khachHang_DAO.getKhachHangTheoTen(ten)) {
			Object[] objects = { khachHang.getMa(), khachHang.getHo(), khachHang.getTen(), khachHang.getDiaChi(),
					khachHang.getSdt(), khachHang.getEmail() };
			model.addRow(objects);
		}
	}

	public void loadDataKhachHangTheoMa(KhachHang_DAO khachHang_DAO, String maKhachHang) throws SQLException {
		model.setRowCount(0);
		KhachHang khachHang = khachHang_DAO.getKhachHangTheoMa(maKhachHang);
		Object[] objects = { khachHang.getMa(), khachHang.getHo(), khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(), khachHang.getEmail()};
		model.addRow(objects);
	}

	public void loadDataKhachHangTheoSoDienThoai(KhachHang_DAO khachHang_DAO, int soDienThoai) throws SQLException {
		model.setRowCount(0);
		KhachHang khachHang = khachHang_DAO.getKhachHangTheoSoDienThoai(soDienThoai);
		Object[] objects = { khachHang.getMa(), khachHang.getHo(), khachHang.getTen(), khachHang.getDiaChi(), khachHang.getSdt(), khachHang.getEmail()};
		model.addRow(objects);
	}
}
