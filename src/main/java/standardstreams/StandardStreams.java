package standardstreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class StandardStreams {
    public static void main(String[] args) throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream("src/out.txt")));
        System.setErr(new PrintStream(new FileOutputStream("src/error.txt")));

        System.out.println("Hey There!");
        System.out.println("IO");
        System.err.println("This is an Exception");
    }
}
