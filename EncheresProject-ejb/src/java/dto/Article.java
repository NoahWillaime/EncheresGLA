/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author noahd
 */
@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ARTICLE_NOM")
    private String nom;
    
    @Column(name = "ARTICLE_DESCRIPTION")
    private String description;
    
    @ManyToMany
    private List<Categorie> categories;
    
    
    @Column(name = "ARTICLE_PANIER")
    private boolean panier;
    
    @Column(name = "ARTICLE_COMMANDE")
    private boolean commande;
    
    @ManyToOne
    private Utilisateur utilisateur;
    
    @Column (name = "ARTICLE_STATUS")
    private String status;
    
    @ManyToOne
    private Utilisateur gagnant;
    
    public Article(){}
    
    public Article(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.categories = new ArrayList<Categorie>();
        this.panier = false;
        this.status = "vente";
    }

    public boolean isPanier() {
        return panier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCommande() {
        return commande;
    }

    public void setCommande(boolean commande) {
        this.commande = commande;
    }

    public void setPanier(boolean panier) {
        this.panier = panier;
    }
   
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }


    public void addUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }
    public Utilisateur getGagnant() {
        return gagnant;
    }


    public void setGagnant(Utilisateur gagnant){
        this.gagnant = gagnant;
    }   

    public void addCategorie(Categorie categorie) {
        this.categories.add(categorie);
    }

    public List<Categorie> getCategorie() {
        return categories;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String val =  id + " : " + this.nom;
        for (Categorie c : this.getCategorie())
            val += " / " + c.getNom();
        return val;
        //return "persistence.Article[ id=" + id + " ]";
    }
    
}
