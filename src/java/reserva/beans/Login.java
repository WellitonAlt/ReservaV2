package reserva.beans;

import java.io.Serializable;

public class Login implements Serializable {
    private String usuario;
    private String senha;
    private String tipo;

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }
}
