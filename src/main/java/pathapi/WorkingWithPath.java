package pathapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkingWithPath {
    public static void main(String[] args) throws IOException {
        // path object creation
//        Path path = Paths.get("src/Bucket.txt");
//        Path pathWithSeparators = Paths.get("src", "PopularHashTags.txt");
        Path dir = Path.of("src/Files");
        Path path = Path.of("src/PopularHashTags.txt");
//        System.out.println(path.getFileName());
//        System.out.println(path.getParent());

//        System.out.println(path.getFileSystem());

        // resolve
//        Path pathRoot = Paths.get("src");
//        Path pathChild = Paths.get("Bucket.txt");
//
//        Path resolve = pathRoot.resolve(pathChild);

        // resolve sibling

        Path pathRoot2 = Paths.get("src/sibling.txt");
        Path pathChild2 = Paths.get("Bucket.txt");

        Path resolve2 = pathRoot2.resolveSibling(pathChild2);

        // relativize -- constructing relative path
        Path relativized = dir.relativize(path);

        // FILES
        System.out.println(Files.exists(path, LinkOption.NOFOLLOW_LINKS));

//        try(BufferedReader reader = Files.newBufferedReader(path)) { // charset
//            String line = reader.readLine();
//
//            while (line != null) {
//                System.out.println(line);
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Files.lines(path).forEach(line -> System.out.println(line));

//            Files.readAllLines(path);

//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND);) {
//            bufferedWriter.write("\nHey may I get in the file!");
//        }
//
//        byte[] bytes = Files.newInputStream(path).readAllBytes();
//        System.out.println(new String(bytes));

        Files.newDirectoryStream(Paths.get("src"), "*.txt").forEach(each -> System.out.println(each));

//        Files.createFile(Paths.get("src/somefile"));

        Files.createTempFile(Paths.get("src"), "calebe", "oliveira");
    }
}
