package Files;

import java.nio.file.*;

import java.util.Scanner;
public class FileStores {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("\nWe'll Be printing out all of the available Drives/Volumes/FileStores on" +
                " the Current System.\n" +
                "Press Enter to Start the program :D");

        sc.nextLine();

        try{

            Iterable<FileStore> fileStores = FileSystems.getDefault().getFileStores();

            int i = 1;

            System.out.println("\nFileStores on the current System are as follows: -\n");

            for(FileStore fileStore: fileStores)
            {
                System.out.println(i++ + ". Name = " + fileStore.name() + ", AKA = " + fileStore);
            }

            System.out.println("\nDone! :D\n");

        } catch (Exception e){
            System.out.printf("Oopsies! Something went wrong I guess :P");
        }

    }

}
