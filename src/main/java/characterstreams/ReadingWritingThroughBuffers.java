package characterstreams;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class ReadingWritingThroughBuffers {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Frankenstein.txt");
        File outFile = new File("src/FrankesteinCopy.txt");

        Reader reader = new FileReader(file);
        Writer writer = new FileWriter(outFile);
        char[] buffer = new char[1024];

        int charRead = 0;

        while (reader.read(buffer) != -1) {
            writer.write(buffer);
        }

        reader.close();
        reader.close();
    }
}
