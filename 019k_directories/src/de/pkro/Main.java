package de.pkro;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {

  public static void main(String[] args) {

    DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
          return Files.isRegularFile(entry);
        }
    };
    // Same as Lambda:
    DirectoryStream.Filter<Path> lambdaFilter = p -> Files.isRegularFile(p);
    // Or even shorter with Method Reference:
    DirectoryStream.Filter<Path> lambdaFilterWithRef = Files::isRegularFile;


    Path directory = Paths.get("FileTree", "Dir2");

    // getting the OS separator (not really needed with Paths.get() ?)
    String separator = File.separator; // or FileSystems.getDefault().getSeparator();


    // with glob
    //try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, "*.{dat,txt}")) {
    // with filter
    try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
      for (Path path : contents) {
        System.out.println(path.toString());
      }
    } catch (IOException | DirectoryIteratorException e) {
      e.printStackTrace();
    }

    // create a temp file in the system's temp direcory
    try {
      Path tempFile = Files.createTempFile("myapp", ".appext");
      System.out.println(tempFile.toAbsolutePath());
      // -> e.g. /tmp/myapp17573002425864035770.appext
    } catch (IOException e) {
      e.printStackTrace();
    }

    // get file stores
    Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
    for(FileStore store: stores) {
      System.out.println(store);
    }

    // get root drives (rarely needed)
    Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
    for(Path path:rootPaths) {
      System.out.println(path); // in linux just "/", in windows etc "c:", "d:" etc
    }

    // Walking a file tree
    System.out.println("Walking file tree--------------------------");
    Path startPath = Paths.get("FileTree", "Dir2");
    try {
      Files.walkFileTree(startPath, new PrintNames()); // also see overloads
    } catch (Exception e) {
      e.printStackTrace();
    }

    // copying directory structures
    System.out.println("copying directory structures");
    Path fromPath = Paths.get("FileTree", "Dir2");
    Path toPath = Paths.get("FileTree", "Dir2_copy");
    try {
      Files.walkFileTree(fromPath, new CopyFiles(fromPath, toPath));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // mapping IO and NIO (mostly for converting / updating older code)
    // NIO Path is mostly preferable to IO File

    // convert IO File to NIO Path
    File file = new File("/Example/file.txt");
    Path convertedPath = file.toPath();

    File parent = new File("/Examples");
    File resolvedFile = new File(parent, "dir/file.txt");
    System.out.println(resolvedFile.toPath());
    // same as: (what's the point? Just that File constructor is overloaded?)
    resolvedFile = new File("/Examples", "dir/file.txt");
    System.out.println(resolvedFile.toPath());
    // same as
    Path parentPath = Paths.get("/Examples");
    Path childRelativePath = Paths.get("dir/file.txt");
    System.out.println(parentPath.resolve(childRelativePath));

    // "Hack" for getting working directory with IO
    File workingDirectory = new File("").getAbsoluteFile();
    System.out.println("Working directory = " +workingDirectory.toPath());

    // List directory content in IO using list()
    System.out.println("Print Dir2 content using list()-------------------------");
    File dir2File = new File(workingDirectory, "FileTree/Dir2");
    String[] dir2Contents = dir2File.list();
    for(int i=0; i<dir2Contents.length; i++) {
      System.out.println("i="+i+" "+dir2Contents[i]);
    }
    // List directory content in IO using list()
    System.out.println("Print Dir2 content using list()-------------------------");
    // File dir2File = new File(workingDirectory, "FileTree/Dir2");
    File[] dir2FileContents = dir2File.listFiles();
    for(int i=0; i<dir2FileContents.length; i++) {
      System.out.println("i="+i+" "+dir2FileContents[i].getName());
    }
  }
}
