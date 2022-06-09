public class Countdown{
    private int i;
    private String color = Colors.ANSI_BLACK;

    public void startCountdown()
    {

        for(i = 0; i<=10; i++) {

            if(Thread.currentThread().getName().equalsIgnoreCase("Thread 1"))
            {
                this.color = Colors.ANSI_CYAN;
            }
            else if (Thread.currentThread().getName().equalsIgnoreCase("Thread 2"))
            {
                this.color = Colors.ANSI_RED;
            }

            System.out.println(color + Thread.currentThread().getName() + " i = " + i + ".");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startSynchronizedCountdown()
    {
        // Synchronizes on the current Countdown instance :D
        // Such that only one thread can operate on this instance at a time :D
        synchronized (this)
        {
            for(i = 0; i<=10; i++) {

                if(Thread.currentThread().getName().equalsIgnoreCase("Thread 1"))
                {
                    this.color = Colors.ANSI_CYAN;
                }
                else if (Thread.currentThread().getName().equalsIgnoreCase("Thread 2"))
                {
                    this.color = Colors.ANSI_RED;
                }

                System.out.println(color + Thread.currentThread().getName() + " i = " + i + ".");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}