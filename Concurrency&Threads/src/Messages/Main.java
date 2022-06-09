package Messages;

import java.util.Random;

public class Main {
    public static void main(String[] args) {        
        System.out.println("\n\n[!] Info about the program :)\n");

        System.out.println("So the Message.read() and Message.write() are synchronized methods (meaning only one runs at a time, so a message is modified correctly).");
        System.out.println("They are called upon by seperate threads w/ runnables Writer and Reader that, as the name suggests, Write into the message object and read from it.");
        System.out.println("\nWe have put in 4 different messages that the writer writes into the common message object, and then the reader should read them.");
        System.out.println("Though it might seem from the code that they do it at once, but as the Writer finishes writing one message and calls 'notifyAll()' the read() in Reader wakes up as isEmpty in Message becomes false.");
        System.out.println("It then readswhatever was written by write() and then changes isEmpty=true, calls notifyAll() and goes to wait() till isEmpty=false again :)");
        System.out.println("\nRead the code once to understand it if that did not make much sense :)\n\n");
        
        System.out.println("\n\n[!] Actual Program :D\n\n");

        Message message = new Message();
        new Thread(new Writer(message)).start();
        new Thread(new Reader(message)).start();
    }
}

class Message{
    private String message;
    private boolean isEmpty = true;

    public synchronized String read(){
        while(isEmpty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty=true;
        if(!message.equals("Finished"))
            notifyAll();
        return message;
    }

    public synchronized Message write(String message)
    {
        while(!isEmpty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
        isEmpty = false;
        this.message = message;
        notifyAll();
        return this;
    }

}

class Writer implements Runnable{
    private Message message;

    public Writer(Message message){
        this.message = message;
    }

    @Override
    public void run() {

        String[] messages = {
                "Some random",
                "Text that",
                "I decided",
                "To write XD",
                "Finished"
        };

        Random random = new Random();

        for(int i = 0; i<messages.length; i++)
        {
            this.message.write(messages[i]);

            try{
                // The nextInt(int bound) generates random integer within given bound :D
                Thread.sleep(Math.abs(random.nextInt(2000)));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Reader implements Runnable{
    private Message message;

    public Reader(Message message){
        this.message = message;
    }

    public void run(){
        for(String latestMessage = message.read(); !latestMessage.equals("Finished");
        latestMessage=message.read()){
            System.out.println(latestMessage);
        
            try{
                Thread.sleep(new Random().nextInt(2000));
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
