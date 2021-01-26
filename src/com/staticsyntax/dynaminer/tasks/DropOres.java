package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;

public class DropOres extends Task {

    public DropOres(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && script.getMiningSettings().isPowerMining() && api.getInventory().isFull();
    }

    @Override
    public void process() {
        api.getInventory().dropAll(Rock.getAllOreNames());
        script.getBehaviourProfile().generateNewProfile();
    }
}
