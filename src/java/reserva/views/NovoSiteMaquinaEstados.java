/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.views;

import java.io.Serializable;

/**
 *
 * @author spooks
 */
public class NovoSiteMaquinaEstados implements Serializable{
    
    public static NovoSiteMaquinaEstados inicio() {
        return new NovoSiteMaquinaEstados(true, true, true, true, false, true, false, false);
    }
    
    public static NovoSiteMaquinaEstados novoSite() {
        return new NovoSiteMaquinaEstados(false, true, false, false, false, false, false, false);
    }
    
    public static NovoSiteMaquinaEstados confirmarNovoSite() {
        return new NovoSiteMaquinaEstados(false, true, false, false, true, false, true, true);
    }
    
    private final boolean campoSenhaDesabilitado;
    private final boolean eventoSenhaDesabilitado;
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosHotelDesabilitados;
    private final boolean camposDadosHotelDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean campoConfirmacaoSenhaVisivel;
    private final boolean botaoConfirmarHotelVisivel;

    public NovoSiteMaquinaEstados(boolean campoSenhaDesabilitado, boolean eventoSenhaDesabilitado, boolean camposDadosPessoaisDesabilitados, boolean camposDadosHotelDesabilitados, boolean camposDadosHotelDestaque, boolean botaoEnvioDesabilitado, boolean campoConfirmacaoSenhaVisivel, boolean botaoConfirmarHotelVisivel) {
        this.campoSenhaDesabilitado = campoSenhaDesabilitado;
        this.eventoSenhaDesabilitado = eventoSenhaDesabilitado;
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosHotelDesabilitados = camposDadosHotelDesabilitados;
        this.camposDadosHotelDestaque = camposDadosHotelDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.campoConfirmacaoSenhaVisivel = campoConfirmacaoSenhaVisivel;
        this.botaoConfirmarHotelVisivel = botaoConfirmarHotelVisivel;
    }

    public boolean isCampoSenhaDesabilitado() {
        return campoSenhaDesabilitado;
    }

    public boolean isEventoSenhaDesabilitado() {
        return eventoSenhaDesabilitado;
    }

    public boolean isCamposDadosPessoaisDesabilitados() {
        return camposDadosPessoaisDesabilitados;
    }

    public boolean isCamposDadosHotelDesabilitados() {
        return camposDadosHotelDesabilitados;
    }

    public boolean isCamposDadosHotelDestaque() {
        return camposDadosHotelDestaque;
    }

    public boolean isBotaoEnvioDesabilitado() {
        return botaoEnvioDesabilitado;
    }

    public boolean isCampoConfirmacaoSenhaVisivel() {
        return campoConfirmacaoSenhaVisivel;
    }

    public boolean isBotaoConfirmarHotelVisivel() {
        return botaoConfirmarHotelVisivel;
    }
}
