package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    
    public static int PORT = 5000;
    
    public static void main(String[] args) throws InterruptedException {
        
        try(Scanner sc = new Scanner(System.in))
        {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket(PORT);
            
            String input;

            do{
                System.out.print("Enter String to Send as UDP Packet: ");
                input = sc.nextLine();

                byte[] buf = input.getBytes();
                DatagramPacket pac = new DatagramPacket(buf, buf.length, address, Server.PORT);
                socket.send(pac);
                Thread.sleep(100);

                
                // Receiving Response UDP Packet
                buf = new byte[Server.BUF_LEN];
                pac = new DatagramPacket(buf, buf.length);
                System.out.println("Waiting for Response UDP Packet");
                socket.receive(pac);;
                System.out.println("Response Received: " + new String(buf));

            } while(!input.equals("exit"));
            
            socket.close();
        } catch(SocketException eSE) 
        {
            System.out.println(eSE.getMessage());
            eSE.printStackTrace();
        }
        catch(IOException eIO)
        {
            System.out.println(eIO.getMessage());
            eIO.printStackTrace();
        }


    }
}
