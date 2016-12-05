Update:

More updates! 
Switched to using Java 6 because that is what the birds use. 

No, I haven't gotten them to work on the birds yet. 

Port 1099 seems to work for the server: 

    $ java myServer.MyMessageServer pelican.cs.qc.cuny.edu 1099
    Now listening on hostname: pelican.cs.qc.cuny.edu

But I cannot reach to it from a client: 

    $ java myClient.MyMessageClient pelican.cs.qc.cuny.edu 1099
    Exception in thread "main" java.rmi.ConnectIOException: Exception creating connection to: pelican.cs.qc.cuny.edu; nested exception is: 
            java.net.NoRouteToHostException: No route to host (Host unreachable)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:631)
            at sun.rmi.transport.tcp.TCPChannel.createConnection(TCPChannel.java:216)
            at sun.rmi.transport.tcp.TCPChannel.newConnection(TCPChannel.java:202)
            at sun.rmi.server.UnicastRef.newCall(UnicastRef.java:341)
            at sun.rmi.registry.RegistryImpl_Stub.lookup(Unknown Source)
            at java.rmi.Naming.lookup(Naming.java:101)
            at myClient.MyMessageClient.main(MyMessageClient.java:28)
    Caused by: java.net.NoRouteToHostException: No route to host (Host unreachable)
            at java.net.PlainSocketImpl.socketConnect(Native Method)
            at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)
            at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)
            at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)
            at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
            at java.net.Socket.connect(Socket.java:576)
            at java.net.Socket.connect(Socket.java:525)
            at java.net.Socket.<init>(Socket.java:425)
            at java.net.Socket.<init>(Socket.java:208)
            at sun.rmi.transport.proxy.RMIDirectSocketFactory.createSocket(RMIDirectSocketFactory.java:40)
            at sun.rmi.transport.proxy.RMIMasterSocketFactory.createSocket(RMIMasterSocketFactory.java:147)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:613)
            ... 6 more


Port 3000 is a no go on the server: 

    $ java myServer.MyMessageServer pelican.cs.qc.cuny.edu 3000
    Exception in thread "main" java.rmi.ConnectException: Connection refused to host: 149.4.211.63; nested exception is: 
            java.net.ConnectException: Connection refused (Connection refused)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:619)
            at sun.rmi.transport.tcp.TCPChannel.createConnection(TCPChannel.java:216)
            at sun.rmi.transport.tcp.TCPChannel.newConnection(TCPChannel.java:202)
            at sun.rmi.server.UnicastRef.newCall(UnicastRef.java:341)
            at sun.rmi.registry.RegistryImpl_Stub.rebind(Unknown Source)
            at java.rmi.Naming.rebind(Naming.java:177)
            at myServer.MyMessageServer.main(MyMessageServer.java:39)
    Caused by: java.net.ConnectException: Connection refused (Connection refused)
            at java.net.PlainSocketImpl.socketConnect(Native Method)
            at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)
            at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)
            at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)
            at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
            at java.net.Socket.connect(Socket.java:576)
            at java.net.Socket.connect(Socket.java:525)
            at java.net.Socket.<init>(Socket.java:425)
            at java.net.Socket.<init>(Socket.java:208)
            at sun.rmi.transport.proxy.RMIDirectSocketFactory.createSocket(RMIDirectSocketFactory.java:40)
            at sun.rmi.transport.proxy.RMIMasterSocketFactory.createSocket(RMIMasterSocketFactory.java:147)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:613)
            ... 6 more

And on the client we get 

    $ java myClient.MyMessageClient pelican.cs.qc.cuny.edu 3000
    Exception in thread "main" java.rmi.NotBoundException: MyMessageServer
            at sun.rmi.registry.RegistryImpl.lookup(RegistryImpl.java:137)
            at sun.rmi.registry.RegistryImpl_Skel.dispatch(Unknown Source)
            at sun.rmi.server.UnicastServerRef.oldDispatch(UnicastServerRef.java:410)
            at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:271)
            at sun.rmi.transport.Transport$2.run(Transport.java:202)
            at sun.rmi.transport.Transport$2.run(Transport.java:199)
            at java.security.AccessController.doPrivileged(Native Method)
            at sun.rmi.transport.Transport.serviceCall(Transport.java:198)
            at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:567)
            at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:828)
            at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.access$400(TCPTransport.java:619)
            at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$1.run(TCPTransport.java:684)
            at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler$1.run(TCPTransport.java:681)
            at java.security.AccessController.doPrivileged(Native Method)
            at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:681)
            at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
            at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
            at java.lang.Thread.run(Thread.java:745)
            at sun.rmi.transport.StreamRemoteCall.exceptionReceivedFromServer(StreamRemoteCall.java:275)
            at sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:252)
            at sun.rmi.server.UnicastRef.invoke(UnicastRef.java:378)
            at sun.rmi.registry.RegistryImpl_Stub.lookup(Unknown Source)
            at java.rmi.Naming.lookup(Naming.java:101)
            at myClient.MyMessageClient.main(MyMessageClient.java:28)
            
