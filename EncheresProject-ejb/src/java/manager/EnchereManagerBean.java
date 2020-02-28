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
import java.util.Calendar;
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
        result.addAll(em.createQuery("SELECT e FROM Enchere e JOIN e.acheteurs a WHERE a.acheteur.id = " + user.getId() + " AND e.article.status not LIKE '%finie%'").getResultList());
        System.out.println("Resulte: " + result.size());
        return result;
    }
    
    @Override
    public Enchere addEnchere(Enchere e) {
        em.persist(e);
        if (!e.getFin())
            timerService.createTimer(e.getDate().getTime() - Calendar.getInstance().getTimeInMillis(), e.getId());
        return e;
    }
    
    @Override
    public List<Enchere> findByWinner(Utilisateur gagnant) {
        if(gagnant != null) {
            return em.createQuery("SELECT e FROM Enchere e WHERE e.article.gagnant.id = " + gagnant.getId() + " AND e.article.panier = false AND e.article.commande = false")
                    .getResultList();
     
        }
        // LISTE VIDE
        Query query = em.createQuery("SELECT a FROM Article a WHERE 0=1");
        return (List<Enchere>) query.getResultList();
    }

    @Timeout
    public void endEcnhere(Timer timer){
       System.out.println("FIN DU TIMER" + timer.getInfo());
       Enchere enchere = (Enchere)em.createQuery("SELECT e FROM Enchere e WHERE e.id = " + timer.getInfo()).getResultList().get(0);
       Article article = enchere.getArticle();
       System.out.println(article.getNom());
       Utilisateur gagnant = enchere.lastEnchere().getAcheteur();
       enchere.setFin(true);
       article.setStatus("finie");
       article.setGagnant(gagnant);
       em.persist(enchere);
       em.persist(article);
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
