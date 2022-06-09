package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

// We use a Try and finally block to avoid writing ourLock.unlock() at multiple places as the finally block will be executed regardless 
// of an error or continue or break statement. :D

public class TryAndFinally {
    public static final String EOF = "EOF";

    class MyProducer implements Runnable{
        private List<String> buffer;
        private String color;
        private ReentrantLock bufferLock;
    
        public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock){
            this.buffer = buffer;
            this.color = color;
            this.bufferLock  = bufferLock;
        }
    
        public void run(){
            Random random = new Random();
            int[] nums = {1, 2, 3, 4, 5};
        
            for(int i : nums){
                 try{
                    System.out.println(color + "Adding... " + i);
                    
                    bufferLock.lock();
                    try{
                        buffer.add(i+"");
                    } finally{
                        bufferLock.unlock();
                    }
    
                    Thread.sleep(random.nextInt(1000));
                 } catch(InterruptedException e){
                     e.printStackTrace();
                 }
            }
            System.out.println(color + "Adding EOF...");
            bufferLock.lock();
            try{
                buffer.add(Main.EOF);
            } finally{
                bufferLock.unlock();
            }
        }
    }
    
    class MyConsumer implements Runnable{
        private List<String> buffer;
        private String color;
        private ReentrantLock bufferLock;
    
        public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock){
            this.buffer = buffer;
            this.color = color;
            this.bufferLock = bufferLock;
        }
    
        public void run(){
            while(true){
                bufferLock.lock();
                try{
                    if(buffer.isEmpty()){
                       // bufferLock.unlock();  No need
                        continue;
                    }
    
                    if(buffer.get(0).equals(Main.EOF)){
                        System.out.println(color + Thread.currentThread().getName() + " Exiting...");
                     //   bufferLock.unlock(); No need
                        break;
                    }
                    else{    
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                } finally{
                    bufferLock.unlock();
                }
                    
            }
        }
    }


    public static void main(String[] args){

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        TryAndFinally wrapper = new TryAndFinally();

        MyProducer producer = wrapper.new MyProducer(buffer, Colors.ANSI_CYAN, bufferLock);
        MyConsumer consumer1 = wrapper.new MyConsumer(buffer, Colors.ANSI_RED, bufferLock);
        MyConsumer consumer2 = wrapper.new MyConsumer(buffer, Colors.ANSI_BLUE, bufferLock);
    
        (new Thread(producer)).start();
        (new Thread(consumer1)).start();
        (new Thread(consumer2)).start();
    
    }
}

