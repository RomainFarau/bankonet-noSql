package com.bankonet.appli;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.bankonet.DAO.ClientDao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class ClientBankonet {
	
	private ClientDao clientDao;
	private Scanner scanEntries;
	private String login, mdp;
	private Document infosClient;
	
	public void launch(){
		clientDao=new ClientDao();
		scanEntries=new Scanner(System.in);
		boolean connexionOk=false;
	
		while(!connexionOk){
			System.out.println("Veuillez entrer votre login.");
			login=scanEntries.next();
			
			System.out.println("Veuillez entrer votre mot de passe.");
			mdp=scanEntries.next();
			
			MongoCollection<Document> clientsCollection=clientDao.getMongoDb().getCollection("clients");
			
			FindIterable<Document> iter=clientsCollection.find();
			Iterator<Document> it=iter.iterator();
			
			while(it.hasNext()){
				infosClient=it.next();
				if(login.equals(infosClient.getString("login"))){				
					if(mdp.equals(infosClient.getString("password"))){
						connexionOk=true;
						break;
					}
				}
			}
			if(!connexionOk){
				System.out.println("Connexion impossible");
			}
		}
			
		while(true){	
			System.out.println("***** APPLICATION CLIENT *****");
			
			List<String> actions=Arrays.asList("0. Arrêter le programme",
											"1. Consulter les soldes des comptes");

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
					stopClientProgram();
					break;
				case 1:
					checkSoldeAccount();
					break;
				default:
					break;
			}
		}
		
	}
	
	private void stopClientProgram(){
		scanEntries.close();
		clientDao.closeClient();
		System.out.println("Arrêt de l'application");
		System.exit(0);
	}
	
	private void checkSoldeAccount(){
		List<Document> listCompteCourant =(List<Document>) infosClient.get("comptesCourants");
		if(listCompteCourant!=null){
			for (Document document : listCompteCourant) {
				System.out.println("Libelle : "+document.getString("libelle")
									+", solde : "+Integer.toString(document.getInteger("solde")));
			}
		}
		List<Document> listCompteEpargne =(List<Document>) infosClient.get("comptesEpargne");
		if(listCompteEpargne!=null){
			for (Document document : listCompteEpargne) {
				System.out.println("Libelle : "+document.getString("libelle")
						+", solde : "+Integer.toString(document.getInteger("solde")));
			}
		}
	}
}
