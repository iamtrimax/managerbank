package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Client;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Jmanagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textHoTen;
	private JTextField textCCCD;
	private JTextField textSDT;
	private DefaultTableModel tbmd;
	private JTable table;
	private ButtonGroup group;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	List<DataInformation> listCus;
	private JTextField textNamSinh;
	
	private RMIClient rmi = new RMIClient();
	
	/**
	 * Launch the application.
	 */
	public void loadDataToTable() {
		tbmd.setRowCount(0);
		try {
			listCus = rmi.callFunction().getCustomer();
			int i = 1;
			for(DataInformation acus:listCus) {
				tbmd.addRow(new String[] {i+"", acus.getSothe(), acus.getHoTen(), acus.getNamSinh(), acus.getCCCD(), acus.getGioiTinh(), acus.getSDT()});
				tbmd.fireTableDataChanged();
				i++;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void displayCustomerByName(String name) {
		tbmd.setRowCount(0);
		try {
			listCus = rmi.callFunction().getCustomerByName(name);
			int i = 1;
			for(DataInformation acus:listCus) {
				tbmd.addRow(new String[] {i+"", acus.getSothe(), acus.getHoTen(), acus.getNamSinh(), acus.getCCCD(), acus.getGioiTinh(), acus.getSDT()});
				tbmd.fireTableDataChanged();
				i++;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getSelection() {
		 int selectedRow = table.getSelectedRow();
		    
		    // Kiểm tra xem có hàng được chọn không
		    if (selectedRow != -1 && selectedRow < table.getRowCount()) {
		        textHoTen.setText(table.getValueAt(selectedRow, 2).toString());
		        textNamSinh.setText(table.getValueAt(selectedRow, 3).toString());
		        textCCCD.setText(table.getValueAt(selectedRow, 4).toString());
		        if(table.getValueAt(selectedRow, 5).toString().equals("Nam"))
		        	rdbtnNam.setSelected(true);
		        else
		        	rdbtnNu.setSelected(true);
		        textSDT.setText(table.getValueAt(selectedRow, 6).toString());
		    }
	}
	/**
	 * Create the frame.
	 */
	public Jmanagement() {
		
	}
	public void initUI() {
		setForeground(new Color(102, 255, 255));
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("CHƯƠNG TRÌNH QUẢN LÍ THÔNG TIN TÀI KHOẢNG NGÂN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 627);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlLtHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDX = new JLabel("Đăng xuất\r\n");
		lblDX.setBounds(10, 10, 155, 21);
		contentPane.add(lblDX);
		// Thêm ActionListener để theo dõi sự thay đổi trong JComboBox
		lblDX.addMouseListener(new MouseAdapter() {
			@Override
		    public void mouseClicked(MouseEvent e) {
		            // Thực hiện hành động khi chỉ mục là 1
		            LoginView lgV = new LoginView();
		            lgV.initUI();
		            System.out.println("dfdssdf");
		            dispose();
		    }
		});
		JLabel lblNewLabel = new JLabel("Họ và tên:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(35, 29, 85, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNmSinh = new JLabel("Năm Sinh:");
		lblNmSinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmSinh.setBounds(35, 77, 85, 38);
		contentPane.add(lblNmSinh);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCccd.setBounds(35, 125, 85, 38);
		contentPane.add(lblCccd);
		
		JLabel lblNmSinh_1_1 = new JLabel("Giới tính:");
		lblNmSinh_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNmSinh_1_1.setBounds(35, 173, 85, 38);
		contentPane.add(lblNmSinh_1_1);
		
		JLabel lblSinThoi = new JLabel("SĐT:");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSinThoi.setBounds(35, 221, 85, 38);
		contentPane.add(lblSinThoi);
		
		textHoTen = new JTextField();
		textHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textHoTen.setBounds(130, 33, 234, 31);
		contentPane.add(textHoTen);
		textHoTen.setColumns(10);
		
		textCCCD = new JTextField();
		textCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCCCD.setColumns(10);
		textCCCD.setBounds(130, 129, 234, 31);
		contentPane.add(textCCCD);
		
		textSDT = new JTextField();
		textSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textSDT.setColumns(10);
		textSDT.setBounds(130, 225, 234, 31);
		contentPane.add(textSDT);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 293, 731, 287);
		contentPane.add(scrollPane);
		
		
		String data[][]= {};
		String[] column = {"STT ", "Số thẻ","H\u1ECD v\u00E0 T\u00EAn ", "N\u0103m Sinh ", "CCCD ", "Gi\u1EDBi T\u00EDnh ", "SDT "};
		tbmd = new DefaultTableModel(data, column);
		table = new JTable(tbmd);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		loadDataToTable();
		table.getSelectionModel().addListSelectionListener(e->{
			getSelection();
		});
		
		rdbtnNam = new JRadioButton("Nam ");
		rdbtnNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNam.setBounds(130, 178, 103, 28);
		contentPane.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ ");
		rdbtnNu.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnNu.setBounds(261, 178, 103, 28);
		contentPane.add(rdbtnNu);
		
		group = new ButtonGroup();
		group.add(rdbtnNam);
		group.add(rdbtnNu);
		
		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String HoTen = textHoTen.getText();
					String NamSinh = textNamSinh.getText();
					String CCCD = textCCCD.getText();
					String GioiTinh = "Nam";
					if (rdbtnNu.isSelected()) GioiTinh = "Nu";
					String SDT = textSDT.getText();
					boolean add = rmi.callFunction().addCustomer(CCCD, HoTen, SDT, Integer.parseInt(NamSinh), GioiTinh);
					if(add)
						JOptionPane.showMessageDialog(null, "Thêm thành công khách hàng mới","Thêm khách hàng", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Thao tác không thành công","Thêm khách hàng", JOptionPane.ERROR_MESSAGE);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				loadDataToTable();
				textHoTen.setText("");
				textCCCD.setText("");
				textSDT.setText("");
				textHoTen.requestFocus();
			}
		});
		btnThem.setForeground(SystemColor.textText);
		btnThem.setBackground(new Color(153, 204, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(482, 32, 146, 32);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("XÓA ");
		btnXoa.setForeground(SystemColor.textText);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(new Color(255, 0, 0));
		btnXoa.setBounds(482, 224, 146, 32);
		contentPane.add(btnXoa);
		btnXoa.addActionListener(e->{
			String userInput = JOptionPane.showInputDialog(null, "nhập vào số tài khoản cần xoá");
			try {
				boolean delete = rmi.callFunction().deleteCustomer(userInput);
				if(delete)
					JOptionPane.showMessageDialog(null, "Xoá khách hàng thành công","xoá khách hàng", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Thao tác thất bại","xoá khách hàng", JOptionPane.ERROR_MESSAGE);
				loadDataToTable();
					
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		JButton btnSua = new JButton("SỬA ");
		btnSua.setForeground(SystemColor.textText);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(new Color(153, 204, 255));
		btnSua.setBounds(482, 80, 146, 32);
		contentPane.add(btnSua);
		btnSua.addActionListener(e->{
			try {
				String HoTen = textHoTen.getText();
				String NamSinh = textNamSinh.getText();
				String CCCD = textCCCD.getText();
				String GioiTinh = "Nam";
				if (rdbtnNu.isSelected()) GioiTinh = "Nu";
				String SDT = textSDT.getText();
				boolean update = rmi.callFunction().updateCustomer(CCCD, HoTen, SDT, Integer.parseInt(NamSinh), GioiTinh);
				if(update)
					JOptionPane.showMessageDialog(null, "Đã update thành công","update", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Thao tác không thành công","update", JOptionPane.ERROR_MESSAGE);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			loadDataToTable();
		});
		JButton btnTimKiem = new JButton("TÌM KIẾM");
		btnTimKiem.setForeground(SystemColor.textText);
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setBackground(new Color(153, 204, 255));
		btnTimKiem.setBounds(482, 128, 146, 32);
		contentPane.add(btnTimKiem);
		btnTimKiem.addActionListener(e->{
			String name = JOptionPane.showInputDialog(null, "Nhập tên cần tìm");
			displayCustomerByName(name);
		});
		
		textNamSinh = new JTextField();
		textNamSinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNamSinh.setColumns(10);
		textNamSinh.setBounds(130, 85, 117, 31);
		contentPane.add(textNamSinh);
		
		JButton btnNapRut = new JButton("NẠP/RÚT TIỀN");
		btnNapRut.setForeground(SystemColor.textText);
		btnNapRut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNapRut.setBackground(new Color(153, 204, 255));
		btnNapRut.setBounds(482, 176, 146, 32);
		contentPane.add(btnNapRut);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReset.setBounds(656, 262, 85, 21);
		contentPane.add(btnReset);
		btnReset.addActionListener(e->{
			loadDataToTable();
		});
		btnNapRut.addActionListener(e->{
			String userInput = JOptionPane.showInputDialog(null, "nhập vào số tài khoản");
			JNapRut NRview = new JNapRut();
			if(userInput!=null)
				NRview.initUI(userInput);
			else
				JOptionPane.showMessageDialog(null, "Thao tác đã huỷ","Nạp/ rút tiền", JOptionPane.ERROR_MESSAGE);
		});
		setVisible(true);
	}
}
