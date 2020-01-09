/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedule;

import javax.ejb.Local;

/**
 *
 * @author noahd
 */
@Local
public interface GeneratePromotionBeanLocal {
    public boolean isPromo(float chance);
    
    public float generateReduc();
    
    public void calculNewPromos();
}
