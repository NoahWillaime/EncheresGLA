/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import dto.Article;
import dto.Utilisateur;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guillaume Saunier
 */
public class Facturation implements Serializable{
    private Utilisateur acheteur;
    private List<Article> panier;
    private double prix;
    
    public Facturation(){}
    
    public Facturation(Utilisateur acheteur, List<Article> panier, double prix) {
        this.acheteur = acheteur;
        this.panier = panier;
        this.prix = prix;
    }

    public Utilisateur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Utilisateur acheteur) {
        this.acheteur = acheteur;
    }

    public List<Article> getPanier() {
        return panier;
    }

    public void setPanier(List<Article> panier) {
        this.panier = panier;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
}
