package com.bankonet.DAO;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;

public class ClientDao {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDb;
	
	public ClientDao(){
		mongoClient=new MongoClient("localhost");
		mongoDb=mongoClient.getDatabase("BankonetDB");
			
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public MongoDatabase getMongoDb() {
		return mongoDb;
	}

	public void setMongoDb(MongoDatabase mongoDb) {
		this.mongoDb = mongoDb;
	}
	
	public void closeClient(){
		if(mongoClient!=null){
			mongoClient.close();
		}
	}
	
}
