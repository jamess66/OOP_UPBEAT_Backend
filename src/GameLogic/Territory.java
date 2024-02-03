package GameLogic;

public interface Territory {
    public Region getRegion(int x, int y);
    public Region[][] getWorld();
    public void setRegionBlock(int x, int y, boolean isBlocked);
    public int[] getTerritorySize();
    public void printClaimTable();
}