package Countdown;

import java.io.BufferedInputStream;
import java.util.*;

class Colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}

public class Main {
    static Scanner sc = new Scanner(new BufferedInputStream(System.in));
    static List<CountdownThread> threads = new ArrayList<CountdownThread>();

    public static void main(String[] args) {

        System.out.println("\n\n");

        System.out.println("Enter number of threads to create!");

        int t = sc.nextInt();
        sc.nextLine();

        System.out.println("[+] For Each Thread, Now enter:" +
                "\n*-* the ColorName for that Thread," +
                "\n*-* the Message to Print," +
                "\n*-* the Number of times to print it," +
                "\n*-* the Time interval between the messages (in milliSeconds), and" +
                "\n*-* the ThreadName for that thread :)");

        while(t-->0){
            System.out.println("\nAvailable ColorCodes are- black, blue, cyan, green, red and purple.");
            System.out.print("Enter colorName: ");
            String colorName = sc.nextLine();

            System.out.print("Enter message: ");
            String message = sc.nextLine();

            System.out.print("Enter count: ");
            int count = sc.nextInt();

            System.out.print("Enter interval(in milliSeconds): ");
            int interval = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Thread Name: ");
            String threadName = sc.nextLine();

            threads.add(new CountdownThread(new Countdown(colorName, message, count, interval, threadName)));
        }

        System.out.println("\n[!] Press any key to allow creation of threads :)");
        sc.nextLine();

        for(CountdownThread thread : threads)
        {
            thread.start();
        }

    }
}

class Countdown{

    String color;
    String message;
    int count;
    int interval;
    String threadName;

    static Map<String, String> colors = new HashMap<>();
    static{

        colors.put("black", Colors.ANSI_BLACK);
        colors.put("blue", Colors.ANSI_BLUE);
        colors.put("cyan", Colors.ANSI_CYAN);
        colors.put("green", Colors.ANSI_GREEN);
        colors.put("purple", Colors.ANSI_PURPLE);
        colors.put("red", Colors.ANSI_RED);

    }

    public Countdown(String colorName, String message, int count, int interval, String threadName) {
        this.color = colors.containsKey(colorName.toLowerCase().trim().toLowerCase()) ? colors.get(colorName.toLowerCase().trim().toLowerCase()) : colors.get("red") ;
        this.count = count <= 0 ? 10 : count;
        this.interval = interval < 0 ? 1000 : interval;
        this.threadName = threadName.isBlank() ? "Untitled Thread" : threadName;
    }

    public void startCountdown()
    {
        Thread.currentThread().setName(threadName);

        for(int i = 0; i<count; i++) {
            System.out.println(color + (i + 1) + ". " + message + " (from " + Thread.currentThread().getName() + ") :D");
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

class CountdownThread extends Thread{

    private Countdown countdown;

    public CountdownThread(Countdown countdown){
        this.countdown = countdown;
    }


    @Override
    public void run() {
        countdown.startCountdown();
    }
}
