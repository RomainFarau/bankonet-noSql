package com.bankonet.appli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.bankonet.DAO.ClientDao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ConseillerBankonet {
	
	private ClientDao dbClient;
	private Scanner scanEntries;
	public void launch(){
		Logger.getLogger("").setLevel(Level.SEVERE);
		
		dbClient=new ClientDao();
		scanEntries=new Scanner(System.in);
		
		while(true){
			System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
			
			List<String> actions=Arrays.asList("0. Arrêter le programme",
												"1. Ouvrir un compte ",
												"2. Lister tous les clients");
			
			for (String string : actions) {
				System.out.println(string);
			}
			
			System.out.println("Veuillez choisir une action.");
			
			
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
				case 2:
					listClients();
					break;
				default:
					break;
			}
		}
		
	}
	
	
	private void stopProgram(){
		scanEntries.close();
		dbClient.closeClient();
		System.out.println("Arrêt de l'application");
		System.exit(0);
	}
	
	private void openAccount(){
		
		System.out.println("Veuillez entrer votre nom.");
		String nom=scanEntries.next();
		
		System.out.println("Veuillez entrer votre prénom.");
		String prenom=scanEntries.next();
		
		System.out.println("Veuillez entrer un login.");
		String login=scanEntries.next();
		
		String mdp="secret";
		
		
		MongoCollection<Document> clientsCollection=dbClient.getMongoDb().getCollection("clients");
		
		Document compteCourantDoc=new Document()
						.append("libelle",nom.toUpperCase()+"_"+prenom.toUpperCase()+"_"+"COURANT_1")
						.append("solde", 120);
		
		List<Document> listDocCompteCourant=new ArrayList<Document>();
		listDocCompteCourant.add(compteCourantDoc);
		
		Document clientDoc=new Document()
						.append("nom", nom)
						.append("prenom", prenom)
						.append("login", login)
						.append("password", mdp)
						.append("comptesCourants", listDocCompteCourant);
		
		clientsCollection.insertOne(clientDoc);
		
		
		
		
	}
	
	private void listClients(){
		MongoCollection<Document> clientsCollection=dbClient.getMongoDb().getCollection("clients");
		
		FindIterable<Document> iter=clientsCollection.find();
		Iterator<Document> it=iter.iterator();
		
		Document docTmp;
		List<Document> listCompteCourant;
		List<Document> listCompteEpargne;
		while(it.hasNext()){
			docTmp=it.next();
			
			listCompteCourant =(List<Document>) docTmp.get("comptesCourants");
			listCompteEpargne =(List<Document>) docTmp.get("comptesEpargne");
			if(listCompteEpargne==null){
				if(listCompteCourant==null){
					System.out.println("Identifiant :"+docTmp.get("_id")
								+" Nom :"+docTmp.getString("nom")
								+", prenom: "+docTmp.getString("prenom")
								+", login: "+docTmp.getString("login"));
				}else{
					System.out.println("Identifiant :"+docTmp.get("_id")
							+" Nom :"+docTmp.getString("nom")
							+", prenom: "+docTmp.getString("prenom")
							+", login: "+docTmp.getString("login")
							+", nombre de comptes courants :"+Integer.toString(listCompteCourant.size()));
				}
			}else if(listCompteCourant==null){
				System.out.println("Identifiant :"+docTmp.get("_id")
						+" Nom :"+docTmp.getString("nom")
						+", prenom: "+docTmp.getString("prenom")
						+", login: "+docTmp.getString("login")
						+", nombre de comptes épargnes :"+Integer.toString(listCompteEpargne.size()));
			}else{
				System.out.println("Identifiant :"+docTmp.get("_id")
						+" Nom :"+docTmp.getString("nom")
						+", prenom: "+docTmp.getString("prenom")
						+", login: "+docTmp.getString("login")
						+", nombre de comptes courants :"+Integer.toString(listCompteCourant.size())
						+", nombre de comptes épargnes :"+Integer.toString(listCompteEpargne.size()));
			}
		}	
	}
}
