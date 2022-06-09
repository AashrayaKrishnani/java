import java.io.BufferedInputStream;
import java.util.Scanner;

public class Quantifiers {
    public static void main(String[] args) throws InterruptedException {
                
        Scanner sc =new Scanner(new BufferedInputStream(System.in));

        System.out.print("\n\nString s = aaabqdaziqenz eqknznzzzznd qen112222 dajlxzicccc\n");
        String s = "aaabqdazziqenzz ccceqknznzzzznzd qen112222 dajlxzicccc";

        System.out.println("\n[+] The {} after a group specifies the NUMBER OF OCCURENCES of the Just PRECEDING GROUP"+ "\n" 
        + "Tip: To Specify Ranges, use COMMAS like: (abc){2,5}" + "\n"  + "Press Enter to see an example!");
        sc.nextLine();
        System.out.println("The Code Below Should Replace all 'n' followed by 3 'z's ");
        System.out.println("[!] Result of running 's.replaceAll(\"nz{3}\", \"!\")' = " + s.replaceAll("nz{3}", "!"));
        Thread.sleep(2000);

        System.out.println("\n[+] The + quantifier means 1 OR MORE occurences of the Just PRECEDING GROUP"+ "\n" + "Press Enter to see an example!");
        sc.nextLine();
        System.out.println("The Code Below Should Replace all 'n' followed by ONE OR MORE 'z's ");
        System.out.println("[!] Result of running 's.replaceAll(\"nz+\", \"!\")' = " + s.replaceAll("nz+", "!"));
        Thread.sleep(2000);

        System.out.println("\n[+] The * quantifier means 0 OR MORE occurences of the Just PRECEDING GROUP"+ "\n" + "Press Enter to see an example!");
        sc.nextLine();
        System.out.println("The Code Below Should Replace all 'n' followed by Any Number (0 or more) Of 'z's ");
        System.out.println("[!] Result of running 's.replaceAll(\"nz*\", \"!\")' = " + s.replaceAll("nz*", "!"));
        Thread.sleep(2000);
        
        System.out.println("\n[+] The ? quantifier means 0 or 1 occurences of the Just PRECEDING GROUP"+ "\n" + "Press Enter to see an example!");
        sc.nextLine();
        System.out.println("The Code Below Should Replace all 'n' followed by 0 or 1 'z' ");
        System.out.println("[!] Result of running 's.replaceAll(\"nz?\", \"!\")' = " + s.replaceAll("nz?", "!"));
        Thread.sleep(2000);

        System.out.println("\nGREEDY vs LAZY Quantifiers :)");
        System.out.println("\n[+] The * and + quantifiers are Greedy Quantifiers, meaning that they'll try to maximize the elements in the Match");
        System.out.println("However, Appending them with a ? makes them Lazy Quantifiers, meaning that they'll report matches with minimum number of elements!");
        System.out.println("Press Enter to see an Example :)");
        sc.nextLine();
        System.out.println("The Code Below Should Replace substrings beginning with 'a' and ending with a 'c' ");
        System.out.println("\nGREEDY Operation:");
        System.out.println("[!] 's.replaceAll(\"a.*c\", \"!\")' = " + s.replaceAll("a.*c", "!"));
        Thread.sleep(2000);
        System.out.println("\nLAZY Operation:");
        System.out.println("[!] 's.replaceAll(\"a.*?c?\", \"!\")' = " + s.replaceAll("a.*?c", "!"));
        Thread.sleep(2000);

    }
}
