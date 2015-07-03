package com.bankonet.model;

public final class CompteCourant extends Compte {
    private float decouvertAutorise;
    public static int nombreComptesCourants = 0;

 
    public CompteCourant(String id, String libelle, float solde, float decouvertAutorise) {

        super(id, libelle, solde);

        this.decouvertAutorise = decouvertAutorise;

        nombreComptesCourants++;
    }

    public float getDecouvertAutorise() {
        return decouvertAutorise;
    }
    
    public String toString() {
        return   super.toString()+
	    		" - Découvert autorisé : "+this.getDecouvertAutorise()+" €";
    }
    

}