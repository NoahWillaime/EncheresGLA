/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Categorie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

/**
 *
 * @author noahd
 */
@Stateless
public class CategorieManagerBean implements CategorieManagerBeanLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    @Override
    public Categorie[] getAll(){
        Query query = em.createQuery("SELECT a FROM Categorie a");
  
        List<Categorie> cat = (List<Categorie>) query.getResultList();
        Categorie[] categorie = new Categorie[cat.size()];
        for (int i = 0; i < cat.size(); i++){
            categorie[i] = cat.get(i);
        }
        return categorie;
    }
    
     @Override
    public Categorie addCategorie(){
        Categorie c = new Categorie("Sport");
        Categorie d = new Categorie("Jeux vidéo");
        Categorie e = new Categorie("Cuisine");
        Categorie f = new Categorie("Mobilier");
        em.persist(c);
        em.persist(d);
        em.persist(e);
        em.persist(f);
        return c;
    }
}