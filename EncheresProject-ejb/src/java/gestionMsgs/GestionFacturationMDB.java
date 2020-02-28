/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import jms.Facturation;
import manager.ArticleManagerBeanLocal;

/**
 *
 * @author noahd
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/RecepFactureQueue")
})
public class GestionFacturationMDB implements MessageListener {
    @Resource
    private MessageDrivenContext context;
    
    @EJB(name="ArticleManagerBean")
    private ArticleManagerBeanLocal articles;
    
    @Override
    public void onMessage(Message message) {
        try {
            Facturation facturation = message.getBody(Facturation.class);
            processFacturation(facturation);
        } catch (JMSException jmse) {
            jmse.printStackTrace();
            context.setRollbackOnly();
        }
    }
    
    public void processFacturation(Facturation facturation) {
        for (long article_id : facturation.getPanier()) {
            articles.commandeValide(article_id);
        }
        System.out.println("Panier validé");
    }
}

