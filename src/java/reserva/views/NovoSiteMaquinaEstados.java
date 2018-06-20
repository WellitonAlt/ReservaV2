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

public class NovoSiteMaquinaEstados implements Serializable {
    
    public static NovoSiteMaquinaEstados inicio() {
        return new NovoSiteMaquinaEstados(true, true, false, true, false);
    }
    
    public static NovoSiteMaquinaEstados novoSite() {
        return new NovoSiteMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovoSiteMaquinaEstados confirmarNovoSite() {
        return new NovoSiteMaquinaEstados(false, false, true, false, false);
    }
    
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosSiteDesabilitados;
    private final boolean camposDadosSiteDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarSiteVisivel;

    public NovoSiteMaquinaEstados(boolean camposDadosPessoaisDesabilitados, boolean camposDadosSiteDesabilitados, 
                                  boolean camposDadosSiteDestaque, boolean botaoEnvioDesabilitado, 
                                  boolean botaoConfirmarSiteVisivel) {
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosSiteDesabilitados = camposDadosSiteDesabilitados;
        this.camposDadosSiteDestaque = camposDadosSiteDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarSiteVisivel = botaoConfirmarSiteVisivel;
    }

    public boolean isCamposDadosPessoaisDesabilitados() {
        return camposDadosPessoaisDesabilitados;
    }

    public boolean isCamposDadosSiteDesabilitados() {
        return camposDadosSiteDesabilitados;
    }

    public boolean isCamposDadosSiteDestaque() {
        return camposDadosSiteDestaque;
    }

    public boolean isBotaoEnvioDesabilitado() {
        return botaoEnvioDesabilitado;
    }

    public boolean isBotaoConfirmarSiteVisivel() {
        return botaoConfirmarSiteVisivel;
    }
}
