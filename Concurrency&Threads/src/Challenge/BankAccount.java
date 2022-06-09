package Challenge;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * BankAccount
 */
public class BankAccount {

    private double balance;
    private String accountNumber;
    private ReentrantLock lock;
    
    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.lock = new ReentrantLock(true);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {

        try {
            if( lock.tryLock(1, TimeUnit.SECONDS) ) 
            {
                lock.lock();
                try{
                    this.accountNumber = accountNumber;
                } finally
                {
                    lock.unlock();
                }
            }
            else{
                System.out.println("Couldn't get the lock :/");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean deposit(double amount) {

        boolean status = false;

        try {
            if( lock.tryLock(1, TimeUnit.SECONDS) ) 
            {
                lock.lock();
                try{
                    if(amount > 0)
                    {
                        balance += (amount>0 ? amount : 0);
                        status = true;
                    }
                    
                } finally
                {
                    lock.unlock();
                }
            }
            else{
                System.out.println("Couldn't get the lock :/");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(accountNumber + ": Trasaction status for depositing " + amount + ": " + status);

        return status;
    }
    
    public boolean withdraw(double amount){
        boolean status = false;

        try {
            if( lock.tryLock(1, TimeUnit.SECONDS) ) 
            {
                lock.lock();
                try{
                    if(amount <= balance)
                    {
                        balance -= amount;
                        status = true;
                    }
                    
                } finally
                {
                    lock.unlock();
                }
            }
            else{
                System.out.println("Couldn't get the lock :/");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(accountNumber + ": Trasaction status for withdrawing " + amount + ": " + status);
        
        return status;
    } 

    public void printBalance(){
        System.out.println(this.accountNumber + ": Account balance: " + this.balance);
    }
    
}