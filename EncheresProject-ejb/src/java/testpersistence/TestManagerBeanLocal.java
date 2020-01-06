/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpersistence;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author noahd
 */
@Local
public interface TestManagerBeanLocal {
    public TestRessource addTest(TestRessource test);
    public TestRessource getTest(long id);
    public List<TestRessource> getAll();
}
