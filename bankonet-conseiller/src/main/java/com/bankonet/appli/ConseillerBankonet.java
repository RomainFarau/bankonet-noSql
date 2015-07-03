package com.bankonet.appli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;


public class ConseillerBankonet {

	private MongoClient mongoClient;
	
	public void launch(){
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		mongoClient=new MongoClient("localhost");
		while(true){
			System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
			
			List<String> actions=Arrays.asList("0. Arrêter le programme",
												"1. Ouvrir un compte ");
			
			for (String string : actions) {
				System.out.println(string);
			}
			
			System.out.println("Veuillez choisir une action.");
			Scanner scanEntries=new Scanner(System.in);
			
			String choice=scanEntries.nextLine();
			scanEntries.close();
			switch(Integer.getInteger(choice)){
				case 0:
					stopProgram();
					break;
				case 1:
					openAccount();
					break;
				default:
					break;
			}
		}
	}
	
	
	private void stopProgram(){
		mongoClient.close();
		System.out.println("Arrêt de l'application");
		System.exit(0);
	}
	
	private void openAccount(){
		Scanner scanEntries=new Scanner(System.in);
		System.out.println("Veuillez entrer votre nom.");
		String nom=scanEntries.nextLine();
		System.out.println("Veuillez entrer votre prénom.");
		String prenom=scanEntries.nextLine();
		System.out.println("Veuillez entrer un login.");
		String login=scanEntries.nextLine();
		String mdp="secret";
		
		
		scanEntries.close();
	}
}
