package com.bankonet.model;
/**
 * @author fguibert
 */
public abstract class Compte{
	private String libelle;
	private String identifiant;
	protected float solde;

	Compte() { }
	
	Compte(String id, String libelle, float solde) {
		this.identifiant = id;
		this.libelle = libelle;
		this.solde = solde;
	}
	
	public String toString() {
	        return  " ID  : "+this.getIdentifiant() +" - "+
		    		" Lib : "+this.getLibelle()+" - "+
		    		" Solde : "+this.getSolde()+"€";
	}

	
	
	public String getLibelle() {
		return libelle;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float newSolde) {
		this.solde = newSolde;
	}

}
