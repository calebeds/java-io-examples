package characterstreams;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class HashTagReader extends FilterReader {
    /**
     * Creates a new filtered reader.
     *
     * @param in a Reader object providing the underlying stream.
     * @throws NullPointerException if {@code in} is {@code null}
     */
    protected HashTagReader(Reader in) {
        super(new BufferedReader(in));
    }

    public String readHashTag() throws IOException {
        StringBuilder hashTag = new StringBuilder();
        int charRead = 0;
        while ((charRead = in.read()) != -1) {
            if((char) charRead == '#') {
                hashTag.append((char) charRead);
                while ((charRead = in.read()) != -1 && ((char) charRead != ' ' && (char) charRead != '\n')) {
                    if(charRead == ',' || charRead == '.' || charRead == ':') {
                        continue;
                    }

                    hashTag.append((char) charRead);
                }

                return hashTag.toString();
            }
        }
        return null;
    }
}
