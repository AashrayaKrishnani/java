import java.io.*;
import java.util.*;

// BufferedReader and BufferedWriter are both faster than normal FileWriter and FileReader as they use an 8k (default)
// RAM buffer to process the data and not use the slow DISK memory to read/write each character each time! :D

public class Locations implements Map<Integer, Location> {

    static String locFilePath = "/code/JAVA/JavaIO/IOStreams/locFile.txt";
    static String exitsFilePath = "/code/JAVA/JavaIO/IOStreams/exitsFile.txt";
    static boolean verbose = false;
    static boolean saveData = false;
    static boolean saveInBinary = false;
    static boolean readInBinary = false;


    public Locations(boolean saveData, boolean saveInBinary, boolean readInBinary) {

        Locations.saveData = saveData;
        Locations.saveInBinary = saveInBinary;
        Locations.readInBinary = readInBinary;

        try {
            Locations.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Locations(String locFilePath, String exitsFilePath, boolean saveData, boolean saveInBinary, boolean readInBinary)  {
        Locations.locFilePath = locFilePath;
        Locations.exitsFilePath = exitsFilePath;
        Locations.saveData = saveData;
        Locations.saveInBinary = saveInBinary;
        Locations.readInBinary = readInBinary;

        try {
            Locations.main(null);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    static Map<Integer, Location> locations = new HashMap<>();

    private static void main(String[] args) throws IOException {

//         Initializing by reading data from TEXT files using Scanner and FileReader.
        if(!readInBinary)
        {
            try(Scanner locFile = new Scanner(new BufferedReader( new FileReader(locFilePath) )) ;
                Scanner exitsFile = new Scanner(new BufferedReader( new FileReader(exitsFilePath) )))
            {                               // BufferedReader uses an 8Kb buffer (default), hence is much faster!!!
                locFile.useDelimiter(",");
                exitsFile.useDelimiter(",");
                // Reading locations.
                while(locFile.hasNextLine()){
                    int locID = locFile.nextInt();
                    locFile.skip(locFile.delimiter());
                    String description = locFile.nextLine();    // Using nextLine() to only read till LINE END

                    LinkedHashMap<String, Integer> temp = new LinkedHashMap<>();

                    Location location = new Location(locID, description, temp);
                    locations.put(locID, location);
                    if(verbose)
                        System.out.println("Imported location with ID: " + locID + " description: " + description);

                }

                while(exitsFile.hasNextLine())                      // Reading and adding exits :D
                {
                    int locID = exitsFile.nextInt();
                    exitsFile.skip(exitsFile.delimiter());
                    String direction = exitsFile.next().trim();
                    exitsFile.skip(exitsFile.delimiter());
                    int destLocID = Integer.parseInt( exitsFile.nextLine().trim() );

                    locations.get(locID).addExit(direction, destLocID);
                    if(verbose)
                        System.out.println("Imported Exit to location ID: " + locID + " with direction: " +
                            direction + " and destLocID: " + destLocID);
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            // Adding '.bin' extensions to locFilePath and exitsFilePath
            String temp[] = locFilePath.split("[.]");        // Regex ke pachde XD
            locFilePath = temp[0] + ".bin";
            temp = exitsFilePath.split("[.]");
            exitsFilePath = temp[0] + ".bin";

            // Reading data in Binary

            try(DataInputStream locFile = new DataInputStream(new BufferedInputStream( new FileInputStream(locFilePath))))
            {                               // BufferedReader uses an 8Kb buffer (default), hence is much faster!!!
                // Reading locations
                while(true){                    // Wait for EOF exception to automatically stop the loop XD
                    int locID = locFile.readInt();
                    String description = locFile.readUTF();

                    LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();

                    Location location = new Location(locID, description, exits);
                    locations.put(locID, location);
                    if(verbose)
                        System.out.println("Imported location with ID: " + locID + " description: " + description);
                }
            }       // It throws EndOfFile (EOF) exception as it reaches the file end :D
            catch (EOFException e) {
                System.out.println("Reached EndOfFile in location.bin.");
            }

            try(DataInputStream exitsFile = new DataInputStream(new BufferedInputStream( new FileInputStream(exitsFilePath)))){

                boolean eof = false;     // Much tidier way of dealing with EndOfFile :D

                while(!eof)                      // Reading and adding exits :D
                {
                    try{
                        int locID = exitsFile.readInt();
                        String direction = exitsFile.readUTF().trim();
                        int destLocID = exitsFile.readInt();

                        locations.get(locID).addExit(direction, destLocID);
                        if(verbose)
                            System.out.println("Imported Exit to location ID: " + locID + " with direction: " +
                                    direction + " and destLocID: " + destLocID);
                    } catch (EOFException e){
                        eof = true;
                    }
                }
            }
            catch(IOException e){
            e.printStackTrace();
            }

        }


        if(saveData && !saveInBinary){

            // Using Try with resources :D  [ Java 7 and + ]
            // *Also writing the exits held by each location on the disk as well, in the exitsFile.txt*
            // This automatically closes the Writer Stream (locFile.close()), irrespective of error occurrence :D

            try (BufferedWriter locFile = new BufferedWriter(new FileWriter(locFilePath));
                 BufferedWriter exitsFile = new  BufferedWriter(new FileWriter(exitsFilePath))) {

                for (Location loc : locations.values()) {
                    locFile.write(loc.getLocationID() + "," +
                            loc.getDescription() + "\n");


                    for (String exit : loc.getExits().keySet()) {
                        if(!exit.equalsIgnoreCase("Q"))         // No Need to Write Exits of Q, as they are same for all locations :D
                            exitsFile.write(loc.getLocationID() + "," + exit + "," + loc.getExits().get(exit) + "\n");
                    }

                }
            }
        }

        if(saveData && saveInBinary){

            // Adding '.bin' extensions to locFilePath and exitsFilePath
            String temp[] = locFilePath.split("[.]");        // Regex ke pachde XD
            locFilePath = temp[0] + ".bin";
            temp = exitsFilePath.split("[.]");
            exitsFilePath = temp[0] + ".bin";


            // Using DataInputStream and DataOutputStream instead of FileReader and FileWriter class :D
            // *Also writing the exits held by each location on the disk as well, in the exitsFile.txt.

            try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(locFilePath)));
                DataOutputStream exitsFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(exitsFilePath)))) {

                for (Location loc : locations.values()) {
                    locFile.writeInt(loc.getLocationID());
                    locFile.writeUTF(loc.getDescription());  // Writes String in UTF [Unicode Transformation Format]


                    for (String exit : loc.getExits().keySet()) {
                        if(!exit.equalsIgnoreCase("Q")){ // No Need to Write Exits of Q, as they are same for all locations :D
                            exitsFile.writeInt(loc.getLocationID());
                            exitsFile.writeUTF(exit);
                            exitsFile.writeInt(loc.getExits().get(exit));
                        }

                    }

                }
            }
        }

    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }

    //         Manually inputting the values
    {
        //
//        HashMap<String, Integer> tempMap1 = new HashMap<>();
//
//        tempMap1.put("W", 2 );
//        tempMap1.put("E", 3 );
//        tempMap1.put("N", 5 );
//        tempMap1.put("S", 4 );
//        //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap2 = new HashMap<>();
//
//        tempMap2.put("N", 5 );
//        //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap3 = new HashMap<>();
//
//        tempMap3.put("W", 1 );
//        //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap4 = new HashMap<>();
//
//        tempMap4.put("N", 1 );
//        tempMap4.put("W", 2 );
//        //    tempMap.put("Q", 0 );
//
//        HashMap<String, Integer> tempMap5 = new HashMap<>();
//
//        tempMap5.put("S", 1 );
//        tempMap5.put("W", 2 );
//        //    tempMap.put("Q", 0 );
//
//        locations.put(0, new Location(0, "[+] In front of a computer learning Java.", new HashMap<>()));
//        locations.put(1, new Location(1, "[+] At the end of a road before a small bridge", tempMap1));
//        locations.put(2, new Location(2, "[+] At the top of a hill", tempMap2));
//        locations.put(3, new Location(3, "[+] Inside a building, a party house!", tempMap3));
//        locations.put(4, new Location(4, "[+] In a valley beside a stream", tempMap4));
//        locations.put(5, new Location(5, "[+] In the forest", tempMap5));
    }


    // Using Try, catch and finally.
    {
        // The 'finally' block in Exceptions is executed regardless of occurrence of any exception.
        //
        // Here it is important to have locFile.close() in a finally block as an open file can cause data corruption
        // and also lead to the file being locked so no processes can access it.

        // To Write Data of all locations into a file :D (Using TryCatch to catch IOException)

//            FileWriter locFile;

//        try {
//            locFile = new FileWriter("locFile.txt"); // Opens FileWriter in current location :)
//            for(Location loc: locations.values()){
//                locFile.write("Index = <" + loc.getLocationID() + ">\n"
//                        + "Description = <" + loc.getDescription() + ">\n\n");
//            }
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        finally{
//            try {
//                if( locFile != null)
//                    locFile.close();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        //  Since we already used 'throws IOException' in main(), we can simply write our code :D
    }

    // Simply Writing the code as main already throws IOException :D
    {
        //            FileWriter locFile;
        //            locFile = new FileWriter("locFile.txt"); // Opens FileWriter in current location :)
//            for (Location loc : locations.values())
//                locFile.write("Index = <" + loc.getLocationID() + ">\n"
//                        + "Description = <" + loc.getDescription() + ">\n\n");
//            locFile.close();
    }


}
