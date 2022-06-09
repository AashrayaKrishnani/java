package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
  
    public static int PORT = 2222;
    public static int BUF_LEN = 500;

    public static void main(String[] args) throws InterruptedException {
      
        try(DatagramSocket socket = new DatagramSocket(PORT))
        {
            DatagramPacket pac;
            while(true)
            {            
                byte[] buf = new byte[BUF_LEN];
                pac = new DatagramPacket(buf, buf.length);
                
                System.out.println("Waiting for UDP Packet.");
                socket.receive(pac);
                System.out.println("Received Data: " + new String(buf));

                // Sending out Response UDP Packet
                pac = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), Client.PORT);
                System.out.println("Sending Response UDP Packet: " + new String(buf));
                socket.send(pac);

                if(new String(buf).equals("exit"))
                    break;
            }

        } catch(SocketException eSE) 
        {
            System.out.println(eSE.getMessage());
        }
        catch(IOException eIO)
        {
            System.out.println(eIO.getMessage());
        }


  }  
}
