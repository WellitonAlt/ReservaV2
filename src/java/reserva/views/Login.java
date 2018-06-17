
package reserva.views;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import reserva.beans.Hotel;
import reserva.beans.Site;
import reserva.dao.HotelDAO;
import reserva.dao.SiteDAO;

@Named
@RequestScoped
public class Login implements Serializable {
    
    String usuario;
    String senha;
    String tipo;   
    Hotel hotel;
    Site site;
    
    
    @Inject 
    HotelDAO hotelDAO;
    SiteDAO siteDAO;

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Site getSite() { return site; }

    public void setSite(Site site) { this.site = site; }
    
    public String fazLogin(){        
        /*System.out.println("usuario: " + usuario);
        System.out.println("senha: " + senha);
        System.out.println("tipo: " + tipo);*/
        
        
        try{
            switch (tipo) {
                case "adm":
                    if(usuario.equals("root") && senha.equals("root")){
                       return "areaAdm";                        
                    }else{
                        //Erro
                    }    break;
                case "hotel":
                    hotel = hotelDAO.loginHotel(usuario, senha);
                    if(hotel != null){
                        return "areaHotel";
                    }
                    else{
                        //Erro
                    }    break;
                case "site":
                    site = siteDAO.loginSite(usuario, senha);
                    if(site != null){
                        return "areaSite";
                    }
                    else{
                        //Erro
                    }    break;
                default:
                    //Nenhum Selecionado
            }
        }catch ( NullPointerException | SQLException | NamingException ex) {
            //Erro
        }
        return "login";
    }  
   
}
