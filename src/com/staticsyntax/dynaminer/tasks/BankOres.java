package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.MethodProvider;

public class BankOres extends Task {

    public BankOres(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable() && !DynaMiner.getMiningSettings().isPowerMining() && api.getInventory().isFull();
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent = new WebWalkEvent(Location.getBanks());
        Utils.initWebWalkEvent(webWalkEvent);
        DynaMiner.getApi().execute(webWalkEvent);

        if(!api.getBank().isOpen() && !api.getDepositBox().isOpen()) {
            boolean depositBoxFound = false;
            if(DynaMiner.getMiningSettings().isUsingDepositBoxes()) {
                depositBoxFound = api.getDepositBox().open();
            }
            if(!DynaMiner.getMiningSettings().isUsingDepositBoxes() || !depositBoxFound) {
                openBank();
            }
        } else {
            if(api.getBank().isOpen()) {
                api.getBank().depositAll(Rock.getAllOreNames());
            } else if(api.getDepositBox().isOpen()) {
                api.getDepositBox().depositAll(Rock.getAllOreNames());
            }
            DynaMiner.getBehaviourProfile().generateNewProfile();
        }
    }

    private void openBank() {
        try {
            api.getBank().open();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
