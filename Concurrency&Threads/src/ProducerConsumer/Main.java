package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// To See how Thread Interference can completely mess up a program, convert 'synchronized' blocks into normal ones.
// ArrayList isn't thread safe, plus three threads working on it simultaneously can easily compromise its internal integrity!

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args){

        List<String> buffer = new ArrayList<>();

        MyProducer producer = new MyProducer(buffer, Colors.ANSI_CYAN);
        MyConsumer consumer1 = new MyConsumer(buffer, Colors.ANSI_RED);
        MyConsumer consumer2 = new MyConsumer(buffer, Colors.ANSI_BLUE);
    
        (new Thread(producer)).start();
        (new Thread(consumer1)).start();
        (new Thread(consumer2)).start();
    
    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color){
        this.buffer = buffer;
        this.color = color;
    }

    public void run(){
        Random random = new Random();
        int[] nums = {1, 2, 3, 4, 5};
    
        for(int i : nums){
             try{
                System.out.println(color + "Adding... " + i);
                synchronized(buffer){
                    buffer.add("" + i);
                }
                Thread.sleep(random.nextInt(1000));
             } catch(InterruptedException e){
                 e.printStackTrace();
             }
        }
        System.out.println(color + "Adding EOF...");
        synchronized(buffer){
            buffer.add(Main.EOF);
        }
    }
}

class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color){
        this.buffer = buffer;
        this.color = color;
    }

    public void run(){
        while(true){
            synchronized(buffer){
                if(buffer.isEmpty()){
                    continue;
                }

                if(buffer.get(0).equals(Main.EOF)){
                    System.out.println(color + Thread.currentThread().getName() + " Exiting...");
                    break;
                }
                else{    
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }   
        }
    }
}

class Colors {
    public static final String ANSI_RESET = "\u001B[;0m";
    public static final String ANSI_BLACK = "\u001B[;30m";
    public static final String ANSI_RED = "\u001B[;31m";
    public static final String ANSI_GREEN = "\u001B[;32m";
    public static final String ANSI_BLUE = "\u001B[;34m";
    public static final String ANSI_PURPLE = "\u001B[;35m";
    public static final String ANSI_CYAN = "\u001B[;36m";
}
