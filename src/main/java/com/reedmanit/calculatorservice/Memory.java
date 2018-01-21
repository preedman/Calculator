/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reedmanit.calculatorservice;

/**
 *
 * @author paul
 */
public class Memory {
    
    private Double number = 0.0;
    
    public Memory() {
        
    }
    
    public void store(Double aNumber) {
        number = aNumber;
    }
    
    public Double getNumber() {
        return number;
    }
    
    public void addToMemory(Double aNumber) {
        number = number + aNumber;
    }
    
    public void subtractFromMemory(Double aNumber) {
        number = number - aNumber;
    }
    
    
    
    
}
