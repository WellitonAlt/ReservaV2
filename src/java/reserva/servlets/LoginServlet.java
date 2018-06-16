package reserva.servlets;

import reserva.beans.Login;
import reserva.dao.HotelDAO;
import reserva.beans.Hotel;
import reserva.dao.SiteDAO;
import reserva.beans.Site;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Resource(name = "jdbc/ReservaDBLocal")
    DataSource dataSource;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
           Login login = new Login();
           HotelDAO hotelDao = new HotelDAO(dataSource);
           Hotel hotel = new Hotel();
           SiteDAO siteDao = new SiteDAO(dataSource);
           Site site = new Site();
           BeanUtils.populate(login, request.getParameterMap());
           request.getSession().setAttribute("login", login);
           
           if (login.getTipo().equals("adm")){
               if(login.getUsuario().equals("root") && login.getSenha().equals("root")){
                    request.getRequestDispatcher("areaAdm.jsp").forward(request, response);
               }else{
                    String mensagem = "Usuario ou Senha de Administrador são Invalidos!!!";
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
               }
           }else if (login.getTipo().equals("hotel")){
               hotel = hotelDao.loginHotel(login.getUsuario().replace(".","").replace("/","").replace("-",""), login.getSenha()); 
               if(hotel != null){
                   request.setAttribute("hotel", hotel);
                   request.getRequestDispatcher("areaHotel.jsp").forward(request, response);
               }
               else{
                    String mensagem = "Usuario ou Senha de Hotel são Invalidos!!!";
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
               }                   
           }else{
               site = siteDao.loginSite(login.getUsuario(), login.getSenha()); 
               if(site != null){
                   request.setAttribute("site", site);
                   request.getRequestDispatcher("areaSite.jsp").forward(request, response);
               }
               else{
                    String mensagem = "Usuario ou Senha de Site são Invalidos!!!";
                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
               }                   
           }
           
               
        }catch (IOException | IllegalAccessException | InvocationTargetException | SQLException | NamingException | ServletException ex) {
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
