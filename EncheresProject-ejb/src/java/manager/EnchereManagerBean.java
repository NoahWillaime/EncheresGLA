/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Enchere;
import dto.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Guillaume Saunier
 */
@Stateless
public class EnchereManagerBean implements EnchereManagerBeanLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Enchere> getAll() {
        Query query = em.createQuery("SELECT e FROM Enchere e");
        return (List<Enchere>) query.getResultList();
    }

    @Override
    public List<Enchere> findByName(String name) {
        System.out.println("Recherche article");
        if(name != null) {
            System.out.println("Nom: " + name);
            return em.createQuery("SELECT e FROM Enchere e,Article a Where e.article.id = a.id AND a.nom LIKE '%"+name+"%'")
                    .getResultList();
        }
        return em.createQuery("SELECT e FROM Enchere e").getResultList();
    }

    @Override
    public List<Enchere> getEncheresByUser(Utilisateur user) {
        System.out.println("ici");
        return em.createQuery("SELECT e FROM Enchere e WHERE e.article.utilisateur.id = " + user.getId()+" OR e.acheteur.id = " + user.getId()).getResultList();
    }
    
    @Override
    public Enchere addEnchere(Enchere e) {
        em.persist(e);
        return e;
    }

    @Override
    public void encherir(Enchere e, Utilisateur u) {
        Enchere en = (Enchere) em.find(Enchere.class, e.getId());
        en.setAcheteur(u);
    }
       
    
}
