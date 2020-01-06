/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import testpersistence.TestManagerBeanLocal;
import testpersistence.TestRessource;

/**
 *
 * @author noahd
 */
@Named(value = "TestMyRessourceBean")
@RequestScoped  
public class TestMyRessource {
    @EJB(name="TestManagerBean")
    private TestManagerBeanLocal testManager;

    public TestMyRessource() {
    }
    
    public String addTests() {
        testManager.addTest(new TestRessource("Myst1"));
        testManager.addTest(new TestRessource("MySuperTest2"));
        testManager.addTest(new TestRessource("MySupst3"));
        testManager.addTest(new TestRessource("MySuperTest4"));
        testManager.addTest(new TestRessource("MySuperTest5"));
        return "listTests";
    }

    public List<TestRessource> allTests() {
        return testManager.getAll();
    }
}
