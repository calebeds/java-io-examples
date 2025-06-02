package dataoutputstream;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WhyDataStreams {
    public static void main(String[] args) throws IOException {
        File file = new File("src/DataFile.txt");
        file.createNewFile();

        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file);

        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

        Integer i = 65;
        System.out.println(i.byteValue() & 0xff);
        System.out.println("------");

        int read = 0;

        dataOutputStream.writeInt(i);
        dataOutputStream.close();

        while ((read = dataInputStream.readInt()) != -1) {
            System.out.println(read);
        }

        fileWriter.close();
        dataInputStream.close();
        fileInputStream.close();
        fileOutputStream.close();
        fileReader.close();
    }
}
