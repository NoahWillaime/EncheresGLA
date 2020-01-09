/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionUser;

import dto.Utilisateur;

/**
 *
 * @author noahd
 */
public class LogSingleton {
    private Utilisateur currentUser;

    private LogSingleton() {
        this.currentUser = null;
    }
    
    public static LogSingleton getInstance() {
        return LogSingletonHolder.INSTANCE;
    }
    
    private static class LogSingletonHolder {

        private static final LogSingleton INSTANCE = new LogSingleton();
    }
    
    public void userLogin(Utilisateur user) {
        this.currentUser = user;
    }
    
    public void logOut() {
        this.currentUser = null;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }
    
    public boolean isLog() {
        return this.currentUser != null;
    }
}
