package servidor;

import java.io.*;
import java.net.Socket;
import java.lang.Thread;

public class ServidorThread extends Thread {
    private Socket conexao;
    public ServidorThread(Socket c){
        this.conexao = c;
    }
    public void run() {
        System.out.println("Conectou!");
        try {
            System.out.println(this.conexao.getInetAddress());
            DataInputStream fluxoEntrada = new DataInputStream(
                    new BufferedInputStream(conexao.getInputStream()));
            DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
            Servidor.nomeArquivo = fluxoEntrada.readUTF(); //espera o cliente enviar o nome do arquivo que deseja
            File file = new File(Servidor.pastaArquivo+Servidor.nomeArquivo);
            FileInputStream in = new FileInputStream(Servidor.pastaArquivo+Servidor.nomeArquivo);
            int size = (int) file.length();
            fluxoSaida.writeUTF(String.valueOf(file.length())); //o servidor passa o tamanho do arquivo que o cliente vai receber
            /*********************************************************/
            /* Inicia comunicacao */
            byte[] data = new byte[size];
            while (in.read(data) > 0) {
                fluxoSaida.write(data);
            }

            in.close();
            fluxoSaida.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
