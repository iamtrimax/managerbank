package manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ChangePass extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private RMIClient rmi = new RMIClient();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangePass() {
		
	}
	public void initUI(String phone) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(168, 27, 215, 47);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField_1.setBounds(168, 99, 215, 47);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField_2.setBounds(168, 175, 215, 47);
		contentPane.add(passwordField_2);
		
		JButton btnNewButton = new JButton("Xác nhận\r\n");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(232, 254, 153, 47);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e->{
			String mkc = new String(passwordField.getPassword());
			String mkm = new String(passwordField_1.getPassword());
			String mkcf = new String(passwordField_2.getPassword());
			try {
				int auth = rmi.callFunction().auth(phone, mkc);
				if(auth==-1)
					JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng","Thay đổi mật khẩu", JOptionPane.ERROR_MESSAGE);
				else if(auth==1) {
					if(mkm.equals(mkcf)) {
						boolean update = rmi.callFunction().updatePass(phone, mkm);
						
						JOptionPane.showMessageDialog(null, "Đã thay đổi mật khẩu","Thay đổi mật khẩu", JOptionPane.INFORMATION_MESSAGE);
						
						dispose();
					}else
						JOptionPane.showMessageDialog(null, "Mật khẩu mới và mật khẩu xác nhận không khớp"," thay đổi mật khẩu", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		JLabel lblNewLabel = new JLabel("MK cũ\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 25, 99, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MK mới");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 97, 99, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("xác nhận MK\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(25, 173, 99, 47);
		contentPane.add(lblNewLabel_2);
		setVisible(true);
	}
}
