package reserva.views;

import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import reserva.beans.Hotel;
import reserva.beans.Site;
import reserva.dao.HotelDAO;
import reserva.dao.SiteDAO;

@Named
@SessionScoped
public class Login implements Serializable {
    
    String usuario;
    String senha;
    String tipo;   
    Hotel hotel;
    Site site;
    MensagemBootstrap mensagem;
    
    @Inject HotelDAO hotelDAO;
    @Inject SiteDAO siteDAO;
    
    public Login(){
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Escolha seu perfil e digite o usuário e senha para realizar login.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
    }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public Hotel getHotel() { return hotel; }

    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public Site getSite() { return site; }

    public void setSite(Site site) { this.site = site; }

    public MensagemBootstrap getMensagem() { return mensagem; }

    public void setMensagem(MensagemBootstrap mensagem) { this.mensagem = mensagem; }

    public String fazLogin(){
        usuario = usuario.trim();
        senha = senha.trim();
        try{
            switch (tipo) {
                case "adm":
                    if(usuario.equals("root") && senha.equals("root")){
                       return "areaAdm";                        
                    } else {
                        mensagem = new MensagemBootstrap();
                        mensagem.setMensagem(true, "Usuario ou Senha invalidos.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                    }    
                    break;
                case "hotel":
                    hotel = hotelDAO.loginHotel(usuario, senha);
                    if(hotel != null){
                        return "areaHotel";
                    } else {
                        mensagem = new MensagemBootstrap();
                        mensagem.setMensagem(true, "Usuario ou Senha invalidos.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                    }   
                    break;
                case "site":
                     site = siteDAO.loginSite(usuario, senha);     
                     if(site != null){
                        return "areaSite";
                    } else {
                        mensagem = new MensagemBootstrap();
                        mensagem.setMensagem(true, "Usuario ou Senha invalidos.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                    }   break;

                default:
                   mensagem = new MensagemBootstrap();
                   mensagem.setMensagem(true, "Nenhum perfil selecionado.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
            }
        }catch ( NullPointerException | SQLException | NamingException ex) {
            //Erro
        }
        return "login";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }
   
}
