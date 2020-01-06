/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpersistence;

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
public class TestManagerBean implements TestManagerBeanLocal{
    @PersistenceContext(unitName = "Encheres-PU")
    private EntityManager em;
    
    @Override
    public TestRessource addTest(TestRessource test) {
        em.persist(test);
        return test;
    }

    @Override
    public TestRessource getTest(long id) {
        return this.em.find(TestRessource.class, id);
    }
    
    public List<TestRessource> getAll() {
        Query query = em.createQuery("SELECT t FROM TestRessource t");
        return (List<TestRessource>) query.getResultList();
    }
}
