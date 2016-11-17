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
        myMessages = new Vector<>();
    }
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        System.getProperties().put("java.rmi/server.hostname", "127.0.0.1");
        MyMessageServerInterface myMessageServer = new MyMessageServer();
        Naming.rebind("MyMessageServer", myMessageServer);
    }
    @Override
    public MyMessageInterface echoMessage() throws RemoteException, ServerNotActiveException {
        return new MyMessage();
    }

    @Override
    public MyMessageInterface echoMessage(int id) throws RemoteException, ServerNotActiveException {
        return myMessages.elementAt(id);
    }

    @Override
    public void addMessage(String messageText) throws RemoteException, ServerNotActiveException {
        MyMessage message = new MyMessage(counter, messageText);
        myMessages.add(message);
        counter++;
        System.out.println(messageText);
    }
}
