package serialization.proxyobject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Player player = new Player();
        User user = new User(101, "user", "xyz");
        player.setLevel(55);
        player.setUserDetails(user);
//        player.setUserDetails("Thanos");
        player.setPoints(345657);

        serialize(player, "playerInfo.bin");

        Player deserializedObject = (Player) deserialize("playerInfo.bin");

        System.out.println(deserializedObject);

    }

    private static void serialize(Player player, String fileName) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(player);

        objectOutputStream.close();
    }

    private static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object object = objectInputStream.readObject();

        objectInputStream.close();

        return object;
    }
}
