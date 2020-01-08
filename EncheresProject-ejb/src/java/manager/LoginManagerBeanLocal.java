/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julien
 */
@Local
public interface LoginManagerBeanLocal {
    public void validateLogin(String pseudo, String mdp);
    public List<Utilisateur> getUsers();
    public Utilisateur getCurrentUser();
    public String getCurrentUserPseudo();
    public void logOut();
    public boolean isLog();
}
