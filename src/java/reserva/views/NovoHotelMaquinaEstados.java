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

public class NovoHotelMaquinaEstados implements Serializable{
    
    public static NovoHotelMaquinaEstados inicio() {
        return new NovoHotelMaquinaEstados(true, true, false, true, false);
    }
    
    public static NovoHotelMaquinaEstados novoHotel() {
        return new NovoHotelMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovoHotelMaquinaEstados confirmarNovoHotel() {
        return new NovoHotelMaquinaEstados(false, false, true, false, false);
    }
    
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosHotelDesabilitados;
    private final boolean camposDadosHotelDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarHotelVisivel;

    public NovoHotelMaquinaEstados(boolean camposDadosPessoaisDesabilitados, boolean camposDadosHotelDesabilitados, 
                                   boolean camposDadosHotelDestaque, boolean botaoEnvioDesabilitado, 
                                   boolean botaoConfirmarHotelVisivel) {
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosHotelDesabilitados = camposDadosHotelDesabilitados;
        this.camposDadosHotelDestaque = camposDadosHotelDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarHotelVisivel = botaoConfirmarHotelVisivel;
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

    public boolean isBotaoConfirmarHotelVisivel() {
        return botaoConfirmarHotelVisivel;
    }
}
