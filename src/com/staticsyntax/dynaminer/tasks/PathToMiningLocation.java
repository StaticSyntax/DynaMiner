package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.MethodProvider;

public class PathToMiningLocation extends Task {

    public PathToMiningLocation(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable() && !Location.MINING.getArea().contains(api.myPlayer());
    }

    @Override
    public void process() {
        WebWalkEvent webWalkEvent = new WebWalkEvent(Location.MINING.getArea().getCentralPosition());
        webWalkEvent.setPathPreferenceProfile(Utils.getStandardPathPreferenceProfile());
        webWalkEvent.setEnergyThreshold(MethodProvider.random(1, 10));
        DynaMiner.getApi().execute(webWalkEvent);
    }
}
