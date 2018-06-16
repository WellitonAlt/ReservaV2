
package reserva;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.MaskFormatter;

public class Site {
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
    
    public List<String> validar() {
        List<String> mensagens = new ArrayList<String>();
        
        try{
            if (url.trim().length() == 0) {
                mensagens.add("URL não pode ser vazio!");
            }
            if (!url.matches("(www.)?[a-zA-Z0-9]+\\.[a-zA-Z]+\\.[a-zA-Z]*")){
                mensagens.add("URL não está no formato padrão!. Ex: www.google.com.br ");       
            }
            if (nome.trim().length() == 0) {
                mensagens.add("Nome não pode ser vazio!");
            }
            if (senha.trim().length() == 0) {
                mensagens.add("Senha não pode ser vazia!");
            }
            if (telefone.trim().length() == 0) {
                mensagens.add("Telefone não pode ser vazio!");
            }
            if (telefone.length() < 10 || telefone.length() > 11 ) {
                mensagens.add("Telefone deve conter 11 digitos!. Ex: 14997555555");
            }
            if (telefone.matches("[a-zA-Z]*")) {
                mensagens.add("Telefone não deve conter letras!");
            }
            return (mensagens.isEmpty() ? null : mensagens);
        }catch(NullPointerException e){
            return null;
        }
    }
}
