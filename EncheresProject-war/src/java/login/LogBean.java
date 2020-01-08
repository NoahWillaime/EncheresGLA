/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import manager.LoginManagerBeanLocal;

/**
 *
 * @author noahd
 */
@Named(value = "logBean")
@RequestScoped
public class LogBean {
    
    @EJB(name="LoginManagerBean")
    private LoginManagerBeanLocal logManager;

    public LogBean() {
    }
    
    public boolean isLog() {
        return this.logManager.isLog();
    }
    
    public String userPseudo() {
        return this.logManager.getCurrentUserPseudo();
    }
    
    public String register() {
        return "utilisateurs";
    }
    
    public String logIn() {
        return "login";
    }
    
    public String logOut() {
        this.logManager.logOut();
        return "login";
    }
}
