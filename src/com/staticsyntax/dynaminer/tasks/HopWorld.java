package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Sleep;

public class HopWorld extends Task {

    public HopWorld(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull()
                && api.getObjects().closest(true, Rock.getTargets(api)[script.getBehaviourProfile().getCurrentTarget()].getIds()) == null
                && script.getMiningSettings().isWorldHopping();
    }

    @Override
    public void process() {
        int lastWorld = api.getWorlds().getCurrentWorld();
        if(api.getWorlds().isMembersWorld()) {
            api.getWorlds().hopToP2PWorld();
        } else {
            api.getWorlds().hopToF2PWorld();
        }
        Sleep.waitCondition(() -> api.getWorlds().getCurrentWorld() != lastWorld, 30000);
    }
}
