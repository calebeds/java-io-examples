package characterstreams;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class ReadingWritingCharArray {
    public static void main(String[] args) throws IOException {
        String message = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?\n"
                + "He would chuck, he would, as much as he could, and chuck as much wood\n"
                + "As a woodchuck would if a woodchuck could chuck wood";

        char[] chars = message.toCharArray();

        CharArrayReader in = new CharArrayReader(chars);
        CharArrayWriter out = new CharArrayWriter();
        char[] buffer = new char[3];

        while ((in.read(buffer)) != -1) {
            out.write(buffer);
        }

        char[] array = out.toCharArray();

        System.out.println(array.length);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

        in.close();
        out.close();
    }
}
