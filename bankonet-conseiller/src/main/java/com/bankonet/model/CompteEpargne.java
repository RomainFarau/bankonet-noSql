package com.bankonet.model;

public final class CompteEpargne extends Compte {
    private float tauxInteret;
    private static int nombreComptesEpargnes = 0;

    private float plafond;

    public CompteEpargne(String id, String libelle, float solde,
            float tauxInteret, float plafond) {
        super(id, libelle, solde);
        this.tauxInteret = tauxInteret;
        this.plafond = plafond;
        nombreComptesEpargnes++;
    }




    public String toString() {
        return  super.toString()+
        		" - Taux interet : "+this.getTauxInteret()+" % "+
	    		" - Plafond : "+this.getPlafond();
    }

    public float getPlafond() {
        return plafond;
    }


    public void setPlafond(float newPlafond) {
        plafond = newPlafond;
    }

    public float getTauxInteret() {
        return tauxInteret;
    }


    public void setTauxInteret(float newTauxInteret) {
        tauxInteret = newTauxInteret;
    }

}