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
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
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
    @Resource
    TimerService timerService;
    
    
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
        result.addAll(em.createQuery("SELECT e FROM Enchere e JOIN e.acheteurs a WHERE a.id = " + user.getId()).getResultList());
        return result;
    }
    
    @Override
    public Enchere addEnchere(Enchere e) {
        em.persist(e);
        timerService.createTimer(e.getDate(), e.getId());
        return e;
    }

    @Timeout
    public void endEcnhere(Timer timer){
       System.out.println("FIN DU TIMER" + timer.getInfo());
       List<Enchere> enchere = em.createQuery("SELECT e FROM Enchere e WHERE e.id = " + timer.getInfo()).getResultList();
       enchere.get(0).setFin(true);
       em.persist(enchere.get(0));
    }
    
    
    @Override
    public void encherir(Enchere e, Utilisateur u, Double enchere) {
        Enchere en = (Enchere) em.find(Enchere.class, e.getId());
        en.setPrix(en.getPrix()+enchere);
        System.out.println(en.getPrix());
        en.addAcheteur(u);
    }
       
    
}
