
package views;

import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import reserva.Hotel;
import reserva.Site;
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
        System.out.println(usuario);
        System.out.println(senha);
        System.out.println(tipo);
        
        
        try{
            switch (tipo) {
                case "adm":
                    if(usuario.equals("root") && senha.equals("root")){
                        //Adm
                    }else{
                        //Erro
                    }    break;
                case "hotel":
                    hotel = hotelDAO.loginHotel(usuario, senha);
                    if(hotel != null){
                        //Hotel
                    }
                    else{
                        //Erro
                    }    break;
                case "site":
                    site = siteDAO.loginSite(usuario, senha);
                    if(site != null){
                        //Site
                    }
                    else{
                        //Erro
                    }    break;
                default:
                    //Nenhum Selecionado
            }
        }catch (NullPointerException | SQLException | NamingException ex) {
            //Erro
        }
        return "login";
    }  
   
}
