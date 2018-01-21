/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reedmanit.calculatorservice;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author paul
 */
public class MemoryCache {
    
    private static ConcurrentHashMap<String, Memory> cache;
    
    public MemoryCache() {
        cache = new ConcurrentHashMap<String, Memory>();
        
        
    }
    
    public void addToCache(String sessionId, Memory calculatorMem) {
        cache.put(sessionId, calculatorMem);
       
    }
    
    public Memory getFromCache(String sessionId) {
        
        return cache.get(sessionId);
        
    }
    
    public boolean containSession(String sessionId) {
        return cache.containsKey(sessionId);
    }
    
    public void removeFromCache(String sessionId) {
        cache.remove(sessionId);
    }
    
    
}
