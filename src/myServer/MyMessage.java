package myServer;

import myInterface.MyMessageInterface;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
class MyMessage extends UnicastRemoteObject implements MyMessageInterface {
    private String messageText;
    private int id;

    MyMessage() throws RemoteException, ServerNotActiveException {
        super(MyMessageServer.MyObjectPort);
        this.messageText = "Hello, world!";
    }

    MyMessage(int id, String messageText) throws RemoteException, ServerNotActiveException {
        super(MyMessageServer.MyObjectPort);
        this.id = id;
        this.messageText = messageText;
    }

    @Override
    public String echoMessage() throws RemoteException {
        return this.messageText;
    }
}
