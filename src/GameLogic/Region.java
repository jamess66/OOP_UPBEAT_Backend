package GameLogic;

import java.util.Objects;

public class Region {
    Region parent;
    final int x;
    final int y;
    private Player onwer;
    private boolean isBlocked;

    public Region(int x, int y, boolean isBlocked) {
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
        onwer = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Region region = (Region) obj;
        return x == region.x && y == region.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int[] getCoordinate(){
        return new int[]{x, y};
    }

    public void claim(Player player){
        onwer = player;
    }

    public void unClaim(Player player){
        if(onwer.equals(player)) onwer = null;
        else System.out.println("Not owner.");
    }

    public Player owner(){
        return onwer;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    public boolean isBlocked(){
        return isBlocked;
    }
}