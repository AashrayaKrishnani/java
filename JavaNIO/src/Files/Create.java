package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Create {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file/directory path to be created");
        String src = sc.nextLine();


        try{
            Path path = Paths.get(src);

            try{
                Files.createFile(path); // Trying to create a file with the given path!
            }
            catch (IOException e){
                Files.createDirectories(path);  // Creating directories if file path isn't suitable!
            }


        } catch (Exception e){
            System.out.printf("Oopsies! ;p");
            e.printStackTrace();
        }

        System.out.println("Done---.\n");
    }
}
