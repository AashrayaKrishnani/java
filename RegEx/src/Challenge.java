import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {
    public static void main(String[] args) {
        
        String regex, challenge;
        Pattern pattern;
        Matcher matcher;

        // Should match substrings starting with a SERIES of Letters, followed by a '.' and then a series of Numbers.
        // Use groups to print the *Numbers* that match with the forementioned pattern
        System.out.println("\n\nChallenge 8:\n");
        challenge = "abcd.135uvqz.7tzik.999";
        regex = ".*?([a-zA-Z]*)(\\.)(\\d*).*?";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(challenge);

        while(matcher.find())
        {
            System.out.println(matcher.group(3));
        }
        
        // Print the *Start* and *End* indices of the *number* parts that match, and make sure that the INDICES BE INCLUSIVE!
        System.out.println("\n\nChallenge 10:\n");
        matcher = pattern.matcher(challenge);

        while(matcher.find())
        {
            // Using matcher.start(int group) and matcher.end(int group)
            System.out.println("Start: " + matcher.start(3) + ", End: " + (matcher.end(3)-1));    // The End Index returned is Exclusive by default.
        }

        // 5-digit pincodes like "11111", "13123" should match
        // 5-digit pincodes optionally followed by '-' and another 4 Digits should ALSO MATCH. Example: "13123-3132" 
        System.out.println("\n\nChallenge 14:\n");
        regex = "^\\d{5}(-\\d{4}){0,1}$";

        System.out.println("11111".matches(regex));
        System.out.println("11111-3123".matches(regex));

    }
}
