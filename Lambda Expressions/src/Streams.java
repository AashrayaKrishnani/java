import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams
 */
public class Streams {

    public static void main(String[] args) {
        
        List<String> bingoNumbers = Arrays.asList(
            "A31", "A4", "A22",
            "H31", "H415", "H951", "H34",
            "K41", "K4111", "K4411",
            "Z1", "Z76", "Z6",
            "G4", "G93", "G34"
        );

// Now Suppose we Want to print numbers starting with 'H', and in Ascending Order

    // Normal Zindagi

        // List<String> list = new ArrayList<>();    

        // bingoNumbers.forEach(s -> {
        //     if(s.startsWith("H"))
        //         list.add(s);
        // });

        // list.sort((s1,s2) -> s1.compareTo(s2));
        
        // list.forEach(System.out::println);  // Using METHOD REFERENCING here, see the notes for more info :D

    // Mentos Zindagi - Power Of Streams.

           bingoNumbers.stream().
                                map(String::toUpperCase).
                                filter(s -> s.startsWith("H")).
                                sorted().
                                forEach(System.out::println);

            Stream<String> ioStream = Stream.of("O31", "O415", "I951", "I34", "I23");
            Stream<String> inStream = Stream.of("N31", "N415", "I951", "N34", "I23");
            Stream<String> ionStream = Stream.concat(ioStream, inStream);   // However this contains Duplicate items

            System.out.println(ionStream.count());

            // Fixing this
            System.out.println(ionStream.distinct().count());

            // Printing out the numbers as they are seperated by .distict().
            ionStream.distinct().peek(System.out::println).close();

    // .flatMap()

            Employee jack = new Employee("Jack", 30);
            Employee reak = new Employee("Reak", 10);
            Employee lazy = new Employee("Layz", 50);
            Employee snow = new Employee("Snow", 20);

            Department hr = new Department("HR");
            Department accnt = new Department("Accounting");

            hr.addEmployee(jack);
            hr.addEmployee(lazy);
            hr.addEmployee(snow);
            accnt.addEmployee(reak);
            accnt.addEmployee(lazy);

            List<Department> departments = new ArrayList<>();
            departments.add(hr);
            departments.add(accnt);

            // To Print names of all Employees working in all departments
            departments.stream()
            .flatMap(d -> d.getEmployees().stream())
            .distinct()
            .forEach(System.out::println);

            // Or use the Stream.collect() to store the result in a variable :)

            List<Employee> employees = departments.stream()
            .flatMap(d -> d.getEmployees().stream())
            .distinct()
            .collect(Collectors.toList());
            
            // To Group them by same age.

            Map<Integer, List<Employee>> map = departments.stream()
            .flatMap(d -> d.getEmployees().stream())
            .collect(Collectors.groupingBy(e -> e.getAge()));
    
            // To get the Youngest Employee
            
            Employee youngest = departments.stream()
            .flatMap(d -> d.getEmployees().stream())
            .reduce( (e1,e2) -> (e1.getAge() < e2.getAge() ? e1 : e2))
            .get();
    }
}