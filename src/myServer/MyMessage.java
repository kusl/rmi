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

    MyMessage() throws RemoteException, ServerNotActiveException {
        super(MyMessageServer.MyObjectPort);
        messageText = "Hello, world!";
    }

    @Override
    public String echoMessage() throws RemoteException {
        return messageText;
    }

    @Override
    public void getMessage(String messageText) throws RemoteException {
        this.messageText = messageText;
    }
}
