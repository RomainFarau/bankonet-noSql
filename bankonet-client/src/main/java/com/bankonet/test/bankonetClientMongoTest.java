package com.bankonet.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.bankonet.appli.ClientBankonet;

public class bankonetClientMongoTest {
	public static void main(String[] args){
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		ClientBankonet clientBankonet=new ClientBankonet();
		
		clientBankonet.launch();
	}
}
