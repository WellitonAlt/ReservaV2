/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.views;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.beans.Hotel;
import reserva.beans.Promocao;
import reserva.beans.Site;
import reserva.dao.HotelDAO;
import reserva.dao.PromocaoDAO;
import reserva.dao.SiteDAO;

/**
 *
 * @author spooks
 */

@Named
@SessionScoped
public class NovaPromocao implements Serializable {
    @Inject SiteDAO siteDao;
    @Inject HotelDAO hotelDao;
    @Inject PromocaoDAO promocaoDao;
    
    Promocao dadosPromocao;
    Hotel hotelEncontrado;
    List<Site> listaSites;
    NovaPromocaoMaquinaEstados estado;
    MensagemBootstrap mensagem;

    public NovaPromocao() { recomecar(); }

    private void recomecar() {
        estado = NovaPromocaoMaquinaEstados.inicio();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Escolha um site para cadastrar a promoção.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosPromocao = new Promocao();
        dadosPromocao.setDataInicial(new Date());
        dadosPromocao.setDataFinal(new Date());
    }

    public Promocao getDadosPromocao() { return dadosPromocao; }

    public void setDadosPalpite(Promocao dadosPromocao) { this.dadosPromocao = dadosPromocao; }

    public MensagemBootstrap getMensagem() { return mensagem; }

    public NovaPromocaoMaquinaEstados getEstado() { return estado; }

    public List<Site> getListaSites() { return listaSites; }
    
    public void procurarCNPJ() {
        simularDemora();
        try {
            String CNPJ = "" + dadosPromocao.getHotel();
            hotelEncontrado = hotelDao.buscarHotelPorCNPJ(CNPJ);
            if (hotelEncontrado == null) {
                mensagem.setMensagem(true, "CNPJ ainda não cadastrado! Informe uma nova senha e demais dados para cadastro", MensagemBootstrap.TipoMensagem.TIPO_INFO);
                estado = NovaPromocaoMaquinaEstados.usuarioInexistente();
            } else {
                mensagem.setMensagem(true, "E-mail já cadastrado! Informe sua senha para enviar o palpite", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
                estado = NovaPromocaoMaquinaEstados.usuarioExistente();
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }

    /*public void conferirSenha() {
        simularDemora();
        String senha = (String) dadosPalpite.getPalpiteiro().getSenha();
        if (senha.equals(usuarioEncontrado.getSenha())) {
            dadosPalpite.setPalpiteiro(usuarioEncontrado);
            mensagem.setMensagem(true, "Senha correta! Informe seu palpite!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
            estado = NovoPalpiteMaquinaEstados.usuarioExistenteSenhaCorreta();
        } else {
            estado = NovoPalpiteMaquinaEstados.usuarioExistente();
            mensagem.setMensagem(true, "Senha incorreta! Informe novamente!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }        
    }*/

    /*public void enviarPalpite() {
       simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme o palpite. Atenção, ao confirmar o palpite você concorda em pagar R$ 20,00", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       if (usuarioEncontrado == null) {
           estado = NovoPalpiteMaquinaEstados.confirmarPalpiteUsuarioInexistente();
       } else {
           estado = NovoPalpiteMaquinaEstados.confirmarPalpiteUsuarioExistente();
       }
    }

    public void confirmarPalpite() {
       simularDemora();
       try {
           Usuario u = usuarioDao.buscarUsuario(dadosPalpite.getPalpiteiro().getId());
           if (u == null) {
               u = usuarioDao.gravarUsuario(dadosPalpite.getPalpiteiro());
           }
           dadosPalpite.getPalpiteiro().setId(u.getId());
           palpiteDao.gravarPalpite(dadosPalpite);

           recomecar();
           mensagem.setMensagem(true, "Seu palpite foi registrado com sucesso! Digite seu e-mail para dar início a outro palpite", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);

       } catch (SQLException ex) {
           Logger.getLogger(NovoPalpite.class.getName()).log(Level.SEVERE, null, ex);
           mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
       }
    }*/
   
    private void simularDemora() {
        // Para testar chamadas AJAX
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
