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
