package fileapi;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FileAPIMethods {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/calebesantos/dev/../projects/MyFile.txt");
        File dir = new File("/home/calebesantos/dev/projects/MyDocuments");
        File dirOnDirectory = new File(dir, "PersonalStuff");
        File dirOnDirectory2 = new File(dir, "WorkDocuments");
        File fileOnDirectory = new File(dir, "FileOnDirectory.txt");
        dirOnDirectory.mkdir();
        dirOnDirectory.mkdir();
        fileOnDirectory.createNewFile();

//        System.out.println(file.exists());
//        System.out.println(dir.exists());

//        file.createNewFile();
//        dir.mkdir();

//        System.out.println(file.exists());
//        System.out.println(dir.exists());

        String[] list = dir.list();
        int files = 0;
        int dirs = 0;

//        Arrays.stream(list).forEach(fileName -> {
//            if(new File(dir, fileName).isDirectory()) {
//                System.out.println(fileName);
//            }
//        });

        for(String fileName: list) {
            if(new File(dir, fileName).isDirectory()) {
                dirs++;
            } else {
                files++;
            }
        }

        System.out.printf("Number of files: %d%n", files);
        System.out.printf("Number of directories: %d%n", dirs);

        long length = file.length();
        System.out.println(length);

        // absolute path, relative path and canonical path
        System.out.println(file.getPath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getCanonicalFile());

        // Parent
        System.out.println(dirOnDirectory.getParent());
        System.out.println(dirOnDirectory2.getParent());
        System.out.println(file.getParent());
    }
}
