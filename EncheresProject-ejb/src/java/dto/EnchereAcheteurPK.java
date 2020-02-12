/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Guillaume Saunier
 */
@Embeddable
public class EnchereAcheteurPK implements Serializable{
    @Column(name = "ENCHERE_ID")
    private Long enchere_id;

     @Column(name = "UTILISATEUR_ID")
    private Long utilisateur_id;

    public EnchereAcheteurPK(Long enchere_id, Long utilisateur_id) {
        this.enchere_id = enchere_id;
        this.utilisateur_id = utilisateur_id;
    }

    public EnchereAcheteurPK() {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        EnchereAcheteurPK that = (EnchereAcheteurPK) o;
        return Objects.equals(enchere_id, that.enchere_id) &&
               Objects.equals(utilisateur_id, that.utilisateur_id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(utilisateur_id, enchere_id);
    }

    public Long getEnchere_id() {
        return enchere_id;
    }

    public void setEnchere_id(Long enchere_id) {
        this.enchere_id = enchere_id;
    }

    public Long getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(Long utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }
}
