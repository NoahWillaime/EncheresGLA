/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionMsgs;

import javax.ejb.Local;
import jms.Livraison;

/**
 *
 * @author noahd
 */
@Local
public interface GestionLivraisonBeanLocal {
     public void sendOrder(Livraison livraison);
}
