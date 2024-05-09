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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

import connect.ConnectDB;
import dao.PhatSinhMa_DAO;
import dao.ThongTinXe_DAO;
import entity.ThongTinXe;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class ThongTinXe_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField textMaLoaiXe;
	private JTextField textTenLoaiXe;
	private JTextField textGiaNiemYet;
	private JTextField textGiaGiam;
	private JTextField textPhienBan;
	private JTextArea textMoTa;
	private DefaultTableModel model;
	private ThongTinXe_DAO thongTinXe_DAO;
	private PhatSinhMa_DAO phatSinhMa_DAO;
	private JComboBox<String> cbTim;

	/**
	 * Create the panel.
	 */
	public ThongTinXe_GUI() throws SQLException {

		// connect
		connect();

		// khai bao dao
		thongTinXe_DAO = new ThongTinXe_DAO();
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
		scrollPane.setBounds(10, 10, 1295, 939);
		panel.add(scrollPane);

		String[] columns = { "M\u00E3 lo\u1EA1i xe", "T\u00EAn Lo\u1EA1i Xe", "Gi\u00E1 Ni\u00EAm Y\u1EBFt",
				"Gi\u00E1 Gi\u1EA3m", "M\u00F4 T\u1EA3 Xe", "Phi\u00EAn B\u1EA3n" };
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setRowHeight(25);
		table.setDefaultEditor(Object.class, null);
		table.setToolTipText("Chọn thông tin xe để thực hiện chức năng");
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
				textMaLoaiXe.setText((String) model.getValueAt(row, 0));
				textTenLoaiXe.setText((String) model.getValueAt(row, 1));
				textGiaNiemYet.setText(model.getValueAt(row, 2) + "");
				textGiaGiam.setText(model.getValueAt(row, 3) + "");
				textMoTa.setText((String) model.getValueAt(row, 4));
				textPhienBan.setText((String) model.getValueAt(row, 5));
			}
		});

		scrollPane.setViewportView(table);

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
				// TODO Auto-generated method stub
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

		JLabel lblNewLabel_4 = new JLabel("Mã loại xe:");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 122, 34);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Tên loại xe:");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(10, 45, 122, 34);
		panel_1.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Giá niêm yết:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(10, 89, 115, 34);
		panel_1.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Giá giảm");
		lblNewLabel_4_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 133, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Mô tả xe:\r\n");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 185, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("Phiên bản:\r\n\r\n\r\n");
		lblNewLabel_4_1_1_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2.setBounds(10, 294, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2);

		textMaLoaiXe = new JTextField();
		textMaLoaiXe.setFont(new Font("Arial", Font.PLAIN, 16));
		textMaLoaiXe.setBounds(160, 18, 206, 24);
		textMaLoaiXe.setEditable(false);
		panel_1.add(textMaLoaiXe);
		textMaLoaiXe.setColumns(10);

		textTenLoaiXe = new JTextField();
		textTenLoaiXe.setFont(new Font("Arial", Font.PLAIN, 16));
		textTenLoaiXe.setColumns(10);
		textTenLoaiXe.setBounds(160, 53, 206, 24);
		panel_1.add(textTenLoaiXe);

		textGiaNiemYet = new JTextField();
		textGiaNiemYet.setFont(new Font("Arial", Font.PLAIN, 16));
		textGiaNiemYet.setColumns(10);
		textGiaNiemYet.setBounds(160, 97, 206, 24);
		panel_1.add(textGiaNiemYet);

		textGiaGiam = new JTextField();
		textGiaGiam.setFont(new Font("Arial", Font.PLAIN, 16));
		textGiaGiam.setColumns(10);
		textGiaGiam.setBounds(160, 141, 206, 24);
		panel_1.add(textGiaGiam);

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
				if (!isNull()) {
					try {
						if (Regex.isNumber(textGiaNiemYet.getText()) && Regex.isNumber(textGiaGiam.getText())) {
							String maLoaiXe = phatSinhMa_DAO.getMaLoaiXe();
							String tenLoaiXe = textTenLoaiXe.getText();
							int giaNiemYet = Integer.parseInt(textGiaNiemYet.getText());
							int giaGiam = Integer.parseInt(textGiaGiam.getText());
							String moTa = textMoTa.getText();
							String phienBan = textPhienBan.getText();
							ThongTinXe thongTinXe = new ThongTinXe(maLoaiXe, tenLoaiXe, giaNiemYet, giaGiam, moTa,
									phienBan);
							thongTinXe_DAO.themThongTinXe(thongTinXe);
							JOptionPane.showMessageDialog(null, "Thêm thành công!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Giá phải là số!");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Thêm thất bại");
						e1.printStackTrace();
					}
				}
			}
		});
		panel_2.add(btnThem);

		JButton btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setForeground(new Color(165, 42, 42));
		btnXoaTrang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoaTrang.setBackground(Color.LIGHT_GRAY);
		btnXoaTrang.setBounds(212, 32, 112, 27);
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		panel_2.add(btnXoaTrang);

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
					JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn dòng cần cập nhật!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn cập nhật loại xe '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						try {
							if (Regex.isNumber(textGiaNiemYet.getText()) && Regex.isNumber(textGiaGiam.getText())) {
								String maLoaiXe = textMaLoaiXe.getText();
								String tenLoaiXe = textTenLoaiXe.getText();
								int giaNiemYet = Integer.parseInt(textGiaNiemYet.getText());
								int giaGiam = Integer.parseInt(textGiaGiam.getText());
								String moTa = textMoTa.getText();
								String phienBan = textPhienBan.getText();
								ThongTinXe thongTinXe = new ThongTinXe(maLoaiXe, tenLoaiXe, giaNiemYet, giaGiam, moTa,
										phienBan);
								thongTinXe_DAO.suaThongTinXe(thongTinXe, maLoaiXe);
								JOptionPane.showMessageDialog(null,
										"Cập nhật thành công loại xe '" + model.getValueAt(row, 0) + "'!");
							}
							else {
								JOptionPane.showMessageDialog(null, "Giá phải là số!");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Cập nhật không thành công loại xe '" + model.getValueAt(row, 0) + "'!");

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
					JOptionPane.showInternalMessageDialog(null, "Bạn phải chọn dòng cần xóa!");
				} else {
					int option = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn xóa loại xe '" + model.getValueAt(row, 0) + "' chứ?", "Xóa?",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						try {
							thongTinXe_DAO.xoaThongTinXeTheoMaLoaiXe((String) model.getValueAt(row, 0));
							JOptionPane.showMessageDialog(null,
									"Xóa thành công loại xe '" + model.getValueAt(row, 0) + "'!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"Bạn phải xóa các xe, xe trong kho và chi tiết hóa đơn có mã loại xe '"
											+ model.getValueAt(row, 0) + "' trước!");

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
		btnLmMi.setBounds(136, 110, 112, 27);
		btnLmMi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					loadDataThongTinXe(thongTinXe_DAO);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnLmMi);

		JLabel lblNewLabel_1_1 = new JLabel("Chức năng:");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(10, 352, 322, 27);
		panel_1.add(lblNewLabel_1_1);

		textMoTa = new JTextArea();
		textMoTa.setColumns(3);
		textMoTa.setBorder(new LineBorder(Color.LIGHT_GRAY));
		textMoTa.setFont(new Font("Arial", Font.PLAIN, 16));
		textMoTa.setBounds(160, 192, 206, 85);
		panel_1.add(textMoTa);

		textPhienBan = new JTextField();
		textPhienBan.setFont(new Font("Arial", Font.PLAIN, 16));
		textPhienBan.setColumns(10);
		textPhienBan.setBounds(160, 299, 206, 24);
		panel_1.add(textPhienBan);

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
		cbTim.addItem("");
		cbTim.addItem("Mã loại xe");
		cbTim.addItem("Tên loại xe");
		cbTim.setToolTipText("Tìm theo mã loại và tên loại của xe");
		add(cbTim);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(223, 19, 289, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Thông Tin Xe");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 70, 1198, 37);
		add(lblNewLabel_3);

		JButton btnTim = new JButton("Tìm Kiếm");
		btnTim.setIcon(new ImageIcon(ThongTinXe_GUI.class.getResource("/image/magnifier.png")));
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
					if (cbTim.getSelectedItem().toString().equals("Mã loại xe")) {
						try {
							loadDataThongTinXeTheoMaLoaiXe(thongTinXe_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							loadDataThongTinXeTheoTenLoaiXe(thongTinXe_DAO, textField.getText());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		add(btnTim);

		// input data from sql server
		loadDataThongTinXe(thongTinXe_DAO);
	}

	public void connect() throws SQLException {
		ConnectDB.getInstance();
		ConnectDB.connect();
	}

	public void loadDataThongTinXe(ThongTinXe_DAO thongTinXe_DAO) throws SQLException {
		model.setRowCount(0);
		for (ThongTinXe thongTinXe : thongTinXe_DAO.getAllThongTinXe()) {
			Object[] objects = { thongTinXe.getMaLoaiXe(), thongTinXe.getTenLoaiXe(), thongTinXe.getGiaNiemYet(),
					thongTinXe.getGiaGiam(), thongTinXe.getMoTaXe(), thongTinXe.getPhienBan() };
			model.addRow(objects);
		}
	}

	public void loadDataThongTinXeTheoMaLoaiXe(ThongTinXe_DAO thongTinXe_DAO, String maLoaiXe) throws SQLException {
		model.setRowCount(0);
		ThongTinXe thongTinXe = thongTinXe_DAO.getThongTinXeTheoMa(maLoaiXe);
		Object[] objects = { thongTinXe.getMaLoaiXe(), thongTinXe.getTenLoaiXe(), thongTinXe.getGiaNiemYet(),
				thongTinXe.getGiaGiam(), thongTinXe.getMoTaXe(), thongTinXe.getPhienBan() };
		model.addRow(objects);
	}
	
	public void loadDataThongTinXeTheoTenLoaiXe(ThongTinXe_DAO thongTinXe_DAO, String tenLoaiXe) throws SQLException {
		model.setRowCount(0);
		ThongTinXe thongTinXe = thongTinXe_DAO.getThongTinXeTheoTen(tenLoaiXe);
		Object[] objects = { thongTinXe.getMaLoaiXe(), thongTinXe.getTenLoaiXe(), thongTinXe.getGiaNiemYet(),
				thongTinXe.getGiaGiam(), thongTinXe.getMoTaXe(), thongTinXe.getPhienBan() };
		model.addRow(objects);
	}

	public boolean isNull() {
		if (textTenLoaiXe.getText().equals("") || textGiaNiemYet.getText().equals("")
				|| textGiaGiam.getText().equals("") || textMoTa.getText().equals("")
				|| textPhienBan.getText().equals(""))
			return true;
		return false;
	}

	public void xoaTrang() {
		textMaLoaiXe.setText("");
		textTenLoaiXe.setText("");
		textGiaNiemYet.setText("");
		textGiaGiam.setText("");
		textMoTa.setText("");
		textPhienBan.setText("");
	}
}
