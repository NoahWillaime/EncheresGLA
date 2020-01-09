/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.Promotion;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author noahd
 */
@Stateless
public class PromotionManagerBean implements PromotionManagerBeanLocal{
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    @Override
    public List<Promotion> getAll() {
        Query query = em.createQuery("SELECT a FROM Promotion a");
        return (List<Promotion>)query.getResultList();
    }

    @Override
    public Promotion addPromotion(Promotion promotion) {
        em.persist(promotion);
        return promotion;
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Promotion");
        query.executeUpdate();
    }
}
