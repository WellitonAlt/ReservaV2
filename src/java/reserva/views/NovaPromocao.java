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
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Named
@SessionScoped
public class NovaPromocao implements Serializable {
    @Inject SiteDAO siteDao;
    @Inject HotelDAO hotelDao;
    @Inject PromocaoDAO promocaoDao;
    
    UIInput hotelID;
    Promocao dadosPromocao;
    int siteId;
    String siteNome;
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

    public int getSiteId() { return siteId;  }

    public void setSiteId(int siteId) { this.siteId = siteId; }

    public MensagemBootstrap getMensagem() { return mensagem; }

    public NovaPromocaoMaquinaEstados getEstado() { return estado; }
    
    public UIInput getHotelID() { return hotelID; }
    
    public void setHotelID(UIInput input) { hotelID = input; }

    public String getSiteNome() { return siteNome; }

    public void setSiteNome(String siteNome) { this.siteNome = siteNome;  }    
    
    public List<Site> getListaSites() {
        try {
            listaSites = siteDao.listarTodosSites();
        } catch (SQLException ex) {
            Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
        }
        return listaSites;
    }
    
    public void procuraSite() {
        simularDemora();
        if (siteId > 0) {
            try {
                siteNome = siteDao.buscarSitePorId(siteId);
            } catch (SQLException ex) {
                Logger.getLogger(NovaPromocao.class.getName()).log(Level.SEVERE, null, ex);
                mensagem.setMensagem(true, "Ocorreu um problema!", MensagemBootstrap.TipoMensagem.TIPO_ERRO);
            }
        }
    }
    
    public void validarHotel() {
        int id = (int) hotelID.getValue();
        if (id > 0) {
            dadosPromocao.setHotel(id);
        }
    }
    
    public void validarPreco(FacesContext context, UIComponent toValidate, String value) { 
        simularDemora();
        float preco = Float.parseFloat(value);
        if (value.equals("0.0")) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Preço não pode ser zero!");
            context.addMessage(toValidate.getClientId(context), message);
        }else if (preco < 0){
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Preço não pode ser negativo!");
            context.addMessage(toValidate.getClientId(context), message);
        }else{
            dadosPromocao.setPreco(preco);
        }
    }
    
    public void validarDataInicial(FacesContext context, UIComponent toValidate, String value) { 
      simularDemora();
        if (value.trim().length() == 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Data Inicial não pode ser vazia!");
            context.addMessage(toValidate.getClientId(context), message);
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dadosPromocao.setDataInicial(formatter.parse(value));
            } catch (ParseException e) { }
        }    
    }
    
    public void validarDataFinal(FacesContext context, UIComponent toValidate, String value) { 
        simularDemora();
        if(value.length() > 0){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dadosPromocao.setDataFinal(formatter.parse(value));
            } catch (ParseException e) { }
        }
        try{
            if (value.trim().length() == 0) {
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage("Data Final não pode ser vazio!");
                context.addMessage(toValidate.getClientId(context), message);
            }else if(promocaoDao.verificaData(dadosPromocao.getDataInicial(), dadosPromocao.getDataFinal(), dadosPromocao.getHotel())){
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage("Ja existe uma promocao para essa mesma data!");
                context.addMessage(toValidate.getClientId(context), message);   
            }else if(dadosPromocao.getDataFinal().before(dadosPromocao.getDataInicial())){
                ((UIInput) toValidate).setValid(false);
                FacesMessage message = new FacesMessage("A Data Final não pode ser anterior à Data Inicial!");
                context.addMessage(toValidate.getClientId(context), message);
            }
        }catch(SQLException ex){}
    }
    
    public void validarSite(FacesContext context, UIComponent toValidate, String value) {
        int site = Integer.parseInt(value);
        if (site <= 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Site escolhido inválido!");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }

    public void enviarPromocao() {
       simularDemora();
       mensagem.setMensagem(true, "Verifique os dados e confirme se as informações da promoção são válidas.", MensagemBootstrap.TipoMensagem.TIPO_AVISO);
       estado = NovaPromocaoMaquinaEstados.confirmarNovaPromocao();
    }

    public void confirmarPromocao() { 
        simularDemora();
        try {
           dadosPromocao.setSite(siteId);
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

