
package views;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.Hotel;
import reserva.Login;
import reserva.Site;
import reserva.dao.HotelDAO;
import reserva.dao.SiteDAO;

@Named
@RequestScoped
public class Login implements Serializable {
    
    private String usuario;
    private String senha;
    private String tipo;

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    
    Login login = new Login();
    Hotel hotel;
    Site site;
    
    @Inject 
    HotelDAO hotelDAO;
    SiteDAO siteDAO;
    
    
    
    
  
}
