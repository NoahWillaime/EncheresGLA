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
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import manager.CategorieManagerBeanLocal;
import manager.LoginManagerBeanLocal;

/**
 *
 * @author cirstea
 */

@Named(value = "rechercheArticleBean")
@RequestScoped  
public class RechercheArticle {
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;    
    
    @EJB(name="CategorieManagerBean")
    private CategorieManagerBeanLocal categories;    
    
    
    @EJB(name="LoginManagerBean")
    private LoginManagerBeanLocal login;    
    
   /* @Inject 
    Greeting greet;*/

    @NotEmpty
    @ManagedProperty(value="#{param.nomInput}")
    private String name;
    
    private long[] categorie;
   
    
    /**
     * Creates a new instance of Hello
     */
    public RechercheArticle() {
        name = "";
    }
    
    public List<Article> getDataSearch() {
        System.out.println(name);
        return articles.findByName(name);
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
        this.name = nom;
    }

    public String getNom() {
        return name;
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
    
     public ArrayList<Article> allArticles(){
        ArrayList<Article> result = new ArrayList<>();
        for (Article a : articles.getAll()){
            result.add(a);
        }
        return result;
    }
     
         
     public ArrayList<Article> allVisibleArticles(Map param){
        ArrayList<Article> result = new ArrayList<>();
        for (Article a : articles.getAll()){
            if(a.getDate().after(new Date()))
            result.add(a);
        }
        return result;
    }
}
 