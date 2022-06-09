import java.io.*;
import java.util.*;

// BufferedReader and BufferedWriter are both faster than normal FileWriter and FileReader as they use an 8k (default)
// RAM buffer to process the data and not use the slow DISK memory to read/write each character each time! :D

public class Locations implements Map<Integer, Location> {
    
    static Map<Integer, Location> locations = new HashMap<>();

    // To read Data :D

    static{

        // Reading Data Thru ObjectInputStream :D

        try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/code/JAVA/JavaIO/IOStreams/Serializable/locations.bin")))){

            // As Simple as this :D
            try {
                while(true){
                    Location loc = (Location) locFile.readObject();
                    locations.put(loc.getLocationID(), loc);
                }
            }
            catch (EOFException e){
                // Nothing
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) throws IOException {

        // To save the data in BINARY 
        
        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("/code/JAVA/JavaIO/IOStreams/Serializable/locations.bin")))){

            // As Simple as this :D
            try {
                for (Location loc : locations.values())
                    locFile.writeObject(loc);
            }
            catch (EOFException e){
                // Nothing
            }
        } catch(IOException e){
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


}
