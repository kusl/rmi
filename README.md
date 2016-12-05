Update:

More updates! 
Switched to using Java 6 because that is what the birds use. 

No, I haven't gotten them to work on the birds yet. 

The birds are old. 

    $ uname -r
    2.6.32-642.11.1.el6.x86_64
    
javac 

    $ javac -version
    javac 1.6.0_40

java 

    $ java -version
    java version "1.7.0_121"
    OpenJDK Runtime Environment (rhel-2.6.8.1.el6_8-x86_64 u121-b00)
    OpenJDK 64-Bit Server VM (build 24.121-b00, mixed mode)

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


Latest tree 

    $ tree
    .
    ├── MyMessageLogger.iml
    ├── out
    │   └── production
    │       └── MyMessageLogger
    │           ├── myClient
    │           │   └── MyMessageClient.class
    │           ├── myInterface
    │           │   ├── MyMessageInterface.class
    │           │   └── MyMessageServerInterface.class
    │           ├── myInterface.jar
    │           └── myServer
    │               ├── MyMessage.class
    │               └── MyMessageServer.class
    ├── README.md
    ├── Screenshot_20161112_204717.png
    ├── Screenshot_20161112_212527.png
    ├── Screenshot_20161112_213117.png
    └── src
        ├── myClient
        │   ├── MyMessageClient.class
        │   └── MyMessageClient.java
        ├── myInterface
        │   ├── MyMessageInterface.class
        │   ├── MyMessageInterface.java
        │   ├── MyMessageServerInterface.class
        │   └── MyMessageServerInterface.java
        ├── myInterface.jar
        └── myServer
            ├── MyMessage.class
            ├── MyMessage.java
            ├── MyMessageServer.class
            └── MyMessageServer.java
    
    10 directories, 22 files
    
Not all of these files are available in git. 

Locally, here is a working version. 
The server and client are local virtual machines using bridged networking in Virtualbox. 
Server has IP address of `192.168.1.9` and client has `192.168.1.4`. 
Because the virtual machines don't have X, they don't need too much memory. 
I have each of these machines (Ubuntu 16.04.1 with default-jdk) at 1 GB RAM. 

`$ uname -r`
`4.4.0-51-generic`

`$ javac -version`
`javac 1.8.0_111`

`$ java -version`
`openjdk version "1.8.0_111"`
`OpenJDK Runtime Environment (build 1.8.0_111-8u111-b14-2ubuntu0.16.04.2-b14)`
`OpenJDK 64-Bit Server VM (build 25.111-b14, mixed mode)`


