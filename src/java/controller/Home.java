/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Plant;
import entity.Watering;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Savindi
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class Home extends HttpServlet {
    
    private static String latestStatus = "Unknown";
    private static String latestPercentage  = "0";
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
        String status = req.getParameter("status");
        String percentage = req.getParameter("percentage");
        
        latestStatus = status;
        latestPercentage = percentage;
        System.out.println(status);
        System.out.println(percentage);
        
        // Always set response content type
//        resp.setContentType("text/plain");

        // Check the value of the status parameter
        if ("1".equals(status)) { // Use .equals() for string comparison
            resp.getWriter().write("on");
        } else if ("2".equals(status)) {
           resp.getWriter().write("off");
       } else if ("0".equals(status)) {
            resp.getWriter().write("invalid"); // Optional: handle unexpected statuses
        }
    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Gson gson = new Gson();
        

        JsonObject responseData= new JsonObject();
        responseData.addProperty("success", false);
        responseData.addProperty("status", latestStatus);
        responseData.addProperty("percentage", latestPercentage);
        
        
        
        if ("1".equals(latestStatus)) { // Use .equals() for string comparison
            responseData.addProperty("action", "on");
        } else if ("2".equals(latestStatus)) {
            responseData.addProperty("action", "off");
        } else if ("0".equals(latestStatus)) {
             responseData.addProperty("action", "invalid");
        }
        
        
        
        responseData.addProperty("success", true);
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responseData));
        
        
        
    
    }
    
    

   

}
