package com.bankonet.appli;

import java.util.Scanner;

import com.bankonet.DAO.ClientDao;

public class ClientBankonet {
	
	private ClientDao clientDao;
	private Scanner scanEntries;
	
	public void launch(){
		clientDao=new ClientDao();
		
		
	}
}
