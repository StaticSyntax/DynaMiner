package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.script.MethodProvider;

public class WieldPickaxe extends Task {

    public WieldPickaxe(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        if(Pickaxe.playerHasUsable()) {
            return api.getInventory().contains(Pickaxe.getCurrent().getName()) && Pickaxe.getCurrent().canWield();
        }
        return false;
    }

    @Override
    public void process() {
        Sleep.waitCondition(() -> api.getInventory().interact("Wield", Pickaxe.getCurrent().getName()), 2500);
        if(api.getBank().isOpen()) api.getBank().depositAll();
    }
}
