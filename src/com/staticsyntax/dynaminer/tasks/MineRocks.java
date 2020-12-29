package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;

public class MineRocks extends Task {

    public MineRocks(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable() && Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull();
    }

    @Override
    public void process() {
        for(Rock rock : Rock.getTargets()) {
            for(int i = 0; i < DynaMiner.getRngProfile().getMiningAmount(); i++) {
                RS2Object rockObject = api.getObjects().closest(Location.MINING.getArea(), rock.getIds());
                mineRock(rockObject);
                if(api.getInventory().isFull()) break;
            }
            if(api.getInventory().isFull()) break;
        }
    }

    private void mineRock(RS2Object rockObject) {
        if(rockObject != null) {
            if(api.getMap().canReach(rockObject)) {
                rockObject.interact("Mine");
                Sleep.waitCondition(() -> api.myPlayer().isAnimating(), 5000);
                Sleep.waitCondition(() -> !api.myPlayer().isAnimating(), 60000);
                DynaMiner.getRngProfile().increaseFatigue();
                sleepOrIdle();
            }
        }
    }

    private void sleepOrIdle() {
        int sleepTime = MethodProvider.random(DynaMiner.getRngProfile().getSleepTime()[0], DynaMiner.getRngProfile().getSleepTime()[1]);
        if(DynaMiner.getMiningSettings().isIdlingRandomly() && MethodProvider.random(100) <= MethodProvider.random(0, 5)) {
            try {
                api.sleep(sleepTime * MethodProvider.random(4, 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                api.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
