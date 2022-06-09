import java.io.BufferedInputStream;
import java.util.Random;
import java.util.Scanner;

public class MoreBasics {
    public static void main(String[] args) throws InterruptedException {
        
        String regex;
        Random random = new Random();
        Scanner sc =new Scanner(new BufferedInputStream(System.in));

        System.out.print("\n\nString s= ");
        String s = sc.nextLine();

        System.out.println("\nThe \\d specifies digits [0-9]. \nThe \\D specifies ALL EXCEPT digits [0-9]"+ "\n" + "Press Enter to see something XD");
        sc.nextLine();
        System.out.println("Result of running 's.replaceAll(\"\\d\", \"X\")' = " + s.replaceAll("\\d", "X"));
        System.out.println("Result of running 's.replaceAll(\"\\D\", \"X\")' = " + s.replaceAll("\\D", "X"));
        Thread.sleep(2000);

        System.out.println("\nThe \\s specifies WHITE-SPACE sequences like \\t or \\n or space. \nThe \\S specifies ALL EXCEPT WHITE-SPACE sequences like \\t or \\n or space"+ "\n" + "Press Enter to see magix ;p");
        sc.nextLine();
        System.out.println("Result of running 's.replaceAll(\"\\s\", \"X\")' = " + s.replaceAll("\\s", "X"));
        System.out.println("Result of running 's.replaceAll(\"\\S\", \"X\")' = " + s.replaceAll("\\S", "X"));
        Thread.sleep(2000);

        System.out.println("\nThe \\w specifies [(a-z)(A-Z)(0-9)(_)]. \nThe \\W specifies ALL EXCEPT [(a-z)(A-Z)(0-9)(_)]"+ "\n" + "Press Enter to see something!");
        sc.nextLine();
        System.out.println("Result of running 's.replaceAll(\"\\w\", \"X\")' = " + s.replaceAll("\\w", "X"));
        System.out.println("Result of running 's.replaceAll(\"\\W\", \"X\")' = " + s.replaceAll("\\W", "X"));
        Thread.sleep(2000);

        System.out.println("\nThe \\b APPENDS, not REPLACES, Word-boundaries like whitespaces or Symbols surrounding a word(alphaNumericSequence) etc. \nThe \\S specifies ALL EXCEPT that match \\s ;p"+ "\n" + "Press Enter to see the results :D!");
        sc.nextLine();
        System.out.println("Result of running 's.replaceAll(\"\\b\", \"X\")' = " + s.replaceAll("\\b", "X"));
        System.out.println("Result of running 's.replaceAll(\"\\B\", \"X\")' = " + s.replaceAll("\\B", "X"));
        Thread.sleep(2000);

    }
    
}
