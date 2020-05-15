package server;

import java.util.ArrayList;
import java.util.List;

public class MetodosServidor {
    static ArrayList<Lista> clientes = new ArrayList<Lista>();

    public static String menu(){
        return("Menu\n" +
                "Digite 1 para criar uma nova lista;\n" +
                "Digite 2 para adicionar conteúdo em uma lista existente;\n" +
                "Digite 3 para obter o último valor adicionado em uma lista;\n"+
                "Digite 4 para remover o último conteúdo da lista;\n" +
                "Digite 5 para sair.\n");
    }
    public static boolean criarLista(String nome){
        Lista cliente = new Lista();
        cliente.setNome(nome);
        return clientes.add(cliente);
    }
    public static boolean adicionarConteudo(String conteudo, String nome){ //nome da lista como parametro
        for (Lista cliente:clientes) {
            if(cliente.getNome().equals(nome)) {
                return cliente.setConteudo(conteudo);
            }
        }
        return false;
    }
    public static boolean removerUltimoConteudo(String nome){
        for (Lista cliente:clientes) {
            if(cliente.getNome().equals(nome)) {
                return cliente.removeConteudo();
            }
        }
        return false;
    }
    public static String obtemUltimoConteudo(String nome){
        for (Lista cliente:clientes) {
            if(cliente.getNome().equals(nome)) {
                return cliente.getConteudo();
            }
        }
        return null;
    }
}
