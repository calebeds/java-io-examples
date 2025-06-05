package compressedstreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipExample {
    private static final String FILE_TO_COMPRESS = "src/Frankenstein.txt";
    private static final String FILE_TO_WRITE = "src/Frankenstein.gz";
    private static final String FILE_TO_DECOMPRESS = "src/Frankenstein-decompressed.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Original size: " + Files.size(Paths.get(FILE_TO_COMPRESS)));
        compress();
        decompress();
    }

    private static void compress() throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(FILE_TO_COMPRESS);
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(FILE_TO_WRITE));

        int size;
        while ((size = fileInputStream.read(buffer)) != -1) {
            gzipOutputStream.write(buffer, 0, size);
        }

        fileInputStream.close();
        gzipOutputStream.finish();
        gzipOutputStream.close();

        System.out.println("File is compressed to the size: " + Files.size(Paths.get(FILE_TO_WRITE)));
    }

    private static void decompress() throws IOException {
        byte[] buffer = new byte[1024];

        GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(FILE_TO_WRITE));
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_TO_DECOMPRESS);

        int size;
        while ((size = gzipInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, size);
        }

        fileOutputStream.close();
        fileOutputStream.close();

        System.out.println("File is decompressed: " + Files.size(Paths.get(FILE_TO_DECOMPRESS)));
    }
}
