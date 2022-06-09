import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Pipes {

    public static void main(String[] args) throws IOException {
//        Pipes can be used in Java to have transfer to data from one thread to another :D
//
//        They have separate writer and reader threads :)
//        Called as 'Sink' channels and 'Flow' Channels respectively :D

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter number of times you wanna see the time XD\n" + "n(times) = ");
        int n = sc.nextInt();

        Pipe pipe = Pipe.open();

        Runnable writer = new Runnable() {
            @Override
            public void run() {
                try{
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    Pipe.SinkChannel sinkChannel = pipe.sink();;

                    for(int i =0 ; i < n; i++){
                        String s = "[+] Time is: " + System.currentTimeMillis();

                        buffer.put(s.getBytes(StandardCharsets.UTF_8));
                        buffer.flip();

                        sinkChannel.write(buffer);
                        buffer.flip();
                        Thread.sleep(1000);
                    }


                } catch(Exception e){

                }
            }
        };

        Runnable reader = new Runnable() {
            @Override
            public void run() {
                try{
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    Pipe.SourceChannel srcChannel = pipe.source();

                    for(int i = 0; i < n; i++){
                        int bytesRead = srcChannel.read(buffer);
                        buffer.flip();

                        byte[] temp = new byte[bytesRead];
                        buffer.get(temp);   // Puts buffer contents in byte[] temp.
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                        Thread.sleep(1000);
                    }

                    System.out.println("\nProgram Done! :D\n");
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(writer).start();
        new Thread(reader).start();



    }

}
