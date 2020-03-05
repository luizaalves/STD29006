package server;
import java.util.*;

public class Lista {
    private ArrayList<String> lista;
    private String nome;

    public Lista(String name){
        this.nome = name;
        this.lista = new ArrayList<>();
    }

    public void addLista(String algo){
        this.lista.add(algo);
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
    public String getNome(){
        return this.nome;
    }
}
