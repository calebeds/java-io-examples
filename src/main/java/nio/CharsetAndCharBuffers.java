package nio;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CharsetAndCharBuffers {
    public static void main(String[] args) {
        String string = "अहं न जानामि, परन्तु मया एतत् पाठं सञ्चिकायां लिखितव्यम्";
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put(string);
        charBuffer.flip();

        Charset utf8 = StandardCharsets.UTF_8;
        ByteBuffer byteBuffer = utf8.encode(charBuffer);
        Path path = Paths.get("src/indian-gibberish.txt");

        try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE))  {
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            System.out.println("something's wrong: " + e.getMessage());
        }

        try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ))  {
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer decodedCharBuffer = utf8.decode(byteBuffer);

            try {
                while (true) {
                    System.out.print(decodedCharBuffer.get());
                }
            } catch (BufferUnderflowException e) {

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
