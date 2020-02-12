/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Enchere;
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
        if(name != null) {
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
    
    @Override
    public void setPanier(Long idArticle, boolean panier){
        Article article = (Article)em.createQuery("SELECT a FROM Article a WHERE a.id = " + idArticle).getResultList().get(0);
        
        article.setPanier(panier);
    }
    
    @Override
    public List<Enchere> getArticlesFromPanier(Long userId){
        List<Enchere> encheres = em.createQuery("SELECT e FROM Enchere e WHERE e.article.gagnant.id = " + userId + " AND e.article.panier = true").getResultList();
        return encheres;
    }
   
    @Override
    public void removeArticle(Long id){
        Query query1 = em.createQuery("DELETE FROM Categorie where id = " +id);
        Query query = em.createQuery("DELETE FROM Article where id = "+id);
        query1.executeUpdate();
       // query.executeUpdate();
    }
}
