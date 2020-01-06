/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
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
public class ArticleManagerBean implements ArticleManagerBeanLocal {
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    @Override
    public Article addArticle(Article article){
        em.persist(article);
        return article;
    }
    
    @Override
    public List<Article> getAll(){
        Query query = em.createQuery("SELECT a FROM Article a");
        return (List<Article>) query.getResultList();
    }
    
    @Override
    public void removeArticle(Long id){
        Query query1 = em.createQuery("DELETE FROM Categorie where id = " +id);
        Query query = em.createQuery("DELETE FROM Article where id = "+id);
        query1.executeUpdate();
       // query.executeUpdate();
    }
}