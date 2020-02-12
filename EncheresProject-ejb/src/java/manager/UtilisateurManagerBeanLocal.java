/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julien
 */
@Local
public interface UtilisateurManagerBeanLocal {
    public Utilisateur addUtilisateur(Utilisateur utilisateur);
     
    public List<Utilisateur> getAll();
    public Utilisateur getUtilisateur(Long id);
}
