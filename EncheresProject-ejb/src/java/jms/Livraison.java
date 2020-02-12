/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import dto.Adresse;
import dto.Article;
import dto.Utilisateur;
import java.util.List;

/**
 *
 * @author Guillaume Saunier
 */
public class Livraison {
    private Utilisateur acheteur;
    private List<Article> panier;

    public Livraison(Utilisateur acheteur, List<Article> panier) {
        this.acheteur = acheteur;
        this.panier = panier;
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
    
    
}
