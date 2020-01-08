/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dto.Utilisateur;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import manager.LoginManagerBean;
import manager.LoginManagerBean;
import manager.LoginManagerBeanLocal;

/**
 *
 * @author noahd
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
     @EJB(name="LoginManagerBean")
    private LoginManagerBeanLocal logManager;
    
    @NotEmpty
    private String pseudo;
    
    @NotEmpty
    private String motdepasse;

    private String errorMessage;
    
    public LoginBean() {
        this.errorMessage = "";
    }

    public LoginBean(String pseudo, String motdepasse) {
        this.pseudo = pseudo;
        this.motdepasse = motdepasse;
        this.errorMessage = "";
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
    
    public String onLoad() {
        return this.errorMessage;
    }
    
    public String validateLogin() {
        logManager.validateLogin(pseudo, motdepasse);
        if (logManager.getUsers().size() != 1)
            this.errorMessage = "Identifiants incorrects";
        else
            return "articles";
        return "login";
    }
}
