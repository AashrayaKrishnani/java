package Files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file/directory path to be deleted");
        String src = sc.nextLine();


        try{
            Path srcPath = Paths.get(src);

            if(Files.exists(srcPath))
                Files.delete(srcPath);
            else
                System.out.println("[+] Don't be cheeky XD");

        } catch (Exception e){
            System.out.printf("Oopsies! ;p");
            e.printStackTrace();
        }

        System.out.println("Done---.\n");
    }
}
