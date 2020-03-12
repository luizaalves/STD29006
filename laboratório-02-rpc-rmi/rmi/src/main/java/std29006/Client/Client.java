package std29006.Client;

import std29006.ContadorDistribuido;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static String ipServer = "127.0.0.1";
    private static int port = 12345;
    private static final String NOMEOBJDIST = "Increment";

    public static void main(String[] args) {
        try{
            if(args[0]!=null){
                ipServer = args[0];
            }
            if(args[1]!=null){
                port = Integer.parseInt(args[1]);
            }

            System.out.println("Conectando no servidor "+ ipServer);

            Registry registro = LocateRegistry.getRegistry(ipServer,port); //conectando no ip e na porta do servidor

            ContadorDistribuido stub = (ContadorDistribuido) registro.lookup(NOMEOBJDIST); //acessando o objeto compartilhado entre cliente e servidor

            System.out.println("Valor atual: " + stub.obtemValorAtual());  //cliente utilizando a função da interface compartilhada

            System.out.println("Solicitando ao servidor para incrementar o contador");
            stub.incrementa();
            System.out.println("Valor atual: " + stub.obtemValorAtual());
            System.out.println("Fim da execução do cliente!");

        }catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
