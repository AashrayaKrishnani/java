package Files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Copy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file path to be copied: ");
        String src = sc.nextLine();
        
        System.out.print("Enter the file path to paste at: ");
        String dest = sc.nextLine();
        
        try{
            Path srcPath = Paths.get(src);
            Path destPath = Paths.get(dest);

            if(Files.isReadable(srcPath))
                Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            else
                System.out.println("[+] Unable to read src. :(");

        } catch (Exception e){
            System.out.printf("Oopsies! ;p");
            e.printStackTrace();
        }

        System.out.println("Done---.\n");
    }
}
