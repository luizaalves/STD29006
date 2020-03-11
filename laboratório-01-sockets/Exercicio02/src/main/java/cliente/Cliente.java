package cliente;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente que envia uma String em UTF por um socket TCP e espera
 * por uma resposta do servidor
 *
 * 2014-08-24
 * @author Emerson Ribeiro de Mello
 */
public class Cliente {

    public static void main(String[] args) throws IOException{
        int porta = 1234;
        String ip = "127.0.0.1";
        String mensagemCliente = "";
        /* Estabele conexao com o servidor */
        Socket conexao = new Socket(ip,porta);
        while(!mensagemCliente.equals("fim")){
            /*********************************************************/
            /* Estabelece fluxos de entrada e saida */
            DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
            DataInputStream fluxoEntrada = new DataInputStream(
                    new BufferedInputStream(conexao.getInputStream()));
            /*********************************************************/
            /* Inicia comunicacao */
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite uma mensagem para o servidor:");
            mensagemCliente = sc.nextLine();
            fluxoSaida.writeUTF(mensagemCliente);
            fluxoSaida.flush();

            String mensagem = fluxoEntrada.readUTF();
            System.out.println(mensagem);
            /*********************************************************/
            if(mensagemCliente.equals("fim")){
                /* Fecha fluxos e socket */
                fluxoSaida.close();
                fluxoEntrada.close();
                conexao.close();
            }
        }
    }
}
