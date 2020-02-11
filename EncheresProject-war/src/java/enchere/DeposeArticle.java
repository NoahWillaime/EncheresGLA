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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import manager.ArticleManagerBeanLocal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    
    private Date date;
    
    private long[] categorie;
    
    private String valEnchere;
   
    
    /**
     * Creates a new instance of Hello
     */
    public DeposeArticle() {
        nom = "";
        description = "";
        prix = null;
        date = null;
        //valEnchere = new HashMap<Long, Integer>();
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
        article.addUtilisateur(login.getCurrentUser());
        Enchere enchere = new Enchere(article,null,getPrix(),getDate());
        for (long l : categorie){
            for (Categorie c : this.getCategorieObjectArray())
                if (c.getId() == l) {
                    article.addCategorie(c);
                }
        }
        articles.addArticle(article);
        encheres.addEnchere(enchere);
        return "listarticles";
    }
    
     public List<Enchere> allEnchere(){
        return encheres.getAll();
    }
     
     public String encherir(Enchere e, String enchere,String path) {
        Double prixEnchere = Double.parseDouble(enchere);
         encheres.encherir(e,login.getCurrentUser(),prixEnchere);
         return path;
     }
     
 
      public List<Article> getArticlesByUsers(){
        return articles.getArticlesByUsers(login.getCurrentUser().getId());
    }
      
      public List<Enchere> getEncheresByUser() {
          return encheres.getEncheresByUser(login.getCurrentUser());
      }
      
      
      public List<Article> getArticlesGagnesByUser() {
          return articles.findByWinner(login.getCurrentUser());
      }
      
     public List<Enchere> allVisibleArticles(){
        ArrayList<Enchere> result = new ArrayList<>();
        for (Enchere e : encheres.getAll()){
            if(e.getDate().after(new Date()))
            result.add(e);
        }
        return result;
    }
     
     public String getValEnchere() {
         return valEnchere;
     }
     
     public void setValEnchere(String val) {
         valEnchere = val;
     }
     
     public void validateEnchere(FacesContext context, 
			         UIComponent component, 
			Object value) throws ValidatorException {
         String val = String.valueOf(value);
         String user = (String) component.getAttributes().get("user");
         Enchere e = (Enchere) component.getAttributes().get("enchere");           
         try {
             Double v = Double.parseDouble(val);
             if(e.getArticle().getUtilisateur() != null && user == e.getArticle().getUtilisateur().getPseudo())
                 throw new ValidatorException(new FacesMessage("Vous êtes le vendeur"));
             System.out.println("Teste = ");
             if(e.getLastAcheteur() != null && user == e.getLastAcheteur().getPseudo())
                 throw new ValidatorException(new FacesMessage("Vous êtes le dernier acheteur"));
         } catch (NumberFormatException err) {
             throw new ValidatorException(new FacesMessage("Entrer une valeur numérique!"));
         }
         
     }
     
     public void validateFuture(FacesContext context, 
			         UIComponent component, 
			Object value) throws ValidatorException {
            System.out.println("ici");
            Date date = (Date)value;
            System.out.println(date);
            if(date.getTime() <= new Date().getTime())
                throw new ValidatorException(new FacesMessage("La date doit être dans le future!"));
               
     }
    
}
 