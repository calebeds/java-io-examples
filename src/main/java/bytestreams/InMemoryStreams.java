package bytestreams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InMemoryStreams {
    public static void main(String[] args) throws IOException {
        String messages = "Let's learn about In-Memory Streams";
        byte[] bytes = messages.getBytes();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes, 2, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[3];
        int number = 0;
//        int count = 0;
        while ((number = in.read(buffer)) != -1) {
            // process the bytes
//            for(byte b: buffer) {
//                System.out.println(b);
//            }

            out.write(buffer);

//            if(count == 1) {
//                in.mark(10); // 0, 1 mark
//            }
//
//            if(count == 2) {
//                in.reset(); //0, 1, 2, 2, 3
//            }
//            count++;
        }
        in.close();

        byte[] byteArray = out.toByteArray();
        for(byte b: byteArray) {
            System.out.println(b);
        }
        out.close();
    }
}
