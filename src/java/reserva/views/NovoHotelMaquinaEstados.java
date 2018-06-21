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
        return new NovoHotelMaquinaEstados(true, false, true, false);
    }
    
    public static NovoHotelMaquinaEstados novoHotel() {
        return new NovoHotelMaquinaEstados(false, false, false, false);
    }
    
    public static NovoHotelMaquinaEstados confirmarNovoHotel() {
        return new NovoHotelMaquinaEstados(false, true, false, true);
    }
    
    private final boolean camposDadosHotelDesabilitados;
    private final boolean camposDadosHotelDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarHotelVisivel;

    public NovoHotelMaquinaEstados(boolean camposDadosHotelDesabilitados, boolean camposDadosHotelDestaque, 
                                   boolean botaoEnvioDesabilitado, boolean botaoConfirmarHotelVisivel) {
        this.camposDadosHotelDesabilitados = camposDadosHotelDesabilitados;
        this.camposDadosHotelDestaque = camposDadosHotelDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarHotelVisivel = botaoConfirmarHotelVisivel;
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
