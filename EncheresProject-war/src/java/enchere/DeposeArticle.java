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
import java.util.Date;
import java.util.ArrayList;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import manager.CategorieManagerBeanLocal;

/**
 *
 * @author cirstea
 */

@Named(value = "deposeArticleBean")
@RequestScoped  
public class DeposeArticle {
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;    
    
    @EJB(name="CategorieManagerBean")
    private CategorieManagerBeanLocal categories;    
    
   /* @Inject 
    Greeting greet;*/

    @NotEmpty
    private String nom;
    
    @NotEmpty
    private String description;
    
    @NotNull
    private Double prix;
    
    @Future
    private Date date;
    
    private String[] categorie;
   
    
    /**
     * Creates a new instance of Hello
     */
    public DeposeArticle() {
        nom = "";
        description = "";
        prix = null;
        date = null;
    }
    
     public Categorie[] getCategorieObjectArray(){
        return categories.getAll();
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

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setDate(Date date) {
        this.date = date;
    }

  
  
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrix() {
        return prix;
    }

    public Date getDate() {
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
        return "listarticles";
    }
    
    public String registerArticle() {     
        Article article = new Article(this.getNom(), this.getDescription(), this.getPrix(), this.getDate());
        for (String s : categorie){
            article.addCategorie(new Categorie(s));
           
        }
        articles.addArticle(article);
        return "listarticles";
    }
    
     public ArrayList<Article> allArticles(){
        ArrayList<Article> result = new ArrayList<>();
        for (Article a : articles.getAll()){
            result.add(a);
        }
        return result;
    }
}
 