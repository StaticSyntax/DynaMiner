package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class GetPickaxe extends Task {

    private Script script;

    public GetPickaxe(MethodProvider api, Script script) {
        super(api);
        this.script = script;
    }

    @Override
    public boolean canProcess() {
        return !Pickaxe.playerHasUsable();
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent = new WebWalkEvent(Location.getBanks());
        Utils.initWebWalkEvent(webWalkEvent);
        DynaMiner.getApi().execute(webWalkEvent);
        if(api.getBank().isOpen()) {
            if(Pickaxe.getBestUsableBanked() != null) {
                api.getBank().withdraw(Pickaxe.getBestUsableBanked().getName(), 1);
            } else {
                api.log("Unable to find usable Pickaxe in bank. Stopping script.");
                script.stop(false);
            }
        } else {
            try {
                DynaMiner.getApi().getBank().open();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
