/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Tomas
 */
public class GetGameStatus extends HttpServlet {

    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(GetGameStatus.class.getName());
    
    
    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        logger.info("Obtaining DB connection.");
        EntityManagerFactory emf =
           (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        logger.info("DB connection obtained.");
 
        try {
            logger.info("Retriving game");
            Integer id = Integer.parseInt(request.getParameter("id"));
            //List<Game> games =  em.createQuery("SELECT g FROM Game AS g WHERE g.name='" + gameName + "'", Game.class).getResultList();
            Game game = em.find(Game.class, id);
            
            
            
            Cookie userCookie = new Cookie("gameId", game.getId().toString());
            userCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
            response.addCookie(userCookie);
            request.setCharacterEncoding("utf8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject(game);
            obj.put("status", "ok");
            out.print(obj);
            
            
        }   catch (JSONException ex) {
            logger.error(ex.toString());
            } finally {
            // Close the database connection:
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
    }
 
    @Override
    protected void doPost(
        HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
