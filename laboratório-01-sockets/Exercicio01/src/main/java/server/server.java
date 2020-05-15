package server;

        import java.io.BufferedInputStream;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.ArrayList;

        import static server.MetodosServidor.menu;

/**
 * Servidor que espera por uma mensagem do cliente (String em UTF) e depois
 * envia uma String de resposta, tambem em UTF
 *
 * 2019-08-28
 * @author Luiza Alves da Silva adaptado de Emerson Ribeiro de Mello
 */
public class server {
    static private int porta;
    public static void main(String[] args) throws IOException {
        try{
            porta = Integer.parseInt(args[0]);
        }catch (Exception e) {
            porta = 1234;
        }
        /* Registra servico na porta 1234 e aguarda por conexoes */
        ServerSocket servidor = new ServerSocket(porta);

        System.out.println("Aguardando por conexoes");
        Socket conexao = servidor.accept();
        System.out.println("Cliente conectou " + conexao);

        DataInputStream fluxoEntrada = new DataInputStream(
                new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        fluxoSaida.writeUTF(MetodosServidor.menu());
        int op = 0;
        String nome = "";
        while (op!=5) {
            op = Integer.parseInt(fluxoEntrada.readUTF());
            if((op>0)&&(op<5)){
                fluxoSaida.writeUTF("Digite o nome da lista: ");
                nome = fluxoEntrada.readUTF();
            }

            switch (op) {
                case 1: {
                    if(MetodosServidor.criarLista(nome))
                        fluxoSaida.writeUTF("Nova lista com o nome " + nome + " foi criado.\n\n"+MetodosServidor.menu());
                    else
                        fluxoSaida.writeUTF("Lista não pôde ser criada, tente novamente.\n\n"+MetodosServidor.menu());
                    break;
                }
                case 2: {
                    fluxoSaida.writeUTF("Digite o conteudo a ser adicionado: ");

                    String conteudo = fluxoEntrada.readUTF();

                    if (MetodosServidor.adicionarConteudo(conteudo, nome)) {
                        fluxoSaida.writeUTF("Adicionado na lista.\n\n"+MetodosServidor.menu());
                    } else fluxoSaida.writeUTF("Deu ruim\n\n"+MetodosServidor.menu());
                    break;
                }
                case 3: {
                    String ultimoConteudo = MetodosServidor.obtemUltimoConteudo(nome);
                    if (ultimoConteudo!=null) {
                        fluxoSaida.writeUTF(ultimoConteudo + "\n\n"+MetodosServidor.menu());
                    } else fluxoSaida.writeUTF("Deu ruim\n\n"+MetodosServidor.menu());
                    break;
                }
                case 4: {
                    if (MetodosServidor.removerUltimoConteudo(nome)) {
                        fluxoSaida.writeUTF("Conteúdo removido.\n\n"+MetodosServidor.menu());
                    } else fluxoSaida.writeUTF("Lista não existe.\n\n"+MetodosServidor.menu());
                    break;
                }
                case 5: {
                    fluxoSaida.writeUTF("5");
                    break;
                }
                default:
                    fluxoSaida.writeUTF("Opção inválida.\n\n"+MetodosServidor.menu());
                    break;

            }
        }
        fluxoSaida.close();
        fluxoEntrada.close();
        conexao.close();

    }
}

