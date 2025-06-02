package characterstreams;

import java.io.FileReader;
import java.io.IOException;

public class FiltersCharacterStreams {
    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("src/PopularHashTags.txt");
        HashTagReader hashTagReader = new HashTagReader(in);

        String hashtag = "";

        while ((hashtag = hashTagReader.readHashTag()) != null) {
            System.out.println(hashtag);
        }
    }
}
