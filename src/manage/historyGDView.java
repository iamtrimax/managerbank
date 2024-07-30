package manage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class historyGDView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tbmd;
	private RMIClient rmi = new RMIClient();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public historyGDView() {
	
	}
	public void loadDataToTable(String nbc) {
		tbmd.setRowCount(0);
		try {
			List<LSGD> lsgd = rmi.callFunction().getLSGD(nbc);
			for(LSGD acus:lsgd) {
				tbmd.addRow(new String[] {acus.getId()+"", acus.getDate()+"",acus.getBalance()+"",acus.getDeposit()+"", acus.getWithdrawl()+""});
				tbmd.fireTableDataChanged();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initUI(String nbc) {
		setTitle("LỊCH SỬ GIAO DỊCH");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1023, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 999, 253);
		contentPane.add(scrollPane);
		String[] column = {"mã giao dịch","Ngày giao dịch","Số dư","Nạp","rút"};
		String [][] data = {};
		tbmd = new DefaultTableModel(data, column);
		
		table = new JTable(tbmd);
		scrollPane.setViewportView(table);
		loadDataToTable(nbc);
		setVisible(true);
		
		 new Thread(new Runnable() {
	            @Override
	            public void run() {
	                while (true) {
	                    try {
	                        Thread.sleep(30000); // Chờ 30 giây
	                        loadDataToTable(nbc); // Cập nhật dữ liệu
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }).start();
	
	}
}
