package server;
import java.util.*;

public class Lista {
    private ArrayList<String> lista;
    private String nome;
    private String conteudo;

    public Lista(){
        this.lista = new ArrayList<>();
    }
    public Boolean setNome(String nomeLista){
        this.nome = nomeLista;
        return true;
    }
    public String getNome(){
        return this.nome;
    }

    public Boolean setConteudo(String conteudo){
        this.lista.add(conteudo);
        return true;
    }

    public String getConteudo(){
        if(!this.lista.isEmpty()) {
            return this.lista.get(lista.size()-1);
        }
        return this.lista + " não tem nenhum conteúdo.";
    }
    public boolean removeConteudo(){
        if(!this.lista.isEmpty()) {
            this.lista.remove(lista.size()-1);
            return true;
        }
        return false;
    }
}
