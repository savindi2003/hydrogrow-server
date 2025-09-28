/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Plant;
import entity.User;
import entity.Watering;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Savindi
 */
@WebServlet(name = "loadWteringHistory", urlPatterns = {"/loadWteringHistory"})
public class loadWteringHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Gson gson = new Gson();

        JsonObject responsejson = new JsonObject();
        Session session = HibernateUtil.getSessionFactory().openSession();

        String userId = req.getParameter("user_id");
        
        
        
        Date today = new Date();
        
        Criteria criteria1 = session.createCriteria(Watering.class);
        criteria1.addOrder(Order.desc("id"));
        criteria1.setMaxResults(8);
        
        
        List<Watering> wateringList = criteria1.list();
        Collections.reverse(wateringList);
        
        JsonArray jsonArray = new JsonArray();
        for(Watering wlist : wateringList){
            System.out.println(wlist.getSoil_moisture());
            
            int p = Integer.parseInt(wlist.getSoil_moisture());
            
            jsonArray.add(p);
            
            responsejson.addProperty("success", true);
            
            
            responsejson.add("percentageArray", gson.toJsonTree(jsonArray));
             
            
        }
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responsejson));
        
    }

}
