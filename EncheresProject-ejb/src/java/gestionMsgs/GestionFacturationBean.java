/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import javax.annotation.Resource;
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
    
    @Resource(lookup = "jms/FacturationQueue")
    Destination facturationQueue;
    
    @Override
    public void sendOrder(Facturation facturation) {
        context.createProducer().send(facturationQueue, "mon message");
    }

}