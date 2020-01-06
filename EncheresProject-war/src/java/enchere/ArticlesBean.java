/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enchere;

import dto.Article;
import manager.ArticleManagerBeanLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 *
 * @author noahd
 */
@Named(value = "articlesBean")
@RequestScoped
public class ArticlesBean {
   @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;  
   
  
    public ArticlesBean(){
    }
    
    public ArrayList<Article> allArticles(){
        ArrayList<Article> result = new ArrayList<>();
        for (Article a : articles.getAll()){
            result.add(a);
        }
        return result;
    }
}
