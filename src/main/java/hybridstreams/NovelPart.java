package hybridstreams;

public class NovelPart {
    private int offset;

    private int length;

    private String partName;

    public NovelPart(int offset, int length, String partName) {
        this.offset = offset;
        this.length = length;
        this.partName = partName;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
