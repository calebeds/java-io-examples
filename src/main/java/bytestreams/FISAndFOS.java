package bytestreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FISAndFOS {
    public static void main(String[] args) throws IOException {
        // Reading and Writing Files
        File file = new File("/home/calebesantos/dev/projects/MyFile.txt");
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream("/home/calebesantos/dev/projects/OtherFile.txt");
        int byteRead = 0;

        // Byte by Byte
//        while ((byteRead = in.read())!= -1) {
//            System.out.println(byteRead);
//            out.write(byteRead);
//        }

        // byte array
        long size = file.length();
        byte[] array = new byte[10];

        while (in.read(array) != -1) {
            // process the bytes
            int availableBytes = in.available();
            System.out.println(availableBytes);
            out.write(array, 2, 6);
        }

        in.close();
        out.close();
    }
}
