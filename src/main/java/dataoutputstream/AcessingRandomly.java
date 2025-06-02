package dataoutputstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

public class AcessingRandomly {
    public static void main(String[] args) throws Exception {
        File file = new File("src/Codes.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));

        int codes[] = {3, 67, 89, 243, 90, 97, 45};
        for (int i = 0; i < codes.length; i++) {
            dataOutputStream.writeInt(codes[i]);
        }

        dataOutputStream.close();

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

        System.out.println(randomAccessFile.getFilePointer());
        System.out.println("----");

        //replace second position (67)
        randomAccessFile.seek(4);
        randomAccessFile.writeInt(91);

        // replace fifth position (97)
        randomAccessFile.seek(5 * 4);
        randomAccessFile.writeInt(92);

//        randomAccessFile.seek(0);
//        try {
//            while (true) {
//                System.out.println(randomAccessFile.readInt());
//            }
//        } catch (EOFException e) {
//
//        }

        randomAccessFile.close();

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        try {
            while (true) {
                System.out.println(dataInputStream.readInt());
            }
        } catch (EOFException e) {

        }

        dataInputStream.close();
    }
}
