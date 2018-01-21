/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reedmanit.calculatorservice.tests;

import com.reedmanit.calculatorservice.Memory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author paul
 */
public class MemoryUnitTest {
    
    public MemoryUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void add() {
        Memory m = new Memory();
        m.addToMemory(Double.valueOf(10.0));
        System.out.println(m.getNumber());
        
        m.subtractFromMemory(Double.valueOf(5.0));
        System.out.println(m.getNumber());
    }
}
