package manage;

import java.lang.invoke.MethodHandles.Lookup;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Server.BankInterface;

public class RMIClient {
	private final int PORT = 3456;
	
	public RMIClient() {
		
	}
	public BankInterface callFunction() {
		try {
			Registry reg = LocateRegistry.getRegistry(PORT);
			BankInterface bm = (BankInterface)reg.lookup("BANK MANAGER");
			return bm;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
}
