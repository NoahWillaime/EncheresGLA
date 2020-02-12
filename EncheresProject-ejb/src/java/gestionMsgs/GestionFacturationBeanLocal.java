/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import javax.ejb.Local;
import jms.Facturation;

/**
 *
 * @author noahd
 */
@Local
public interface GestionFacturationBeanLocal {
    public void sendOrder(Facturation facturation);
}
