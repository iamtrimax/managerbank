package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
	private static final int PORT = 3456;
	public Server() {
		
	}
	public void builtServer() {
		try {
			BankInterface bankManager = new BankServices();
			Registry reg = LocateRegistry.createRegistry(PORT);
			reg.bind("BANK MANAGER", bankManager);
			while(true) {
				System.out.println("SERVER IS RUNNING");
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("SERVER STOPED");
		}
	}
}
