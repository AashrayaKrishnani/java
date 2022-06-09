package Challenge;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount(1000, "10231-241-2");

        new Thread(new Runnable() {
            public void run(){
                bankAccount.deposit(1000);
                bankAccount.withdraw(50);
            }
        }).start();


        new Thread(new Runnable() {
            public void run(){
                bankAccount.deposit(205.9);
                bankAccount.withdraw(160.1415);
            }
        }).start();

        Thread.sleep(500);

        bankAccount.printBalance();

    }

}