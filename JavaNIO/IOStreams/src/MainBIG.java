import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainBIG {

    static boolean saveData = true;
    static boolean saveInBinary = true;
    static boolean readInBinary = true;

    private static Locations locations = new Locations("/code/JAVA/JavaIO/IOStreams/locations_big.txt","/code/JAVA/JavaIO/IOStreams/directions_big.txt", saveData, saveInBinary, readInBinary);


    private static Map<String, String> DIRECTIONS = new HashMap<>();
    static {
        DIRECTIONS.put("QUIT","Q");
        DIRECTIONS.put("SOUTH", "S");
        DIRECTIONS.put("EAST", "E");
        DIRECTIONS.put("WEST", "W");
        DIRECTIONS.put("NORTH", "N");
        DIRECTIONS.put("SOUTHWEST", "SW");
        DIRECTIONS.put("SOUTHEAST", "SE");
        DIRECTIONS.put("NORTHWEST", "NW");
        DIRECTIONS.put("NORTHEAST", "NE");
        DIRECTIONS.put("SOUTH-WEST", "SW");
        DIRECTIONS.put("SOUTH-EAST", "SE");
        DIRECTIONS.put("NORTH-WEST", "NW");
        DIRECTIONS.put("NORTH-EAST", "NE");
        DIRECTIONS.put("UP", "U");
        DIRECTIONS.put("DOWN", "D");

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("[+] Welcome to the Adventure Game!!! [-]\n");

        System.out.println("Refer to your current position in the Map,\n" +
                "See the available exits from that location, and finally\n" +
                "Press :-\n"+
                "Q = Quit\n" +
                "U = Up\n" +
                "D = Down\n" +
                "N = Go North\n" +
                "S = Go South\n" +
                "E = Go East\n" +
                "W = Go West\n" +
                "NE = Go NorthEast\n" +
                "NW = Go NorthWest\n" +
                "SE = Go SouthEast\n" +
                "SW = Go SouthWest\n\n" +
                "[*] You can also type the direction in word as 'north' or in a sentence as 'Go north' :D\n\n");

        int loc = 65;

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

                String input = sc.nextLine().toUpperCase();
                String[] temp = input.split(" ");

                String direction = temp[0] ;

                for(String c: DIRECTIONS.values()){
                    if(direction.trim().equalsIgnoreCase(c) && exits.containsKey(c)){
                        direction = c;
                        loc = exits.get(direction);
                        continue;
                    }
                }

                for( String s : temp ){
                    if (DIRECTIONS.containsKey(s))
                        direction = String.valueOf( DIRECTIONS.get(s) );
                }
                if (exits.containsKey(direction))
                    loc = exits.get(direction);
                else {
                        System.out.println("\nKindly enter a valid direction.\n");
                }
            } else {
                System.out.println("\nKindly enter valid Input.\n");
            }
        }

    }



}
