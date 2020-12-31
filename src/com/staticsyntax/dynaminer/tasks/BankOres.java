package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;

public class BankOres extends Task {

    public BankOres(DynaMiner script) {
        super(script);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && !script.getMiningSettings().isPowerMining() && api.getInventory().isFull();
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent = new WebWalkEvent(Location.getBanks(api));
        Utils.initWebWalkEvent(webWalkEvent, script);
        script.getApi().execute(webWalkEvent);

        if(!api.getBank().isOpen() && !api.getDepositBox().isOpen()) {
            boolean depositBoxFound = false;
            if(script.getMiningSettings().isUsingDepositBoxes()) {
                depositBoxFound = api.getDepositBox().open();
            }
            if(!script.getMiningSettings().isUsingDepositBoxes() || !depositBoxFound) {
                openBank();
            }
        } else {
            if(api.getBank().isOpen()) {
                api.getBank().depositAll(Rock.getAllOreNames());
            } else if(api.getDepositBox().isOpen()) {
                api.getDepositBox().depositAll(Rock.getAllOreNames());
            }
            script.getBehaviourProfile().generateNewProfile();
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
