import java.util.ArrayList;
import java.util.List;

public class Predicate{

    // The 'Predicate' functional interface allows us to store a LOGICAL EXPRESSION, or an if-check that we can pass as an argument where needed :)

    public static void main(String[] args) {
        List<String> people = new ArrayList<>();

        people.add("Ram");
        people.add("Shyam");
        people.add("Radha");
        people.add("Krishna");
        people.add("Aashraya");

        // To Print Names of People starting with letter 'R'

    // Instead of Writing:

        // for(String s: people)
        // {
        //     if(s.charAt(0) == 'R')  System.out.println("Name: " + s);
        // }

    // Or doing

        // people.forEach(new Consumer<String>() {
        //     @Override
        //     public void accept(String s) {
        //         if(s.charAt(0)==0) System.out.println("Name: " + s);   
        //     }
        // });

    // Best to do :D

        printPeople(people, (s) -> s.charAt(0) == 'R');
        
        // This makes the printPeople() method SUPER VERSATILE, as we can pass the condition to check on the fly! <3 :D

    }

    public static void printPeople(List<String> people, java.util.function.Predicate<String> predicate)
    {
        people.forEach(s -> 
        { 
            if(predicate.test(s)) System.out.println(s);
        } );
    }


}