package Models.Region;

public interface Territory {
    Region getRegion(long row, long col);
    long getRows();
    long getCols();
}
