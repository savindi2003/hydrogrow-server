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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Savindi
 */
@WebServlet(name = "LoadTimetable", urlPatterns = {"/LoadTimetable"})
public class LoadTimetable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Gson gson = new Gson();
        JsonObject responsejson = new JsonObject();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String userId = req.getParameter("user_id");
        User user = (User) session.get(User.class, Integer.parseInt(userId));
        
        System.out.println("user id is ---"+userId);
        
        Criteria criteria1 = session.createCriteria(Plant.class);
        criteria1.add(Restrictions.eq("user", user));
        criteria1.addOrder(Order.desc("id"));
        
        List<Plant> wateringtime = criteria1.list();
        
        JsonArray jsonTimeArray = new JsonArray();
        
        for (Plant timetable : wateringtime) {
            
            JsonObject jsonItem = new JsonObject();
            jsonItem.addProperty("after", timetable.getAfter_soil_moisture());
            jsonItem.addProperty("befor", timetable.getBefor_soil_moisture());
            
            SimpleDateFormat simpledate = new SimpleDateFormat("yyyy MMM dd");
            SimpleDateFormat simpledatetime = new SimpleDateFormat("yyyy MMM dd hh:mm a");
            SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
            
            
            jsonItem.addProperty("date",simpledatetime.format(timetable.getDate()));
            
            
            jsonTimeArray.add(jsonItem);
            
            responsejson.addProperty("success", true);
            responsejson.add("jsonTimeArray", gson.toJsonTree(jsonTimeArray));
            
            
            
        
        }
        
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responsejson));
    
    }

   

}
