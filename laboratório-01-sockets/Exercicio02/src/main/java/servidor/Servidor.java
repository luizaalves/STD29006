package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static int porta;
    public static String pastaArquivo;
    public static void main(String args[]) throws IOException {
        try{
            porta = Integer.parseInt(args[0]);
            pastaArquivo = args[1];
        }catch (Exception e) {
            porta = 1234;
            pastaArquivo="/home/luiza";
        }
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Aguardando por conexoes");
        while(true){
            Socket conexao = servidor.accept();
            Thread t = new ServidorThread(conexao);
            t.start();
        }
    }
}
