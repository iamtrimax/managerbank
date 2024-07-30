package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Data.Database;
import manage.DataInformation;
import manage.LSGD;

public class BankServices extends UnicastRemoteObject implements BankInterface{
	protected BankServices() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Database data = new Database();
	@Override
	public boolean addCustomer(String cccd, String name, String numberphone, int yBirth, String sex) throws RemoteException{
		String numbercard = RandomNumberCard();
		boolean addCus = data.addCustomer(numbercard, cccd, name, numberphone, yBirth, sex);
		if(addCus)
			return true;
		return false;
	}
	
	private boolean isNumberExist(String numberCard) {
		ResultSet rs = data.getNumberCard(numberCard);
		if(rs==null)
			return false;
		return true;
	}
	private String RandomNumberCard() {
		String nbc;
		do {
			nbc = generateRandomNumber();
		}while(isNumberExist(nbc)==false);
		return nbc;
	}
	private String generateRandomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

	@Override
	public List<DataInformation> getCustomer() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			List<DataInformation> cus = new ArrayList<>();
			ResultSet rs = data.getCustomer();
			while(rs.next()) {
				cus.add(new DataInformation(rs.getString(1), rs.getString(3), rs.getInt(5)+"", rs.getString(2), rs.getString(6), rs.getString(4)));
			}
			
			return cus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(String numbercard) throws RemoteException {
		// TODO Auto-generated method stub
		boolean delete = data.deleteCustomer(numbercard);
		if(delete)
			return true;
		return false;
	}

	@Override
	public List<DataInformation> getCustomerByName(String name) throws RemoteException {
		try {
			List<DataInformation> cus = new ArrayList<>();
			ResultSet rs = data.getCustomerByName(name);
			while(rs.next()) {
				cus.add(new DataInformation(rs.getString(1), rs.getString(3), rs.getInt(5)+"", rs.getString(2), rs.getString(6), rs.getString(4)));
			}
			
			return cus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateCustomer(String cccd, String name, String numberphone, int yBirth, String sex)throws RemoteException {
		// TODO Auto-generated method stub
		boolean update = data.updateCustomer(cccd, name, numberphone, yBirth, sex);
		if(update)
			return true;
		return false;
	}

	@Override
	public int auth(String sdtus, String pwus) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet acc = data.getAccount(sdtus, pwus);
			if(acc==null)
				return -1;
			else {
				if(acc.next()&acc.getInt(4)==1) {
					return 1;
				}
				else {
					return 0;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

	@Override
	public String getNameUser(String phone) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = data.getUserByNP(phone);
			if(rs.next())
				return rs.getString(1);
			else
				return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public String getNC(String phone) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = data.getUserByNP(phone);
			if(rs.next())
				return rs.getString(2);
			else
				return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public String getSD(String phone) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = data.getUserByNP(phone);
			if(rs.next())
				return rs.getString(4);
			else
				return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@Override
	public boolean depositUpdate(String nc, int balance) throws RemoteException {
		// TODO Auto-generated method stub
		if(data.Deposit_update(nc, balance))
			return true;
		return false;
	}

	@Override
	public String getSDbyNC(String nbc) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = data.getSDbyNC(nbc);
			if(rs.next())
				return rs.getInt(1)+"";
			else
				return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public boolean updatePass(String phone, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		boolean update = data.updatePass(phone, pass);
		if(update)
			return true;
		return false;
	}

	@Override
	public List<LSGD> getLSGD(String nbc) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			List<LSGD> lsgd = new ArrayList<>();
			ResultSet rs = data.getLSGD(nbc);
			while(rs.next()) {
				lsgd.add(new LSGD(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			}
			
			return lsgd;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getDate(String phone) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = data.getUserByNP(phone);
			if(rs.next())
				return rs.getDate(5)+"";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
}