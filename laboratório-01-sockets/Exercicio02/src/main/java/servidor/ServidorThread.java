package servidor;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServidorThread extends Thread {
    private Socket conexao;
    public ServidorThread(Socket c){
        this.conexao = c;
    }
    public void run() {
        System.out.println("Conectou!");
        /* Estabelece fluxos de entrada e saida */
        DataInputStream fluxoEntrada;
        //DataOutputStream fluxoSaida;
        try {
            fluxoEntrada = new DataInputStream(
                    new BufferedInputStream(conexao.getInputStream()));
            FileOutputStream fos = new FileOutputStream(Servidor.pastaArquivo+"recebido.txt");
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
