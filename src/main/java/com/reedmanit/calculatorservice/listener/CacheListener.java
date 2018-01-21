package com.reedmanit.calculatorservice.listener;

import com.reedmanit.calculatorservice.Memory;
import com.reedmanit.calculatorservice.MemoryCache;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author paul
 */
public class CacheListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        MemoryCache theCache = new MemoryCache();

        sc.setAttribute("cache", theCache);
      //  sc.setAttribute("memory", new Memory());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
