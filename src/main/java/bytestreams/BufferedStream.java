package bytestreams;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedStream {
    public static void main(String[] args) {
        File file = new File("/home/calebesantos/dev/projects/text.txt");

        try(FileInputStream in = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in)) {
            int byteRead = 0;

            while ((byteRead = bufferedInputStream.read()) != -1) {
                System.out.println(byteRead);
                // process the data
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
