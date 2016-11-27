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
        final String myHost = String.format("rmi://%s:%d/", "67.205.162.207", Registry.REGISTRY_PORT);
        try (Scanner scanner = new Scanner(System.in)) {
            MyMessageServerInterface server = (MyMessageServerInterface) Naming.lookup(myHost + "MyMessageServer");
            for (int i = 0; i < 1000000000; i++) {
                //String newMessage = scanner.nextLine();
                String newMessage = Integer.toString(i);
                server.addMessage(newMessage);
                System.out.println(server.echoMessage(0).getMessage());
                //String oneMessage = scanner.nextLine();
                String oneMessage = 2 + Integer.toString(i);
                server.addMessage(oneMessage);
                System.out.println(server.echoMessage(1).getMessage());
            }
        } catch (MalformedURLException | RemoteException | NotBoundException | ServerNotActiveException exception) {
            exception.printStackTrace();
        }
    }
}
