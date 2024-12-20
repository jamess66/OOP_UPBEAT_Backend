package com.UPBEATGame.Game.UPBEAT.GameData.Player;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@JsonRootName(value = "crew")
public class Crew implements Player{
    private long budget;
    private Region cityCenter;
    private Region currentRegion;
    private String playerColor;

    public Crew(Territory territory, String playerColor) {
        this.budget = ConfigLoader.getInit_budget();
        this.playerColor = playerColor;
        randomSpawn(territory);
    }

    @JsonCreator
    public Crew(Territory territory, int x, int y, String playerColor){ // fixed spawn point **for testing only**
        this.budget = ConfigLoader.getInit_budget();
        Region region = territory.getRegion(x,y);
        this.playerColor = playerColor;
        this.currentRegion = region;
        this.cityCenter = region;
        this.cityCenter.updateDeposit(true , ConfigLoader.init_center_dep);
        region.updateOwner(this);
    }

    private void randomSpawn(Territory territory){
        Region region = Utility.getRandomRegion(territory);
        while(region.getOwner() != null){
            region = Utility.getRandomRegion(territory);
        }
        region.updateOwner(this);
        this.cityCenter = region;
        this.currentRegion = region;
        this.cityCenter.updateDeposit(true ,ConfigLoader.init_center_dep);
    }

    @Override
    public long getBudget() {
        return this.budget;
    }

    @Override
    @JsonIgnore
    public void payCost(long cost) {
        addBudget(-1 * cost);
    }

    @Override
    @JsonIgnore
    public void addBudget(long budget) {
        this.budget = Math.max(0, this.budget + budget);
    }

    @Override
    public Region getCityCenter() {
        return this.cityCenter;
    }

    @Override
    @JsonIgnore
    public void updateCurrentRegion(Region region){
        currentRegion = region;
    }

    @Override
    public Region getCurrentRegion(){
        return currentRegion;
    }

    @Override
    @JsonIgnore
    public void updateCityCenter(Region target) {
        cityCenter = target;
    }


    @Override

    public boolean equals(Object obj) {
        if (obj == null ) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return super.equals(player);
    }

}
