//
// Bulk operations are particularly well suited to Sets; when applied, they perform standard set-algebraic operations. Suppose s1 and s2 are sets. Here's what bulk operations do:
//
//      - s1.containsAll(s2) — returns true if s2 is a subset of s1.
//      (s2 is a subset of s1 if set s1 contains all of the elements in s2.)
//      - s1.addAll(s2) — transforms s1 into the union of s1 and s2.
//      (The union of two sets is the set containing all of the elements contained in either set.)
//      - s1.retainAll(s2) — transforms s1 into the intersection of s1 and s2.
//      (The intersection of two sets is the set containing only the elements common to both sets.)
//      - s1.removeAll(s2) — transforms s1 into the (asymmetric) set difference of s1 and s2.
//      (For example, the set difference of s1 minus s2 is the set containing all of the elements found in s1 but not in s2.)
//

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetMain {

    private static Set<Integer> squares = new HashSet<>();
    private static Set<Integer> cubes = new HashSet<>();
    private static Set<String> words = new HashSet<>();

    public static void main(String[] args) {

        // Initializing squares and cubes
        {
            System.out.println("\n Initializing squares and cubes of digits from 1 to 100.");

            for(int i = 1 ; i<100 ; i++){
                squares.add( i*i );
                cubes.add( i*i*i);
            }

        }

        // Performing Bulk Operations
        {
            // Bulk operations are destructive, i.e., original information in the set gets modified.

            // Union of sets.
            {
                Set<Integer> union = new HashSet<>(squares); // Hence a deep copy of squares is made.
                union.addAll(cubes); // Performs the algebraic union operation in sets! :D

                System.out.println("\nSize of Set 'squares' = " + squares.size());
                System.out.println("Size of Set 'cubes' = " + cubes.size());
                System.out.println("Size of Set 'union' = " + union.size()); // Repeated elements are not added! :D
            }

            // Intersection of sets.
            {
                Set<Integer> intersection = new HashSet<>(squares); // Hence a deep copy of squares is made.
                intersection.retainAll(cubes); // Performs the algebraic intersection operation in sets! :D

                System.out.println("\nSize of Set 'intersection' = " + intersection.size()); // Repeated elements are not added! :D

                System.out.println("\nElements in set 'intersection' are :-\n");

                for(Integer i : intersection){
                    System.out.println("\t Element '" + i + "' is square of " +
                            ((int)(Math.sqrt(i))) + " and is cube of " + ((int)(Math.cbrt(i)))  );
                }

            }

            // Initializing String[] words using Arrays.asList() function ;p
            {
                String sentence = "The quick brown fox jumps over the lazy dog!";

                String[] arrayWords = sentence.split(" ");

                words.addAll(Arrays.asList(arrayWords));

                System.out.println("\nElements in Set 'words' are :-\n");

                for (String s : words) {
                    System.out.println("\t" + s);
                }
            }


            // Asymmetrical, Symmetrical Difference,
            // And to check if Subset or not :D
            {

                Set<String> nature = new HashSet<>();
                Set<String> divine = new HashSet<>();

                String[] natureWords = { "all", "nature", "is", "but", "art", "unknown", "to", "thee"};
                String[] divineWords = { "to", "err", "is", "human", "to", "forgive", "divine"};

                nature.addAll( Arrays.asList(natureWords) );
                divine.addAll( Arrays.asList(divineWords) );

                // Asymmetrical Difference
                {
                    // Asymmetrical difference basically means that if two sets are there such that they have
                    // some elements in common, then asymmetrical difference as A-B = A - (A ∩ B) :D
                    // Meaning - All elements of 'A' except for those with intersection in 'B'

                    // 'divine' set - 'nature' set = 'diff1' set
                    Set<String> diff1 = new HashSet<>(nature);
                    diff1.removeAll(divine);

                    // 'nature' set - 'divine' set = 'diff2' set

                    Set<String> diff2 = new HashSet<>(divine);
                    diff2.removeAll(nature);


                    // Printing Set Values.
                    {
                        printSet("nature", nature);

                        printSet("divine", divine);

                        System.out.println("\n-----------------------------\nAsymmetrical Difference.");


                        System.out.println("\nSet 'nature' - Set 'divine' = Set 'diff1'");
                        printSet("diff1", diff1);

                        System.out.println("\nSet 'divine' - Set 'nature' = Set 'diff2'");
                        printSet("diff2", diff2);
                    }
                }

                // Creating their Union.
                Set<String> union = new HashSet<>(nature);
                union.addAll(divine);

                // Creating their Intersection.
                Set<String> intersection = new HashSet<>(nature);
                intersection.retainAll(divine);

                // Symmetrical Difference
                {
                    // Symmetrical difference basically means that if two sets are there such that they have
                    // some elements in common, then Symmetrical difference between them is = (A ∪ B) - (A ∩ B)
                    // Meaning - All elements of 'A' and 'B' except for those that we common in them!

                    // Java doesn't provide a direct function for it, because we can simply do this :-
                    // The Union of those sets, and then subtract their intersection!

                    // Finally the Symmetrical Difference.
                    Set<String> symmetricalDifference = new HashSet<>(union);
                    symmetricalDifference.removeAll(intersection);

                    // Printing Set Values.
                    {

                        System.out.println("\n-----------------------------\nSymmetrical Difference :-");

                        System.out.println("\nSet 'nature' UNION Set 'divine' = Set 'union'");
                        printSet("union", union);

                        System.out.println("\nSet 'nature' INTERSECTION Set 'divine' = Set 'intersection'");
                        printSet("intersection", intersection);

                        System.out.println("\nSet 'nature' SYMMETRICAL DIFFERENCE Set 'divine' = Set 'symmetricalDifference'");
                        printSet("symmetricalDifference", symmetricalDifference);
                    }
                }

                // To Check if one is a subset of the other :D
                {
                    System.out.println("\n--------------------------------\n" +
                            "Subset checking using .containsAll() :-");

                    System.out.println("\n[-] Checking if 'divine' is a subset of 'nature' or not :-");
                    System.out.println("Result = " + nature.containsAll(divine));

                    System.out.println("\n[-] Checking if 'nature' is a subset of 'divine' or not :-");
                    System.out.println("Result = " + divine.containsAll(nature));

                    System.out.println("\n[-] Checking if 'intersection' is a subset of 'nature' or not :-");
                    System.out.println("Result = " + nature.containsAll(intersection));

                    System.out.println("\n[-] Checking if 'intersection' is a subset of 'divine' or not :-");
                    System.out.println("Result = " + divine.containsAll(intersection));

                    System.out.println("\n[-] Checking if 'union' is a subset of 'nature' or not :-");
                    System.out.println("Result = " + nature.containsAll(union));

                    System.out.println("\n[-] Checking if 'nature' is a subset of 'union' or not :-");
                    System.out.println("Result = " + union.containsAll(nature));

                }

            }



        }

    }

    private static void printSet(String nameOfSet, @NotNull Set<? extends Object> set){

        System.out.println("\n[+] Set '" + nameOfSet + "' :-\n");

        System.out.print("\t");

        for(Object o : set){
            System.out.print(o + "  ");
        }

        System.out.println();

    }

}
