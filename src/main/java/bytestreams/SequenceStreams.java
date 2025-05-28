package bytestreams;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class SequenceStreams {
    public static void main(String[] args) throws IOException {
        File file1 = new File("/home/calebesantos/dev/projects/text.txt");
        File file2 = new File("/home/calebesantos/dev/projects/text2.txt");
        String data = "\nThis is data from byte array\n";
        byte[] byteArray = data.getBytes();

        FileInputStream fileInputStream1 = new FileInputStream(file1);
        FileInputStream fileInputStream2 = new FileInputStream(file2);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

        Vector<InputStream> vector = new Vector<>();
        vector.add(fileInputStream1);
        vector.add(byteArrayInputStream);
        vector.add(fileInputStream2);

        Enumeration<InputStream> inputStreams = vector.elements();

//        SequenceInputStream in = new SequenceInputStream(new SequenceInputStream(fileInputStream1, fileInputStream2), byteArrayInputStream);
        SequenceInputStream in = new SequenceInputStream(inputStreams);

        int read = 0;
        while ((read = in.read()) != -1) {
            System.out.print((char) read);
        }

        in.close();
    }
}
