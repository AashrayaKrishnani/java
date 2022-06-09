package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

// We use a Manual lock this time instead of the synchronized block as it gives us the flexibility to lock and unlock when we wish :D
// Just make sure that the Threads share the SAME instance of the Lock else Stuff will go haywire XD

public class WithReentrantLock {
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
                        buffer.add("" + i);
                    bufferLock.unlock();
    
                    Thread.sleep(random.nextInt(1000));
                 } catch(InterruptedException e){
                     e.printStackTrace();
                 }
            }
            System.out.println(color + "Adding EOF...");
            bufferLock.lock();
                buffer.add(Main.EOF);
            bufferLock.unlock();
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
                    if(buffer.isEmpty()){
                        bufferLock.unlock();
                        continue;
                    }
    
                    if(buffer.get(0).equals(Main.EOF)){
                        System.out.println(color + Thread.currentThread().getName() + " Exiting...");
                        bufferLock.unlock(); 
                        break;
                    }
                    else{    
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                bufferLock.unlock();   
            }
        }
    }


    public static void main(String[] args){

        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        WithReentrantLock wrapper = new WithReentrantLock();

        MyProducer producer = wrapper.new MyProducer(buffer, Colors.ANSI_CYAN, bufferLock);
        MyConsumer consumer1 = wrapper.new MyConsumer(buffer, Colors.ANSI_RED, bufferLock);
        MyConsumer consumer2 = wrapper.new MyConsumer(buffer, Colors.ANSI_BLUE, bufferLock);
    
        (new Thread(producer)).start();
        (new Thread(consumer1)).start();
        (new Thread(consumer2)).start();
    
    }
}

