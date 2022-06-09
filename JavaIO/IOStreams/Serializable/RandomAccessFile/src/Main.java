import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Main {

      private static Locations locations = new Locations();;

    private static Map<String, Character> directions = new HashMap<>();
    static {
        directions.put("QUIT", 'Q');
        directions.put("SOUTH", 'S');
        directions.put("EAST", 'E');
        directions.put("WEST", 'W');
        directions.put("NORTH", 'N');
        directions.put("QUIT", 'Q');

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Original Initialization code + Game's map details below.
        {
            // adding specific exits to each 'location'
            // Implementing a map like this :-
            //                    ------>  5
            //                    |        ↑
            //                    |        |
            //                    ↓        ↓
            //                    2  <---- 1 <----->  3                North = ↑
            //                    ↑        ↑
            //                    |        |
            //                    |        ↓
            //                    -------  4


 }

        System.out.println("[+] Welcome to the Adventure Game!!! [-]\n");
        System.out.println("Here is the Map! :-\n\n" +
                "------>  5\n" +
                "|        ↑\n" +
                "|        |\n" +
                "↓        ↓\n" +
                "2  <---- 1 <----->  3                North = ↑\n" +
                "↑        ↑\n" +
                "|        |\n" +
                "|        ↓\n" +
                "-------  4\n"+
                "[*] Press 'M' to view the Map.\n\n");
        System.out.println("The Numbers represent the following locations:-\n" +
                "1 = The end of a road before a small bridge" + "\n" +
                "2 = The top of a hill" +"\n" +
                "3 = Inside a building, a party house!" +"\n" +
                "4 = Valley beside a stream" +"\n" +
                "5 = The Forest" +"\n\n");

        System.out.println("Refer to your current position in the Map,\n" +
                "See the available exits from that location, and finally\n" +
                "Press :-\n"+
                "Q = Quit\n" +
                "N = Go North\n" +
                "S = Go South\n" +
                "E = Go East\n" +
                "W = Go West\n\n" +
                "[*] You can also type the direction in word as 'north' or in a sentence as 'Go north' :D\n\n");

        int loc = 1;

        while (true){

            locations.loadLocation(loc);

            Map<String, Integer> exits = locations.get(loc).getExits();

            System.out.println( "Current Location:-\n"+ locations.get(loc).getDescription() );

            if ( loc == 0 ) {
                break;
            }

            System.out.println("\nAvailable exits are :-");

            for(String exit : locations.get(loc).getExits().keySet() )
                System.out.print(exit + " " );

            System.out.println();

            if(sc.hasNext()) {
                // String direction = String.valueOf( sc.nextLine().toUpperCase().charAt(0) ) ;

                String input = sc.nextLine().toUpperCase();

                if(input.trim().charAt(0) == 'M')
                    System.out.println("Here is the Map! :-\n\n" +
                            "------>  5\n" +
                            "|        ↑\n" +
                            "|        |\n" +
                            "↓        ↓\n" +
                            "2  <---- 1 <----->  3                North = ↑\n" +
                            "↑        ↑\n" +
                            "|        |\n" +
                            "|        ↓\n" +
                            "-------  4\n"+
                            "[*] Press 'M' to view the Map.\n\n");;

                String direction = String.valueOf( input.toUpperCase().charAt(0) ) ;

                boolean isValid = false;

                for(char c: directions.values()){
                    if(direction.equalsIgnoreCase(String.valueOf(c)))
                        isValid = true;
                }

                String[] temp = input.split(" ");

                for( String s : temp ){
                    if (directions.containsKey(s))
                        direction = String.valueOf( directions.get(s) );
                }
                if (exits.containsKey(direction))
                    loc = exits.get(direction);
                else {
                    if(isValid)
                        System.out.println("\nYou cannot go in that direction!\n");
                    else
                        System.out.println("\nKindly enter a valid direction.\n");
                }
            } else {
                System.out.println("\nInvalid value entered.\n");
            }
        }

    }

}
