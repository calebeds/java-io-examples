package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class SelectorsWithChannels {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(1234));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("Waiting for Events");
            int events = selector.select();// blocking
            System.out.println("Events: " + events);
            Set<SelectionKey> keys = selector.selectedKeys();

            for (SelectionKey key: keys) {
                if(((key.readyOps()) & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    // accepted
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    keys.remove(key);
                } else if(((key.readyOps()) & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    // read
                    System.out.println("Reading from connection: ");
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);

                    byteBuffer.flip();
                    CharBuffer decoded = StandardCharsets.UTF_8.decode(byteBuffer);
                    System.out.println(new String(decoded.array()));
                    byteBuffer.clear();
                    keys.remove(key);
                    key.cancel();
                    channel.close();
                }
            }
        }
    }
}
