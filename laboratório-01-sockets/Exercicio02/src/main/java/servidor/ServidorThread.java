package servidor;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorThread extends Thread {
    private Socket conexao;
    public ServidorThread(Socket c){
        this.conexao = c;
    }
    public void run() {
        String mensagemCliente = "";
        while (!mensagemCliente.equals("fim")) {
            /* Estabelece fluxos de entrada e saida */
            DataInputStream fluxoEntrada = null;
            DataOutputStream fluxoSaida = null;
            try {
                fluxoEntrada = new DataInputStream(
                        new BufferedInputStream(conexao.getInputStream()));
                fluxoSaida = new DataOutputStream(conexao.getOutputStream());
                mensagemCliente = fluxoEntrada.readUTF();
                System.out.println("Cliente> "+mensagemCliente);

                if(mensagemCliente.equals("fim")){
                    fluxoSaida.writeUTF("Servidor> Tchau cliente!!!");
                    fluxoEntrada.close();
                    fluxoSaida.close();
                    conexao.close();
                }
                else {
                    fluxoSaida.writeUTF("Servidor: Cliente> "+mensagemCliente);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
