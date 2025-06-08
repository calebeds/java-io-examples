package nio2;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchingDirEvents {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path dir = Paths.get("src");
        FileSystem fileSystem = dir.getFileSystem();
        WatchService watchService = fileSystem.newWatchService();

        WatchKey watchKey = dir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        while (watchKey.isValid()) {
            WatchKey keyTaken = watchService.take();
            List<WatchEvent<?>> events = keyTaken.pollEvents();

            for (WatchEvent<?> event : events) {
                if(event.kind() == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                } else if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path path = (Path) event.context();
                    System.out.println(path + " is created");
                } else if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    Path path = (Path) event.context();
                    System.out.println(path + " is modified");
                } else if(event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                    Path path = (Path) event.context();
                    System.out.println(path + " is deleted");
                }
            }

            keyTaken.reset();
        }

    }
}
