package myInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public interface MyMessageInterface extends Remote {
    String echoMessage() throws RemoteException;

    void setMessage(String messageText) throws RemoteException;
}
