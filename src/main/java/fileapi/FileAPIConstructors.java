package fileapi;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class FileAPIConstructors {
    public static void main(String[] args) throws URISyntaxException {
        File f1 = new File("/home/calebesantos/dev/projects/");
        File f2 = new File(f1, "text.txt");
        File f3 = new File("/home/calebesantos/dev/projects/", "text.txt");

        URI uri = new URI("");
        File f4 = new File(uri);
    }
}
