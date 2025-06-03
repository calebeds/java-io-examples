package serialization.externalization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Subtitles implements Externalizable {
    private static final long serialVersionUID = 3678729905709169863L;

    private String engSub;

    private String frenchSub;

    private String germanSub;

    public Subtitles() {
        System.out.println("inside no-args constructor");
    }

    public String getEngSub() {
        return engSub;
    }

    public void setEngSub(String engSub) {
        this.engSub = engSub;
    }

    public String getFrenchSub() {
        return frenchSub;
    }

    public void setFrenchSub(String frenchSub) {
        this.frenchSub = frenchSub;
    }

    public String getGermanSub() {
        return germanSub;
    }

    public void setGermanSub(String germanSub) {
        this.germanSub = germanSub;
    }

    @Override
    public String toString() {
        return "Subtitles{" +
                "engSub='" + engSub + '\'' +
                ", frenchSub='" + frenchSub + '\'' +
                ", germanSub='" + germanSub + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(engSub);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.engSub = in.readUTF();
        this.frenchSub = convertToFrench(engSub);
        this.germanSub = covertToGerman(engSub);
    }

    private String covertToGerman(String engSub) {
        return "DEMO DEUTSCHE UNTERTITEL";
    }

    private String convertToFrench(String string) {
        return "DEMO SOUS-TITRE FRANCAIS";
    }
}
