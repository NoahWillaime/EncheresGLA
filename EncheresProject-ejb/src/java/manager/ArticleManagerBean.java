/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Utilisateur;
import gestionUser.LogSingleton;
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
import schedule.GeneratePromotionBean;

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
    public List<Article> findByName(String name) {
        System.out.println("Recherche article");
        if(name != null) {
            System.out.println("Nom: " + name);
            return em.createQuery("SELECT a FROM Article a WHERE a.nom LIKE '%"+name+"%'")
                    .getResultList();
        }
        Query query = em.createQuery("SELECT a FROM Article a");
        return (List<Article>) query.getResultList();
    }
    
    @Override
    public List<Article> getArticlesByUsers(Long id){
        //Query query = em.createQuery("SELECT a FROM Article a WHERE UTILISATEUR_ID = " + id);
         List<Article> article = em.createQuery("SELECT a FROM Article a WHERE a.utilisateur.id = "+ id).getResultList();
        return article;
    }
    /*
    @Override
    public List<Article> getArticlesByCategorieID(Long id){
        //Query query = em.createQuery("SELECT a FROM Article a WHERE UTILISATEUR_ID = " + id);
         List<Article> article = em.createQuery("SELECT a FROM Article a WHERE a.id in (SELECT articles_id AS id FROM Article_Categorie b WHERE categories_id ="+id +" ) ").getResultList();
        return article;
    }
    */
    @Override
    public void removeArticle(Long id){
        Query query1 = em.createQuery("DELETE FROM Categorie where id = " +id);
        Query query = em.createQuery("DELETE FROM Article where id = "+id);
        query1.executeUpdate();
       // query.executeUpdate();
    }
}
