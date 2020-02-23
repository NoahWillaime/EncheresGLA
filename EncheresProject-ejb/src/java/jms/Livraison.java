/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import dto.Adresse;
import dto.Article;
import dto.Utilisateur;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guillaume Saunier
 */
public class Livraison implements Serializable {
    private long acheteur;
    private long[] panier;
    private double prix;

    public Livraison(long acheteur, long[] panier, double prix) {
        this.acheteur = acheteur;
        this.panier = panier;
        this.prix = prix;
    }

    public long getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(long acheteur) {
        this.acheteur = acheteur;
    }

    public long[] getPanier() {
        return panier;
    }

    public void setPanier(long[] panier) {
        this.panier = panier;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    
}
