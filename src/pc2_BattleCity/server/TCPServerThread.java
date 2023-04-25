package pc2_BattleCity.server;

import java.io.PrintWriter;
import java.net.Socket;

public class TCPServerThread extends Thread {

    private Socket client;
    private TCPServer tcpServer;

    private int clientId;
    private boolean running = false;
    public PrintWriter mOut;

//    private TCPServer.


}
