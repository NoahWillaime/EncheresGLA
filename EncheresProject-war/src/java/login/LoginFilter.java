/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.LoginManagerBeanLocal;

/**
 *
 * @author noahd
 */
@WebFilter("/*")
public class LoginFilter implements Filter{
    @EJB(name="LoginManagerBean")
    private LoginManagerBeanLocal logManager;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        System.out.println(request.getRequestURI());
        String splitUrl[] = request.getRequestURI().split("/");
        String myPage = splitUrl[splitUrl.length-1];
        //Not login or register && not 
        if (!(myPage.equals("login.xhtml") || myPage.equals("utilisateurs.xhtml")) && !myPage.endsWith("css") && !logManager.isLog()) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }
    
}