SSDD on 3001:             

    $ java myServer.MyMessageServer pelican.cs.qc.cuny.edu 3001
    Exception in thread "main" java.rmi.ConnectException: Connection refused to host: 149.4.211.63; nested exception is: 
            java.net.ConnectException: Connection refused (Connection refused)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:619)
            at sun.rmi.transport.tcp.TCPChannel.createConnection(TCPChannel.java:216)
            at sun.rmi.transport.tcp.TCPChannel.newConnection(TCPChannel.java:202)
            at sun.rmi.server.UnicastRef.newCall(UnicastRef.java:341)
            at sun.rmi.registry.RegistryImpl_Stub.rebind(Unknown Source)
            at java.rmi.Naming.rebind(Naming.java:177)
            at myServer.MyMessageServer.main(MyMessageServer.java:39)
    Caused by: java.net.ConnectException: Connection refused (Connection refused)
            at java.net.PlainSocketImpl.socketConnect(Native Method)
            at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)
            at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)
            at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)
            at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
            at java.net.Socket.connect(Socket.java:576)
            at java.net.Socket.connect(Socket.java:525)
            at java.net.Socket.<init>(Socket.java:425)
            at java.net.Socket.<init>(Socket.java:208)
            at sun.rmi.transport.proxy.RMIDirectSocketFactory.createSocket(RMIDirectSocketFactory.java:40)
            at sun.rmi.transport.proxy.RMIMasterSocketFactory.createSocket(RMIMasterSocketFactory.java:147)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:613)
            ... 6 more
            
We know it is not even worth trying 3001 on the client but just for the sake of completeness

    $ java myClient.MyMessageClient pelican.cs.qc.cuny.edu 3001
    Exception in thread "main" java.rmi.ConnectIOException: Exception creating connection to: pelican.cs.qc.cuny.edu; nested exception is: 
            java.net.NoRouteToHostException: No route to host (Host unreachable)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:631)
            at sun.rmi.transport.tcp.TCPChannel.createConnection(TCPChannel.java:216)
            at sun.rmi.transport.tcp.TCPChannel.newConnection(TCPChannel.java:202)
            at sun.rmi.server.UnicastRef.newCall(UnicastRef.java:341)
            at sun.rmi.registry.RegistryImpl_Stub.lookup(Unknown Source)
            at java.rmi.Naming.lookup(Naming.java:101)
            at myClient.MyMessageClient.main(MyMessageClient.java:28)
    Caused by: java.net.NoRouteToHostException: No route to host (Host unreachable)
            at java.net.PlainSocketImpl.socketConnect(Native Method)
            at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:339)
            at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:200)
            at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:182)
            at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
            at java.net.Socket.connect(Socket.java:576)
            at java.net.Socket.connect(Socket.java:525)
            at java.net.Socket.<init>(Socket.java:425)
            at java.net.Socket.<init>(Socket.java:208)
            at sun.rmi.transport.proxy.RMIDirectSocketFactory.createSocket(RMIDirectSocketFactory.java:40)
            at sun.rmi.transport.proxy.RMIMasterSocketFactory.createSocket(RMIMasterSocketFactory.java:147)
            at sun.rmi.transport.tcp.TCPEndpoint.newSocket(TCPEndpoint.java:613)
            ... 6 more


The client will now actually echo what you input.
I use java util scanner for this.


--
Hello, world!

This is my first attempt at creating a Java RMI application.
This application doesn't do much at the moment.
All I have is echo out "Hello, world!"

I figured I should at least give it a shot.
Time allowing, I'd like to add a way to store multiple messages on the server.
I would also like to add an ID to each message.
I would also like to add the ability for a client to retrieve any message using the message ID.
However, all that is far away.

For now, the way to build the application is

// open two terminal instances
// cd into the application folder in both

// terminal instance 1
    * `cd src`
    * `javac myServer/MyMessageServer.java`
    * `java myServer.MyMessageServer`

//terminal instance 2
    * `cd src`
    * `javac myClient/MyMessageClient.java`
    * `java myClient.MyMessageClient`

When you `cd` into src, your `tree` should look like

    $ tree
    .
    ├── myClient
    │   ├── MyMessageClient.class
    │   └── MyMessageClient.java
    ├── myInterface
    │   ├── MyMessageInterface.class
    │   ├── MyMessageInterface.java
    │   ├── MyMessageServerInterface.class
    │   └── MyMessageServerInterface.java
    └── myServer
        ├── MyMessage.class
        ├── MyMessage.java
        ├── MyMessageServer.class
        └── MyMessageServer.java

    3 directories, 10 files
