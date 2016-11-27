package myInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public interface MyMessageInterface extends Remote {
    String getMessage() throws RemoteException;
}
