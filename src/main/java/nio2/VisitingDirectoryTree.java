package nio2;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VisitingDirectoryTree {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(URI.create("file:///home/calebesantos/dev/projects"));
        MyFileVisitor visitor = new MyFileVisitor();
        Files.walkFileTree(path, visitor);
        visitor.getMap().forEach((type, num) -> System.out.printf("Number of %s: %s%n", type, num));
    }
}
