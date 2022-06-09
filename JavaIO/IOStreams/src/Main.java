import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Main {

    static boolean saveData = true;
    static boolean saveInBinary = true;
    static boolean readInBinary = true;

      private static Locations locations = new Locations(saveData, saveInBinary, readInBinary);;

    // private static Map<Integer, Location> locations = new HashMap<>();

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


//        HashMap<String, Integer> tempMap1 = new HashMap<>();
//
//        tempMap1.put("W", 2 );
//        tempMap1.put("E", 3 );
//        tempMap1.put("N", 5 );
//        tempMap1.put("S", 4 );
//    //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap2 = new HashMap<>();
//
//        tempMap2.put("N", 5 );
//    //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap3 = new HashMap<>();
//
//        tempMap3.put("W", 1 );
//    //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap4 = new HashMap<>();
//
//        tempMap4.put("N", 1 );
//        tempMap4.put("W", 2 );
//    //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap5 = new HashMap<>();
//
//        tempMap5.put("S", 1 );
//        tempMap5.put("W", 2 );
//    //    tempMap.put("Q", 0 );
//
//        locations.put(0, new Location(0, "[+] You are sitting in front of a computer learning Java. \nExiting lol ;p", new HashMap<>()));
//        locations.put(1, new Location(1, "[+] You are standing at the end of a road before a small bridge", tempMap1));
//        locations.put(2, new Location(2, "[+] You are at the top of a hill", tempMap2));
//        locations.put(3, new Location(3, "[+] You are inside a building, a party house!", tempMap3));
//        locations.put(4, new Location(4, "[+] You are in a valley beside a stream", tempMap4));
//        locations.put(5, new Location(5, "[+] You are in the forest", tempMap5));
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

            Map<String, Integer> exits = locations.get( loc ).getExits();

            System.out.println( "Current Location:-\n"+ locations.get(loc).getDescription() );

            if ( loc == 0 ) {
                System.out.println("Exiting lol ;p");
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



    // From Adventure Game Challenge.
//
//    public void command() {
//
//        Scanner sc = new Scanner(System.in) ;
//
//        Map<String, String> vocabulary = new HashMap<String, String>() ;
//
//        vocabulary.put("NORTH", "N");
//        vocabulary.put("SOUTH", "S");
//        vocabulary.put("WEST", "W");
//        vocabulary.put("EAST", "E");
//        vocabulary.put("QUIT", "Q");
//
//
//        int loc = 1;
//
//        while (true){
//
//            Map<String, Integer> exits = locations.get( loc ).getExits();
//
//            System.out.println( locations.get(loc).getDescription() + "\n");
//
//            if ( loc == 0 )
//                break;
//
//            System.out.print("Available exits are ");
//
//            for(String exit : locations.get(loc).getExits().keySet() )
//                System.out.print(exit + ", " );
//
//            System.out.println();
//
//            if( 2 / 1 == 2) {
//
//                String input = sc.nextLine().toUpperCase();
//
//                String direction = String.valueOf( input.toUpperCase().charAt(0) ) ;
//
//
//                if(input.length() > 1 ) {
//
//                    String[] temp = input.split(" ");
//
//                    for( String s : temp ){
//
//                        if(vocabulary.containsKey(s) ){
//                            direction = vocabulary.get(s);
//                        }
//
//                    }
//
//                }
//
//                if (exits.containsKey(direction))
//                    loc = exits.get(direction);
//                else
//                    System.out.println("You cannot go in that direction");
//
//            } else {
//                System.out.println("Invalid value entered.");
//            }
//        }
//
//    }
}
