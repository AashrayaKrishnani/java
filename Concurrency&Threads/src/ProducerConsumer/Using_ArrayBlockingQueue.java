package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Using ArrayBlockingQueue class here rather than an ArrayList as this removes the need to synchronise code 
// because the data structure's implementation is in itself ThreadSafe :D

// We using peek() and put() instead of add() and remove() as the latter pair will throw an error if the ArrayBlockingQueue is locked.
// While the better ones are implemented to be Thread-Safe :)  
// ArrayBlockingQueue: the take() method to remove an object.


public class Using_ArrayBlockingQueue{
    public static final String EOF = "EOF";

    class MyProducer implements Runnable{
        private ArrayBlockingQueue<String> buffer;
        private String color;
    
        public MyProducer(ArrayBlockingQueue<String> buffer2, String color){
            this.buffer = buffer2;
            this.color = color;
        }
    
        public void run(){
            Random random = new Random();
            int[] nums = {1, 2, 3, 4, 5};
        
            for(int i : nums){
                 try{
                    System.out.println(color + "Adding... " + i);

                    buffer.put("" + i);

                    Thread.sleep(random.nextInt(1000));
                 } catch(InterruptedException e){
                     e.printStackTrace();
                 }
            }
            System.out.println(color + "Adding EOF...");

            try {
                buffer.put(Main.EOF);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }

        }
    }
    
    class MyConsumer implements Runnable{
        private ArrayBlockingQueue<String> buffer;
        private String color;

    
        public MyConsumer(ArrayBlockingQueue<String> buffer2, String color){
            this.buffer = buffer2;
            this.color = color;
        }
    
        public void run(){
            while(true){
                synchronized(buffer)            // Need this to make sure the same thread that checked to see the value, takes it out too. 
                {   
                    if(buffer.isEmpty()){
                        continue;
                    }
    
                    if(buffer.peek().equals(Main.EOF)){
                        System.out.println(color + Thread.currentThread().getName() + " Exiting...");
                        break;
                    }
                    else{    
                        try {
                            System.out.println(color + "Removed " + buffer.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    public static void main(String[] args){

        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(10);

        Using_ArrayBlockingQueue wrapper = new Using_ArrayBlockingQueue();

        MyProducer producer = wrapper.new MyProducer(buffer, Colors.ANSI_CYAN);
        MyConsumer consumer1 = wrapper.new MyConsumer(buffer, Colors.ANSI_RED);
        MyConsumer consumer2 = wrapper.new MyConsumer(buffer, Colors.ANSI_BLUE);
    
        ExecutorService ex = Executors.newFixedThreadPool(4); // We can also have cached thread pools and thread pools without a fixed size :D

        // Note that increasing max threads to 3 or less will make others threads/callables in queue to wait before current ones terminate

        ex.execute(producer);
        ex.execute(consumer1);
        ex.execute(consumer2);

        Future<String> future = ex.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                
                System.out.println("\n" + Colors.ANSI_PURPLE + "~~Inside callable.call().~~");

                return Colors.ANSI_GREEN + "I was returned by Callable ;p";
            }
        });

        try {
            System.out.println(Colors.ANSI_RED + "Return value from callable: -" + Colors.ANSI_RESET + future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Messed up :p");
            e.printStackTrace();
        }

        System.out.println(Colors.ANSI_RED + "\n[!] Sending  Shutdown signal to Executor Service.");
        ex.shutdown(); // This is IMPORTANT else the application won't terminate :)
    
    }
}

