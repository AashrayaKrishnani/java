import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiFunction {
    // Now the BiFunction does the same but has the ability to  take in two arguments! :D

    public static void main(String[] args) {
        List<String> people = new ArrayList<>();

        people.add("Ram");
        people.add("Shyam");
        people.add("Radha");
        people.add("Krishna");
        people.add("Aashraya");

        // To Print Concatenated names of any two people from the list.

        Random random = new Random();

        final java.util.function.BiFunction<String, String, String> joinTwoPeople = (s1, s2) -> s1 + " " + s2;

        people.forEach(s -> System.out.println( joinTwoPeople.apply(s, people.get(random.nextInt(people.size()))) ) );

    }

}
