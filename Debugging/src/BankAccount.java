/**
 * BankAccount
 */
public class BankAccount {

    private String firstname;
    private String lastName;
    private double balance;

    public BankAccount(String firstname, String lastName, double balance) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.balance = balance;
    }

    public double deposit(double amount, boolean fromBranch)
    {
        balance += amount>0 ? amount : 0;
        return balance;
    }

    public double withdraw(double amount, boolean fromBranch)
    {
        balance -= amount <= balance? amount:0; 
        return balance;
    }

}