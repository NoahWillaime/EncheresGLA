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
import gestionMsgs.GestionFacturationBeanLocal;
import gestionMsgs.GestionLivraisonBeanLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import manager.ArticleManagerBeanLocal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import jms.Facturation;
import jms.Livraison;
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
    
    @EJB(name="GestionFacturationBean")
    private GestionFacturationBeanLocal gestionFacturation;
    
    @EJB(name="GestionLivraisonBean")
    private GestionLivraisonBeanLocal gestionLivraison;
    
   /* @Inject 
    Greeting greet;*/

    @NotEmpty
    private String nom;
    
    @NotEmpty
    private String description;
    
    @NotNull
    private Double prix;
    
    private Date date;
    
    private HashMap<Long,Boolean> categorie = new HashMap<>();   
    
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
         Categorie[] cat = categories.getAll();
         if(categorie.size() == 0) {
             System.out.println("ici");
            for(Categorie c : cat) {
                categorie.put(c.getId(), false);
            }
         }
        return categories.getAll();
    }
     
     
    
   public HashMap<Long,Boolean> getCategorie(){
      getCategorieObjectArray();
      for(Map.Entry<Long,Boolean> e : categorie.entrySet())
            System.out.println( e.getKey()+" Value: " + e.getValue());
      return categorie;
   }
   
   public void setCategorie(HashMap<Long,Boolean> categorie){
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

    public void setCheck(Long id, boolean value) {
        categorie.replace(id, value);
        for(Map.Entry<Long,Boolean> e : categorie.entrySet())
            System.out.println( e.getKey()+" Value: " + e.getValue());
    }
 
  /*  public String sayHello() {
        return nameHandler.greeetingsMessage(this.getNickname());  
    }*/
    
    public String addArticle(){
        return "articles.xhtml";
    }
    
    public String listArticles(){
        return "listarticles";
    }
    
    public String retirerArticle(Long id){
        articles.removeArticle(id);
        return "listarticles";
    }
    
    public String retirerEnchere(Long id) {
        encheres.retirerEnchere(id);
        return "listarticlesuser";
    }
    
    public String registerArticle() {     
        Article article = new Article(this.getNom(), this.getDescription());
        article.addUtilisateur(login.getCurrentUser());
        Enchere enchere = new Enchere(article,getPrix(),getDate());
        for(Map.Entry<Long,Boolean> e : categorie.entrySet())
            System.out.println("Value: " + e.getValue());
        for (Categorie c : categories.getAll()){
                if (categorie.get(c.getId())) {
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
     
     public String annuleEnchere(Enchere e){
         encheres.annuleEnchere(login.getCurrentUser().getId(),e.getId());
         return "listarticlesuser";
     }
     
 
      public List<Article> getArticlesByUsers(){
        return articles.getArticlesByUsers(login.getCurrentUser().getId());
    }
      
      public List<Enchere> getEncheresByUser() {
          return encheres.getEncheresByUser(login.getCurrentUser());
      }
      
      
      public List<Enchere> getArticlesGagnesByUser() {
          return encheres.findByWinner(login.getCurrentUser());
      }
      
      public String validerPanier(){
          return "validerpanier";
      }
      
     public List<Enchere> allVisibleArticles(){
        ArrayList<Enchere> result = new ArrayList<>();
        for (Enchere e : encheres.getAll()){
            if(!e.getFin())
            result.add(e);
        }
        return result;
    }
  
     public void ajouterPanier(Enchere enchere) {
       //  System.out.println(enchere.getId());
         articles.setPanier(enchere.getArticle().getId(), true);
     }
     public List<Enchere> getEncheresPanier(){
        return articles.getArticlesFromPanier(login.getCurrentUser().getId());
     }
     public void validateEnchere(FacesContext context, UIComponent component, Object value) throws ValidatorException {
         String val = String.valueOf(value);
         String user = (String) component.getAttributes().get("user");
         Enchere e = (Enchere) component.getAttributes().get("enchere");           
         try {
             Double v = Double.parseDouble(val);
             if(e.lastEnchere().getPrix() >= v)
                 throw new ValidatorException(new FacesMessage("Enchère trop faible"));
             if(e.getArticle().getUtilisateur() != null && user == e.getArticle().getUtilisateur().getPseudo())
                 throw new ValidatorException(new FacesMessage("Vous êtes le vendeur"));
             System.out.println("Teste = ");
             if(e.lastEnchere().getAcheteur() != null && user == e.lastEnchere().getAcheteur().getPseudo())
                 throw new ValidatorException(new FacesMessage("Vous êtes le dernier acheteur"));
         } catch (NumberFormatException err) {
             throw new ValidatorException(new FacesMessage("Entrer une valeur numérique!"));
         }
         
     }
     
     public void validateFuture(FacesContext context, 
			         UIComponent component, 
			Object value) throws ValidatorException {
           
            Date date = (Date)value;
            
            if(date.getTime() <= new Date().getTime())
                throw new ValidatorException(new FacesMessage("La date doit être dans le future!"));
               
     }
     
     public double getPrixPanier() {
         double prix = 0;
         for(Enchere e : articles.getArticlesFromPanier(login.getCurrentUser().getId())) {
             prix += e.getPrixFinal();
         }
         return prix;
     }
     
     public String commander() {
         ArrayList<Article> panier = new ArrayList<>();
         for(Enchere e : getEncheresPanier())
             panier.add(e.getArticle());
         long[] articles = new long[panier.size()];
         for (int i = 0; i < panier.size(); i++)
             articles[i] = panier.get(i).getId();
         Facturation facture = new Facturation(login.getCurrentUser().getId(), articles, getPrixPanier());
         Livraison livraison = new Livraison(login.getCurrentUser().getId(), articles, getPrixPanier());
         this.gestionFacturation.sendOrder(facture);
         this.gestionLivraison.sendOrder(livraison);
         return "validationCommande";
     }
}
 