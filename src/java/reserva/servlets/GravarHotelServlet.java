/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.servlets;

import reserva.beans.Hotel;
import reserva.dao.HotelDAO;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "GravarHotelServlet", urlPatterns = {"/GravarHotelServlet"})
public class GravarHotelServlet extends HttpServlet {

    @Resource(name = "jdbc/ReservaDBLocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Hotel hotel = new Hotel();     
        HotelDAO hotelDao = new HotelDAO(dataSource);
        
        try {
            BeanUtils.populate(hotel, request.getParameterMap());
            request.getSession().setAttribute("hotel", hotel);
            List<String> mensagens = hotel.validar();
           
            if(mensagens == null) {
                hotelDao.gravarHotel(hotel);
                String mem = "Dados Salvos: <br/>";
                mem = mem + "CNPJ: " + hotel.getCNPJMascara()+ "<br/>";
                mem = mem + "Senha: **** <br/>";
                mem = mem + "Nome: " + hotel.getNome() + "<br/>";
                mem = mem + "Cidade: " + hotel.getCidade() + "<br/>";
                request.setAttribute("salvou", mem);
                request.getRequestDispatcher("cadastroHotel.jsp").forward(request, response);
            } else {
                request.setAttribute("mensagens", mensagens);
                request.getRequestDispatcher("cadastroHotel.jsp").forward(request, response);
            }
   
        } catch (IOException | IllegalAccessException | InvocationTargetException | SQLException | NamingException | ServletException ex) {
            request.setAttribute("mensagem", ex.getLocalizedMessage());
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
