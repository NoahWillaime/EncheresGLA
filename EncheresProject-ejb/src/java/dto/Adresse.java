/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author noahd
 */
@Entity
@Table(name = "ADRESSE")
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ADRESSE_RUE")
    private String rue;
    
    @Column(name = "ADRESSE_VILLE")
    private String ville;
    
    @Column(name = "ADRESSE_CODEPOSTAL")
    private String code;
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Utilisateur utilisateur;
    
    public Adresse(){}
    
    public Adresse(String ville, String rue, String code) {
        this.ville = ville;
        this.code = code;
        this.rue = rue;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public void addUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getCode() {
        return code;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCode(String code) {
        this.code = code;
    }

 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "persistence.Address[ id=" + id + " ]";
    }

  
    
}
