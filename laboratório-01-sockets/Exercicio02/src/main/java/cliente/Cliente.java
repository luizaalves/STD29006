package cliente;

import java.io.*;
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
    private static String ipServidor;
    public static void main(String[] args) throws IOException{
        String pastaArquivo;
        int porta;
        try{
            ipServidor = args[0];
            porta = Integer.parseInt(args[1]);
            pastaArquivo = args[2];
        }catch (Exception e) {
            porta = 1234;
            pastaArquivo ="./";
        }
        /* Estabele conexao com o servidor */
        Socket conexao = new Socket(ipServidor, porta);
        /*********************************************************/
        /* Estabelece fluxos de entrada e saida */
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        File file = new File(pastaArquivo+"arq.txt");
        FileInputStream in = new FileInputStream(pastaArquivo +"arq.txt");
        int size = (int) file.length();
        fluxoSaida.writeUTF(String.valueOf(file.length()));
        /*********************************************************/
        /* Inicia comunicacao */
        byte[] data = new byte[size];
        while (in.read(data) > 0) {
            fluxoSaida.write(data);
        }

        in.close();
        fluxoSaida.close();
    }
}