Server: 

    $ rm myServer/*.class && javac myServer/MyMessageServer.java && java myServer.MyMessageServer 192.168.1.9 1099
    Now listening on hostname: 192.168.1.9

Client (with the same server running persistently): 

First run: 

    $ rm myClient/*.class && javac myClient/MyMessageClient.java && java myClient.MyMessageClient 192.168.1.9 1099
    Please choose a menu option: 
        Enter 1 to add a new message. 
        Enter 2 to retrieve an existing message.
    1
    apple
    
Second run: 

    $ rm myClient/*.class && javac myClient/MyMessageClient.java && java myClient.MyMessageClient 192.168.1.9 1099
    Please choose a menu option: 
        Enter 1 to add a new message. 
        Enter 2 to retrieve an existing message.
    2
    0
    apple

Third run: 
    
    $ rm myClient/*.class && javac myClient/MyMessageClient.java && java myClient.MyMessageClient 192.168.1.9 1099
    Please choose a menu option: 
        Enter 1 to add a new message. 
        Enter 2 to retrieve an existing message.
    2
    99
    Server returned a null. I imagine a message by that ID is not available. 
    
The server doesn't look much different at the end of third run: 

    $ rm myServer/*.class && javac myServer/MyMessageServer.java && java myServer.MyMessageServer 192.168.1.9 1099
    Now listening on hostname: 192.168.1.9
    apple
    The current count is 1
    
We can also experiment with larger messages. Lets see our fourth run on the client: 

    $ rm myClient/*.class && javac myClient/MyMessageClient.java && java myClient.MyMessageClient 192.168.1.9 1099
    Please choose a menu option: 
        Enter 1 to add a new message. 
        Enter 2 to retrieve an existing message.
    1
    We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness. — That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed, — That whenever any Form of Government becomes destructive of these ends, it is the Right of the People to alter or to abolish it, and to institute new Government, laying its foundation on such principles and organizing its powers in such form, as to them shall seem most likely to effect their Safety and Happiness. Prudence, indeed, will dictate that Governments long established should not be changed for light and transient causes; and accordingly all experience hath shewn that mankind are more disposed to suffer, while evils are sufferable than to right themselves by abolishing the forms to which they are accustomed. But when a long train of abuses and usurpations, pursuing invariably the same Object evinces a design to reduce them under absolute Despotism, it is their right, it is their duty, to throw off such Government, and to provide new Guards for their future security. — Such has been the patient sufferance of these Colonies; and such is now the necessity which constrains them to alter their former Systems of Government. The history of the present King of Great Britain is a history of repeated injuries and usurpations, all having in direct object the establishment of an absolute Tyranny over these States. To prove this, let Facts be submitted to a candid world.
    
and a fifth run to get the text back (also on the client): 

    $ rm myClient/*.class && javac myClient/MyMessageClient.java && java myClient.MyMessageClient 192.168.1.9 1099
    Please choose a menu option: 
        Enter 1 to add a new message. 
        Enter 2 to retrieve an existing message.
    2
    1
    We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness. — That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed, — That whenever any Form of Government becomes destructive of these ends, it is the Right of the People to alter or to abolish it, and to institute new Government, laying its foundation on such principles and organizing its powers in such form, as to them shall seem most likely to effect their Safety and Happiness. Prudence, indeed, will dictate that Governments long established should not be changed for light and transient causes; and accordingly all experience hath shewn that mankind are more disposed to suffer, while evils are sufferable than to right themselves by abolishing the forms to which they are accustomed. But when a long train of abuses and usurpations, pursuing invariably the same Object evinces a design to reduce them under absolute Despotism, it is their right, it is their duty, to throw off such Government, and to provide new Guards for their future security. — Such has been the patient sufferance of these Colonies; and such is now the necessity which constrains them to alter their former Systems of Government. The history of the present King of Great Britain is a history of repeated injuries and usurpations, all having in direct object the establishment of an absolute Tyranny over these States. To prove this, let Facts be submitted to a candid world.

When we add this second message on the client, we can also see this reflected on the server: 

    $ rm myServer/*.class && javac myServer/MyMessageServer.java && java myServer.MyMessageServer 192.168.1.9 1099
    Now listening on hostname: 192.168.1.9
    apple
    The current count is 1
    We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness. — That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed, — That whenever any Form of Government becomes destructive of these ends, it is the Right of the People to alter or to abolish it, and to institute new Government, laying its foundation on such principles and organizing its powers in such form, as to them shall seem most likely to effect their Safety and Happiness. Prudence, indeed, will dictate that Governments long established should not be changed for light and transient causes; and accordingly all experience hath shewn that mankind are more disposed to suffer, while evils are sufferable than to right themselves by abolishing the forms to which they are accustomed. But when a long train of abuses and usurpations, pursuing invariably the same Object evinces a design to reduce them under absolute Despotism, it is their right, it is their duty, to throw off such Government, and to provide new Guards for their future security. — Such has been the patient sufferance of these Colonies; and such is now the necessity which constrains them to alter their former Systems of Government. The history of the present King of Great Britain is a history of repeated injuries and usurpations, all having in direct object the establishment of an absolute Tyranny over these States. To prove this, let Facts be submitted to a candid world.
    The current count is 2



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
