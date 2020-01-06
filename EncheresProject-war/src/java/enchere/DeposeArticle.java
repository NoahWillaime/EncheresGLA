/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enchere; 
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import dto.Categorie;
import dto.Article;
import manager.ArticleManagerBeanLocal;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author cirstea
 */

@Named(value = "deposeArticleBean")
@RequestScoped  
public class DeposeArticle {
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;    
    
   /* @Inject 
    Greeting greet;*/

    private String nom;
    private String description;
    private String prix;
    private String date;
     private String[] categorie = {"Jeux", "Sport", "Cuisine", "Mobilier"};
   
 
        
    
    /**
     * Creates a new instance of Hello
     */
    public DeposeArticle() {
        nom = "";
        description = "";
        prix = "";
        date = "";
    }
    
   public String[] getCategorie(){
      return categorie;
   }
   
   public void setCategorie(String[] categorie){
       this.categorie = categorie;
   }
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

  
  
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }

 
  /*  public String sayHello() {
        return nameHandler.greeetingsMessage(this.getNickname());  
    }*/
    
    public String addArticle(){
        return "articles";
    }
    
    public String retirerArticle(Long id){
        articles.removeArticle(id);
        return "listpersons";
    }
    
    public String registerArticle() {     
        Article article = new Article(this.getNom(), this.getDescription(), this.getPrix(), this.getDate());
        for (String s : categorie){
            Categorie cat = new Categorie(s);
            article.addCategorie(new Categorie(s));
           
        }
        articles.addArticle(article);
        return "listarticles";
    }
    
}
 