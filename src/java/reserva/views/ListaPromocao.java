package reserva.views;

import reserva.dao.PromocaoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import reserva.beans.PromocaoConsulta;

@Named
@RequestScoped
public class ListaPromocao implements Serializable{
   
    List<PromocaoConsulta> promocoes;
    
    @Inject 
    PromocaoDAO promocaoDAO;
     

    public List<PromocaoConsulta> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<PromocaoConsulta> promocoes) {
        this.promocoes = promocoes;
    }

    public String listaPromocao(int op, int siteId) throws SQLException, NamingException {
        String id = Integer.toString(siteId);
        if (op == 1){
            promocoes = promocaoDAO.listarTodasPromocoesByHotel(id);        
            return "listaPromocoes";
        }else{
            promocoes = promocaoDAO.listarTodasPromocoesBySite(id);
            return "listaPromocoes";
        }
    }
    
    
}
