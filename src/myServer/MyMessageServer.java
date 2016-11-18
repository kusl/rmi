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
        System.out.println("The current count is " + Integer.toString(counter));
        //The current count is 2439527
        //Exception in thread "RMI TCP Connection(idle)" java.lang.OutOfMemoryError: GC overhead limit exceeded
        //The current count is 2440891
//        java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
//        java.io.EOFException
//        at sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:229)
//        at sun.rmi.server.UnicastRef.invoke(UnicastRef.java:162)
//        at java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:227)
//        at java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:179)
//        at com.sun.proxy.$Proxy0.echoMessage(Unknown Source)
//        at myClient.MyMessageClient.main(MyMessageClient.java:26)
//        Caused by: java.io.EOFException
//        at java.io.DataInputStream.readByte(DataInputStream.java:267)
//        at sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:215)
//        ... 5 more
//        khada@khada-Lenovo-IdeaPad-Y510P:~/Documents/src/me/MyMessageLogger/src$ java myClient.MyMessageClient
//        0
//        20
//        0
//        20
//        0
//        20
//        0
//        20
//        0
//        java.rmi.UnmarshalException: error unmarshalling return; nested exception is:
//        java.io.EOFException
//        at sun.rmi.server.UnicastRef.invoke(UnicastRef.java:193)
//        at java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:227)
//        at java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:179)
//        at com.sun.proxy.$Proxy1.echoMessage(Unknown Source)
//        at myClient.MyMessageClient.main(MyMessageClient.java:30)
//        Caused by: java.io.EOFException
//        at java.io.ObjectInputStream$BlockDataInputStream.peekByte(ObjectInputStream.java:2626)
//        at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1321)
//        at java.io.ObjectInputStream.readObject(ObjectInputStream.java:373)
//        at sun.rmi.server.UnicastRef.unmarshalValue(UnicastRef.java:326)
//        at sun.rmi.server.UnicastRef.invoke(UnicastRef.java:175)
//        ... 4 more

    }
}
