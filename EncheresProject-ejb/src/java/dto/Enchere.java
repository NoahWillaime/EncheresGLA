/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
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
       
    @OneToMany(
        mappedBy = "enchere",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<EnchereAcheteur> acheteurs = new ArrayList<>();
     
    @Column(name = "ENCHERE_PRIX")
    private Double prix;
       
    @Column (name="ARTICLE_DUREE")
    private Date date;
    
    @Column (name="ENCHERE_FIN")
    private boolean fin;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<Promotion> promotions = new ArrayList<>();
    
    public Enchere() {
    }

    public Enchere(Article article, Double prix, Date date) {
        this.article = article;
        this.prix = prix;
        this.date = date;
        fin = Calendar.getInstance().getTimeInMillis() > date.getTime();
    }

   
    
    public void setFin(boolean fin){
        this.fin = fin;
    }

    public boolean getFin(){
        return fin;
    }
    
    

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void addPromotions(Promotion p) {
        this.promotions.add(p);
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
    
    public void addAcheteur(Utilisateur acheteur, Double prix) {
        EnchereAcheteur enchereAcheteur = new EnchereAcheteur(this, acheteur,prix);
        acheteurs.add(enchereAcheteur);
        acheteur.getEncheres().add(enchereAcheteur);
    }
 
    public void removeAcheteur(Utilisateur acheteur) {
        System.out.println(acheteurs.size());
        for (Iterator<EnchereAcheteur> iterator = acheteurs.iterator();
             iterator.hasNext(); ) {
            EnchereAcheteur enchereAcheteur = iterator.next();
 
            if (enchereAcheteur.getEnchere().equals(this) &&
                    enchereAcheteur.getAcheteur().equals(acheteur)) {
                iterator.remove();
                enchereAcheteur.getAcheteur().getEncheres().remove(enchereAcheteur);
                acheteurs.remove(enchereAcheteur);
            }
        }
                System.out.println(acheteurs.size());

    }
    
    public EnchereAcheteur lastEnchere() {
        EnchereAcheteur result = new EnchereAcheteur(this, article.getUtilisateur(), prix);
        for(EnchereAcheteur ea : acheteurs) {
            if(prix < ea.getPrix())
                result = ea;
        }
        return result;
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
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Enchere post = (Enchere) o;
        return Objects.equals(id, post.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Enchere{" + "id=" + id + ", article=" + article + ", prix=" + prix + ", date=" + date + '}';
    }   
    
    public boolean hasPromo() {
        if(promotions.size() > 0)
            return true;
        return false;
    }
    
    public double getPrixFinal() {
        if(hasPromo())
            return lastEnchere().getPrix() * promotions.get(0).getReduction_bon()+ 10 * promotions.get(0).getReduction_transport();
        return lastEnchere().getPrix();
    }
}
