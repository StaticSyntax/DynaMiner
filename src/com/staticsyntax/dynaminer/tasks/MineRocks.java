package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;


public class MineRocks extends Task {

    public MineRocks(DynaMiner script) {
        super(script);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull();
    }

    @Override
    public void process() {
        RS2Object rockObject = api.getObjects().closest(true, Rock.getTargets(api)[script.getBehaviourProfile().getCurrentTarget()].getIds());
        mineRock(rockObject);
        enableRunEnergy();
    }

    private void mineRock(RS2Object rockObject) {
        if(rockObject != null) {
            if(Location.MINING.getArea().contains(rockObject)) {
                if(rockObject.interact("Mine")) {
                    Sleep.waitCondition(() -> !rockObject.exists(), MethodProvider.random(10000, 60000));
                    if(script.getMiningSettings().isFatigueEnabled()) {
                        script.getBehaviourProfile().increaseFatigue();
                    }
                }
            }
        }
    }

    private void enableRunEnergy() {
        if(!api.getSettings().isRunning() && api.getSettings().getRunEnergy() >= MethodProvider.random(1, 15)
                && script.getMiningSettings().getRadius() > 1 && MethodProvider.random(100) > 75) {
            api.getSettings().setRunning(true);
        }
    }
}
