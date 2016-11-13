package myClient;

import myInterface.MyMessageInterface;
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
        final String myHost = String.format("rmi://%s:%d/", "127.0.0.1", Registry.REGISTRY_PORT);
        try (Scanner scanner = new Scanner(System.in)) {
            MyMessageServerInterface server = (MyMessageServerInterface) Naming.lookup(myHost + "MyMessageServer");
            MyMessageInterface myMessage = server.echoMessage();
            myMessage.getMessage("tsk tsk");
            System.out.println(myMessage.echoMessage());
        } catch (MalformedURLException | RemoteException | NotBoundException | ServerNotActiveException exception) {
            exception.printStackTrace();
        }
    }
}
