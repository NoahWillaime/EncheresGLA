/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateur; 
import dto.Adresse;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import dto.Categorie;
import dto.Article;
import dto.CompteBancaire;
import dto.Utilisateur;
import manager.ArticleManagerBeanLocal;
import java.util.Date;
import java.util.ArrayList;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import manager.UtilisateurManagerBeanLocal;

/**
 *
 * @author cirstea
 */

@Named(value = "utilisateurBean")
@RequestScoped  
public class UtilisateurBean {
    @EJB(name="UtilisateurManagerBean")
    private UtilisateurManagerBeanLocal utilisateurs;    
    
   /* @Inject 
    Greeting greet;*/

    @NotEmpty
    private String nom;
    
    @NotEmpty
    private String prenom;
    
    @NotEmpty
    private String pseudo;
    
    @NotEmpty
    private String mdp;
   
    @NotEmpty
    private String nomCompte;
      
    @NotEmpty
    private String rib;
    
    @NotEmpty
    private String ville;
     
    @NotEmpty
    private String rue;
    
    @NotEmpty
    private String codePostal;

    public UtilisateurBean(){
        
    }
    
    public UtilisateurBean(String nom, String prenom, String pseudo, String mdp, String nomCompte, String rib) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.nomCompte = nomCompte;
        this.rib = rib;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNomCompte(String nomCompte) {
        this.nomCompte = nomCompte;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNomCompte() {
        return nomCompte;
    }

    public String getRib() {
        return rib;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    
    public String addUtilisateur(){
        return "utilisateurs";
    }
    
    
    public String registerUtilisateur() {
        Utilisateur utilisateur = new Utilisateur(this.getPrenom(), this.getNom(), this.getPseudo(), this.getMdp());
        if (this.getVille() != null && this.getRue() != null && this.getCodePostal() != null){
            utilisateur.addAdresse(new Adresse(ville, rue, codePostal));
        }
        if (this.getNomCompte() != null && this.getRib() != null){
            utilisateur.addCompteBancaire(new CompteBancaire(nom, rib));
        }
        utilisateurs.addUtilisateur(utilisateur);
        return "listeutilisateurs";
    }
    
     public ArrayList<Utilisateur> allUtilisateurs(){
        ArrayList<Utilisateur> result = new ArrayList<>();
        for (Utilisateur a : utilisateurs.getAll()){
            result.add(a);
        }
        return result;
    }
}
 