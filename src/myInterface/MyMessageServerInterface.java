package myInterface;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public interface MyMessageServerInterface extends Remote {
    MyMessageInterface echoMessage() throws RemoteException, MalformedURLException, ServerNotActiveException;
}
