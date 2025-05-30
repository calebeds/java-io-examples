package characterstreams;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class TheNeed {
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("/home/calebesantos/dev/projects/Morning.txt");
        int c = 0;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }

        in.close();
    }
}
