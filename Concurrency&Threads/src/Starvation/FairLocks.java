package Starvation;

import java.util.concurrent.locks.ReentrantLock;

class FairLocks{

    // To ensure that the thread which suspended to get the lock first, actually gets it and that lock isn't given at random!
    public static ReentrantLock lock = new ReentrantLock(true); // The true is to enable First-Come-First-Served basis :)

    public static void main(String[] args) {
    
        System.out.println("Note that Setting priority to a thread is only a suggestion to the OS,"
        +"\nThe real order in which they may run entirely depends upon how the OS decides to impart the lock.\n\n");


        Thread t1 = new Thread(new Worker(Colors.ANSI_BLUE, lock), "Priority 1");
        Thread t2 = new Thread(new Worker(Colors.ANSI_BLACK, lock), "Priority 3");
        Thread t3 = new Thread(new Worker(Colors.ANSI_CYAN, lock), "Priority 5");
        Thread t4 = new Thread(new Worker(Colors.ANSI_PURPLE, lock), "Priority 7");
        Thread t5 = new Thread(new Worker(Colors.ANSI_GREEN, lock), "Priority 9");
        Thread t6 = new Thread(new Worker(Colors.ANSI_RED, lock), "Priority 10");

        // The Thread.setPriority() method only takes integeres between 1-10.

        // Don't really matter now :p
        // t1.setPriority(1);
        // t2.setPriority(3);
        // t3.setPriority(5);
        // t4.setPriority(7);
        // t5.setPriority(9);
        // t6.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }

    static class Worker implements Runnable{
        String color;
        ReentrantLock lock;


        public Worker(String color, ReentrantLock lock){
            this.color=color;
            this.lock=lock;
        }

        public void run(){
            for(int i = 1; i<=10; i++)
            {
                lock.lock();
                try{
                        System.out.format("%s %s: Value of i = %s %n", this.color, Thread.currentThread().getName(), i);
                } finally{
                    this.lock.unlock();
                }
            }

        }
    }

}