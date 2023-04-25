package pc2_BattleCity.server;

import java.net.ServerSocket;

public class TCPServer {

    public static  final int SERVERPORT = 4444;

    private OnMessageReceived messageListener = null;
    private boolean running = false;
    TCPServerThread[] sendclis = new TCPServerThread[10];

    ServerSocket serverSocket;

    public TCPServer(OnMessageReceived messageListener){
        this.messageListener = messageListener;
    }

    public OnMessageReceived getMessageListener(){
        return this.messageListener;
    }

    



    public interface OnMessageReceived{
        public void messageReceived(String message);
    }
}
