package bytestreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreams {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream outputStream = new FileOutputStream("/home/calebesantos/dev/projects/OtherFile.txt");
        PrintStream printStream = new PrintStream(outputStream, true);

        printStream.print("String data using print stream");
        printStream.print(123);
        printStream.println((float) 1.23);

        printStream.close();
    }
}
