package Files;

import com.sun.jdi.PathSearchingVirtualMachine;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFiles extends SimpleFileVisitor<Path> {

    Path dest;
    Path src;

    public CopyFiles(Path src, Path dest ) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("[+] Error Visiting File: " + file.toUri().getPath());
        System.out.println(exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        Path relativizedPath = src.relativize(file);
        Path finalPath = dest.resolve(relativizedPath);

        System.out.println("[+] Copying File: " + file.toUri().getPath() + " to " + finalPath.toUri().getPath() );

        System.out.println("Relativized Path + " + relativizedPath);
        System.out.println("Final Path: " + finalPath);

        Files.copy(file, finalPath);

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        Path relativizedPath = src.relativize(dir);

        Path finalPath = dest.resolve(relativizedPath);

        System.out.println("Relativized Path = " + relativizedPath);
        System.out.println("Final Path: " + finalPath);

        try {
            Files.copy(dir, finalPath);
            System.out.println("\n[+] Copying Directory: " + dir.toUri().getPath() + " to " + dest.toUri().getPath() + dest.relativize(dir).toUri().getPath());
        } catch (IOException e)
        {
            System.out.println("Exception!!\n");
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }


}
