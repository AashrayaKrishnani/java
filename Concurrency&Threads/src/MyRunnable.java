public class MyRunnable implements Runnable{

    String colorCode;
    String message;
    int count;
    int interval;
    String threadName;

    public MyRunnable(String colorCode, String message, int count, int interval, String threadName)
    {
        this.colorCode = colorCode;
        this.message = message;
        this.count = count;
        this.interval = interval;
        this.threadName = threadName;
    }


    @Override
    public void run() {
        System.out.println(colorCode);

        Thread.currentThread().setName(threadName);

        for(int i = 0; i<count; i++) {
            System.out.println(colorCode + (i + 1) + ". " + message + " (from " + Thread.currentThread().getName() + ") :D");
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
