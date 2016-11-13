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

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public class MyMessageServer extends UnicastRemoteObject implements MyMessageServerInterface {
    static final int MyObjectPort = 1100;
    private MyMessageServer() throws RemoteException {
        super(MyObjectPort);
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
}
