/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Adresse;
import dto.Article;
import dto.Enchere;
import dto.Categorie;
import dto.Promotion;
import java.util.Calendar;
import dto.Utilisateur;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import schedule.GeneratePromotionBeanLocal;

/**
 *
 * @author Julien
 */
@Singleton
@Startup
public class ArticleSingleton implements ArticleSingletonLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;

    @EJB
    private CategorieManagerBeanLocal categorieManagerBean;
    
    @EJB
    private GeneratePromotionBeanLocal generatePromotionBeanLocal;
    
    
    @EJB
    private UtilisateurManagerBeanLocal utilisateurManagerBean;
    
    @PostConstruct
    public void init(){
        categorieManagerBean.addCategorie();
        Calendar c = Calendar.getInstance();
        c.set(2100, 1, 1);
        Article aa = new Article("stylo", "tr0bi1");
        Enchere ea = new Enchere(aa,25.01,c.getTime());
        c.set(2050,3,1);
        Article ab = new Article("gamer grill bath water", "potable btw");
        Enchere eb = new Enchere(ab,2.01,c.getTime());
        c.set(2050, 4, 2);
        Article ac = new Article("pizza", "a manger sans trop trainer");
        Enchere ec = new Enchere(ac,42.01,c.getTime()); 
        c.set(1995, 4, 2);
        Article ad = new Article("UN VIEUX", "je suis vieux");
        Enchere ed = new Enchere(ad,58.01,c.getTime());

        Utilisateur user = new Utilisateur("Julien", "Micheletti", "julien", "mdp");
        Utilisateur user2 = new Utilisateur("Guillaume", "Micheletti", "gg", "mdp");
        Adresse adresse = new Adresse("rue", "390", "mexy");
        user2.addAdresse(adresse);
        aa.addUtilisateur(user);
        ab.addUtilisateur(user2);
        ac.addUtilisateur(user2);
        ad.addUtilisateur(user);
        eb.addAcheteur(user,10.0);
        aa.setGagnant(user2);
        ea.addPromotions(new Promotion("Bon d'achat", 0.5, 0.75));
        em.persist(aa);
        em.persist(ab);
        em.persist(ac);
        em.persist(ad);
        em.persist(ea);
        em.persist(eb);
        em.persist(ec);
        em.persist(ed);
        System.out.println("Ajout user");
        utilisateurManagerBean.addUtilisateur(user);
        utilisateurManagerBean.addUtilisateur(user2);

    }
    
    private void loadArticle(){
        
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
