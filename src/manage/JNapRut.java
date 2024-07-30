package manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JNapRut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textRut;
	private RMIClient rmi = new RMIClient();
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public JNapRut() {
		
	}
	public void initUI(String numberCard) {
		setTitle("NẠP/RÚT TIỀN TÀI KHOẢN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNap = new JPanel();
		panelNap.setForeground(new Color(142, 240, 240));
		panelNap.setBackground(Color.LIGHT_GRAY);
		panelNap.setBounds(10, 10, 288, 293);
		contentPane.add(panelNap);
		panelNap.setLayout(null);
		
		JLabel lblNap = new JLabel("NẠP TIỀN");
		lblNap.setBackground(new Color(142, 240, 240));
		lblNap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNap.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNap.setForeground(Color.BLACK);
		lblNap.setBounds(10, 67, 268, 27);
		panelNap.add(lblNap);
		
		JLabel lblText1 = new JLabel("Nhập số tiền cần nạp: ");
		lblText1.setHorizontalAlignment(SwingConstants.CENTER);
		lblText1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblText1.setBounds(10, 104, 268, 27);
		panelNap.add(lblText1);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 255));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 141, 268, 36);
		panelNap.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("NẠP");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(84, 187, 122, 34);
		panelNap.add(btnNewButton);
		btnNewButton.addActionListener(e->{
			try {
				int currbalnce = Integer.parseInt(rmi.callFunction().getSDbyNC(numberCard));
				int deposit = Integer.parseInt(textField.getText());
				int balance = currbalnce + deposit;
				boolean update = rmi.callFunction().depositUpdate(numberCard, balance);
				if(update)
					JOptionPane.showMessageDialog(null, "Giao dịch thành công","Nạp tiền", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Lỗi giao dịch","Rút tiền", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		});
		JPanel panelRut = new JPanel();
		panelRut.setBackground(Color.LIGHT_GRAY);
		panelRut.setBounds(308, 10, 288, 293);
		contentPane.add(panelRut);
		panelRut.setLayout(null);
		
		JLabel lblRut = new JLabel("RÚT TIỀN");
		lblRut.setBounds(10, 31, 268, 26);
		lblRut.setHorizontalAlignment(SwingConstants.CENTER);
		lblRut.setForeground(Color.BLACK);
		lblRut.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRut.setBackground(new Color(142, 240, 240));
		panelRut.add(lblRut);
		
		JLabel lblNewLabel = new JLabel("Rút tối thiểu:     50.000 ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 56, 268, 34);
		panelRut.add(lblNewLabel);
		
		JLabel lblRtTia = new JLabel("Rút tối đa:         3.000.000 ");
		lblRtTia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRtTia.setBounds(10, 87, 268, 34);
		panelRut.add(lblRtTia);
		
		textRut = new JTextField();
		textRut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textRut.setBounds(10, 141, 268, 34);
		panelRut.add(textRut);
		textRut.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("RÚT");
		btnNewButton_1.setBackground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(80, 185, 122, 34);
		panelRut.add(btnNewButton_1);
		btnNewButton_1.addActionListener(e->{
			try {
				int curBalance = Integer.parseInt(rmi.callFunction().getSDbyNC(numberCard));
				int withDrawl_amount = Integer.parseInt(textRut.getText());
				if(withDrawl_amount>curBalance) {
					JOptionPane.showMessageDialog(null, "Số dư trong tài khoản của bạn không đủ","Rút tiền", JOptionPane.ERROR_MESSAGE);
				}else {
					if(withDrawl_amount<50000 || withDrawl_amount>3000000)
						JOptionPane.showMessageDialog(null, "Số tiền bạn rút không hợp lệ>50000 và <3000000","Rút tiền", JOptionPane.ERROR_MESSAGE);
					else {
						int balance = curBalance - withDrawl_amount;
						boolean update = rmi.callFunction().depositUpdate(numberCard, balance);
						if(update)
							JOptionPane.showMessageDialog(null, "Giao dịch thành công","Rút tiền", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "Lỗi giao dịch","Rút tiền", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		setVisible(true);
	}
}
