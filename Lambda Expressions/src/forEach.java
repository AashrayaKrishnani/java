import java.util.ArrayList;
import java.util.List;

class forEach{

    // The Iterable interface has this feature as 'forEach' that is same as the 'Enhanced for-loop' but it takes a lambda expression instead! :D
    // It actually takes an instance of the 'Consumer' interface, so we can either use an anonymous class or the good ol' lambdas! :D

    public static void main(String[] args) {
        List<String> people = new ArrayList<>();

        people.add("Ram");
        people.add("Shyam");
        people.add("Radha");
        people.add("Krishna");
        people.add("Aashraya");

    // Instead of Writing:

        // for(String s: people)
        // {
        //     System.out.println("Name: " + s);
        // }

    // Or doing

        // people.forEach(new Consumer<String>() {
        //     @Override
        //     public void accept(String s) {
        //         System.out.println("Name: " + s);   
        //     }
        // });

    // Best to do :D

        people.forEach( s -> System.out.println("Name: " + s));

    }

}