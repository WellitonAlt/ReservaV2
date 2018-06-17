/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.servlets;

import reserva.dao.PromocaoDAO;
import reserva.beans.PromocaoConsulta;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "ListarPromocaoServlet", urlPatterns = {"/ListarPromocaoServlet"})
public class ListarPromocaoServlet extends HttpServlet {
    
    @Resource(name = "jdbc/ReservaDBLocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PromocaoDAO promocaoDAO = new PromocaoDAO(dataSource);
        
        String tipo = request.getParameter("tipo");
        String ID = request.getParameter("ID");
        
        List<PromocaoConsulta> promocoes = null;
        try {
            if (tipo.equals("1")) {
                promocoes = promocaoDAO.listarTodasPromocoesByHotel(ID);
            } else {
                promocoes = promocaoDAO.listarTodasPromocoesBySite(ID);
            }
            
            request.setAttribute("promocoes", promocoes);
            request.getRequestDispatcher("listaPromocoes.jsp").forward(request, response);
        } catch (IOException | SQLException | NamingException | ServletException e) {
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        
        
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
