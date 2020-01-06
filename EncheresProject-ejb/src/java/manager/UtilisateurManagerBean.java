/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Utilisateur;
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
public class UtilisateurManagerBean implements UtilisateurManagerBeanLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        em.persist(utilisateur);
        return utilisateur;
    }
    
    @Override
    public List<Utilisateur> getAll(){
        Query query = em.createQuery("SELECT a FROM Utilisateur a");
        return (List<Utilisateur>) query.getResultList();
    }
    
}
