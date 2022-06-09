package Files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class FileAttributes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file/directory path to fetch the metadata :D");
        String src = sc.nextLine();


        try{
            Path path = Paths.get(src);

            System.out.println("Metadata for: " + path.toUri().getPath());

//            System.out.println("Size: " + Files.size(path));
//            System.out.println("Last Modified: " + Files.getLastModifiedTime(path));
//            System.out.println("Owner: " + Files.getOwner(path));

            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);

            System.out.println("Size: " + attributes.size() + " bytes");
            System.out.println("Last Modified: " + attributes.lastModifiedTime());
            System.out.println("Created: " + attributes.creationTime());
            System.out.println("Is Directory: " + attributes.isDirectory());
            System.out.println("Is RegularFile: " + attributes.isRegularFile());

        } catch (Exception e){
            System.out.printf("Oopsies! ;p");
            e.printStackTrace();
        }

        System.out.println("\nDone---.\n");
    }

}
