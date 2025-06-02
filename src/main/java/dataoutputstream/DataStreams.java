package dataoutputstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStreams {
    public static void main(String[] args) throws IOException {
        File file = new File("src/Bucket.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

        String[] items = {"Cofee", "Shoes", "Detergent"};
        double[] costs = {25, 300, 50};

        // S.NO., item, cost

        for (int i = 0; i < costs.length; i++) {
            dataOutputStream.writeLong(i + 1);
            dataOutputStream.writeUTF(items[i]);
            dataOutputStream.writeDouble(costs[i]);
        }

        dataOutputStream.close();

        // calc total
        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);

        long serialNo;
        String item;
        double cost;
        double total = 0.0;

        try {
            while (true) {
                serialNo = dataInputStream.readLong();
                item = dataInputStream.readUTF();
                cost = dataInputStream.readDouble();
                total += cost;

                System.out.printf("%d. You have ordered %s at $%.2f%n", serialNo, item, cost);
            }
        } catch (EOFException e) {

        }

        fileInputStream.close();
        dataInputStream.close();
        System.out.printf("Total cost is $%.2f%n", total);
    }
}
