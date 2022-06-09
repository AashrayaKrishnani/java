package Files;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadingFileTree {

    static Scanner sc = new Scanner(new BufferedInputStream(System.in));

    public static void main(String[] args) {
        System.out.println("Enter file path to traverse it till the end and print out each directory and file name under it :)");

        String input = sc.nextLine();

        try{

            Path path = Paths.get(input);

            Files.walkFileTree(path, new PrintNames());

        } catch(IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
