package characterstreams;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedCharacterStreams {
    public static void main(String[] args) throws IOException {

        // write -> read

        File inFile = new File("src/Frankenstein.txt");
        File outFile = new File("src/OutFile.txt");

        FileReader fIn = new FileReader(inFile);
        FileWriter fOut = new FileWriter(outFile);

        PipedReader pIn = new PipedReader();
        PipedWriter pOut = new PipedWriter(pIn);

        Runnable writeToPipe = () -> {
            int read = 0;
            int c = 0;
            try {
                while ((read = fIn.read()) != -1) {
                    if(c == 1024) {
                        System.out.println();
                        System.out.println("1024 characters written to the queue");
                    }
                    pOut.write(read);
                    c++;
                }
                fIn.close();
                pOut.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable readToPipe = () -> {
            int read = 0;
            try {
                while ((read = pIn.read()) != -1) {
                    System.out.print((char) read);
                    Thread.sleep(100);
                    fOut.write(read);
                }
                pIn.close();
                fOut.close();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(writeToPipe).start();
        new Thread(readToPipe).start();
    }
}
