package myServer;

import myInterface.MyMessageInterface;
import myInterface.MyMessageServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public class MyMessageServer extends UnicastRemoteObject implements MyMessageServerInterface {
    static final int MyObjectPort = 1100;
    private static int counter = 0;
    private Vector<MyMessageInterface> myMessages;
    private MyMessageServer() throws RemoteException {
        super(MyObjectPort);
        myMessages = new Vector<MyMessageInterface>();
    }
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        if (args.length > 1) {
            LocateRegistry.createRegistry(Integer.parseInt(args[1]));
        } else {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        }
        if (args.length == 0) {
            System.getProperties().put("java.rmi.server.hostname", "127.0.0.1");
        } else {
            System.getProperties().put("java.rmi.server.hostname", args[0]);
        }
        MyMessageServerInterface myMessageServer = new MyMessageServer();
        Naming.rebind("MyMessageServer", myMessageServer);
        System.out.println("Now listening on hostname: " + System.getProperties().getProperty("java.rmi.server.hostname"));
    }

    @Override
    public MyMessageInterface echoMessage(int id) throws RemoteException, ServerNotActiveException {
        if (id >= 0 && myMessages.size() > id) {
            return myMessages.elementAt(id);
        }
        return null;
    }

    @Override
    public void addMessage(String messageText) throws RemoteException, ServerNotActiveException {
        MyMessage message = new MyMessage(counter, messageText);
        myMessages.add(message);
        counter++;
        System.out.println(messageText);
        System.out.println("The current count is " + Integer.toString(counter));
    }
}
