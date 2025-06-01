package characterstreams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class BufferedCharacterStreams {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Frankestein.txt");
        File outFile = new File("src/Acopy.txt");

        var reader = new BufferedReader(new FileReader(file));
        var writer = new BufferedWriter(new FileWriter(outFile));

        outFile.createNewFile();

        reader.transferTo(writer);

//        int charRead = 0;

//        while ((charRead = reader.read()) != -1) {
//            writer.write((char) charRead);
//        }

//        String lineRead = "";
//        while ((lineRead = reader.readLine()) != null) {
//            writer.write(lineRead);
//            writer.newLine();
//        }

//        reader.lines()
////                        .filter(line -> line.endsWith("I"))
//                .filter(line -> line.contains("Frankenstein"))
//                .map(String::toUpperCase)
//                                .forEach(line -> {
//                                    try {
//                                        writer.write(line);
//                                        writer.newLine();
//                                    } catch (IOException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                });

        reader.close();
        writer.close();


    }
}
