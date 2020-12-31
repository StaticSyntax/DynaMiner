package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;

public class GetPickaxe extends Task {

    public GetPickaxe(DynaMiner script) {
        super(script);
    }

    @Override
    public boolean canProcess() {
        return !Pickaxe.playerHasUsable(api);
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent = new WebWalkEvent(Location.getBanks(api));
        Utils.initWebWalkEvent(webWalkEvent, script);
        script.getApi().execute(webWalkEvent);
        if(api.getBank().isOpen()) {
            if(Pickaxe.getBestUsableBanked(api) != null) {
                api.getBank().withdraw(Pickaxe.getBestUsableBanked(api).getName(), 1);
            } else {
                api.log("Unable to find usable Pickaxe in bank. Stopping script.");
                script.stop(false);
            }
        } else {
            try {
                script.getApi().getBank().open();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
