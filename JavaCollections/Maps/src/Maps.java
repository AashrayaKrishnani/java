import java.util.HashMap;
import java.util.Map;

public class Maps {

    public static void main(String[] args) {

      Map<String, String> languages = new HashMap<>();

        if( languages.containsKey("Java") ) {
            System.out.println("Java Already Exists ;D");
        }
        else {
            languages.put("Java", "I am learning JAVA! :D");
            System.out.println("Successfully added Java into languages! :D");
        }

      languages.put("Python", "An interpreted, object-oriented, high-level programming language with dynamic semantics.");
      languages.put("Algol", "An algorithmic language");
      System.out.println( languages.put("BASIC", "Beginners All Purpose Symbolic Instuction Code") ) ;
      System.out.println( languages.put("Lisp", "Therein lies madeness") );

      System.out.println("----------------");

      System.out.println( languages.get("Java") );
      if( languages.containsKey("Java") ) {
            System.out.println("Java Already Exists ;D");
        }
      else {
            System.out.print("Previous value of Java : ");
            System.out.println( languages.put("Java", "I am learning JAVA! :D") );
            System.out.println("Successfully updated Java into languages! :D");
        }
      System.out.println( languages.get("Java") );

      System.out.println("\n Printing all values in 'languages'.\n");
      for( String key : languages.keySet() ){
          System.out.println( key + " - " + languages.get(key));
      }

        System.out.println("\n Now Removing entries using remove() function.");

        System.out.println("Removing Lisp using remove(Lisp)") ;
        languages.remove("Lisp");

        if(languages.remove("Java", "A coding language") )
            System.out.println("Succefully deleted Java from languages.");
        else
            System.out.println("Did not delete Java, key/value pair entered in remve() not matched.");

        System.out.println("\n Printing all values in 'languages'.\n");
        for( String key : languages.keySet() ){
            System.out.println( key + " - " + languages.get(key));
        }

      // Now Using replace() function :-

        System.out.println( "\nOutput of languages.replace(\"Lisp\", \"Some programming language!\") "+ languages.replace("Lisp", "Some programming language!") ) ;

        if( languages.replace("Algol", "An algorithmic language", "[+] Value of Algol Changedddd by replace()")) {

            System.out.println("\n Printing all values in 'languages'.\n");
            for( String key : languages.keySet() ){
                System.out.println( key + " - " + languages.get(key));
            }

        }

        System.out.println();

        // Creating copy of languages.
        // HashMaps are copied as DEEP COPIES.

        HashMap<String, String> copy = new HashMap<>(languages);

        copy.put("Java", "Value Of Java Changed in Copy Of languages.");

        if (  languages.get("Java").equals( copy.get("Java") )){
            System.out.println("copy is a SHALLOW COPY of languages.");
        } else {
            System.out.println("copy is a DEEP COPY of languages.");
        }

    }

}
