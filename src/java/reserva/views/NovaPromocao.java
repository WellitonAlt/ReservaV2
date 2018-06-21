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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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
    
    UIInput hotelID;
    Promocao dadosPromocao;
    Site siteEscolhido;
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

    public void setDadosPromocao(Promocao dadosPromocao) { this.dadosPromocao = dadosPromocao; }

    public Site getSiteEscolhido() { return siteEscolhido; }
    
    public void setSiteEscolhido(Site site) { siteEscolhido = site; }

    public MensagemBootstrap getMensagem() { return mensagem; }

    public NovaPromocaoMaquinaEstados getEstado() { return estado; }
    
    public UIInput getHotelID() { return hotelID; }
    
    public void setHotelID(UIInput input) { hotelID = input; }

    public List<Site> getListaSites() {
        try {
            listaSites = siteDao.listarTodosSites();
        } catch (SQLException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
        return listaSites;
    }
    
    public void validarHotel() {
        int id = (int) hotelID.getValue();
        if (id > 0) {
            dadosPromocao.setHotel(id);
        }
    }
    
    public void validarSite(FacesContext context, UIComponent toValidate) {
        if (siteEscolhido != null) {
            dadosPromocao.setSite(siteEscolhido.getId());
        } else {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Site escolhido inválido!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void validarPreco(FacesContext context, UIComponent toValidate, String value) { 
        float preco = Float.parseFloat(value);
        if (preco <= 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Preço não pode ser menor ou igual a zero!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    // TODO: validar se data inicial < data final e se já não existe uma promoção acontecendo no mesmo site nesse período.
    public void validarDataInicial(FacesContext context, UIComponent toValidate, String value) {
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Data inicial não pode ser vazia!");
            context.addMessage(toValidate.getClientId(context), message);
        } else {
        }
        //Date today = new Date( )
    }
    
    public void validarDataFinal(FacesContext context, UIComponent toValidate, String value) { }
    
    public void conferirDatas() {} 
    
    /*public void procurarUsuario() {
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

    public void enviarPromocao() {
       simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme se as informações da promoção são válidas.", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       estado = NovaPromocaoMaquinaEstados.confirmarNovaPromocao();
    }

    public void confirmarPromocao() { 
        simularDemora();
        try {
           promocaoDao.gravarPromocao(dadosPromocao);
           recomecar();
           mensagem.setMensagem(true, "Sua promoção foi registrado com sucesso!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
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
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
