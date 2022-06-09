import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Function {
    
    // So they are the embodiment of Function passing  as in python or JS :)
    //
    // Useful to add variation to code when the changes are small but likely to be from one of the given options.
    //
    // Syntax:  
    // 
    // Function< argType, returnType >  name = $Lambda_expression$ ; 


    public static void main(String[] args) {
        List<String> people = new ArrayList<>();

        people.add("Ram");
        people.add("Shyam");
        people.add("Radha");
        people.add("Krishna");
        people.add("Aashraya");

        // Look and have fun ;p

        java.util.function.Function<String, String> reverse = s -> {
            char[] arr = s.toCharArray();
            
            for(int i =0; i<s.length()/2; i++)
            {
                arr[i] = s.charAt(arr.length -1 -i);
                arr[arr.length-i-1] = s.charAt(i);               
            }
        return new String(arr);
        };

        java.util.function.Function<String,String> upperCase = s -> s.toUpperCase();

        System.out.println("Press: \n1. to view Reverse of each person's name." + "\n" + "2. to view UpperCased version of the name");

        int n = new Scanner(System.in).nextInt();

        java.util.function.Function<String,String> selected = n==2 ? upperCase :reverse;

        people.forEach(s -> System.out.println(selected.apply(s)));
    }
}
