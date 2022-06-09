import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.LinkedHashMap;

// BufferedReader and BufferedWriter are both faster than normal FileWriter and FileReader as they use an 8k (default)
// RAM buffer to process the data and not use the slow DISK memory to read/write each character each time! :D

public class Locations implements Map<Integer, Location> {

    static Map<Integer, Location> locations = new HashMap<>();
    static List<Long> endIndices = new ArrayList<>();
    static Path locFilePath = FileSystems.getDefault().getPath("/code/JAVA/JavaNIO/IOStreams/Serializable/RandomAccessFile/" + "locations.bin");
    static long startOffset = 0;
    // To read Data :D

    static{

//        locations.put(0, new Location(0, "[+] In front of a computer learning Java.", new LinkedHashMap<>()));

//         Just Indexing the offSet values in 'startOffset' and 'endIndex'

        {
            try (RandomAccessFile RAFstream = new RandomAccessFile(locFilePath.toUri().getPath(), "r");

                FileChannel channel = RAFstream.getChannel()) {

                ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES);

                channel.position(0);

                channel.read(longBuffer);
                longBuffer.flip();
                startOffset = longBuffer.getLong(0);
                System.out.println("StartOffSet= " + startOffset);
                longBuffer.clear();

                channel.read(longBuffer);
                longBuffer.flip();
                long count = longBuffer.getLong();
                longBuffer.flip();

                while (count-- > 0) {             // Reading the End Indices
                    longBuffer.clear();
                    channel.read(longBuffer);
                    longBuffer.flip();
                    endIndices.add(longBuffer.getLong());
                }

                System.out.println("Static block clear!");
                System.out.println("The indices array list is as follows :-\n");
                for (int i = 0; i < endIndices.size(); i++) {
                    System.out.print(endIndices.get(i) + " ");
                }
                System.out.println("");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         loadLocation(0);
        loadLocation(1);
//        {
//            try(RandomAccessFile locFile = new RandomAccessFile("/code/JAVA/JavaNIO/IOStreams/Serializable/RandomAccessFile/input.bin", "r")){
//
//                startOffset = locFile.readLong();
//                int count = 0;
//
//                while(locFile.getFilePointer() != startOffset){             // Reading the End Indices
//                    endIndices.add(count, locFile.readLong());
//                    count++;
//                }
//
//                for (int locationID = 1; locationID>0; locationID++)
//                {
//                    long startPos = startOffset;
//                    long endPos = 0;
//                    try {
//                        endPos = Locations.endIndices.get(locationID) + 1;        // This is next location's start Index
//                    }
//                    catch (IndexOutOfBoundsException e){
//                        break;
//                    }
//                    if(locationID != 0)
//                    {
//                        startPos = endIndices.get(locationID-1) + 1;       // Current start index = prevFile's End Index + 1
//                    }
//
//                    locFile.seek(startPos);
//
//                    String description = locFile.readUTF();
//
//                    LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();
//
//                    while(locFile.getFilePointer() != endPos){
//                        int destLocID = locFile.readInt();
//                        String direction = locFile.readUTF();
//
////                        System.out.println("Importing exits: " + locationID + direction + destLocID);
//
//                        exits.put(direction, destLocID);
//                    }
//
//                    Location newLocation = new Location(locationID, description, exits);
//                    locations.put(locationID, newLocation);
//                    System.out.println("Location with locID: " + locationID + " description " + description +" imported");
//
//                }
//
//            }catch(EOFException | IndexOutOfBoundsException exception) {
//            }
//            catch(IOException e)
//            {
//                e.printStackTrace();
//            }
//
//        }
    }
    
    public static void main(String[] args) throws IOException {


        //
//
//        // To save the data in BINARY
//            {
//                try(RandomAccessFile RAFstream = new RandomAccessFile(locFilePath.toUri().getPath(), "rwd");
//                    FileChannel channel = RAFstream.getChannel()){
//
//                    ByteBuffer buffer = ByteBuffer.allocate(500);
//                    buffer.flip();
//                    buffer.clear();
//
//                    long totalLocNum = locations.size();
//
//                    channel.position(16 + 8*totalLocNum - 1);   // Space for Locations Data offset and Index
//                    startOffset = channel.position();
//
//                    for(Location loc : locations.values()){
//
//                        System.out.println("BUFFER " + new String(buffer.array()));
//                        buffer.put(loc.getDescription().getBytes());    // Writing Description in the buffer
//
//                        Map<String, Integer> exits = loc.getExits();
//
//                        for(String direction: exits.keySet()){                  // Writing Exits
//                            if(!direction.equalsIgnoreCase("Q"))
//                            {
//                                // #ChainedPutMethods! Writing the Exit destLoc (int) and the direction code (String)
//                                System.out.println("Buff before: " + new String(buffer.array()));
//                                System.out.println(loc.getLocationID() + ". Adding to buffer: " + exits.get(direction) + " " + direction);
//
//                                buffer.putInt(exits.get(direction)).put(direction.getBytes(StandardCharsets.UTF_8));
//                                System.out.println("Buff after: " + new String(buffer.array()));
//
//                            }
//                        }
//
//                        System.out.println("EndIndices entry: " + loc.getLocationID() + " " +  (startOffset + buffer.position() + 1));
//
//
//                        endIndices.add(loc.getLocationID(), (startOffset + buffer.position() + 1));
//
//                    }
//
//                    buffer.flip();
//                    channel.write(buffer);
//                    System.out.println("final Write: " + new String(buffer.array()));
//                    buffer.flip();
//                    buffer = ByteBuffer.allocate(500);
//                    buffer.flip();
//
//                    channel.position(0);
//                    buffer.clear();
//                    buffer.putLong(startOffset).putLong(totalLocNum);
//
//                    for(int i = 0; i < endIndices.size(); i++){
//                        buffer.putLong(endIndices.get(i));
//                    }
//
//                    buffer.flip();
//                    channel.write(buffer);
//
//
//                } catch(IOException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//
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

    public static void loadLocation(int locationID){

        System.out.println("locID " + locationID + " " + locations.containsKey(locationID) );

        if(!locations.containsKey(locationID))
        {
            try(RandomAccessFile RAFstream = new RandomAccessFile(locFilePath.toUri().getPath(), "rw");
                FileChannel channel = RAFstream.getChannel()){

                long startPos = startOffset;
                long endPos = Locations.endIndices.get(locationID);        // This is next location's start Index

                if( locationID > 0){
                    startPos = endIndices.get(locationID-1);       // Current start index = prevFile's End Index + 1
                }

                ByteBuffer buffer = ByteBuffer.allocate((int) (endPos-startPos));

                channel.position(startPos);
                channel.read(buffer);


                byte[] input = buffer.array();

                LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();

                List<Character> chars = new ArrayList<>();
                int index = 0;

                // Getting description.
                while(index < input.length){
                    try{
                        byte[] tmp = new byte[4];
                        for(int i = index; i <= index+3 ; i++)
                            tmp[i-index] = input[i];
                        int destLoc = Integer.parseInt(new String(tmp));
                        System.out.println(destLoc);
                        index += 4;
                        String direction = String.valueOf(input[index++]);

                        exits.put(direction, destLoc);

                    } catch( ArrayIndexOutOfBoundsException | NumberFormatException e){
                        chars.add((char)input[index++]);
                    }

                }

                String description = "";
                for(char c : chars)
                    description += c ;

                Location newLocation = new Location(locationID, description, exits);
                locations.put(locationID, newLocation);
                System.out.println("Location with locID: " + locationID + " description " + description +" imported");
                System.out.println("Exits:-");
                for(String direction: exits.keySet())
                    System.out.println(direction + " " + exits.get(direction));
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
