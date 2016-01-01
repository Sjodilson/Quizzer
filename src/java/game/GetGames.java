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
public class GetGames extends HttpServlet {

    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(GetGames.class.getName());
    
    
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
            List<Game> games =  em.createQuery("SELECT g FROM Game g", Game.class).getResultList();            
            logger.info("Creating response to client");
            request.setCharacterEncoding("utf8");
            //response.setCharacterEncoding("utf8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONArray jArr = new JSONArray(games);
            JSONObject obj = new JSONObject();
            obj.put("status", "ok");
            out.print(jArr);
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
