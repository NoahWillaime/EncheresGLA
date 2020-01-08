/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Categorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julien
 */
@Local
public interface CategorieManagerBeanLocal {
       public Categorie[] getAll();
       public Categorie addCategorie();
}
