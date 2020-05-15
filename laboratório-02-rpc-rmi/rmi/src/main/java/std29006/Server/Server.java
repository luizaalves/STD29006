package std29006.Server;
import std29006.ContadorDistribuido;
import std29006.Server.Contador;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static String ipServer = "127.0.0.1";
    private static int port = 12345;
    private static final String NOMEOBJDIST = "Increment";

    public static void main(String[] args) {
        try{
            if(args[0]!= null) {
                ipServer = args[0];
            }
            if(args[1]!= null){
                port = Integer.parseInt(args[1]);
            }

            Contador counter = new Contador(); // iniciando o objeto

            System.setProperty("java.rmi.server.hostname", ipServer);//informe do ip do servidor para os clientes acessarem

            /*
                inicio o objeto na qual a interface do servidor com os métodos que poderão ser
                acessados pelo cliente
             */
            ContadorDistribuido stub = (ContadorDistribuido)
                    UnicastRemoteObject.exportObject(counter, 0);

            Registry registro = LocateRegistry.createRegistry(port); //Registro a porta de acesso ao servidor para o cliente se conectar

            //inicio o acesso a essa interface
            registro.bind(NOMEOBJDIST, stub);

            System.out.println("Servidor pronto!\n");
            System.out.println("Pressione CTRL + C para encerrar...");

        }catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
