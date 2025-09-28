/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Plant;
import entity.User;
import entity.Watering;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Savindi
 */
@WebServlet(name = "ControlPump", urlPatterns = {"/ControlPump"})
public class ControlPump extends HttpServlet {

    private static String pump_action = "Unknown";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        JsonObject requestJson = gson.fromJson(req.getReader(), JsonObject.class);
        String action = requestJson.get("action").getAsString();

        System.out.println(action);

        pump_action = action;

        if (action.equals("stop")) {

            String start_p = requestJson.get("start_percentage1").getAsString();
            String stop_p = requestJson.get("stop_percentage").getAsString();

            String user = requestJson.get("user").getAsString();
            User user_id = (User) session.get(User.class, Integer.parseInt(user));

            System.out.println("stop percentage : " + stop_p);
            System.out.println("start percentage : " + start_p);
            System.out.println("user : " + user_id);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy, MM dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

            try {

                Plant watering = new Plant();
                watering.setBefor_soil_moisture(start_p);
                watering.setAfter_soil_moisture(stop_p);
                watering.setDate(new Date());
                watering.setStart_time(new Date());
                watering.setUser(user_id);

                session.save(watering);
                
                Watering wateringChart1 = new Watering();
                wateringChart1.setSoil_moisture(stop_p);
                wateringChart1.setDate(new Date());
                session.save(wateringChart1);

            } catch (Exception e) {
                e.printStackTrace();
            }

            

        }

        if (action.equals("start")) {
            try {

                String start_p = requestJson.get("start_percentage").getAsString();
               

                Watering wateringChart = new Watering();
                wateringChart.setSoil_moisture(start_p);
                wateringChart.setDate(new Date());
                session.save(wateringChart);

                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        
        transaction.commit();
        
        session.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (pump_action.equals("start")) {
            resp.getWriter().write("start");
        } else if (pump_action.equals("stop")) {
            resp.getWriter().write("stop");
        }

    }

}
