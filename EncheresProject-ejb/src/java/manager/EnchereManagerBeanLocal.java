/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Enchere;
import dto.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Guillaume Saunier
 */
@Local
public interface EnchereManagerBeanLocal {
    List<Enchere> getAll();
    public List<Enchere> findByName(String name);
    public List<Enchere> getEncheresByUser(Utilisateur user);
    public Enchere addEnchere(Enchere e);
    public void encherir(Enchere e, Utilisateur u, Double enchere);
    public void annuleEnchere(long currentUser, long enchere);
    public void retirerEnchere(Long id);
}
