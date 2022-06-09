import java.io.BufferedInputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Basics
 */
public class Basics {

    public static void main(String[] args) throws InterruptedException {
        
        String regex;
        Random random = new Random();
        Scanner sc =new Scanner(new BufferedInputStream(System.in));

        System.out.print("\n\nString s= ");
        String s = sc.nextLine();

        System.out.println("\nThe carrot class (^) in regex specifies the *START OF A STRING*." + "\n" + "Enter valid RegEx to run replace(regex) on 's' with a surprise:-");
        System.out.print("Regex = ^");
        regex = "^" + sc.nextLine();
        System.out.println("Result of running 's.replaceAll(\"" + regex +  "\",  \"YouAreGreat!\")' = " + s.replaceAll(regex, "YouAreGreat!"));
        Thread.sleep(2000);

        System.out.println("\nThe Dollar class ($) in regex specifies the *END OF A STRING*." + "\n" + "Enter valid RegEx to run replace(regex) on 's' with a surprise:-");
        System.out.print("Regex (Will be appended by $) = ");
        regex = sc.nextLine() + "$";
        System.out.println("Result of running 's.replaceAll(\"" + regex +  "\",  \"EveryEndHasANewBeginning!\")' = " + s.replaceAll(regex, "EveryEndHasANewBeginning!"));
        Thread.sleep(2000);

        System.out.println("\nThe characters (a, b, etc) specified WITHIN [] -WITHOUT COMMAS- indicate check for EITHER of the Options in the []." + "\n" + "The Entries can also be complex Strings enclosed within () like - [(yoq)(fawq)]" + "\n" + "Enter valid RegEx to run replace(regex) on 's' for EITHER of the options:-");
        System.out.println("Tip: We can use '-' to specify ranges as: [abcdef3456789] is same as [a-f3-9]");
        System.out.print("Regex (Will be enclosed with []) = ");
        regex = "[" + sc.nextLine() + "]";
        System.out.println("Result of running 's.replaceAll(\"" + regex +  "\",  \":D\")' = " + s.replaceAll(regex, ":D"));

        System.out.println("\nWhen used as the FIRST CHARACTER within [], the carrot '^' means NOT or ALL EXCEPT."+ "\n" + "Meaning any entry except that of those specified within [] will MATCH" + "\n" + "Enter valid RegEx to run replace(regex) on 's' with a random character:-");
        System.out.print("Regex (Will be enclosed with [^])= ");
        regex = "[^" + sc.nextLine() + "]";
        System.out.println("Result of running 's.replaceAll(\"" + regex +  "\",  s.replaceAll(regex, String.valueOf(random.nextInt(100))))' = " + "\n"+s.replaceAll(regex, String.valueOf(random.nextInt(100))));
        Thread.sleep(2000);

        System.out.println("\nThe (?i) group MAKES a RegEx be CASE-INSENSITIVE." + "\n" + "Enter valid RegEx to run replace(regex) on 's' with a surprise:-");
        System.out.print("Regex = (?i) ");
        regex = "(?i)" + sc.nextLine() ;
        System.out.println("Result of running 's.replaceAll(\"" + regex +  "\",  \"YouAreNumber\" + random.nextInt(10))' = ");
        System.out.println(s.replaceAll(regex, "YouAreNumber" + random.nextInt(10)));
        Thread.sleep(2000);


    }
    
}