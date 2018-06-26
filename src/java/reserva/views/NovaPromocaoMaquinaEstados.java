package reserva.views;

import java.io.Serializable;

public class NovaPromocaoMaquinaEstados implements Serializable {
    
    public static NovaPromocaoMaquinaEstados inicio() {
        return new NovaPromocaoMaquinaEstados(false, false, false, true);
    }
    
    public static NovaPromocaoMaquinaEstados confirmarNovaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, true, false, false);
    }
    
    private final boolean camposDadosPromocaoDesabilitados;
    private final boolean camposDadosPromocaoDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarPromocaoVisivel;

    public NovaPromocaoMaquinaEstados(boolean camposDadosPromoDesabilitados, boolean camposDadosPromoDestaque, 
                                      boolean botaoEnvioDesabilitado, boolean botaoConfirmarPromocaoVisivel) {
        this.camposDadosPromocaoDesabilitados = camposDadosPromoDesabilitados;
        this.camposDadosPromocaoDestaque = camposDadosPromoDestaque;
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarPromocaoVisivel = botaoConfirmarPromocaoVisivel;
    }

    public boolean isCamposDadosPromocaoDesabilitados() {
        return camposDadosPromocaoDesabilitados;
    }

    public boolean isCamposDadosPromocaoDestaque() {
        return camposDadosPromocaoDestaque;
    }

    public boolean isBotaoEnvioDesabilitado() {
        return botaoEnvioDesabilitado;
    }

    public boolean isBotaoConfirmarPromocaoVisivel() {
        return botaoConfirmarPromocaoVisivel;
    }
   
}
