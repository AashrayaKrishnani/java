package src;

import java.util.Arrays;
import java.util.Collection;

/**
 * BankAccountParameterizedTest
 */
public class BankAccountParameterizedTest {
 
    private BankAccount ba;
    private double balance;
    private double expected;
    private double amount;


    public BankAccountParameterizedTest(double balance, double amount, double expected) {
        this.balance = balance;
        this.amount = amount;
        this.expected = expected;
    }

    @Parameterized.parameters
    public static Collection<Object[]> testCondition()
    {
        return Arrays.asList(new Object[][]{
            // Initializing Constructor values :D
            {1000, 200, 1200},
            {200, -200, 200},
            {1000, 1000, 2000}
        });
    }

    @org.junit.Before
    public void setup()
    {
        ba = new BankAccount("firstname", "lastName", balance);
    }

    @org.junit.Test
    public void deposit()
    {
        assertEquals(expected, ba.deposit(amount, false), 0.1); 
        // The Third Argument is the Delta Argument to make up for the calculation error when dealing with Double values :)
    }

    

}