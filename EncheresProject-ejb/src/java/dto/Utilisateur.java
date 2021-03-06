/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author noahd
 */
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "UTILISATEUR_NOM")
    private String nom;
    
    @Column(name = "UTILISATEUR_PRENOM")
    private String prenom;
    
    @Column (name ="UTILISATEUR_PSEUDO")
    private String pseudo;
    
    @Column (name="UTILISATEUR_MOTDEPASSE")
    private String mdp;
   
    @OneToMany (cascade = CascadeType.PERSIST, targetEntity = Adresse.class, mappedBy="Utilisateur")
    private List<Adresse> adresse;
   
    @OneToMany (cascade = CascadeType.PERSIST, targetEntity = CompteBancaire.class, mappedBy="Utilisateur")
    private List<CompteBancaire> compteBancaire;
    
    @OneToMany (cascade = CascadeType.PERSIST, targetEntity = Article.class, mappedBy="Utilisateur")
    private List<Article> articles;   
    
    @OneToMany(
        mappedBy = "acheteur",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<EnchereAcheteur> encheres = new ArrayList<>();
    
    @Column (name="UTILISATEUR_ANNULATION")
    private int nbAnnul = 0;
    
    public Utilisateur(){}
    
    public Utilisateur(String prenom, String nom, String pseudo, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.compteBancaire = new ArrayList<CompteBancaire>();
        this.adresse = new ArrayList<Adresse>();
        this.articles = new ArrayList<Article>();
    }

    public List<EnchereAcheteur> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<EnchereAcheteur> encheres) {
        this.encheres = encheres;
    }
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    
    
    public int getNbAnnul() {
        return nbAnnul;
    }

    public void setNbAnnul(int nbAnnul) {
        this.nbAnnul = nbAnnul;
    }
    
    public void addAnnul() {
        this.nbAnnul++;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

      public void addArticles(Article article) {
        this.articles.add(article);
        article.addUtilisateur(this);
        System.out.println(this.getId());
    }
    
   
     public void addAdresse(Adresse adresse) {
        this.adresse.add(adresse);
        adresse.addUtilisateur(this);
    }

    public List<Adresse> getAdresse() {
        return adresse;
    }
    
    public void addCompteBancaire(CompteBancaire cb) {
        this.compteBancaire.add(cb);
        cb.addUtilisateur(this);
    }

    public List<CompteBancaire> getCompteBancaire() {
        return compteBancaire;
    }
    
    @Override   
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur tag = (Utilisateur) o;
        return Objects.equals(id, tag.id);
    }
    
    

 

    @Override
    public String toString() {
        return "persistence.Article[ id=" + id + " ]";
    }
    
}
