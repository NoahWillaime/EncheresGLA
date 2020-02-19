/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Article;
import dto.Enchere;
import dto.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Julien
 */
@Local
public interface ArticleManagerBeanLocal {
   public Article addArticle(Article article);
    public List<Article> getAll();
    public void removeArticle(Long id);
    public List<Article> findByName(String name);
    public void setPanier(Long idArticle, boolean panier);
    public List<Enchere> getArticlesFromPanier(Long userId);
    public List<Article> getArticlesByUsers(Long id);
    public void commandeValide(Long id);
    //public List<Article> getArticlesByCategorieID(Long id);
}
