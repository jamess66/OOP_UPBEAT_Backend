package com.UPBEATGame.Game.UPBEAT.Config;

import org.junit.Test;

import static com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader.*;
import static org.junit.Assert.assertEquals;

public class LoadConfigTest {


    @Test
    public void testLoadConfig(){
        assertEquals(10 , rows);
        assertEquals(10 , cols);
        assertEquals(10 , init_plan_min);
        assertEquals(0 , init_plan_sec);
        assertEquals(5000 , init_budget);
        assertEquals(1000 , init_center_dep);
        assertEquals(5 , plan_rev_min);
        assertEquals(0 , plan_rev_sec);
        assertEquals(500 , rev_cost);
        assertEquals(500 , max_dep);
        assertEquals(150.0f , interest_pct, 0.01f);
    }
}
