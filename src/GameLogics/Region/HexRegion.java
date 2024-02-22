package GameLogics.Region;

import Config.ConfigLoader;
import GameLogics.Player.Player;

public class HexRegion implements Region{
    private Player owner;
    private long deposit;
    private final long max_deposit = ConfigLoader.getMax_dep();
    private  int x, y;

    public HexRegion(int x, int y) {
        this.x = x;
        this.y = y;
        this.owner = null;
        this.deposit = 0;
    }

    public HexRegion(){
        this.x = Integer.MIN_VALUE;
        this.y = Integer.MIN_VALUE;
        this.owner = null;
        this.deposit = Integer.MIN_VALUE;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public float getDeposit() {
        return this.deposit;
    }

    @Override
    public void updateDeposit(long amount) {
        this.deposit = Math.max(0, this.deposit + amount);
        this.deposit = Math.min(this.deposit, max_deposit);
    }

    public void updateDeposit(boolean instant, long amount){
        this.deposit = Math.max(0, amount);
        this.deposit = Math.min(this.deposit, max_deposit);
    }

    @Override
    public void updateOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public int getRegionX() {
        return this.x;
    }
    @Override
    public int getRegionY() {
        return this.y;
    }
}
