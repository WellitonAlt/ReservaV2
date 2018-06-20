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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import reserva.beans.Site;
import reserva.dao.SiteDAO;

/**
 *
 * @author spooks
 */

@Named
@SessionScoped
public class NovoSite implements Serializable {
    
    @Inject SiteDAO siteDao;
    Site dadosSite;
    Site usuarioEncontrado;
    NovoSiteMaquinaEstados estado;
    MensagemBootstrap mensagem;
    
    public NovoSite(){
        recomecar();
    }

    private void recomecar() {
        estado = NovoSiteMaquinaEstados.inicio();
        mensagem = new MensagemBootstrap();
        mensagem.setMensagem(true, "Digite a url do site para dar início.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
        dadosSite = new Site();
    }

    public Site getDadosSite() {
        return dadosSite;
    }

    public void setDadosSite(Site dadosSite) {
        this.dadosSite = dadosSite;
    }

    public NovoSiteMaquinaEstados getEstado() {
        return estado;
    }

    public MensagemBootstrap getMensagem() {
        return mensagem;
    }
    
    public void validarNome(FacesContext context, UIComponent toValidate, String value){
        simularDemora();
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Nome não pode ser vazia!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void validarTelefone(FacesContext context, UIComponent toValidate, String value){
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Telefone não pode ser vazio!");
            context.addMessage(toValidate.getClientId(context), message);
        }else if (value.length() < 10 || value.length() > 11 ) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Telefone deve conter 11 digitos!. Ex: 14997555555");
            context.addMessage(toValidate.getClientId(context), message);
        }else if (value.matches("[a-zA-Z]*")) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Telefone não deve conter letras!");
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
        
    public void procurarUrl() {
        simularDemora();
        try {
            if (dadosSite.getUrl().length() == 0){
                mensagem.setMensagem(true, "URL Invalida!! URL não pode ser vazia!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
                estado = NovoSiteMaquinaEstados.inicio();
            }else if(!dadosSite.getUrl().matches("(www.)?[a-zA-Z0-9]+\\.[a-zA-Z]+\\.[a-zA-Z]*")){
                mensagem.setMensagem(true, "URL Invalida!! URL não está no formato padrão!. Ex: www.google.com.br.", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
               estado = NovoSiteMaquinaEstados.inicio();
            }else{
                usuarioEncontrado = siteDao.buscarSitePorUrl(dadosSite.getUrl());
                if (usuarioEncontrado == null) {
                    mensagem.setMensagem(true, "URL ainda não cadastrada! Informe uma nova senha e demais dados para cadastro.", MensagemBootstrap.TipoMensagem.TIPO_INFO);
                    estado = NovoSiteMaquinaEstados.novoSite();
                } else {
                    mensagem.setMensagem(true, "URL já cadastrada! Informe uma nova URL para efetuar cadastro.", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
                    estado = NovoSiteMaquinaEstados.inicio();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovoHotel.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
    }
    
    public void enviarSite() {
       simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme se as informações do site são válidas.", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       estado = NovoSiteMaquinaEstados.confirmarNovoSite();
    }
    
    public void confirmarSite() {
       simularDemora();
       try {
           siteDao.gravarSite(dadosSite);
           recomecar();
           mensagem.setMensagem(true, "Seu site foi registrado com sucesso!", MensagemBootstrap.TipoMensagem.TIPO_SUCESSO);
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