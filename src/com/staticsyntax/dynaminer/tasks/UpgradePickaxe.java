package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Sleep;
import org.osbot.rs07.script.MethodProvider;

public class UpgradePickaxe extends Task {

    public UpgradePickaxe(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        if(Pickaxe.playerHasUsable() && api.getBank().isOpen()) {
            if(Pickaxe.getBestUsableBanked() != null) {
                return Pickaxe.getCurrent().ordinal() < Pickaxe.getBestUsableBanked().ordinal();
            }
        }
        return false;
    }

    @Override
    public void process() {
        String bestPickaxe = Pickaxe.getBestUsableBanked().getName();
        api.getBank().withdraw(bestPickaxe, 1);
        Sleep.waitCondition(() -> api.getInventory().contains(bestPickaxe), MethodProvider.random(750, 1550));
        api.getBank().depositAllExcept(bestPickaxe);
    }
}
