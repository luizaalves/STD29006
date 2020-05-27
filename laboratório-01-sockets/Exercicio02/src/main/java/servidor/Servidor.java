package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static int porta;
    public static String nomeArquivo,pastaArquivo;
    public static void main(String args[]) throws IOException {
        try{
            porta = Integer.parseInt(args[0]);
            pastaArquivo = args[1];
        }catch (Exception e) {
            porta = 1234;
            pastaArquivo ="/home/luiza/STD29006/";
        }
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Aguardando por conexoes");
        int countClients=0;
        while(true){
            countClients++;
            Socket conexao = servidor.accept();
            Thread cliente = new ServidorThread(conexao);
            cliente.start();
        }
    }
}
