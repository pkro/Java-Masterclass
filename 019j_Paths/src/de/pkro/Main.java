package de.pkro;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class Main {

  public static void main(String[] args) {
    Path sourceFile = Paths.get("Examples", "file1.txt");
    Path copyFile = Paths.get("Examples", "file1copy.txt");
    try {
      /*      // Copying files
      Files.copy(sourceFile, copyFile); // exception if file exists
      Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

      // copying directory
      Path sourceDirectory = Paths.get("Examples", "Dir2", "Dir3");
      Path copyDirectory = Paths.get("Examples", "Dir2", "Dir3_copy");
      Files.copy(sourceDirectory, copyDirectory); // copies directory but not contained files

      // move / rename
      Files.move(copyDirectory, Paths.get("Examples", "Dir2", "Dir4") );

      // delete
      Files.delete(Paths.get("Examples", "Dir2", "Dir4"));
      Files.deleteIfExists(Paths.get("Examples", "Dir2", "Dir4"));

      // create files and directories
      Path fileToCreate = Paths.get("Examples", "file2.txt");
      Files.createFile(Paths.get("Examples", "file2.txt"));*/

      // Files.createDirectory(Paths.get("Examples", "new_directory"));
      // creates "nested" under "Examples" and "directories" inside "nested"
      // Files.createDirectories(Paths.get("Examples", "nested", "directories"));

      // File attributes / metadata
      Path filePath = Paths.get("Examples", "Dir1", "file1.txt");
      long size = Files.size(filePath);
      FileTime modified = Files.getLastModifiedTime(filePath);

      System.out.println("File has " + size + " bytes and was last modified " + modified);

      // Get all attributes in one object
      BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
      System.out.println("Last accessed: " + attrs.lastAccessTime() + ", Is a symbolic link: " + attrs.isSymbolicLink());
    } catch (IOException e) {
      e.printStackTrace();
    }


    /*
    Path path = FileSystems.getDefault().getPath("WorkingDirectory.txt");
    printFile(path, true);

    path = Paths.get(".", "WorkingDirectory.txt");
    printFile(path, true);

    path = FileSystems.getDefault().getPath("files", "file1.txt");
    printFile(path, true);

    path = Paths.get(".", "files", "file1.txt");
    printFile(path, true);

    path = Paths.get("/", "home", "pk", "projects", "IdeaProjects", "Java_masterclass", "OutThere.txt");
    printFile(path, true);

    path = Paths.get("/home/pk/projects/IdeaProjects/Java_masterclass/OutThere.txt");
    printFile(path, true);

    path = Paths.get("jfdlkfsd", "doesn't exist");
    // check if file / path exists
    System.out.println("File path exists: " + Files.exists(path));

     */
  }

  private static void printFile(Path path, boolean showPathOnly) {
    System.out.println(path.toAbsolutePath());
    if(showPathOnly) {
      return;
    }
    try (BufferedReader fileReader = Files.newBufferedReader(path)) {
      String line;
      while ((line = fileReader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
