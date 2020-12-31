package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;

public class PathToMiningLocation extends Task {

    public PathToMiningLocation(DynaMiner script) {
        super(script);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable(api) && !Location.MINING.getArea().contains(api.myPlayer()) && !api.getInventory().isFull();
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent;
        if(script.getMiningSettings().getRadius() == 1) {
            webWalkEvent = new WebWalkEvent(Location.MINING.getArea().getRandomPosition());
        } else {
            webWalkEvent = new WebWalkEvent(Location.MINING.getArea().getCentralPosition());
        }
        Utils.initWebWalkEvent(webWalkEvent, script);
        script.getApi().execute(webWalkEvent);
    }
}
