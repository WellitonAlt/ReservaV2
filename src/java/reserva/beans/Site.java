
package reserva.beans;

import java.io.Serializable;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class Site implements Serializable {
    private int id;
    private String url, senha, nome, telefone;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getTelefoneMascara(){
        try{
            String mascara;
            if (telefone.length() == 10)
                mascara = "(AA)AAAA-AAAA";
            else
                mascara = "(AA)AAAAA-AAAA";
            MaskFormatter mask = new MaskFormatter(mascara); 
            mask.setValueContainsLiteralCharacters(false);  
            return mask.valueToString(telefone);
        }catch(ParseException e){}
        return "";
    }

}
