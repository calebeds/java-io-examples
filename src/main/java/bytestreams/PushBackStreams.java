package bytestreams;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class PushBackStreams {
    public static void main(String[] args) throws IOException {
        String s = "if(a && b) return c & d";

        byte[] buffer = s.getBytes();

        InputStream in = new ByteArrayInputStream(buffer);
        PushbackInputStream filter = new PushbackInputStream(in);

        int b = 0;
        while ((b = filter.read()) != -1) {
            if (b == '&') {
                if ((b = filter.read()) == '&') {
                    System.out.print("Logical And");
                } else {
                    System.out.print("Bitwise And");
                    filter.unread(b);
                }
            } else {
                System.out.print((char) b);
            }
        }
    }
}
