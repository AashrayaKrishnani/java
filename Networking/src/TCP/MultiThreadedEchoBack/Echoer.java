package TCP.MultiThreadedEchoBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;
    private int socketId;
    
    public Echoer(Socket socket, int socketId)
    {
        this.socket = socket;
        this.socketId = socketId;
    }

    @Override
    public void run() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // true for AutoFlush.

            String input="Connection Established with Socket "+socketId;

            while(!input.equals("exit"))
            {
                pw.println(input);
                System.out.println("Echoing back: " + input);
                input = br.readLine();
                System.out.println("Client Input: " + input);
            }

            input = "Closing Connection with Socket "+socketId;
            pw.println(input);
            System.out.println("Server: " + input);

            socket.close();
            
        } catch(IOException e)
        {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    
}
