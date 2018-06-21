package reserva.views;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.beans.Hotel;
import reserva.dao.HotelDAO;

@Named
@SessionScoped
public class NovoHotel implements Serializable {
    
    @Inject HotelDAO hotelDao;
    Hotel dadosHotel;
    Hotel usuarioEncontrado;
    NovoHotelMaquinaEstados estado;
    MensagemBootstrap mensagem;

    public NovoHotel(){
        recomecar();
    }

    private void recomecar() {
        estado = NovoHotelMaquinaEstados.inicio();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite seu CNPJ para dar início.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosHotel = new Hotel();
    }
    
    public Hotel getDadosHotel() {
        return dadosHotel;
    }

    public void setDadosHotel(Hotel dadosHotel) {
        this.dadosHotel = dadosHotel;
    }
    
    public MensagemBootstrap getMensagem() {
        return mensagem;
    }

    public NovoHotelMaquinaEstados getEstado() {
        return estado;
    }
    
    public void validarNome (FacesContext context, UIComponent toValidate, String value) {
        simularDemora();
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Nome não pode ser vazio!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void validarCidade (FacesContext context, UIComponent toValidate, String value) {
        simularDemora();
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Cidade não pode ser vazia!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void validarSenha (FacesContext context, UIComponent toValidate, String value) {
        simularDemora();
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Senha não pode ser vazia!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void procurarCNPJ() {
        simularDemora();
        try {
            dadosHotel.setCNPJ(dadosHotel.getCNPJ().replace(".","").replace("/","").replace("-",""));
            if (dadosHotel.getCNPJ().length() == 0){
                mensagem.setMensagem(true, "CNPJ Inválido!! CNPJ não pode ser vazio.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                estado = NovoHotelMaquinaEstados.inicio();
            }else if(dadosHotel.getCNPJ().length() < 14 || dadosHotel.getCNPJ().length() >= 15){
                mensagem.setMensagem(true, "CNPJ Inválido!! CNPJ deve conter 14 dígitos!. Ex: 72629140000134 .", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                estado = NovoHotelMaquinaEstados.inicio();
            }else if(dadosHotel.getCNPJ().matches("[0-9]*[a-zA-Z]+[0-9]*")){
                mensagem.setMensagem(true, "CNPJ Inválido!! CNPJ não deve conter letras.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                estado = NovoHotelMaquinaEstados.inicio();
            }else{
                usuarioEncontrado = hotelDao.buscarHotelPorCNPJ(dadosHotel.getCNPJ());
                if (usuarioEncontrado == null) {
                    mensagem.setMensagem(true, "CNPJ ainda não cadastrado! Informe uma nova senha e demais dados para cadastro.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
                    estado = NovoHotelMaquinaEstados.novoHotel();
                } else {
                    mensagem.setMensagem(true, "CNPJ já cadastrado! Informe um novo CNPJ para efetuar cadastro.", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
                    estado = NovoHotelMaquinaEstados.inicio();
                }
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    public void enviarHotel() {
       simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme se as informações do hotel são válidas.", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       estado = NovoHotelMaquinaEstados.confirmarNovoHotel();
    }
    
    public void confirmarHotel() {
       simularDemora();
       try {
           hotelDao.gravarHotel(dadosHotel);
           recomecar();
           mensagem.setMensagem(true, "Seu hotel foi registrado com sucesso!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
       } catch (SQLException ex) {
           Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
           mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
       }
    }
    
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}