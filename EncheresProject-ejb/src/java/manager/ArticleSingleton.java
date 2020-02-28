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
import dto.CompteBancaire;
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
        Utilisateur user = new Utilisateur("Julien", "Le maitre", "julien", "mdp");
        user.addCompteBancaire(new CompteBancaire("Orange", "FR65464856412"));
        Adresse adresse = new Adresse("rue", "48", "loulou");
        user.addAdresse(adresse);
        Utilisateur user2 = new Utilisateur("Guillaume", "Le boss", "guillaume", "mdp");
        user2.addCompteBancaire(new CompteBancaire("Orange Banque", "FR65464856412"));
        Adresse adresse2 = new Adresse("rue", "390", "mexy");
        user2.addAdresse(adresse2);
        Utilisateur user3 = new Utilisateur("Noah", "Le patron", "noah", "mdp");
        user3.addCompteBancaire(new CompteBancaire("Mut", "FR65464856412"));
        Adresse adresse3 = new Adresse("avenue", "748", "Allalala");
        user.addAdresse(adresse3);
        Utilisateur user4 = new Utilisateur("Alexis", "Le chef", "alexis", "mdp");
        user4.addCompteBancaire(new CompteBancaire("Agricopain", "FR65464856412"));
        Adresse adresse4 = new Adresse("terre", "42", "Ou pas");
        user4.addAdresse(adresse4);
        Utilisateur user5 = new Utilisateur("Lui", "L'esclave", "esclave", "mdp");
        categorieManagerBean.addCategorie();
        Calendar c = Calendar.getInstance();
        
        c.set(2020, 2, 27);
        Article aa = new Article("stylo", "tr0bi1");
        aa.addCategorie(categorieManagerBean.getByNom("Cuisine"));
        Enchere ea = new Enchere(aa,25.01,c.getTime());
        aa.addUtilisateur(user);
        aa.setGagnant(user2);
        
        c.set(2050,3,1);
        Article ab = new Article("gamer grill bath water", "potable btw");
        ab.addCategorie(categorieManagerBean.getByNom("Jeux vidéo"));
        ab.addCategorie(categorieManagerBean.getByNom("Mobilier"));
        Enchere eb = new Enchere(ab,2.01,c.getTime());
        eb.addAcheteur(user,10.0);
        ab.addUtilisateur(user2);
        
        c.set(2050, 4, 2);
        Article ac = new Article("pizza", "a manger sans trop trainer");
        ac.addCategorie(categorieManagerBean.getByNom("Cuisine"));
        Enchere ec = new Enchere(ac,42.01,c.getTime());
        ac.addUtilisateur(user3);
        
        c.set(1995, 4, 2);
        Article ad = new Article("UN VIEUX", "je suis vieux");
        ad.addCategorie(categorieManagerBean.getByNom("Mobilier"));
        Enchere ed = new Enchere(ad,58.01,c.getTime());
        ad.addUtilisateur(user4);
        
        c.set(2020,3,2);
        Article ae = new Article("Boite de chaussures", "Sans chaussures");
        ae.addCategorie(categorieManagerBean.getByNom("Sport"));
        Enchere ee = new Enchere(ae,2.01,c.getTime());
        ae.addUtilisateur(user4);

        
        
        
        
        
        
        
        
        
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
        utilisateurManagerBean.addUtilisateur(user3);
        utilisateurManagerBean.addUtilisateur(user4);
        utilisateurManagerBean.addUtilisateur(user5);
        
        generatePromotionBeanLocal.calculNewPromos();

    }
    
    private void loadArticle(){
        
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
