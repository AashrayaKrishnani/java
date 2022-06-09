package Files;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CopyFileTree {

    static Scanner sc = new Scanner(new BufferedInputStream(System.in));

    public static void main(String[] args) {

        System.out.println("\n");

        System.out.print("Enter source directory to fully copy from: ");
        String src = sc.nextLine();

        System.out.print("Enter target directory to fully copy to: ");
        String dest = sc.nextLine();

        try{

            Path srcPath = Paths.get(src);
            Path destPath = !dest.isBlank() ? Paths.get(dest) : srcPath ;

            Files.walkFileTree(srcPath, new CopyFiles(srcPath, destPath));

        } catch(IOException e)
        {
            System.out.println("[!] Oopsies!!");
            e.printStackTrace();
        }


    }
}
