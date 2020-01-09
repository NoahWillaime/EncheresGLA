/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Utilisateur;
import gestionUser.LogSingleton;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noahd
 */
@Stateless
public class LoginManagerBean implements LoginManagerBeanLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    
    @Override
    public List<Utilisateur> validateLogin(String pseudo, String mdp) {
        List<Utilisateur> users2 = em.createQuery("SELECT a FROM Utilisateur a")
            .getResultList();
        List<Utilisateur> users = em
            .createQuery("SELECT a FROM Utilisateur a WHERE a.pseudo = :userPseudo AND a.mdp = :userMdp")
            .setParameter("userPseudo", pseudo)
            .setParameter("userMdp", mdp)
            .setMaxResults(1)
            .getResultList();
        if (users.size() == 1)
             LogSingleton.getInstance().userLogin(users.get(0));
        return users;
    }
    
    public Utilisateur getCurrentUser() {
        return LogSingleton.getInstance().getCurrentUser();
    }
    
    public String getCurrentUserPseudo() {
        if (isLog()) {
            return getCurrentUser().getPseudo();
        }
        return "Not connected";
    }
    
    public void logOut() {
        LogSingleton.getInstance().logOut();
    }
    
    public boolean isLog() {
        return LogSingleton.getInstance().isLog();
    }
}