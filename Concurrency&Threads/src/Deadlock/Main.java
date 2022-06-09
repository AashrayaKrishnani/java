package Deadlock;

// So Thread one initially has Lock 1 and is waiting for Lock2,
// The issue is that Lock2 is with Thread2, which is waiting for Lock1 XD
// Messed up stuff you see XD
//
// Solution is to make the threads get the locks in the SAME ORDER, i.e., Lock1 --> Lock2
// That way Thread2 won't have lock2 before getting lock1, 
// And our good old Thread1 with lock1, will get lock 2, do its job, release the locks in the respective order for Thread2 to take over :D


public class Main {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread1().start();
        new Thread2().start();

    }

    static class Thread2 extends Thread{
        public void run(){

            synchronized(lock2){

                System.out.println("[-] Thread2 has Lock2 :)");

                try{
                    Thread.sleep(100);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("[-] Thread2 Attempting to get Lock1, waiting currently...");

                synchronized(lock1){
                    System.out.println("[-] Thread2 got Lock1 :D  [ Has Lock2 and Lock1 now ]");
                }

                System.out.println("[-] Thread2 released Lock 1 :)");

            }

            System.out.println("[-] Thread2 released Lock2 :)");

        }
    }


    static class Thread1 extends Thread{
        public void run(){

            synchronized(lock1){

                System.out.println("[-] Thread 1 has Lock 1 :)");

                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("[-] Thread1 Attempting to get Lock2, waiting currently...");

                synchronized(lock2){
                    System.out.println("[-] Thread1 got Lock2 :D  [ Has Lock2 and Lock1 now ]");
                }

                System.out.println("[-] Thread 1 released Lock 2 :)");

            }

            System.out.println("[-] Thread 1 released Lock 1 :)");

        }
    }

}
