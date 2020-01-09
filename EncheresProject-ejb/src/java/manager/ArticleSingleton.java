/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Categorie;
import dto.Utilisateur;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Julien
 */
@Singleton
@Startup
public class ArticleSingleton implements ArticleSingletonLocal {

    @EJB
    private CategorieManagerBeanLocal categorieManagerBean;
    
    
    @EJB
    private UtilisateurManagerBeanLocal utilisateurManagerBean;
    
    @PostConstruct
    public void init(){
        categorieManagerBean.addCategorie();
        utilisateurManagerBean.addUtilisateur(new Utilisateur("Julien", "Micheletti", "julien", "mdp"));
    }
    
    private void loadArticle(){
        
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
