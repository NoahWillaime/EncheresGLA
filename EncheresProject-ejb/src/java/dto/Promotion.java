/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author noahd
 */
@Entity
@Table(name="PROMOTION")
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PROMOTION_NOM")
    private String nom;
    
    @Column(name = "PROMOTION_TRANSPORT")
    private float reduction_transport;
    
    @Column(name = "PROMOTION_BON")
    private float reduction_bon;

    @ManyToMany
    private List<Enchere> encheres;

    public Promotion() {
    }

    public Promotion(String nom, float reduction_transport, float reduction_bon) {
        this.nom = nom;
        this.reduction_transport = reduction_transport;
        this.reduction_bon = reduction_bon;
        this.encheres = new ArrayList<Enchere>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getReduction_transport() {
        return reduction_transport;
    }

    public void setReduction_transport(float reduction_transport) {
        this.reduction_transport = reduction_transport;
    }

    public float getReduction_bon() {
        return reduction_bon;
    }

    public void setReduction_bon(float reduction_bon) {
        this.reduction_bon = reduction_bon;
    }

    public void addEnchere(Enchere e) {
        this.encheres.add(e);
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nom);
        sb.append("(");
        sb.append(this.reduction_bon * 100);
        sb.append(" %)");
        return sb.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    } 
}
