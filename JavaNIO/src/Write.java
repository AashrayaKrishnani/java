import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Write {

    // JavaNIO uses CHANNELS and BLOCKS(buffers) to write Data
    //
    // Channels are the file stream/source where we write/read from!
    // Blocks are essentially buffers that are used to write/read data :D

    public static void main(String[] args) throws IOException {

        // IO through Files class
        {
            Path filePath = FileSystems.getDefault().getPath("/code/JAVA/JavaNIO/src/text.txt");
            int count = 0;

            try{
                // Super Cool way to quickly read all lines from a File :D
                List<String> strings = Files.readAllLines(filePath);
                count = Integer.parseInt(strings.get(strings.size()-1).substring(0, 1));
            } catch (IOException | IndexOutOfBoundsException exception )
            {

            }
//            for(String string : strings){
//                System.out.println(string);
//            }


            // This will write the data, but create a new file altogether by DEFAULT
            // Files.write(filePath, "Namaste! :D".getBytes("UTF-8"));

            // And yes, Files.write() requires input in BYTES!! :D
            Files.write(filePath, (++count + ". Namaste! :D\n").getBytes("UTF-8"), StandardOpenOption.APPEND);
            // This will make sure to append, and not create from scratch! :D
        }

        // Channels and Buffers.
        {
            // We can get a channel from any FileStream Object (FileInputStream, FileOutputStream, etc.)
            // REMEMBER: The channel will only have the ability that it's parent does.
            // Eg. Channel obtained from an FileInputStream object will only have READ permissions :D

            FileOutputStream stream = new FileOutputStream("/code/JAVA/JavaNIO/src/text.bin");
            FileChannel outChannel = stream.getChannel();

            byte[] byteArray = "Writing This into text.bin as bytes.\n".getBytes();

            // The ByteBuffer.wrap() also sets the buffer's pointer position to zero :D
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);     // This allots the space for byteArray to buffer.

            // Note that now buffer and byteArray share the same memory!! So changes will reflect in both!

            int bytesWritten = outChannel.write(buffer);

            // Since pointer is at zero, so when we write contents of a buffer it starts from 0 and writes throughout!
            System.out.println("\n[+] Number of Bytes Written to text.bin = " + bytesWritten);

            // Making a buffer to store one Int :D
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            intBuffer.putInt(22);
            // But doing this makes the buffer pointer go 4 bytes forward as it writes the int to the buffer

            bytesWritten = outChannel.write(intBuffer);
            System.out.println("\n[+] Value of bytesWritten without resetting pointer = " + bytesWritten);

            // The ByteBuffer.flip() resets the pointer's location to 0 so then when we write, it actually writes! XD
            bytesWritten = outChannel.write(intBuffer.flip());
            System.out.println("\n[+] Value of bytesWritten after resetting pointer = " + bytesWritten);

            // Also if we don't ensure enough space ahead of the pointer in the buffer before giving/writing/reading value
            // See what happens :D
            intBuffer.putInt(13);   // But pointer is not at 0. Have written to file the int, but haven't called flip().

        }

    }

}
