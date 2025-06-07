package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientCode {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1234);
        SocketChannel socketChannel = SocketChannel.open(socketAddress);
        Socket socket = socketChannel.socket();

        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put("Hey this is the content");

        charBuffer.flip();

        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);

        socketChannel.write(byteBuffer);

        socket.close();
    }
}
