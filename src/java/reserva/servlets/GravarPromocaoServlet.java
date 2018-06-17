/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.servlets;

import reserva.beans.Promocao;
import reserva.dao.PromocaoDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;

@WebServlet(name = "GravarPromocaoServlet", urlPatterns = {"/GravarPromocaoServlet"})
public class GravarPromocaoServlet extends HttpServlet {

    @Resource(name = "jdbc/ReservaDBLocal")
    DataSource dataSource;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Promocao promocao = new Promocao();  
        PromocaoDAO promocaoDao = new PromocaoDAO(dataSource);
        
        try {
            List<String> mensagens = new ArrayList<>();
            promocao.setHotel(Integer.valueOf(request.getParameter("hotel")));
            promocao.setSite(Integer.valueOf(request.getParameter("site")));
            
            String preco = request.getParameter("preco");
            if (!preco.matches("[0-9]+((\\,|\\.)[0-9][0-9])?"))
                mensagens.add("Preço deve ser na forma NN,NN!");    
            else if (preco.isEmpty())
                mensagens.add("Preço não pode ser vazio!");
            else{
                preco = preco.replace(',', '.');
                promocao.setPreco(Float.valueOf(preco));
            }
            
            if (!request.getParameter("dataInicial").isEmpty()){
                promocao.setDataInicial(Date.valueOf(request.getParameter("dataInicial")));
            }
            else
               mensagens.add("Data Inicial não pode ser vazio!"); 
            
            if (!request.getParameter("dataFinal").isEmpty()){
                promocao.setDataFinal(Date.valueOf(request.getParameter("dataFinal")));
            }
            else
               mensagens.add("Data Final não pode ser vazio!");
             
            if(mensagens.isEmpty()) {           
            
                if(promocaoDao.verificaData(promocao.getDataInicial(), promocao.getDataFinal(), promocao.getHotel()))
                    mensagens.add("Ja existe uma promocao para essa mesma data!");
                
                if(promocao.getDataFinal().before(promocao.getDataInicial()))
                    mensagens.add("A Data Final não pode ser anterior à Data Inicial!");
            }
            
            request.getSession().setAttribute("promocao", promocao);            
            String aux = "CadastroPromocaoServlet?hotel=" + request.getParameter("hotel");        
            
            if(mensagens.isEmpty()) {
                promocaoDao.gravarPromocao(promocao);
                String mem = "Dados Salvos: <br/>";
                mem = mem + "Hotel: " + request.getParameter("hotelNome") + "<br/>";
                mem = mem + "Preço: R$" + promocao.getPreco()+ "<br/>";
                mem = mem + "Data Inicial: " + promocao.getDataInicial() + "<br/>";
                mem = mem + "Data Final: " + promocao.getDataFinal() + "<br/>";
                request.setAttribute("salvou", mem);                
                request.getRequestDispatcher(aux).forward(request, response);
            } else {
                request.setAttribute("mensagens", mensagens);
                request.getRequestDispatcher(aux).forward(request, response);
            }
        } catch (IOException | SQLException | NamingException | ServletException ex) {
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
