package com.bankonet.appli;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ConseillerBankonet {

	private MongoClient mongoClient;
	
	public void launch(){
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		mongoClient=new MongoClient("localhost");
		while(true){
			System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
			
			List<String> actions=Arrays.asList("0. Arrêter le programme",
												"1. Ouvrir un compte ",
												"2. Lister tous les clients");
			
			for (String string : actions) {
				System.out.println(string);
			}
			
			System.out.println("Veuillez choisir une action.");
			Scanner scanEntries=new Scanner(System.in);
			
			//check integer input
			while(!scanEntries.hasNextInt()){
				scanEntries.next();
			}
			
			int choice=scanEntries.nextInt();
			
			switch(choice){
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

		
		String nom=scanEntries.next();
		
		System.out.println("Veuillez entrer votre prénom.");

		String prenom=scanEntries.next();
		
		System.out.println("Veuillez entrer un login.");

		String login=scanEntries.next();
		
		String mdp="secret";
		
		MongoDatabase mongoDb=mongoClient.getDatabase("BankonetDB");
		
		MongoCollection<Document> clientsCollection=mongoDb.getCollection("clients");
		
		Document compteCourantDoc=new Document()
						.append("libelle",nom.toUpperCase()+"_"+prenom.toUpperCase()+"_"+"COURANT_1")
						.append("solde", 120);
		
		Document clientDoc=new Document()
						.append("nom", nom)
						.append("prenom", prenom)
						.append("login", login)
						.append("password", mdp)
						.append("comptesCourants", compteCourantDoc);
		
		clientsCollection.insertOne(clientDoc);
		
		
		
		
	}
	
	private void listClient(){
		MongoCollection<Document> clientsCollection=mongoDb.getCollection("clients");
		
		FindIterable<Document> iter=clientsCollection.find();
		Iterator<Document> it=iter.iterator();
		
		Document docTmp;
		while(it.hasNext()){
			docTmp=it.next();
			System.out.println("Nom :"+docTmp.getString("nom")+", prenom: "+docTmp.getString("prenom"));
		}	
	}
}
