package Files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TempFile {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("\nEnter name for the Temp File to be Created. :D\n");
        String name = sc.nextLine();
        System.out.print("\nEnter extension for the Temp File. \n(Default is '.temp')");
        String ext = sc.nextLine();
        ext = Pattern.compile("\\..*").matcher(ext).find() ? ext : ".temp";

        try{
            Path path = Files.createTempFile(name, ext);

            System.out.println("Created temp file: " + name + ext + ", at path: " + path.toUri().getPath() + ".");

            System.out.println("\nGo and check it out if you want :D" + "\n"
                                + "Return and press enter to allow the program to Delete it! :)");

            sc.nextLine();
            System.out.println("Deleting temp file.");
            Files.delete(path);

            System.out.println("\nDone---.\n");

        } catch (Exception e){
            System.out.printf("Oopsies! Something went wrong I guess :P");

        }

    }

}
