package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String args[]) throws IOException {
        ServerSocket servidor = new ServerSocket(1234);
        System.out.println("Aguardando por conexoes");
        while(true){
            Socket conexao = servidor.accept();
            Thread t = new ServidorThread(conexao);
            t.start();
        }
    }
}
