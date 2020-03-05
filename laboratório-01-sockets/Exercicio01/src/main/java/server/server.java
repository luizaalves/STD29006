package server;

        import java.io.BufferedInputStream;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.ArrayList;

/**
 * Servidor que espera por uma mensagem do cliente (String em UTF) e depois
 * envia uma String de resposta, tambem em UTF
 *
 * 2019-08-28
 * @author Luiza Alves da Silva adaptado de Emerson Ribeiro de Mello
 */
public class server {
    private static Lista lista = new Lista("");
    private static ArrayList<Lista> list = new ArrayList();

    public static void main(String[] args) throws IOException {
        /* Registra servico na porta 1234 e aguarda por conexoes */
        ServerSocket servidor = new ServerSocket(1234);

        System.out.println("Aguardando por conexoes");
        Socket conexao = servidor.accept();
        System.out.println("Cliente conectou " + conexao);
        DataInputStream fluxoEntrada = new DataInputStream(
                new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        String op= "";
        while(!op.equals("5")) {
            if(op.length()==0){
                fluxoSaida.writeUTF(menu());
            }

            op = fluxoEntrada.readUTF();

            switch (op){
                case "1": {
                    fluxoSaida.writeUTF("Digite o nome da lista: ");

                    String nome = fluxoEntrada.readUTF();
                    criarLista(nome);
                    fluxoSaida.writeUTF("Nova lista com o nome "+nome+" foi criado.\n\n" + menu());
                    break;
                }
                case "2":{
                    fluxoSaida.writeUTF("Digite o nome da lista: ");

                    String nome = fluxoEntrada.readUTF();
                    fluxoSaida.writeUTF("Digite o conteudo a ser adicionado: ");

                    String conteudo = fluxoEntrada.readUTF();

                    if(adicionarConteudo(conteudo, nome)){
                        fluxoSaida.writeUTF("Adicionado na lista.\n\n" + menu());
                    }
                    else fluxoSaida.writeUTF("Deu ruim\n\n" + menu());
                    break;
                }
                case "3":{
                    fluxoSaida.writeUTF("Digite o nome da lista: ");

                    String nome = fluxoEntrada.readUTF();
                    if(verifica(nome)!=-1) {
                        fluxoSaida.writeUTF(obterUltimoConteudo(nome) + "\n\n" + menu());
                    }
                    else fluxoSaida.writeUTF("Deu ruim\n\n" + menu());
                    break;
                }
                case "4":{
                    fluxoSaida.writeUTF("Digite o nome da lista: ");

                    String nome = fluxoEntrada.readUTF();
                    if (removerConteudo(nome)){
                        fluxoSaida.writeUTF("Conteúdo removido.\n\n" + menu());
                    }
                    else fluxoSaida.writeUTF("Lista não existe.\n\n" + menu());
                    break;
                }
                case "5": {
                    fluxoSaida.writeUTF("5");
                    break;
                }
                default: {
                    fluxoSaida.writeUTF("Opção inexistente.\n\n" + menu());
                }
            }
        }
        fluxoSaida.close();
        fluxoEntrada.close();
        conexao.close();

    }
    public static String menu(){
        return("Menu\n" +
                "Digite 1 para criar uma nova lista;\n" +
                "Digite 2 para adicionar conteúdo em uma lista existente;\n" +
                "Digite 3 para obter o último valor adicionado em uma lista;\n"+
                "Digite 4 para remover o último conteúdo da lista;\n" +
                "Digite 5 para sair.\n");
    }
    public static void criarLista(String nome){
        list.add(new Lista(nome));
        System.out.println(list.get(0).getNome());
    }
    public static boolean adicionarConteudo(String conteudo, String name){ //nome da lista como parametro
        int x = verifica(name);

        if (x!=-1){
            list.get(x).addLista(conteudo);
            return true;
        }
        return false;
    }
    public static String obterUltimoConteudo(String nome_l){ //nome da lista como parametro
        int ver = verifica(nome_l);

        if (ver!=-1){
            return list.get(ver).getConteudo();
        }
        return "Essa lista não existe.\n";
    }
    public static boolean removerConteudo(String nome){
        int x= verifica(nome);

        if (x!=-1){
            list.get(x).removeConteudo();
            return true;
        }
        return false;
    }
    public static int verifica(String nome){
        int x=-1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNome().equals(nome)){
                x = i;
                break;
            }
        }
        return x;
    }
}

