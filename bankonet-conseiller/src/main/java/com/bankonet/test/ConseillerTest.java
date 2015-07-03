package com.bankonet.test;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.bankonet.appli.ConseillerBankonet;


public class ConseillerTest {

	public static void main(String args[]){
		Logger.getLogger("").setLevel(Level.SEVERE);
		ConseillerBankonet conseiller=new ConseillerBankonet();
		
		conseiller.launch();
	}
	
}
