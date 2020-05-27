package cliente;

import java.io.*;
import java.net.Socket;
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
        String nomeArquivo;
        int porta;
        try{
            ipServidor = args[0];
            porta = Integer.parseInt(args[1]);
            nomeArquivo = args[2];
        }catch (Exception e) {
            ipServidor = "127.0.0.1";
            porta = 1234;
            nomeArquivo ="arq.txt";
        }
        /* Estabele conexao com o servidor */
        Socket conexao = new Socket(ipServidor, porta);
        DataInputStream fluxoEntrada = new DataInputStream(
                new BufferedInputStream(conexao.getInputStream()));
        DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
        FileOutputStream fos = new FileOutputStream("/home/luiza/STD29006/recebido-do-servidor.txt");
        fluxoSaida.writeUTF(nomeArquivo);
        System.out.println(nomeArquivo);
        int count = Integer.parseInt(fluxoEntrada.readUTF());
        byte[] data = new byte[2048];
        int read;
        int totalRead = 0;

        while((read=fluxoEntrada.read(data,0,Math.min(data.length,count)))>0){
            totalRead += read;
            count -= read;
            System.out.println("read " + totalRead + " bytes.");
            fos.write(data, 0, read);
        }
        fos.close();
        fluxoEntrada.close();
    }
}
