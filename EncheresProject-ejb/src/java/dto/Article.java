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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Column (name ="ARTICLE_PRIX")
    private String prix;
    
    
    @Column (name="ARTICLE_DUREE")
    private String date;
    
    @OneToMany (cascade = CascadeType.PERSIST, targetEntity = Categorie.class, mappedBy="Article")
    private List<Categorie> categorie;
   
    
    public Article(){}
    
    public Article(String nom, String description, String prix, String date) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.date = date;
        this.categorie = new ArrayList<Categorie>();
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

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }

    public void addCategorie(Categorie categorie) {
        this.categorie.add(categorie);
        categorie.addArticle(this);
    }

    public List<Categorie> getCategorie() {
        return categorie;
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
        return "persistence.Article[ id=" + id + " ]";
    }
    
}
