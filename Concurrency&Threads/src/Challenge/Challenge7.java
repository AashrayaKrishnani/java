package Challenge;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Challenge7
 */
public class Challenge7 {

    public static void main(String[] args) {
        NewBankAccount acc1 = new NewBankAccount("2131-313", 500);
        NewBankAccount acc2 = new NewBankAccount("2123-421", 1000);

        // new Transfer(acc1, acc2, 200).run();
        // new Transfer(acc2, acc1, 100).run();
        new Thread(new Transfer(acc1, acc2, 100), "Transfer1").start();
        new Thread(new Transfer(acc2, acc1, 50), "Transfer1").start();
    }
}

class NewBankAccount{

    private double balance;
    private String accountNumber;
    private ReentrantLock lock;
    
    public NewBankAccount(String accountNumber, int balance) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.lock = new ReentrantLock(true);
    }


    public boolean deposit(double amount) {
        if(lock.tryLock()) 
        {
            try{
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                if(amount > 0)
                {
                    balance -= amount;
                    System.out.printf("%s: Deposited %f to %s%n", Thread.currentThread().getName(), amount, accountNumber);
                    return true;
                } 
            } finally {
                lock.unlock();
            }


        }

    return false;
    }
    
    public boolean withdraw(double amount){
            if(lock.tryLock()) 
            {   try{
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    if(amount <= balance && amount>0)
                    {
                        balance -= amount;
                        System.out.printf("%s: Withdrew %f from %s%n", Thread.currentThread().getName(), amount, accountNumber);
                        return true;
                    }
                } finally {
                    lock.unlock();
                }

                
            }
        return false;
    } 

    public boolean transfer(NewBankAccount dest, double amount){
        if(withdraw(amount) && amount<=balance && amount>0)
        {
           if(dest.deposit(amount))
           { 
               return true; 
            }
            else{
                System.out.printf("%s: Destination account busy. Initiating Refund to %s%n", Thread.currentThread().getName(), accountNumber);
                balance += amount;
            }
        }

        return false;
    }

}


class Transfer implements Runnable{

    private NewBankAccount src,dest;
    private double amount;

    public Transfer(NewBankAccount src, NewBankAccount dest, double amount) {
        this.src = src;
        this.dest = dest;
        this.amount = amount;
    }

    @Override
    public void run() {
        while(!src.transfer(dest, amount))
        {
            continue;
        }

        System.out.printf("%s Transaction Completed%n", Thread.currentThread().getName());
    }

}