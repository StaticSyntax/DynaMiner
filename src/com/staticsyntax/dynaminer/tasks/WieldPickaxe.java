package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Sleep;

public class WieldPickaxe extends Task {

    public WieldPickaxe(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        if(Pickaxe.playerHasUsable(api)) {
            return api.getInventory().contains(Pickaxe.getCurrent(api).getName()) && Pickaxe.getCurrent(api).canWield(api);
        }
        return false;
    }

    @Override
    public void process() {
        Sleep.waitCondition(() -> api.getInventory().interact("Wield", Pickaxe.getCurrent(api).getName()), 2500);
        if(api.getBank().isOpen()) api.getBank().depositAll();
    }
}
