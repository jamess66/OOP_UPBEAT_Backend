package HexWorld;

public class WorldGrid implements Territory {
    private final Region[][] world;
    private final int m, n;

    public WorldGrid(int m, int n){
        this.m = m;
        this.n = n;
        world = new Region[m][n];
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = new Region(i, j, false);
            }

        }
    }

    @Override
    public Region getRegion(int x, int y) {
        return world[x][y];
    }

    @Override
    public Region[][] getWorld(){
        return world;
    }

    @Override
    public void setRegionBlock(int x, int y, boolean isBlocked) {
        world[x][y].setBlocked(isBlocked);
    }

    @Override
    public int[] getTerritorySize() {
        return new int[]{m, n};
    }

    public void printClaimTable(){
        for (Region[] regions: world){

            for(Region region: regions){
                if(region.owner() != null){
                    System.out.print("1  ");
                }else{
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }



    }
}
