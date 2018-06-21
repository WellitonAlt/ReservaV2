package reserva.views;

import java.io.Serializable;

public class NovaPromocaoMaquinaEstados implements Serializable {
    
    public static NovaPromocaoMaquinaEstados inicio() {
        return new NovaPromocaoMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados novaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, false, false, false, false);
    }
    
    public static NovaPromocaoMaquinaEstados confirmarNovaPromocao() {
        return new NovaPromocaoMaquinaEstados(false, false, true, false, false);
    }
    
    private final boolean camposDadosPessoaisDesabilitados;
    private final boolean camposDadosPromocaoDesabilitados;
    private final boolean camposDadosPromocaoDestaque;
    private final boolean botaoEnvioDesabilitado;
    private final boolean botaoConfirmarPromocaoVisivel;

    public NovaPromocaoMaquinaEstados(boolean camposDadosPessoaisDesabilitados, boolean camposDadosPromocaoDesabilitados, 
                                   boolean camposDadosPromocaoDestaque, boolean botaoEnvioDesabilitado, 
                                   boolean botaoConfirmarPromocaoVisivel) {
        this.camposDadosPessoaisDesabilitados = camposDadosPessoaisDesabilitados;
        this.camposDadosPromocaoDesabilitados = camposDadosPromocaoDesabilitados;
        this.camposDadosPromocaoDestaque = camposDadosPromocaoDestaque;
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
