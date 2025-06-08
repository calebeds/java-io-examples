package nio2;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesSystemNIO {
    public static void main(String[] args) throws IOException {
        List<FileSystemProvider> fileSystemProviders = FileSystemProvider.installedProviders();

//        fileSystemProviders.forEach(System.out::println);

        FileSystem defaultFileSystem = fileSystemProviders.get(0).getFileSystem(URI.create("file:///"));

        System.out.println(defaultFileSystem == FileSystems.getFileSystem(URI.create("file:///")));
        System.out.println(defaultFileSystem == FileSystems.getDefault());

        Iterable<Path> rootDirectories = defaultFileSystem.getRootDirectories();

//        rootDirectories.forEach(System.out::println);

        Iterable<FileStore> fileStores = defaultFileSystem.getFileStores();
        fileStores.forEach(fileStore -> System.out.println(fileStore.name() + " -- " + fileStore.type()));

        // To create a file
        FileSystemProvider systemProvider = fileSystemProviders.get(0);

        Path path = Paths.get("/home/calebesantos/temp");

//        systemProvider.createDirectory(path);

        URI uriFile = URI.create("file:///home/calebesantos/dev/projects/java-io/src/Acopy.txt");
        PosixFileAttributes basicFileAttributes = systemProvider.readAttributes(Paths.get(uriFile), PosixFileAttributes.class);
        System.out.println(basicFileAttributes.permissions());

        // JAR file system
        URI uriZip = URI.create("jar:file:///home/calebesantos/dev/projects/java-io/src/archive2.zip");
        Map<String, String> options = new HashMap<>();
        options.put("create", "true");

        FileSystem zipFileSystem = FileSystems.newFileSystem(uriZip, options);


        Path local = Paths.get("/home/calebesantos/dev/projects/java-io/src/Acopy.txt");

        zipFileSystem.provider().createDirectory(zipFileSystem.getPath("/textFiles"));

        Path target = zipFileSystem.getPath("/textFiles/dataCompressed.txt");

        Files.copy(local, target);

        zipFileSystem.close();
    }
}
