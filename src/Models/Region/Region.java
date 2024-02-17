package Models.Region;

import Models.Player.*;

public interface Region {
    Player getOwner();
    float getDeposit();
    void updateDeposit(long amount);
    void updateDeposit(boolean instant, long amount);
    void updateOwner(Player owner);
    int getRegionX();
    int getRegionY();
}