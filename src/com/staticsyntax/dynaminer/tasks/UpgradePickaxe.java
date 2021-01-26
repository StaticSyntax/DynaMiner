package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;

public class UpgradePickaxe extends Task {

    public UpgradePickaxe(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        if(Pickaxe.playerHasUsable(api) && api.getBank().isOpen() && !api.getInventory().isFull()) {
            if(Pickaxe.getBestUsableBanked(api) != null) {
                return Pickaxe.getCurrent(api).ordinal() < Pickaxe.getBestUsableBanked(api).ordinal();
            }
        }
        return false;
    }

    @Override
    public void process() {
        String bestPickaxe = Pickaxe.getBestUsableBanked(api).getName();
        api.getBank().withdraw(bestPickaxe, 1);
    }
}
