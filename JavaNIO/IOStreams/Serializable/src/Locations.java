import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// BufferedReader and BufferedWriter are both faster than normal FileWriter and FileReader as they use an 8k (default)
// RAM buffer to process the data and not use the slow DISK memory to read/write each character each time! :D

public class Locations implements Map<Integer, Location> {
    
    static Map<Integer, Location> locations = new HashMap<>();
    static Path locFilePath = FileSystems.getDefault().getPath("locations.bin");
    
    
    // To read Data :D

    static{

        // Reading Data Thru ObjectInputStream :D

        // Using Files.newInputStream(locfilePath), Here locFilePath is object of 'Path' class.
        // Instead of new FileInputStream(locFilePath),  Here locFilePath is a String 
        
        try(ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locFilePath)))){

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

        // Using Files.newOutputStream(locfilePath), Here locFilePath is object of 'Path' class.
        // Instead of new FileOutputStream(locFilePath),  Here locFilePath is a String 
        
        try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locFilePath)))){

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
