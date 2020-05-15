package client;

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
public class client {
    static private int porta ;
    static private String ip = "";
    public static void main(String[] args) throws IOException{
        try{
            ip = args[0];
            porta = Integer.parseInt(args[1]);
        }catch(Exception e){
            ip = "127.0.0.1";
            porta = 1234;
        }
        Scanner ler = new Scanner(System.in);

        /* Estabelece conexao com o servidor */
        Socket conexao = new Socket(ip,porta);
        System.out.println("Conectado! " + conexao);
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        DataInputStream fluxoEntrada = new DataInputStream(
                new BufferedInputStream(conexao.getInputStream()));
        String op;
        while(true){
            String resposta = fluxoEntrada.readUTF();
            if(resposta.equals("5"))break;
            else {
                System.out.println(resposta);
                op = ler.nextLine();
                fluxoSaida.writeUTF(op);
                fluxoSaida.flush();
            }

        }

        fluxoSaida.close();
        fluxoEntrada.close();
        conexao.close();

    }

}