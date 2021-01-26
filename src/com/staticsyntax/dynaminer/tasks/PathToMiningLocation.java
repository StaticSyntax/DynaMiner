package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;

public class PathToMiningLocation extends Task {

    public PathToMiningLocation(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && !Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull();
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent;
        webWalkEvent = new WebWalkEvent(Location.MINING.getArea().getRandomPosition());
        Utils.initWebWalkEvent(webWalkEvent, script);
        script.getApi().execute(webWalkEvent);
    }
}
