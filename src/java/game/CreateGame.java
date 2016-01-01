/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Tomas
 */
public class CreateGame extends HttpServlet {

        private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(CreateGame.class.getName());
    private Object JSONValue;
    
    
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
            logger.info("Creating new game with name parameter");
            String name = request.getParameter("name");
            String img = request.getParameter("img");
            String description = request.getParameter("description");
            Scoreboard scoreboard = new Scoreboard();
            em.getTransaction().begin();
            em.persist(scoreboard);
            em.getTransaction().commit();
            
            Game game = new Game(name, img, description);
            game.setScoreboard(scoreboard);
            if (name != null) {
                em.getTransaction().begin();
                em.persist(game);
                em.getTransaction().commit();
            } else {
                logger.info("No game created");
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JSONObject obj = new JSONObject();
                obj.put("status", "error");
                obj.put("message", "No game added");
                out.print(obj);
            }
            logger.info("Game created.");
            request.setCharacterEncoding("utf8");
        //response.setCharacterEncoding("utf8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        obj.put("message", "Game: " + name + " created");
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
