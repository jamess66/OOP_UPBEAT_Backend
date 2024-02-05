package HexWorld;

import java.util.Arrays;
import java.util.Objects;

public class Region {
    Region parent;
    final int x;
    final int y;
    private Player onwer;
    private boolean isBlocked;
    public float deposit;

    public Region(int x, int y, boolean isBlocked) {
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
        onwer = null;
        deposit = Float.MIN_VALUE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (x == -1 && y == -1) return false;
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
        deposit = 0;
    }

    public void invest(Player player, float investment){
        if(onwer == null){
            this.deposit = investment;
        }else if(onwer.equals(player)){
            this.deposit = this.deposit + investment;
        }
    }

    public float getDeposit(){
        return deposit;
    }

    public void collect(float collect){
        deposit = Math.max(deposit - collect, 0);
    }

    public void unClaim(Player player){
        if(onwer.equals(player)){
            onwer = null;
            deposit = Float.MIN_VALUE;
        } else System.out.println("Not owner.");
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

    public static int distance(Region r1, Region r2){
        if(Arrays.equals(r1.getCoordinate(), new int[]{-1, -1}) || Arrays.equals(r2.getCoordinate(), new int[]{-1, -1})) return 0;
        return Math.abs(r1.x - r2.x) + Math.abs(r1.y - r2.y);
    }
}