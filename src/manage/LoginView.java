package manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private RMIClient rmi = new RMIClient();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public LoginView() {
		
	}
	public void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblsdt = new JLabel("Số điện thoại");
		lblsdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblsdt.setBounds(33, 87, 115, 27);
		contentPane.add(lblsdt);
		
		JLabel lblpw = new JLabel("Mật khẩu");
		lblpw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpw.setBounds(33, 144, 115, 27);
		contentPane.add(lblpw);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 139, 203, 42);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(133, 82, 203, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(174, 202, 122, 36);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e->{
			String textsdt = textField.getText();
			String pw = new String(passwordField.getPassword());
			try {
				int auth = rmi.callFunction().auth(textsdt, pw);
				System.out.println(pw);
				if(auth==1) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công","Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
					if(textsdt.equals("0909000090")){
						JOptionPane.showMessageDialog(null, "Chào mừng bạn quay trở lại với trang quản trị dành cho ADMIN","Admin", JOptionPane.INFORMATION_MESSAGE);
						Jmanagement manager = new Jmanagement();
						manager.initUI();
						dispose();
					}
					else {
						CustomerView cus = new CustomerView();
						cus.initUI(textsdt);
						dispose();
					}
				}
				else if(auth==-1) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại","Đăng nhập", JOptionPane.ERROR_MESSAGE);
				}
				else if(auth==0) {
					JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khoá","Đăng nhập", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		JLabel lblNewLabel = new JLabel("Đăng nhập tài khoản\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(100, 28, 236, 27);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
}
