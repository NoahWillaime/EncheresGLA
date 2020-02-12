/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

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
        if(name != null) {
            return em.createQuery("SELECT e FROM Enchere e,Article a Where e.article.id = a.id AND a.nom LIKE '%"+name+"%'")
                    .getResultList();
        }
        return em.createQuery("SELECT e FROM Enchere e").getResultList();
    }

    @Override
    public List<Enchere> getEncheresByUser(Utilisateur user) {
        ArrayList<Enchere> result = new ArrayList<>();
        result.addAll(em.createQuery("SELECT e FROM Enchere e  WHERE e.article.utilisateur.id = " + user.getId()).getResultList());
        result.addAll(em.createQuery("SELECT e FROM Enchere e JOIN e.acheteurs a WHERE a.acheteur.id = " + user.getId()).getResultList());
        System.out.println("Resulte: " + result.size());
        return result;
    }
    
    @Override
    public Enchere addEnchere(Enchere e) {
        em.persist(e);
        return e;
    }

    @Override
    public void encherir(Enchere e, Utilisateur u, Double enchere) {
        Enchere en = (Enchere) em.find(Enchere.class, e.getId());
        en.addAcheteur(u,enchere);
    }
    
    public void retirerEnchere(Long id) {
        Enchere en = (Enchere) em.find(Enchere.class, id);
        em.remove(en);
    }
    
    @Override
    public void annuleEnchere(long user, long enchere) {
        Utilisateur u = (Utilisateur) em.find(Utilisateur.class, user);
        u.addAnnul();
        if(u.getNbAnnul() >= 3){
            for(Enchere e : (List<Enchere>) em.createQuery("SELECT e FROM Enchere e JOIN e.acheteurs a WHERE a.acheteur.id = " + user).getResultList()) {
                e.removeAcheteur(u);
            }
        } else {
            Enchere en = (Enchere) em.find(Enchere.class, enchere);
            en.removeAcheteur(u);
        }
    }
    
}
