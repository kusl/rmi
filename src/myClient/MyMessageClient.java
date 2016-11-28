package myClient;

import myInterface.MyMessageServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public class MyMessageClient {
    public static void main(String[] args) throws RemoteException {
        final String myHost;
        if (args.length == 0) {
            myHost = String.format("rmi://%s:%d/", "127.0.0.1", Registry.REGISTRY_PORT);
        } else if (args.length == 1) {
            myHost = String.format("rmi://%s:%d/", args[0], Registry.REGISTRY_PORT);
        } else {
            myHost = String.format("rmi://%s:%d/", args[0], Integer.parseInt(args[1]));
        }
        try (Scanner scanner = new Scanner(System.in)) {
            MyMessageServerInterface server = (MyMessageServerInterface) Naming.lookup(myHost + "MyMessageServer");
            for (int i = 0; i < 100; i++) {
                //String newMessage = scanner.nextLine();
                String newMessage = Integer.toString(i);
                server.addMessage(newMessage);
                System.out.println(server.echoMessage(i).getMessage());
            }
        } catch (MalformedURLException | RemoteException | NotBoundException | ServerNotActiveException exception) {
            exception.printStackTrace();
        }
    }
}
