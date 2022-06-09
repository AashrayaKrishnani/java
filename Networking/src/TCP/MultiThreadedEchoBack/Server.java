package TCP.MultiThreadedEchoBack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main
 */
public class Server{
    final static int PORT_NUMBER = 5000;

    public static void main(String[] args) {

        int socketCount = 1;

        try(ServerSocket serverSocket = new ServerSocket(PORT_NUMBER))
        {
            while(true)
            {
                System.out.println("Listening for Client Connection! :)");
                Socket socket = serverSocket.accept();  // Waits for a connection on the said PortNumber, and accepts once it gets one.
                
                System.out.println("Client " + socketCount + " Connected! :)");
                new Echoer(socket, socketCount++).start();
            }
            
        } catch(IOException e)
        {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public int getPortNum(){return PORT_NUMBER;}
}