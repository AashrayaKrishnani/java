import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Throw_and_MultiCatch {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int x,y ;

        System.out.println("\n[-] Program to input two integers and output their division.\n" +
                "(Possible Exceptions- InputMismatchException and ArithmeticException).\n");

        try {
            x = getIntEAFP('x');
            y = getIntEAFP('y');

            divideEAFP(x, y);
        } catch(ArithmeticException | NoSuchElementException exception) {   // Single Pipe or Bitwise Inclusive OR '|'
                                                                // is used to specify Multi-catch Exceptions (Java7+)

            System.out.println("\n" + exception.toString());
            System.out.println("Throw_and_MultiCatch.main():" + "\n[!] Unsuccessful in program execution, Jarvis Signing off!");
        }
    }


    // The EAFP (Easy to Ask for Forgiveness than Permission) way.

    public static int getIntEAFP(char c){

        try {
            System.out.print("Please enter value for integer " + c + " = ");
            return sc.nextInt();
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException("[!] Invalid Input detected [!] - Try&Catch block.");
        }
    }

    public static void divideEAFP(int x, int y){

        System.out.println("\nDividing x (" + x + ") by y (" + y + ").");

        try{
            System.out.println("x/y = " + (x/y) + "(Q), " + (x%y) + "(R).\n");
        } catch (ArithmeticException e ){
            throw new ArithmeticException("[!] Invalid attempt to 'divide by 0' detected [!] - Try&Catch block.");
        }
    }

}
