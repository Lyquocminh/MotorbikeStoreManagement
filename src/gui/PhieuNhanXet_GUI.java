package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PhieuNhanXet_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField textMaPhieu;
	private JTextField textLiDoBh;
	private JTextField textLoiThuocVe;
	private JTextField textGiaTien;
	private JTextField textLinhKien;

	/**
	 * Create the panel.
	 */
	public PhieuNhanXet_GUI() {
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
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, ""},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 phi\u1EBFu", "L\u00ED do b\u1EA3o h\u00E0nh", "L\u1ED7i thu\u1ED9c v\u1EC1", "Gi\u00E1 ti\u1EC1n", "Linh ki\u1EC7n", "M\u00E3 kh\u00E1ch h\u00E0ng", "M\u00E3 nh\u00E2n vi\u00EAn"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBorder(new LineBorder(Color.CYAN));
		panel_1.setBounds(812, 148, 376, 545);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Mã phiếu:");
		lblNewLabel_4.setForeground(new Color(165, 42, 42));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 10, 122, 34);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Lí do bảo hành:");
		lblNewLabel_4_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1.setBounds(10, 45, 122, 34);
		panel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Lỗi thuộc về:");
		lblNewLabel_4_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1.setBounds(10, 89, 115, 34);
		panel_1.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Giá tiền:");
		lblNewLabel_4_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 133, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Linh kiện:");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_1.setBounds(10, 185, 105, 34);
		panel_1.add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_2 = new JLabel("Mã khách hàng:");
		lblNewLabel_4_1_1_1_2.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2.setBounds(10, 229, 122, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2);
		
		textMaPhieu = new JTextField();
		textMaPhieu.setFont(new Font("Arial", Font.PLAIN, 16));
		textMaPhieu.setBounds(160, 18, 206, 24);
		panel_1.add(textMaPhieu);
		textMaPhieu.setColumns(10);
		
		textLiDoBh = new JTextField();
		textLiDoBh.setFont(new Font("Arial", Font.PLAIN, 16));
		textLiDoBh.setColumns(10);
		textLiDoBh.setBounds(160, 53, 206, 24);
		panel_1.add(textLiDoBh);
		
		textLoiThuocVe = new JTextField();
		textLoiThuocVe.setFont(new Font("Arial", Font.PLAIN, 16));
		textLoiThuocVe.setColumns(10);
		textLoiThuocVe.setBounds(160, 97, 206, 24);
		panel_1.add(textLoiThuocVe);
		
		textGiaTien = new JTextField();
		textGiaTien.setFont(new Font("Arial", Font.PLAIN, 16));
		textGiaTien.setColumns(10);
		textGiaTien.setBounds(160, 141, 206, 24);
		panel_1.add(textGiaTien);
		
		textLinhKien = new JTextField();
		textLinhKien.setFont(new Font("Arial", Font.PLAIN, 16));
		textLinhKien.setColumns(10);
		textLinhKien.setBounds(160, 185, 206, 24);
		panel_1.add(textLinhKien);
		
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
		panel_2.add(btnThem);
		
		JButton btnXoatrang = new JButton("Xóa Trắng");
		btnXoatrang.setForeground(new Color(165, 42, 42));
		btnXoatrang.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoatrang.setBackground(Color.LIGHT_GRAY);
		btnXoatrang.setBounds(212, 32, 112, 27);
		panel_2.add(btnXoatrang);
		
		JButton btnCapnhat = new JButton("Cập Nhật");
		btnCapnhat.setForeground(new Color(165, 42, 42));
		btnCapnhat.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCapnhat.setBackground(Color.LIGHT_GRAY);
		btnCapnhat.setBounds(56, 69, 112, 27);
		panel_2.add(btnCapnhat);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(165, 42, 42));
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 16));
		btnXoa.setBackground(Color.LIGHT_GRAY);
		btnXoa.setBounds(212, 69, 112, 27);
		panel_2.add(btnXoa);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setForeground(new Color(165, 42, 42));
		btnLuu.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLuu.setBackground(Color.LIGHT_GRAY);
		btnLuu.setBounds(136, 106, 112, 27);
		panel_2.add(btnLuu);
		
		JLabel lblNewLabel_1_1 = new JLabel("Chức năng:");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(10, 352, 322, 27);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_4_1_1_1_2_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_4_1_1_1_2_1.setForeground(new Color(165, 42, 42));
		lblNewLabel_4_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_4_1_1_1_2_1.setBounds(10, 267, 122, 34);
		panel_1.add(lblNewLabel_4_1_1_1_2_1);
		
		JComboBox cbMaKH = new JComboBox();
		cbMaKH.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMaKH.setBounds(160, 238, 206, 21);
		panel_1.add(cbMaKH);
		
		JComboBox cbMaNV = new JComboBox();
		cbMaNV.setFont(new Font("Arial", Font.PLAIN, 16));
		cbMaNV.setBounds(160, 276, 206, 21);
		panel_1.add(cbMaNV);
		
		JLabel lblNewLabel = new JLabel("Danh Sách Phiếu:");
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
		
		JComboBox cbTim = new JComboBox();
		cbTim.setForeground(Color.RED);
		cbTim.setFont(new Font("Arial", Font.PLAIN, 16));
		cbTim.setBounds(88, 25, 125, 21);
		add(cbTim);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(223, 19, 289, 27);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Phiếu Nhận Xét\r\n");
		lblNewLabel_3.setBackground(new Color(165, 42, 42));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 70, 1198, 37);
		add(lblNewLabel_3);
		
		JButton btnTim = new JButton("Tìm Kiếm");
		btnTim.setHorizontalAlignment(SwingConstants.LEFT);
		btnTim.setIcon(new ImageIcon("D:\\Study\\OOPJava\\21091031_TrinhMinhKhaa\\Motorbike-Store-Project\\data\\image\\icons8-search-30.png"));
		btnTim.setForeground(new Color(165, 42, 42));
		btnTim.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTim.setBackground(Color.LIGHT_GRAY);
		btnTim.setBounds(522, 20, 133, 27);
		btnTim.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTim.setVerticalTextPosition(SwingConstants.CENTER);
		btnTim.setHorizontalAlignment(SwingConstants.LEFT);
		add(btnTim);

	}
}
