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
        return new NovaPromocaoMaquinaEstados(false, false, true, false);
    }

    public static NovaPromocaoMaquinaEstados confirmarNovaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, true, false, true);
    }
    
    private final boolean camposDadosPromoDesabilitados;
    private final boolean camposDadosPromoDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarPromoVisivel;

    public NovaPromocaoMaquinaEstados(boolean camposDadosPromoDesabilitados, boolean camposDadosPromoDestaque, 
                                      boolean botaoEnvioDesabilitado, boolean botaoConfirmarPromoVisivel) {
        this.camposDadosPromoDesabilitados = camposDadosPromoDesabilitados;
        this.camposDadosPromoDestaque = camposDadosPromoDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarPromoVisivel = botaoConfirmarPromoVisivel;
    }

    public boolean isCamposDadosPromoDesabilitados() {
        return camposDadosPromoDesabilitados;
    }

    public boolean isCamposDadosPromoDestaque() {
        return camposDadosPromoDestaque;
    }

    public boolean isBotaoEnvioDesabilitado() {
        return botaoEnvioDesabilitado;
    }
    
    public boolean isBotaoConfirmarPromoVisivel() {
        return botaoConfirmarPromoVisivel;
    }
   
}
