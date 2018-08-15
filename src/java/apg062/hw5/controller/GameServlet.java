/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg062.hw5.controller;

import apg062.hw5.model.GameBean;
import apg062.hw5.model.PegColor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles player's guesses while playing the game. Note that all requests that
 * are forwarded to the game.jsp page must come from this servlet as it is
 * responsible for retrieving the peg colors used in the game for the jsp.
 *
 * @author Productivity
 */
public class GameServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //get data
        String selection1 = request.getParameter("selection1");
        String selection2 = request.getParameter("selection2");
        String selection3 = request.getParameter("selection3");
        String selection4 = request.getParameter("selection4");

        GameBean game = (GameBean) session.getAttribute("game");
        
        

        /* validate data
        the isWon and isValid flags prevent a player from submitting guesses 
        to games that are already complete*/
        if (!game.isWon() && game.isValid()
                && selection1 != null
                && selection2 != null
                && selection3 != null
                && selection4 != null) {
            //create array to represent guess
            PegColor[] guess = new PegColor[]{
                PegColor.parse(selection1),
                PegColor.parse(selection2),
                PegColor.parse(selection3),
                PegColor.parse(selection4)
            };

            game.takeGuess(guess);
            
            if (game.isWon()) {
                //increment games won
                session.setAttribute("gamesWon",
                        (Integer) session.getAttribute("gamesWon") + 1);
                //show solution
                request.setAttribute("showSolution", true);
                //update highscore
                Integer best = (Integer)session.getAttribute("bestScore");
                if(best == null || best > game.getHistory().size()){
                    session.setAttribute("bestScore", game.getHistory().size());
                }
            }
        }

        //forward
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
