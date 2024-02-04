package HexWorld;

public interface Territory {
    default Territory getInstance(){
        return this;
    }
    Region getRegion(int x, int y);
    Region[][] getWorld();
    void setRegionBlock(int x, int y, boolean isBlocked);
    int[] getTerritorySize();
    void printClaimTable();
}