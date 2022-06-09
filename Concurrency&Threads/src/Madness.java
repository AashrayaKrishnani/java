
public class Madness {

    // To Show that thread output sequences are never guaranteed XD
    public static void main(String[] args) {

        System.out.println("\n");

        System.out.println("Threads are Weird XD \nEspecially while they work together on a common variable XD\n");

        Countdown countdown = new Countdown();

        // Note that they'll access the same instance variable 'i' in 'countdown'
        CountdownThread thread1 = new CountdownThread(countdown);
        CountdownThread thread2 = new CountdownThread(countdown);

        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();
        System.out.println("Do check out SynchronizedMadness.java and compare the output with this :P\n");
    }

}

class CountdownThread extends Thread{
    private Countdown countdown;

    public CountdownThread(Countdown countdown) { this.countdown = countdown;}

    @Override
    public void run() { this.countdown.startCountdown(); }
}
