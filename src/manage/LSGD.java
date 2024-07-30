package manage;

import java.sql.Date;
import java.sql.Timestamp;
import java.io.Serializable;
public class LSGD implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Timestamp date;
	private int balance;
	private int deposit;
	private int withdrawl;
	public LSGD(int id, Timestamp date, int balance, int deposit, int withdrawl) {
		this.id = id;
		this.date = date;
		this.balance = balance;
		this.deposit = deposit;
		this.withdrawl = withdrawl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getWithdrawl() {
		return withdrawl;
	}
	public void setWithdrawl(int withdrawl) {
		this.withdrawl = withdrawl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
