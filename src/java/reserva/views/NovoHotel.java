/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.views;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.beans.Hotel;
import reserva.dao.HotelDAO;

/**
 *
 * @author spooks
 */

@Named
@ViewScoped
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
    
    public void procurarCNPJ() {
        simularDemora();
        try {
            usuarioEncontrado = hotelDao.buscarHotelPorCNPJ(dadosHotel.getCNPJ());
            if (usuarioEncontrado == null) {
                mensagem.setMensagem(true, "CNPJ ainda não cadastrado! Informe uma nova senha e demais dados para cadastro.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
                estado = NovoHotelMaquinaEstados.novoHotel();
            } else {
                mensagem.setMensagem(true, "CNPJ já cadastrado! Informe um novo CNPJ para efetuar cadastro.", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
                estado = NovoHotelMaquinaEstados.inicio();
            }
        } catch (SQLException ex) {
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
