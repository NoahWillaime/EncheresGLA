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
 * @author noahd
 */
@Local
public interface LoginManagerBeanLocal {
    public List<Utilisateur> validateLogin(String pseudo, String mdp);
    public Utilisateur getCurrentUser();
    public String getCurrentUserPseudo();
    public void logOut();
    public boolean isLog();
}
