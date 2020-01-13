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
import dto.Enchere;
import manager.ArticleManagerBeanLocal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import manager.CategorieManagerBeanLocal;
import manager.EnchereManagerBean;
import manager.EnchereManagerBeanLocal;
import manager.LoginManagerBeanLocal;

/**
 *
 * @author cirstea
 */

@Named(value = "deposeArticleBean")
@RequestScoped  
public class DeposeArticle {
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;    
    
    @EJB(name="EnchereManagerBean")
    private EnchereManagerBeanLocal encheres;  
    
    @EJB(name="CategorieManagerBean")
    private CategorieManagerBeanLocal categories;    
    
    
    @EJB(name="LoginManagerBean")
    private LoginManagerBeanLocal login;    
    
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
    
    private long[] categorie;
   
    
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
     
    
   public long[] getCategorie(){
      return categorie;
   }
   
   public void setCategorie(long[] categorie){
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
    
    public String listArticles(){
        return "listarticles";
    }
    
    public String retirerArticle(Long id){
        articles.removeArticle(id);
        return "listarticles";
    }
    
    public String registerArticle() {     
        Article article = new Article(this.getNom(), this.getDescription(), this.getPrix());
        Enchere enchere = new Enchere(article,null,getPrix(),getDate());
        for (long l : categorie){
            for (Categorie c : this.getCategorieObjectArray())
                if (c.getId() == l) {
                    article.addCategorie(c);
                }
        }
        System.out.println(article.toString());
        System.out.println(article);
        login.getCurrentUser().addArticles(article);
        articles.addArticle(article);
        encheres.addEnchere(enchere);
        System.out.println(login.getCurrentUserPseudo());
        return "listarticles";
    }
    
     public List<Enchere> allEnchere(){
        return encheres.getAll();
    }
     
     public String encherir(Enchere e) {
         encheres.encherir(e,login.getCurrentUser());
         return "listarticles";
     }
     
 
      public List<Article> getArticlesByUsers(){
        return articles.getArticlesByUsers(login.getCurrentUser().getId());
    }
      
      public List<Enchere> getEncheresByUser() {
          return encheres.getEncheresByUser(login.getCurrentUser());
      }
      
     public List<Enchere> allVisibleArticles(){
        ArrayList<Enchere> result = new ArrayList<>();
        for (Enchere e : encheres.getAll()){
            if(e.getDate().after(new Date()))
            result.add(e);
        }
        return result;
    }
}
 