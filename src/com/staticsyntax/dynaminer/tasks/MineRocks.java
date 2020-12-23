package com.staticsyntax.dynaminer.tasks;

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
        return Pickaxe.playerHasUsable() && Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull() && !api.myPlayer().isAnimating();
    }

    @Override
    public void process() {
        for(Rock rock : Rock.getTargets()) {
            RS2Object rockObject = api.getObjects().closest(Location.MINING.getArea(), rock.getIds());
            if(rockObject != null) {
                if(api.getMap().canReach(rockObject)) {
                    rockObject.interact("Mine");
                    try {
                        MethodProvider.sleep(MethodProvider.random(750, 1150));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Sleep.waitCondition(() -> !api.myPlayer().isAnimating() && !api.myPlayer().isMoving(), MethodProvider.random(10000, 30000));
                    break;
                }
            }
        }
    }
}
