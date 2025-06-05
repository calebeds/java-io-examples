package hybridstreams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class NovelReader {
    public static byte[] readData(String string) throws IOException {
        URL url = new URL(string);
        URLConnection connection = url.openConnection();
        BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int readByte = 0;
        while ((readByte = in.read()) != -1) {
            byteArrayOutputStream.write(readByte);
        }

        in.close();
        byteArrayOutputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    public static List<ImageData> readImages(String string) throws IOException {
        List<ImageData> list = new ArrayList<>();

        URL url = new URL(string);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if(line.contains(".jpg")) {
                    ImageData data = new ImageData();
                    String imageName = line.substring(91, line.indexOf(".jpg<")) + ".jpg";
                    data.setName(imageName);
                    String imageUrlString = string + line.substring(91, line.indexOf(".jpg<"));
                    byte[] readData = readData(imageUrlString);
                    data.setBinary(readData);
                    data.setLength(readData.length);
                    list.add(data);
                }

                if(line.contains(".png")) {
                    ImageData data = new ImageData();
                    String imageName = line.substring(91, line.indexOf(".png<")) + ".png";
                    data.setName(imageName);
                    String imageUrlString = string + line.substring(91, line.indexOf(".png<"));
                    byte[] readData = readData(imageUrlString);
                    data.setBinary(readData);
                    data.setLength(readData.length);
                    list.add(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
