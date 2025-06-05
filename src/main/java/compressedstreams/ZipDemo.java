package compressedstreams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipDemo {
    private static final String archiveFolder = "src/archive.zip";
    public static void main(String[] args) throws IOException {
        write();

        System.out.println(Files.size(Paths.get(archiveFolder)));

        read();
    }

    private static void read() {
        try(InputStream in = new FileInputStream(archiveFolder);
        ZipInputStream zipInputStream = new ZipInputStream(in);
        DataInputStream dataInputStream = new DataInputStream(zipInputStream)) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (nextEntry != null) {
                if(nextEntry.isDirectory()) {
                    System.out.println("Reading: " + nextEntry);
                } else {
                    byte[] bytes = dataInputStream.readAllBytes();
                    System.out.println(new String(bytes));
                }
                nextEntry = zipInputStream.getNextEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void write() {
        try(OutputStream outputStream = new FileOutputStream(archiveFolder);
              ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
              DataOutputStream dataOutputStream = new DataOutputStream(zipOutputStream)) {
            ZipEntry dirEntry = new ZipEntry("BinFiles/");
            zipOutputStream.putNextEntry(dirEntry);

            ZipEntry fileEntry = new ZipEntry("BinFiles/LineCount.bin");
            zipOutputStream.putNextEntry(fileEntry);

            IntStream.range(0, 100).forEach(i -> {
                try {
                    dataOutputStream.writeUTF(" This is line number: " + (i + 1));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            zipOutputStream.closeEntry();

            ZipEntry dirEntry2 = new ZipEntry("TextFiles/");
            zipOutputStream.putNextEntry(dirEntry2);

            ZipEntry fileEntry2 = new ZipEntry("FileFiles/LineCount.bin");
            zipOutputStream.putNextEntry(fileEntry2);

            dataOutputStream.writeUTF(" This file is to be archived.");

            zipOutputStream.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
