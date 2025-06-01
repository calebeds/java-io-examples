package characterstreams;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderAndStringWriter {
    public static void main(String[] args) throws IOException {
        String message = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?\n"
                + "He would chuck, he would, as much as he could, and chuck as much wood\n"
                + "As a woodchuck would if a woodchuck could chuck wood";

        StringReader in = new StringReader(message);
        StringWriter out = new StringWriter();

        char[] buffer = new char[3];
        while (in.read(buffer) != -1) {
            out.write(buffer);
        }

        String result = out.toString();
        System.out.println(result);
    }
}
