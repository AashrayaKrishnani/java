import java.io.*;
import java.util.*;

// BufferedReader and BufferedWriter are both faster than normal FileWriter and FileReader as they use an 8k (default)
// RAM buffer to process the data and not use the slow DISK memory to read/write each character each time! :D

public class Locations implements Map<Integer, Location> {
    
    static Map<Integer, Location> locations = new HashMap<>();
    static List<Long> endIndex = new ArrayList<>();
    static String locFilePath = "/code/JAVA/JavaIO/IOStreams/Serializable/RandomAccessFile/locations.bin";
    static long startOffset = 0;
    // To read Data :D

    static{

        // Just Indexing the offSet values in 'startOffset' and 'endIndex'
        try(RandomAccessFile locFile = new RandomAccessFile(new File(locFilePath), "r")){

            startOffset = locFile.readLong();
            int count = 0;

            while(locFile.getFilePointer() != startOffset){             // Reading the End Indices
                endIndex.add(count, locFile.readLong());
                count++;
            }

        } catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) throws IOException {

        // To save the data in BINARY 
        
        try(RandomAccessFile locFile = new RandomAccessFile(new File(locFilePath), "rw")){

            int totalLocNum = locations.size();

            locFile.seek((8 + 8*totalLocNum));   // Space for Locations Data offset and Index
            startOffset = locFile.getFilePointer();
            for(Location loc : locations.values()){

                locFile.writeUTF(loc.getDescription());

                Map<String, Integer> exits = loc.getExits();

                for(String direction: exits.keySet()){                  // Writing Exits
                    if(!direction.equalsIgnoreCase("Q"))
                    {
                        locFile.writeInt(exits.get(direction));
                        locFile.writeUTF(direction);
                    }
                }

                endIndex.add(loc.getLocationID(), locFile.getFilePointer() - 1);
            }


            locFile.seek(0);
            locFile.writeLong(startOffset);

            for(int i = 0 ; i < endIndex.size(); i++){
                locFile.writeLong(endIndex.get(i));
            }

         } catch(IOException e)
        {
            e.printStackTrace();
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

    public void loadLocation(int locationID){

        if(!locations.containsKey(locationID))
        {
            try(RandomAccessFile locFile = new RandomAccessFile(new File(locFilePath), "r")){

            long startPos = startOffset;
            long endPos = Locations.endIndex.get(locationID) + 1;        // This is next location's start Index

            if(locationID != 0)
            {
                startPos = endIndex.get(locationID-1) + 1;       // Current start index = prevFile's End Index + 1
            }

            locFile.seek(startPos);

            String description = locFile.readUTF();

            LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();

            while(locFile.getFilePointer() != endPos){
                int destLocID = locFile.readInt();
                String direction = locFile.readUTF();

                System.out.println("Importing exits: " + locationID + direction + destLocID);

                exits.put(direction, destLocID);
            }

            Location newLocation = new Location(locationID, description, exits);
            locations.put(locationID, newLocation);
            System.out.println("Location with locID: " + locationID + " description " + description +" imported");
        } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
