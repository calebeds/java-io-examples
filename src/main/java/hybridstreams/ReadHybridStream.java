package hybridstreams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;

public class ReadHybridStream {
    public static void main(String[] args) {
        int n = 3;
        File file = new File("src/TheLandOfAfternoon.bin");
        try(InputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            bufferedInputStream.mark((int) file.length() + 1);

            InputStreamReader readerBridge = new InputStreamReader(bufferedInputStream);
            LineNumberReader reader = new LineNumberReader(readerBridge);

            while (reader.getLineNumber() < n - 1) {
                reader.readLine();
            }

            String part = reader.readLine();
            int offset = Integer.parseInt(part.substring(0, 7).trim());
            int length = Integer.parseInt(part.substring(9, 16).trim());
            String partName = part.substring(16);

            System.out.printf("%d %d %s%n", offset, length, partName);

            bufferedInputStream.reset();

            int skipped = (int) bufferedInputStream.skip(offset);
            int total = skipped;

            while (total < offset) {
                skipped = (int) bufferedInputStream.skip(offset - total);
                total += skipped;
            }

            byte[] bytes = new byte[length + 100];
            bufferedInputStream.read(bytes);

            bufferedInputStream.close();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, length);

            if(n == 1) {
                // text
                try(var textReader = new BufferedReader(new InputStreamReader(byteArrayInputStream))) {
                    String line;
                    while ((line = textReader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } else {
                File image = new File("src/" + partName);
                try(OutputStream out = new FileOutputStream(image)) {
                    out.write(byteArrayInputStream.readAllBytes());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
