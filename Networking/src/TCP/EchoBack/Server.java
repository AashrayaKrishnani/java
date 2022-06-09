package TCP.EchoBack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main
 */
public class Server{
    final static int PORT_NUMBER = 5000;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT_NUMBER))
        {
            System.out.println("Listening for Client Connection! :)");
            Socket socket = serverSocket.accept();  // Waits for a connection on the said PortNumber, and accepts once it gets one.
        
            System.out.println("Client Connected! :)");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // true for AutoFlush.

            String input="Connection Established.";

            while(!input.equals("exit"))
            {
                pw.println(input);
                System.out.println("Echoing back: " + input);
                input = br.readLine();
                System.out.println("Client Input: " + input);
            }

            input = "Closing Connection";
            pw.println(input);
            System.out.println("Server: " + input);

            socket.close();
            
        } catch(IOException e)
        {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public int getPortNum(){return PORT_NUMBER;}
}