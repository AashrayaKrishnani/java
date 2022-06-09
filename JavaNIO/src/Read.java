import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Read {
    public static void main(String[] args) throws IOException {

        System.out.println("Going to read from text.bin what we wrote :D\n");

        RandomAccessFile raf = new RandomAccessFile("/code/JAVA/JavaNIO/src/text.bin", "rwd");
        FileChannel channel = raf.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate("Writing This into text.bin as bytes.\n".getBytes().length);
        int bytesRead = channel.read(buffer);

        System.out.println("Number of bytes read = " + bytesRead);
        System.out.println("What was read: " + new String(buffer.array()) + "\n");

        // To read the integers!
        ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

        bytesRead = channel.read(intBuffer);
        // intBuffer pointer is currently at the end of buffer as it has moved 4bytes to write the int.
        System.out.println("Number of bytes read = " + bytesRead);
        // The getInt(index) is absolute reading function as it makes pointer read from given index and not current pos.
        System.out.println("What was read: " + intBuffer.getInt(0) + "\n");
        intBuffer.flip();   // Resets pointer pos to 0 so we may read the next val :D
    }
}
