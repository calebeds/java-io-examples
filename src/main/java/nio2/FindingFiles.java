package nio2;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

public class FindingFiles {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(URI.create("file:///home/calebesantos/dev/projects"));
        Stream<Path> filesStream = Files.find(path, Integer.MAX_VALUE, (p, attr) -> p.toString().endsWith(".txt"));
        System.out.println(filesStream.count());
        filesStream.close();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0);
        long timeInMillis = calendar.getTimeInMillis();


        Stream<Path> filesStream2 = Files.find(path, Integer.MAX_VALUE, (p, attr) -> p.toString().endsWith(".txt") && attr.creationTime().toMillis() < timeInMillis);
        System.out.println(filesStream2.count());

    }
}
