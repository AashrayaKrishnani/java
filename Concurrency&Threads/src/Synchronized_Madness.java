public class Synchronized_Madness {

    // To Show that thread output sequences are never guaranteed XD
    public static void main(String[] args) {

        System.out.println("\n");

        System.out.println("Threads can be sane too XD \nEspecially after you use 'synchronized' in Java XD\n");

        Countdown countdown = new Countdown();

        // Note that they'll access the same instance variable 'i' in 'countdown'
        // But Still due to the use of 'synchronized' it'll be fine :P
        SynchronizedCountdownThread thread1 = new SynchronizedCountdownThread(countdown);
        SynchronizedCountdownThread thread2 = new SynchronizedCountdownThread(countdown);

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();

        System.out.println("Do check out Madness.java and compare the output with this :P\n");
    }

}



class SynchronizedCountdownThread extends Thread{
    private Countdown countdown;

    public SynchronizedCountdownThread(Countdown countdown) { this.countdown = countdown;}

    @Override
    public void run() { this.countdown.startSynchronizedCountdown(); }
}
