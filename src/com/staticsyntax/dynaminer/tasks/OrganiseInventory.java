package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;
import org.osbot.rs07.utility.Condition;

public class OrganiseInventory extends Task {

    public OrganiseInventory(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        if(Pickaxe.playerHasUsable(api)) {
            if(api.getInventory().contains(Pickaxe.getCurrent(api).getName())) {
                return api.getInventory().getSlot(Pickaxe.getCurrent(api).getName()) != 0;
            }
        }
        return false;
    }

    @Override
    public void process() {
        swapInventoryItems(api.getInventory().getSlot(Pickaxe.getCurrent(api).getName()), 0);
    }

    private boolean swapInventoryItems(int slot1, int slot2) {
        if (api.getInventory().isItemSelected()) {
            api.getInventory().deselectItem();
        }
        return api.getMouse().continualClick(api.getInventory().getMouseDestination(slot1), new Condition() {
            @Override
            public boolean evaluate() {
                api.getMouse().move(api.getInventory().getMouseDestination(slot2), true);
                return api.getInventory().getMouseDestination(slot2).getBoundingBox().contains(api.getMouse().getPosition());
            }
        });
    }
}
