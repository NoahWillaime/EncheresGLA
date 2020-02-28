/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import jms.Livraison;

/**
 *
 * @author noahd
 */
@Stateless
public class GestionLivraisonBean implements GestionLivraisonBeanLocal {
    @Inject
    JMSContext context;
    
    @Resource(lookup = "jms/LivraisonQueue")
    Destination livraisonQueue;
    
    @Asynchronous
    @Override
    public void sendOrder(Livraison livraison) {
        context.createProducer().send(livraisonQueue, livraison);
    }
}
