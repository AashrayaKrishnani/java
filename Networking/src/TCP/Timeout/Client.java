package TCP.Timeout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {

    final static int PORT = Server.PORT_NUMBER;
    final static String address = "127.0.0.1"; // This is the address for the same machine, i.e., localhost, alternatively we can also use 'localhost'

    public static void main(String[] args) {
        try(Socket socket = new Socket(address, PORT );
        Scanner sc = new Scanner(System.in))
        {
            System.out.println("Setting socket timeout = " + (Server.TIMEOUT_MILLIS/2) + " ms");
            socket.setSoTimeout(Server.TIMEOUT_MILLIS/2);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // true for AutoFlush.
            
            System.out.println("Server's Response: " + br.readLine()); 
            
            String input;

            do{
                System.out.print("Enter a String to Send to the Server: ");
                input = sc.nextLine();
                pw.println(input);
                
                System.out.println("Server's Response: " + br.readLine());
            } while(!input.equals("exit"));
                

        } catch(SocketTimeoutException timeout)
        {
            System.out.println("Socket Timed Out!");
        } 
        catch(IOException e)
        {
            System.out.println("Client Side Error: " + e.getMessage());
        }
    }
}
