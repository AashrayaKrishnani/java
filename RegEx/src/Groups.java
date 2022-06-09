import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Groups {

    public static void main(String[] args) throws InterruptedException{
        Scanner sc = new Scanner(System.in);

        System.out.println("\n[+] Groups are expressions enclosed within ()." 
        + "\n" + "Their Counting starts from the left with index 1 (as 0 is occupied by the whole parent expression)");
        Thread.sleep(2000);
        System.out.println("Press Enter to see an Example :)");

        sc.nextLine();

        String s = "<html> \n<head> \n<\\head> \n<body> \n<p> Something \n<\\p>  \n<p> Bla Bla\n<\\p>\n<\\body> <\\html>";
        System.out.println("\n[-] Suppose we have the given string: s = \"" + s + "\"");
        Thread.sleep(2000);

        System.out.println("And we Create a matcher by executing the following lines:" + "\n" + 
        "\n" + "String pTagRegex = \"(<p>)(.*?)(<\\p>)\";" + 
        "\n" + "Pattern pTag = new Pattern(pTagRegex);" +
        "\n" + "Matcher pTagMatcher = pTag.matcher(s);\n");
        Thread.sleep(2000);

        String pTagRegex = "(<p>)(.*?)(<\\p>)";
        Pattern pTag = Pattern.compile(pTagRegex);
        Matcher pTagMatcher = pTag.matcher(s);

        System.out.println("[!] And now we run the following code:\n" + "\n" + 
        "\n" + "while(pTagMatcher.find()){" + 
        "\n" + "\t" +" System.out.println(\"\nOccurence Found:\");" +
        "\n" + "\t" +" System.out.println(\"Group 0:\" + pTagMatcher.group(0));" + 
        "\n" + "\t" +" System.out.println(\"Group 1:\" + pTagMatcher.group(1));" +
        "\n" + "\t" +" System.out.println(\"Group 2:\" + pTagMatcher.group(2));" +
        "\n" + "\t" +" System.out.println(\"Group 3:\" + pTagMatcher.group(3)); \n}" );

        Thread.sleep(2000);

        System.out.println("Press Enter to see the output :)");
        sc.nextLine();

        while(pTagMatcher.find())
        {
            System.out.println("\nOccurence Found:");
            System.out.println("Group 0:" + pTagMatcher.group(0));
            System.out.println("Group 1:" + pTagMatcher.group(1));
            System.out.println("Group 2:" + pTagMatcher.group(2));
            System.out.println("Group 3:" + pTagMatcher.group(3));
               
        }
        
        sc.close();
    }
}
