package Files;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Paths {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Reading file in current Directory.");
        printFile(FileSystems.getDefault().getPath("currentDirectory.txt"));

        System.out.println("Reading file in sub Directory: src.");
        printFile(FileSystems.getDefault().getPath("src","subDirectory.txt"));

        System.out.println("Reading file in parent Directory.");
        printFile(FileSystems.getDefault().getPath("../parentDirectory.txt"));
        // Or Use Files.Paths.get(_AbsolutePath_);  :D

        System.out.println("Choose from the following: -\n" +
                "1. Check existence of a file path.\n" +
                "2. Check permission to write.\n" +
                "3. Check permission to read.\n" +
                "4. Check permission to execute.\n");

        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("\nEnter a path (with '/'s and stuff ;p)");
        String input = sc.nextLine();
        Path path = java.nio.file.Paths.get(input);

        boolean result = false;

        switch(n) {
            case 1:
                    result = Files.exists(path);
                    break;
            case 2: result = Files.isWritable(path);
                    break;
            case 3:
                    result = Files.isReadable(path);
                    break;
            case 4: result = Files.isExecutable(path);
                    break;
            default:
                System.out.println("Invalid input");
                System.exit(1);
        }
        System.out.println("Result: " + result + "\n");



    }
    static void printFile(Path path){

        System.out.println("Reading file at path: " + path.toUri().getPath());

        System.out.println("------");
        try{
            List<String> input = Files.readAllLines(path);

            for(String line : input){
                System.out.println(line);
            }
        } catch(IndexOutOfBoundsException | FileNotFoundException e ){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("--------");

    }
}
