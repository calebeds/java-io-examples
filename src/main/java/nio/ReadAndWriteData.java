package nio;

import java.io.File;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadAndWriteData {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(12);
        buffer.putInt(13);

        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());

        buffer.flip();

        System.out.println("Position: " + buffer.position());
        System.out.println("Limit: " + buffer.limit());

        Path path = Paths.get("src/demo.bin");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        channel.write(buffer);
        channel.close();

        FileChannel readChannel = FileChannel.open(path, StandardOpenOption.READ);
        buffer.clear();
        readChannel.read(buffer);
        readChannel.close();

        buffer.flip();


        try {
            while (true) {
                System.out.println(buffer.getInt());
            }
        } catch (BufferUnderflowException e) {
            System.out.println("Reading buffer is over");
        }


    }
}
