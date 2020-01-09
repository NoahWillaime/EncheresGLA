/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Promotion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author noahd
 */
@Local
public interface PromotionManagerBeanLocal {
    public List<Promotion> getAll();
    public Promotion addPromotion(Promotion promotion);
    public void deleteAll();
}
