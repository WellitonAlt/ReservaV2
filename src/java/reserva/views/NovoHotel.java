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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.beans.Hotel;
import reserva.dao.HotelDAO;

/**
 *
 * @author spooks
 */

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
        mensagem.setMensagem(true, "Digite seu CNPJ para dar início", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosHotel = new Hotel();
        dadosHotel.setCidade("Teste cidade");
        dadosHotel.setCNPJ("12.345.678/1234-56");
        dadosHotel.setNome("Teste nome");
        dadosHotel.setSenha("1234");
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
    
    public void procurarEmail() {
        simularDemora();
        try {
            usuarioEncontrado = hotelDao.buscarHotelPorCNPJ(dadosHotel.getCNPJ());
            if (usuarioEncontrado == null) {
                mensagem.setMensagem(true, "CNPJ ainda não cadastrado! Informe uma nova senha e demais dados para cadastro", MensagemBootstrap.TipoMensagem.TIPO_INFO);
                estado = NovoHotelMaquinaEstados.usuarioInexistente();
            } else {
                mensagem.setMensagem(true, "CNPJ já cadastrado! Informe sua senha para enviar o palpite", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
                estado = NovoHotelMaquinaEstados.usuarioExistente();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    public void conferirSenha() {}
    
    public void enviarHotel() {
        simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme o palpite. Atenção, ao confirmar o palpite você concorda em pagar R$ 20,00", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       if (usuarioEncontrado == null){
           estado = NovoHotelMaquinaEstados.confirmarHotelUsuarioInexistente();
       } else {
           estado = NovoHotelMaquinaEstados.confirmarHotelUsuarioExistente();
       }
    }
    
    public void confirmarPalpite() {}
    
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
