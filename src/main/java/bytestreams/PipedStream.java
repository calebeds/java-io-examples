package bytestreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStream {

    public static void main(String[] args) throws IOException {
        // write --> read

        File file = new File("/home/calebesantos/dev/projects/text.txt");
        File outFile = new File("/home/calebesantos/dev/projects/outfile.txt");
        outFile.createNewFile();

        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);

        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);

        Runnable writeToPipe = () -> {
            int read = 0;
            int c = 0;
            try {
                while ((read = fileInputStream.read()) != -1) {
                    if(c == 1024) {
                        System.out.println("1024 bytes process");
                    }

                    pipedOutputStream.write(read);
                    System.out.println("Supplied " + read);
                    c++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        };

        Runnable readFromPipe = () -> {
            int read = 0;
            try {
                while ((read = pipedInputStream.read()) != -1) {
                    // process the bytes
                    Thread.sleep(1000);
                    System.out.println("Processed " + read);
                    fileOutputStream.write(read);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(writeToPipe).start();
        new Thread(readFromPipe).start();

    }


}
