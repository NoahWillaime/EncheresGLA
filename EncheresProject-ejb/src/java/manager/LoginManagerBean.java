/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Julien
 
 */
@Stateful
public class LoginManagerBean implements LoginManagerBeanLocal {
     @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em; 
    private Utilisateur currentUser;
    private List<Utilisateur> users;
    
    
   @Override
    public void validateLogin(String pseudo, String mdp) {
        List<Utilisateur> users2 = new ArrayList<Utilisateur>();
        users2 = em.createQuery("SELECT a FROM Utilisateur a").getResultList();
        System.out.println(users2.get(0).getPseudo()+ ": "+users2.get(0).getMdp());
        System.out.println(pseudo+ ": "+mdp);        
        users = em
            .createQuery("SELECT a FROM Utilisateur a WHERE a.pseudo = :userPseudo AND a.mdp = :userMdp")
            .setParameter("userPseudo", pseudo)
            .setParameter("userMdp", mdp)
            .setMaxResults(1)
            .getResultList();
        if (users.size() == 1){
            currentUser = users.get(0);  
            login();
        }
    }
    
    @PostConstruct
    public void init() {
        currentUser = null;
    }
    
    public void login(){
        System.out.println("coucou");
        if (currentUser != null)
            System.out.println(currentUser);
                
                
    }
    
    public List<Utilisateur> getUsers(){
        return users;
    }
    
   @Override
    public Utilisateur getCurrentUser() {
        return this.currentUser;
    }
    
   @Override
    public String getCurrentUserPseudo() {
        if (isLog()) {
            return currentUser.getPseudo();
        }
        return "Not connected";
    }
    
   @Override
    public void logOut() {
        this.currentUser = null;
    }
    
   @Override
    public boolean isLog() {
         if (currentUser != null)
            System.out.println(currentUser);
         else
             System.out.println("Non co");
          return this.currentUser != null;
    }
}
