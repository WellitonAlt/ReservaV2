/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reserva.views;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author spooks
 */
public class NovoSite {
        public List<String> validar() {
        List<String> mensagens = new ArrayList<String>();
               
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
    }
    
}
