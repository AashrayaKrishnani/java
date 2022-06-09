package Files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DirectoryReading {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("\nEnter the directory path to query it's contents. :D\n");
        String src = sc.nextLine();

        System.out.println("\nEnter filter regEx to view only select type of contents: -" + "\n"
        + "(Leave blank to include all, i.e. \"*\") :)");
        String regEx = sc.nextLine();

        try{
            Path path = Paths.get(src);
            Pattern pattern = Pattern.compile(regEx.isBlank() ? ".*" : regEx);

            DirectoryStream.Filter<Path> filter = (p) -> pattern.matcher(p.toUri().getPath()).find();

            // This was the lambda expression for:-
/*
            DirectoryStream.Filter<Path> filter = new DirectoryStream<Path>(){
                public boolean accept(Path p)
                {
                    return pattern.matcher(p.toUri.getPath());
                }
            };
 */

            DirectoryStream<Path> reader = Files.newDirectoryStream(path, filter);

            System.out.println("The files matching the provided search expression in the directory: " +
                    path.toUri().getPath() + " are :-\n");

            int i = 1;

            for(Path content: reader)
            {
                System.out.println(i++ + ". " + content.toUri().getPath());
            }

        } catch (Exception e){
            System.out.printf("Oopsies! ;p");
            e.printStackTrace();
        }

        System.out.println("\nDone---.\n");
    }

}
