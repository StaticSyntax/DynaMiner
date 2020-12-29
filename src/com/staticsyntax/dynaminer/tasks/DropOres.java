package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import org.osbot.rs07.script.MethodProvider;

public class DropOres extends Task {

    public DropOres(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable() && DynaMiner.getMiningSettings().isPowerMining() && api.getInventory().isFull();
    }

    @Override
    public void process() {
        api.getInventory().dropAll(Rock.getAllOreNames());
        DynaMiner.getRngProfile().generateNewProfile(false);
    }
}
