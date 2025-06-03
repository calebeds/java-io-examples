package serialization.externalization;

import serialization.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalizationDemo {
    private static final String filename = "subtitles.bin";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Subtitles subtitles = new Subtitles();
        subtitles.setEngSub("DEMO ENGLISH SUBTITLES");

        serialize(subtitles, filename);

        Path path = Paths.get(filename);
        System.out.println("Size of file: " + Files.size(path));

        Subtitles subs = (Subtitles) deserialize(filename);

        System.out.println(subs);
    }

    private static void serialize(Subtitles subtitles, String fileName) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(subtitles);

        objectOutputStream.close();
    }

    private static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();

        objectInputStream.close();

        return object;
    }
}
