package hybridstreams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class WriteToHybridStream {
    public static void main(String[] args) throws IOException {
        // offset, length
        // text
        // image

        // Meta information
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter writerBridge = new OutputStreamWriter(byteArrayOutputStream);
        PrintWriter printWriter = new PrintWriter(writerBridge);

        // text
        printWriter.printf("%7d %7d %s %n", 0, 0, "text");
        // image parts
        List<ImageData> readImages = NovelReader.readImages("https://www.gutenberg.org/cache/epub/65560/images/");

        for(ImageData img: readImages) {
            printWriter.printf("%7d %7d %s %n", 0, 0, img.getName());
        }

        printWriter.flush();
        writerBridge.close();

        System.out.println(byteArrayOutputStream.size());

        int offset = byteArrayOutputStream.size();
        byte[] text = NovelReader.readData("https://www.gutenberg.org/cache/epub/65560/pg65560-images.html");
        int length =  text.length;

        List<NovelPart> parts = new ArrayList<>();
        parts.add(new NovelPart(offset, length, "Text"));

        for (ImageData imageData: readImages) {
            offset += length;
            length = imageData.getLength();
            parts.add(new NovelPart(offset, length, imageData.getName()));
        }

        byteArrayOutputStream = new ByteArrayOutputStream();
        writerBridge = new OutputStreamWriter(byteArrayOutputStream);
        printWriter = new PrintWriter(writerBridge);

        for(NovelPart novelPart: parts) {
            printWriter.printf("%7d %7d %s %n", novelPart.getOffset(), novelPart.getLength(), novelPart.getPartName());
        }

        printWriter.flush();

        System.out.println(new String(byteArrayOutputStream.toByteArray()));

        // Actual Data

        // text
        byteArrayOutputStream.write(text);

        // images
        for(ImageData imageData: readImages) {
            byteArrayOutputStream.write(imageData.getBinary());
        }

        // flush bytes on the file

        File file = new File("src/TheLandOfAfternoon.bin");
        try(OutputStream out = new FileOutputStream(file)) {
            out.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------- Done Writing Data ---------");
    }
}
