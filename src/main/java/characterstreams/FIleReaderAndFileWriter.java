package characterstreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FIleReaderAndFileWriter {
    public static void main(String[] args) {
        File file = new File("src/Frankenstein.txt");
        File bookInformation = new File("src/BookInformation.txt");

        try(Reader reader = new FileReader(file, StandardCharsets.UTF_8); Writer writer = new FileWriter(bookInformation)) {
//        System.out.println(Charset.defaultCharset());
            bookInformation.createNewFile();

            int charRead = 0;
            int starRead = 0;
            while ((charRead = reader.read()) != -1) {
                if(starRead == 3) {
                    break;
                }

                if(((char) charRead) == '*') {
                    starRead++;
                }

                writer.write((char) charRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
