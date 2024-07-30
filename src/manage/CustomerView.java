package manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.List;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerView extends JFrame {

	private JPanel contentPane;
	private RMIClient rmi = new RMIClient();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public CustomerView() {
		//initUI(phone);
	}
	public void initUI(String phone) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel textAreaCus = new JLabel();
		textAreaCus.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textAreaCus.setBackground(SystemColor.control);
		textAreaCus.setBounds(315, 94, 326, 48);
		contentPane.add(textAreaCus);
		try {
			textAreaCus.setText(rmi.callFunction().getNameUser(phone));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JLabel lblCus = new JLabel("Khách Hàng\r\n:");
		lblCus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCus.setBounds(188, 98, 127, 40);
		contentPane.add(lblCus);
		
		JLabel textAreaSth = new JLabel();
		textAreaSth.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textAreaSth.setBackground(SystemColor.menu);
		textAreaSth.setBounds(249, 193, 168, 48);
		contentPane.add(textAreaSth);
		try {
			textAreaSth.setText(rmi.callFunction().getNC(phone));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel lblSTh = new JLabel("Số thẻ:\r\n");
		lblSTh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSTh.setBounds(155, 197, 110, 40);
		contentPane.add(lblSTh);
		
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
		JButton btnChangepass = new JButton("Thay đổi mật khẩu");
		btnChangepass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChangepass.setBounds(43, 324, 179, 55);
		contentPane.add(btnChangepass);
		btnChangepass.addActionListener(e->{
			ChangePass pass = new ChangePass();
			pass.initUI(phone);
		});
		JButton btnSD = new JButton("Số dư");
		btnSD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSD.setBounds(280, 324, 122, 55);
		contentPane.add(btnSD);
		btnSD.addActionListener(e->{
			try {
			JOptionPane.showMessageDialog(null, "Số dư hiện tại của bạn là: "+rmi.callFunction().getSD(phone)+"VNĐ");	
			} catch (Exception e2) {
			// TODO: handle exception
				e2.printStackTrace();
			}
		});
		JButton btnNpRtTin = new JButton("Nạp/Rút tiền");
		btnNpRtTin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNpRtTin.setBounds(430, 324, 135, 55);
		contentPane.add(btnNpRtTin);
		btnNpRtTin.addActionListener(e->{
			try {
				JNapRut nrv = new JNapRut();
				nrv.initUI(rmi.callFunction().getNC(phone));
			} catch (Exception e3) {
				// TODO: handle exception
			}
		});
		
		JButton btnLchSGd = new JButton("Lịch sử GD");
		btnLchSGd.addActionListener(e->{
			historyGDView hsv = new historyGDView();
			try {
				hsv.initUI(rmi.callFunction().getNC(phone));
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnLchSGd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLchSGd.setBounds(602, 324, 122, 55);
		contentPane.add(btnLchSGd);
		
		JLabel lblSTh_1 = new JLabel("Ngày lập thẻ:\r\n");
		lblSTh_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSTh_1.setBounds(416, 197, 127, 40);
		contentPane.add(lblSTh_1);
		
		JLabel txtrEwrewfwfwererewrewe = new JLabel();
		txtrEwrewfwfwererewrewe.setFont(new Font("Monospaced", Font.PLAIN, 20));
		txtrEwrewfwfwererewrewe.setBackground(SystemColor.menu);
		txtrEwrewfwfwererewrewe.setBounds(545, 193, 179, 48);
		try {
			txtrEwrewfwfwererewrewe.setText(rmi.callFunction().getDate(phone));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(txtrEwrewfwfwererewrewe);
		setVisible(true);
	}
}
