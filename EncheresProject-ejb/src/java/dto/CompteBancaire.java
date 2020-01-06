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
@Table(name = "COMPTEBANCAIRE")
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COMPTEBANCAIRE_NOM")
    private String nom;
    
    @Column(name = "COMPTEBANCAIRE_RIB")
    private String rib;
    
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Utilisateur utilisateur;
    
    public CompteBancaire(){}
    
    public CompteBancaire(String nom, String rib) {
        this.nom = nom;
        this.rib = rib;
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

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
