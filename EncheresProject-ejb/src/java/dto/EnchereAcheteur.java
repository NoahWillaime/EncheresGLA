
package dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Guillaume Saunier
 */
@Entity()
@Table(name = "ENCHERE_ACHETEUR")
public class EnchereAcheteur implements Serializable {
    @EmbeddedId
    private EnchereAcheteurPK id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("enchere_id")
    private Enchere enchere;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("utilisateur_id")
    private Utilisateur acheteur;
    
    @Column(name = "ENCHERE_PRIX")
    private Double prix;
    
    public EnchereAcheteur() {
    }
    
    public EnchereAcheteur(Enchere enchere, Utilisateur acheteur, Double prix) {
        this.enchere = enchere;
        this.acheteur = acheteur;
        this.prix = prix;
        this.id = new EnchereAcheteurPK(enchere.getId(), acheteur.getId());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EnchereAcheteur that = (EnchereAcheteur) o;
        return Objects.equals(enchere, that.enchere) &&
               Objects.equals(acheteur, that.acheteur);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(enchere, acheteur);
    }

    public Utilisateur getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Utilisateur acheteur) {
        this.acheteur = acheteur;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    } 
}