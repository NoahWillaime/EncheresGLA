/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Guillaume Saunier
 */
@Entity
@Table(name = "ENCHERE")
public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;
       
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
       
    @OneToOne
    private Article article;
       
    @ManyToOne
    private Utilisateur acheteur;
       
    @Column(name = "ENCHERE_PRIX")
    private Double prix;
       
    @Column (name="ARTICLE_DUREE")
    private Date date;

    public Enchere() {
    }

    public Enchere(Article article, Utilisateur acheteur, Double prix, Date date) {
        this.article = article;
        this.acheteur = acheteur;
        this.prix = prix;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Utilisateur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Utilisateur acheteur) {
        this.acheteur = acheteur;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enchere other = (Enchere) obj;
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        if (!Objects.equals(this.acheteur, other.acheteur)) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Enchere{" + "id=" + id + ", article=" + article + ", acheteur=" + acheteur + ", prix=" + prix + ", date=" + date + '}';
    }
    
    

    
       
       
       
}
