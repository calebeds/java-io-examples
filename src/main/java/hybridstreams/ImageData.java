package hybridstreams;

public class ImageData {
    private String name;

    private byte[] binary;

    private int length;

    public ImageData() {
    }

    public ImageData(String name, byte[] binary, int length) {
        this.name = name;
        this.binary = binary;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBinary() {
        return binary;
    }

    public void setBinary(byte[] binary) {
        this.binary = binary;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
