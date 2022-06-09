// Install Junit and add it to the classpath to solve these issues :)

public class BankAccountTest {

    @org.junit.Test
    public void withdraw() throws Exception
    {
        BankAccount ba = new BankAccount("dummy", "dummy", 1000);
        assertEquals(1000, ba.withdraw(0, false));
        assertEquals(1000, ba.withdraw(-200, false));
        assertEquals(800, ba.withdraw(200, false));
    }

    @org.junit.Test
    public void getBalance() throws Exception
    {
        fail("Yet to be Implemented."); // fail() is from JUnit itself
    }

}
