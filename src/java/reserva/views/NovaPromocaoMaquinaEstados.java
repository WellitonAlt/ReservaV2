package reserva.views;

import java.io.Serializable;

public class NovaPromocaoMaquinaEstados implements Serializable {
    
    public static NovaPromocaoMaquinaEstados inicio() {
<<<<<<< HEAD
        return new NovaPromocaoMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados novaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados confirmarNovaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, false, true, false, false);
=======
        return new NovaPromocaoMaquinaEstados(false, false, true, false);
    }

    public static NovaPromocaoMaquinaEstados confirmarNovaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, true, false, true);
>>>>>>> 245455d04a2e2d8b80b9105ac59df0e437223a61
    }
    
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosPromocaoDesabilitados;
    private final boolean camposDadosPromocaoDestaque;
    private final boolean botaoEnvioDesabilitado;
<<<<<<< HEAD
    private final boolean botaoConfirmarPromocaoVisivel;

    public NovaPromocaoMaquinaEstados(boolean camposDadosPessoaisDesabilitados, boolean camposDadosPromocaoDesabilitados, 
                                   boolean camposDadosPromocaoDestaque, boolean botaoEnvioDesabilitado, 
                                   boolean botaoConfirmarPromocaoVisivel) {
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosPromocaoDesabilitados = camposDadosPromocaoDesabilitados;
        this.camposDadosPromocaoDestaque = camposDadosPromocaoDestaque;
=======
    private final boolean botaoConfirmarPromoVisivel;

    public NovaPromocaoMaquinaEstados(boolean camposDadosPromoDesabilitados, boolean camposDadosPromoDestaque, 
                                      boolean botaoEnvioDesabilitado, boolean botaoConfirmarPromoVisivel) {
        this.camposDadosPromoDesabilitados = camposDadosPromoDesabilitados;
        this.camposDadosPromoDestaque = camposDadosPromoDestaque;
>>>>>>> 245455d04a2e2d8b80b9105ac59df0e437223a61
        this.botaoEnvioDesabilitado = botaoEnvioDesabilitado;
        this.botaoConfirmarPromocaoVisivel = botaoConfirmarPromocaoVisivel;
    }

    public boolean isCamposDadosPessoaisDesabilitados() {
        return camposDadosPessoaisDesabilitados;
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
