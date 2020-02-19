/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import jms.Facturation;

/**
 *
 * @author noahd
 */
@Stateless
public class GestionFacturationBean implements GestionFacturationBeanLocal{    
    @Inject
    JMSContext context;
    
    @Resource(lookup = "jms/FactureQueue")
    Destination facturationQueue;
    
    @Asynchronous
    @Override
    public void sendOrder(Facturation facturation) {
        context.createProducer().send(facturationQueue, facturation);
    }

}
