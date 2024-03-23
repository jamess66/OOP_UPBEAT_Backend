package com.UPBEATGame.Game.UPBEAT.GameData.Region;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class HexRegion implements Region{
    @JsonPropertyOrder({ "x", "y", "deposit", "max_deposit"})

    @JsonIgnore
    private Player owner;

    private String regionColor = "#ffffff";
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
    @JsonIgnore
    public void updateDeposit(long amount) {
        this.deposit = Math.max(0, this.deposit + amount);
        this.deposit = Math.min(this.deposit, max_deposit);
    }

    @JsonIgnore
    public void updateDeposit(boolean instant, long amount){ // For test only
        this.deposit = Math.max(0, amount);
        this.deposit = Math.min(this.deposit, max_deposit);
    }

    @Override
    @JsonIgnore
    public void updateOwner(Player owner) {
        this.owner = owner;
        if(owner == null){
            regionColor = "#ffffff";
        }else regionColor = owner.getPlayerColor();

    }

    @Override
    @JsonIgnore
    public int getRegionX() {
        return this.x;
    }
    @Override
    @JsonIgnore
    public int getRegionY() {
        return this.y;
    }
}
