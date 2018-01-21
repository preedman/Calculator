/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reedmanit.calculatorservice;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author paul
 */
@Path("/calculator")
public class Calculator {

    private ServletContext context;
    private MemoryCache theCache;
    private Memory m;

    @Context
    public void setServletContext(ServletContext context) {
        System.out.println("servlet context set");
        this.context = context;
        theCache = (MemoryCache) context.getAttribute("cache");

       // m = (Memory) context.getAttribute("memory");

    }

    Memory calculatorMemory;

    @GET
    @Produces("application/json")
    @Path("/session/{id}")
    public String establishSession(@PathParam("id") String sessionId) {

        Integer result = new Integer(0);

        if (!theCache.containSession(sessionId)) {

            Memory m = new Memory();
            theCache.addToCache(sessionId, m);
            result = 1;
            System.out.println("Session Established " + sessionId);
        }

        JsonObject myObject = Json.createObjectBuilder()
                .add("id", sessionId)
                .add("result", result.toString())
                .build();

        return myObject.toString();

    }

    @GET
    @Produces("application/json")
    @Path("/expression/{values}/{id}")
    public String calculate(@DefaultValue("0+0") @PathParam("values") String expression, @DefaultValue("0") @PathParam("id") String sessionId) {

        Double result = new Double(0);
        System.out.println("In - expression " + expression);
        System.out.println("In - session id " + sessionId);

        try {
            if (theCache.containSession(sessionId)) {
                expression = StringUtils.replace(expression, "~", "/");    // a cludge - workaround for / division symbol in data
                System.out.println("Expression is " + expression);
                Calculable calc = new ExpressionBuilder(expression).build();
                result = calc.calculate();
            }
        } catch (UnknownFunctionException ex) {
            System.out.println("Exception " + ex.getMessage());
        } catch (UnparsableExpressionException ex) {
            System.out.println("Exception " + ex.getMessage());
        }

        JsonObject myObject = Json.createObjectBuilder()
                .add("values", expression)
                .add("result", result.toString())
                .build();

        return myObject.toString();

    }
    
    @GET
    @Produces("application/json")
    @Path("/clearmemory/{value}/{sessionid}")
    public String cancelMemory(@DefaultValue("0.0") @PathParam("value") String number, @DefaultValue("0") @PathParam("sessionid") String sessionId) {
        
        if (theCache.containSession(sessionId)) {
            m = theCache.getFromCache(sessionId);
            m.store(new Double(number));
            theCache.addToCache(sessionId, m);
        }
        JsonObject myObject = Json.createObjectBuilder()
                .add("sessionid", sessionId)
                .add("value", number)
                .add("result", m.getNumber().toString())
                .build();

        return myObject.toString();
        
    }

    @GET
    @Produces("application/json")
    @Path("/addtomemory/{value}/{sessionid}")
    public String addToMemory(@DefaultValue("0.0") @PathParam("value") String number, @DefaultValue("0") @PathParam("sessionid") String sessionId) {

        Double n = new Double(number);

        if (theCache.containSession(sessionId)) {

            m = theCache.getFromCache(sessionId);

            System.out.println("Value of memory before add " + m.getNumber() + " for session " + sessionId);

            m.addToMemory(n);

            System.out.println("Value of memory after add " + m.getNumber() + " for session " + sessionId);

            theCache.addToCache(sessionId, m);
        }

        JsonObject myObject = Json.createObjectBuilder()
                .add("sessionid", sessionId)
                .add("value", number)
                .add("result", m.getNumber().toString())
                .build();

        return myObject.toString();

    }

    @GET
    @Produces("application/json")
    @Path("/subtractfrommemory/{value}/{sessionid}")
    public String subtractFromMemory(@DefaultValue("0.0") @PathParam("value") String number, @DefaultValue("0") @PathParam("sessionid") String sessionid) {

        Double n = new Double(number);

        if (theCache.containSession(sessionid)) {
            m = theCache.getFromCache(sessionid);
            
            System.out.println("Value of memory before subtract " + m.getNumber() + " for session " + sessionid);
            
            m.subtractFromMemory(n);
            
            System.out.println("Value of memory after subtract " + m.getNumber() + " for session " + sessionid);
            
            theCache.addToCache(sessionid, m);
        }

            JsonObject myObject = Json.createObjectBuilder()
                    .add("value", number)
                    .add("result", m.getNumber().toString())
                    .build();
        

        return myObject.toString();

    }

}
