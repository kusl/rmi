package myClient;

import myInterface.MyMessageInterface;
import myInterface.MyMessageServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by khada on 11/12/16.
 * Hello, world!
 */
public class MyMessageClient {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException, ServerNotActiveException {
        final String myHost;
        if (args.length == 0) {
            myHost = String.format("rmi://%s:%d/", "127.0.0.1", Registry.REGISTRY_PORT);
        } else if (args.length == 1) {
            myHost = String.format("rmi://%s:%d/", args[0], Registry.REGISTRY_PORT);
        } else {
            myHost = String.format("rmi://%s:%d/", args[0], Integer.parseInt(args[1]));
        }
        MyMessageServerInterface server = (MyMessageServerInterface) Naming.lookup(myHost + "MyMessageServer");
//            for (int i = 0; i < 100; i++) {
//                //String newMessage = scanner.nextLine();
//                String newMessage = Integer.toString(i);
//                server.addMessage(newMessage);
//                System.out.println(server.echoMessage(i).getMessage());
//            }
        printMenu(server);
    }

    private static void printMenu(MyMessageServerInterface server) throws RemoteException, ServerNotActiveException, MalformedURLException {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Please choose a menu option: ");
                System.out.println("    Enter 1 to add a new message. ");
                System.out.println("    Enter 2 to retrieve an existing message.");
                System.out.println("    Enter 3 to exit.");
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        addNewMessage(server);
                        break;
                    case "2":
                        MyMessageInterface message = retrieveAMessage(server);
                        System.out.println(Objects.requireNonNull(message) != null ? message.getMessage() : null);
                        break;
                    case "3":

                        break label;
                    case "exit":

                        break label;
                    default:
                        break;
                }
            }
        }
    }

    private static MyMessageInterface retrieveAMessage(MyMessageServerInterface server) throws RemoteException, ServerNotActiveException, MalformedURLException {
        try (Scanner scanner = new Scanner(System.in)) {
            Integer id = scanner.nextInt();
            return server.echoMessage(id);
        }
    }

    private static void addNewMessage(MyMessageServerInterface server) throws RemoteException, ServerNotActiveException, MalformedURLException {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            server.addMessage(input);
        }
    }
}
