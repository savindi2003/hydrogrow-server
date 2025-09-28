/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import model.Validation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Savindi
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Gson gson = new Gson();
        JsonObject responsejson = new JsonObject();
        responsejson.addProperty("success", false);

        JsonObject requestJson = gson.fromJson(req.getReader(), JsonObject.class);
        String email = requestJson.get("email").getAsString();
        String password = requestJson.get("password").getAsString();
        String username = requestJson.get("username").getAsString();
        

        if (email.isEmpty()) {
            responsejson.addProperty("message", "Please fill the Email Address");
        } else if (username.isEmpty()) {
            responsejson.addProperty("message", "Please fill your user name");
        
        } else if (password.isEmpty()) {
            responsejson.addProperty("message", "Please fill the Password");
        
        } else if (Validation.isValidPassword(password)) {
            responsejson.addProperty("message", "Invalid Password");
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria1 = session.createCriteria(User.class);
            criteria1.add(Restrictions.eq("email", email));

            if (!criteria1.list().isEmpty()) {
                responsejson.addProperty("message", "This email alreay Used");
            } else {
                User user = new User();
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                

                session.save(user);
                session.beginTransaction().commit();

                responsejson.addProperty("success", true);
                responsejson.addProperty("message", "Registration Complete!");

            }

            session.close();
        }
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(responsejson));
    }
    
    }

    


