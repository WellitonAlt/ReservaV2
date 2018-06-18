package reserva.beans;


import java.io.Serializable;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Hotel implements Serializable {
    private int id;
    private String cnpj, senha, nome, cidade; 

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCNPJMascara(){
        try{
            MaskFormatter mask = new MaskFormatter("AA.AAA.AAA/AAAA-AA"); 
            mask.setValueContainsLiteralCharacters(false);  
            return mask.valueToString(cnpj);
        }catch(ParseException e){}
        return "";
    }

    public String getCNPJ() { return cnpj; }

    public void setCNPJ(String cnpj) { this.cnpj = cnpj; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }
           
}
