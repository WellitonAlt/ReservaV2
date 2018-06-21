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
public class NovaPromocaoMaquinaEstados implements Serializable {
    
    public static NovaPromocaoMaquinaEstados inicio() {
        return new NovaPromocaoMaquinaEstados(true, true, true, true, false, true, false, false);
    }

    public static NovaPromocaoMaquinaEstados usuarioExistente() {
        return new NovaPromocaoMaquinaEstados(false, false, true, true, false, true, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados usuarioExistenteSenhaCorreta() {
        return new NovaPromocaoMaquinaEstados(false, false, true, false, false, false, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados usuarioInexistente() {
        return new NovaPromocaoMaquinaEstados(false, true, false, false, false, false, false, false);
    }

    public static NovaPromocaoMaquinaEstados confirmarPalpiteUsuarioExistente() {
        return new NovaPromocaoMaquinaEstados(false, true, false, false, true, false, false, true);
    }
    
    public static NovaPromocaoMaquinaEstados confirmarPalpiteUsuarioInexistente() {
        return new NovaPromocaoMaquinaEstados(false, true, false, false, true, false, true, true);
    }
    
    private final boolean campoSenhaDesabilitado;
    private final boolean eventoSenhaDesabilitado;
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosPalpiteDesabilitados;
    private final boolean camposDadosPalpiteDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean campoConfirmacaoSenhaVisivel;
    private final boolean botaoConfirmarPalpiteVisivel;

    public NovaPromocaoMaquinaEstados(boolean campoSenhaDesabilitado, boolean eventoSenhaDesabilitado, boolean camposDadosPessoaisDesabilitados, boolean camposDadosPalpiteDesabilitados, boolean camposDadosPalpiteDestaque, boolean botaoEnvioDesabilitado, boolean campoConfirmacaoSenhaVisivel, boolean botaoConfirmarPalpiteVisivel) {
        this.campoSenhaDesabilitado = campoSenhaDesabilitado;
        this.eventoSenhaDesabilitado = eventoSenhaDesabilitado;
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosPalpiteDesabilitados = camposDadosPalpiteDesabilitados;
        this.camposDadosPalpiteDestaque = camposDadosPalpiteDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.campoConfirmacaoSenhaVisivel = campoConfirmacaoSenhaVisivel;
        this.botaoConfirmarPalpiteVisivel = botaoConfirmarPalpiteVisivel;
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

    public boolean isCamposDadosPalpiteDesabilitados() {
        return camposDadosPalpiteDesabilitados;
    }

    public boolean isCamposDadosPalpiteDestaque() {
        return camposDadosPalpiteDestaque;
    }

    public boolean isBotaoEnvioDesabilitado() {
        return botaoEnvioDesabilitado;
    }

    public boolean isCampoConfirmacaoSenhaVisivel() {
        return campoConfirmacaoSenhaVisivel;
    }

    public boolean isBotaoConfirmarPalpiteVisivel() {
        return botaoConfirmarPalpiteVisivel;
    }
   
}
