import java.util.InputMismatchException;
import java.util.Scanner;

public class Basics {

    static Scanner sc = new Scanner(System.in);

    // Exceptions are interruptions in the general flow of a program
    //
    // There are :-
    //
    // *Checked* Exceptions = Need to be handled else code won't compile.
    // Eg = IOException :D
    //
    // *UnChecked* Exceptions = Can be left Unhandled ;p
    // Eg = ArithmeticException, InputMismatchException, etc.

    // We can handle exceptions following two paths in programming:-

    public static void main(String[] args) {

        int n=0,x,y ;

        System.out.println("\n[-] Program to input two integers and output their division.\n" +
                "(Possible Exceptions- InputMismatchException and ArithmeticException).\n");

        System.out.println("Press:-"+
                            "\n" + "'1' for LBYL (Look Before You Leap) method." +
                            "\n" + "'2' for EAFP (Easy to Ask for Forgiveness than Permission) way.\n" );

        try{
            System.out.print("Input - ");
            n = sc.nextInt();
        } catch(InputMismatchException e){
            System.out.println("\nDon't act cheeky ;p\n");
            System.exit(0);
        }

        switch(n) {

            case 1:
                x = getIntLBYL('x');
                y = getIntLBYL('y');

                divideLBYL(x,y);
                break;

            case 2:
                x = getIntEAFP('x');
                y = getIntEAFP('y');

                divideEAFP(x,y);
                break;

            default:
                System.out.println("\n[!] Invalid Input! T_T [!]\n");
        }
    }

    // The LBYL (Look Before You Leap) method.

    public static int getIntLBYL(char c){

        System.out.print("Please enter value for integer " + c + " = ");

        if(sc.hasNextInt())
            return sc.nextInt();
        else
            System.out.println("\n[!] InputMisMatchException found using * if(sc.hasNextInt()) *  T_T [!]\n");

        System.exit(0);
        return -1;
    }

    public static void divideLBYL(int x, int y){

        System.out.println("\nDividing x (" + x + ") by y (" + y + ").");

        if(y==0){
            System.out.println("Found ArithmeticException of Division by 0 as y = 0.\n");
            System.exit(0);
        }
        else{
            System.out.println("x/y = " + (x/y) + "(Q), " + (x%y) + "(R).\n");
        }

    }


    // The EAFP (Easy to Ask for Forgiveness than Permission) way.

    public static int getIntEAFP(char c){

        try {
            System.out.print("Please enter value for integer " + c + " = ");
            return sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("\n[!] InputMisMatchException found using Try&Catch block T_T [!]\n");
            System.out.println("Stack Trace is as follows:-");
            System.out.println("[!] Read from Bottom to Up to understand occurrence hierarchy!\n");
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    public static void divideEAFP(int x, int y){

        System.out.println("\nDividing x (" + x + ") by y (" + y + ").");

        try{
            System.out.println("x/y = " + (x/y) + "(Q), " + (x%y) + "(R).\n");
        } catch (ArithmeticException e ){
            System.out.println("Found ArithmeticException through Try&Catch block..\n");
            System.out.println("Stack Trace is as follows:-");
            System.out.println("[!] Read from Bottom to Up to understand occurrence hierarchy!\n");
            e.printStackTrace();
            System.exit(0);
        }
    }

}
