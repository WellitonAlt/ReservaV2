
package reserva.views;

import reserva.dao.HotelDAO;
import reserva.beans.Hotel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

@Named
@RequestScoped
public class ListaHoteis implements Serializable {
    
    List<Hotel> hoteis;
    String cidade;
    
    @Inject 
    HotelDAO hotelDAO;
    
    public List<Hotel> getHoteis() { 
        return hoteis;
    }

    public void setHoteis(List<Hotel> hoteis) {
        this.hoteis = hoteis;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String listaTodosHoteis() throws SQLException, NamingException {
        hoteis = hotelDAO.listarHoteisPorCidade("");        
        return "listaHoteis";
    }
    
    public String listaTodosHoteisByCidade() throws SQLException, NamingException {
        hoteis = hotelDAO.listarHoteisPorCidade(cidade);        
        return "listaHoteis";
    }
    
}
