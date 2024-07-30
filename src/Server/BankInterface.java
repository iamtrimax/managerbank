package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import manage.DataInformation;
import manage.LSGD;

public interface BankInterface extends Remote {
	public boolean addCustomer(String cccd, String name, String numberphone, int yBirth, String sex) throws RemoteException;
	public List<DataInformation>getCustomer()throws RemoteException;
	public boolean deleteCustomer(String numbercard)throws RemoteException;
	public List<DataInformation>getCustomerByName(String name)throws RemoteException;
	public boolean updateCustomer(String cccd, String name, String numberphone, int yBirth, String sex) throws RemoteException;
	public int auth(String sdtus, String pwus) throws RemoteException;
	public String getNameUser(String phone) throws RemoteException;
	public String getNC(String phone) throws RemoteException;
	public String getSD(String phone) throws RemoteException;
	public String getDate(String phone) throws RemoteException;
	public String getSDbyNC(String nbc) throws RemoteException;
	public boolean depositUpdate(String nc, int balance) throws RemoteException;
	public boolean updatePass(String phone, String pass) throws RemoteException;
	public List<LSGD> getLSGD(String nbc) throws RemoteException;
}
