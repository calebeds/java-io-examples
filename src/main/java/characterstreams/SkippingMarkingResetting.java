package characterstreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class SkippingMarkingResetting {
    public static void main(String[] args) {
        File file = new File("src/Frankenstein.txt");
        File outFile = new File("src/BookContent");

        try(Reader reader = new FileReader(file); Writer writer = new FileWriter(outFile)) {
            outFile.createNewFile();
            reader.skip(904);
            int charRead = 0;
            while ((charRead = reader.read()) != -1) {
                writer.write((char) charRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
