import java.io.BufferedInputStream;
import java.util.*;

class Input{
    String colorCode;
    String message;
    String threadName;
    int count;
    int interval;

    public Input(String colorCode, String message, int count, int interval, String threadName) {
        this.colorCode = colorCode;
        this.message = message;
        this.count = count;
        this.interval = interval;
        this.threadName = threadName;
    }
}

public class Threads {

    static Scanner sc = new Scanner(new BufferedInputStream(System.in));

    static List<Input> inputs = new ArrayList<>();

    static Map<String, String> colors = new HashMap<>();
    static{

        colors.put("black", Colors.ANSI_BLACK);
        colors.put("blue", Colors.ANSI_BLUE);
        colors.put("cyan", Colors.ANSI_CYAN);
        colors.put("green", Colors.ANSI_GREEN);
        colors.put("purple", Colors.ANSI_PURPLE);
        colors.put("red", Colors.ANSI_RED);

    }


    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(100);
        System.out.println();



        System.out.println("Enter number of threads to create!");

        int t = sc.nextInt();
        sc.nextLine();

        System.out.println("[+] For Each Thread, Now enter:" +
                "\n*-* the ColorCode for that Thread," +
                "\n*-* the Message to Print," +
                "\n*-* the number of times to print it," +
                "\n*-* the time interval between the messages (in milliSeconds), and" +
                "\n*-* the threadName for that thread :)");

        while(t-->0){
            System.out.println("\nAvailable ColorCodes are- black, blue, cyan, green, red and purple.");
            System.out.print("Enter colorCode: ");
            String colorCode = sc.nextLine();

            System.out.print("Enter message: ");
            String message = sc.nextLine();

            System.out.print("Enter count: ");
            int count = sc.nextInt();

            System.out.print("Enter interval(in milliSeconds): ");
            int interval = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Thread Name:");
            String threadName = sc.nextLine();

            inputs.add(new Input(colorCode, message, count, interval, threadName));
        }

        System.out.println("\n[!] Press any key to allow creation of threads :)");
        sc.nextLine();

        for(int j = 0; j<inputs.size(); j++)
        {
            Input i = inputs.get(j);

            String colorCode = i.colorCode;
            String message = i.message;
            String threadName = i.threadName;
            int count = i.count;
            int interval = i.interval;

            if(j%3 == 0)
                createThread(colorCode, message, count, interval, threadName);
            else if(j%3 == 1)
                createThreadAnotherThread(colorCode, message, count, interval, threadName);
            else
                createThreadMyRunnable(colorCode, message, count, interval, threadName);
        }

    }


    static void createThread(String colorCode, String message, int count, int interval, String threadName)
    {
        if (colors.containsKey(colorCode))
        {
            colorCode = colors.get(colorCode);

            System.out.println("Creating thread using Anonymous class: Color '" + colorCode + "color" + Colors.ANSI_RESET +
                    "', Message '" + message + "' to count for " + count + " times. :D");

            String finalColorCode = colorCode;
            new Thread(() -> {      // Anonymous class Usage :D Instead of doing "new Thread(){ CODE; };"

                Thread.currentThread().setName(threadName);
                System.out.println(finalColorCode);

                for(int i = 0; i<count; i++){
                    System.out.println(finalColorCode + (i + 1) + ". " + message + " (from " + Thread.currentThread().getName() + ") :D");
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        else{
            System.out.println("[+] Invalid color Code (" + colorCode+ ") Entered! *_*");
        }

    }

    static void createThreadAnotherThread(String colorCode, String message, int count, int interval, String threadName)
    {
        if (colors.containsKey(colorCode))
        {
            colorCode = colors.get(colorCode);

            System.out.println("Creating thread through AnotherThread: Color '" + colorCode + "color" + Colors.ANSI_RESET +
                    "', Message '" + message + "' to count for " + count + " times. :D");


            new AnotherThread(colorCode, message, count, interval, threadName).start();

        }
        else{
            System.out.println("[+] Invalid color Code (" + colorCode+ ") Entered! *_*");
        }

    }

    static void createThreadMyRunnable(String colorCode, String message, int count, int interval, String threadName)
    {
        if (colors.containsKey(colorCode))
        {
            colorCode = colors.get(colorCode);

            System.out.println("Creating thread through MyRunnable: Color '" + colorCode + "color" + Colors.ANSI_RESET +
                    "', Message '" + message + "' to count for " + count + " times. :D");


            new Thread(new MyRunnable(colorCode, message, count, interval, threadName)).start();
        }
        else{
            System.out.println("[+] Invalid color Code (" + colorCode+ ") Entered! *_*");
        }

    }

}
