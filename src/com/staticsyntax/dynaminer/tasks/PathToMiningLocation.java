package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Location;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.script.MethodProvider;

public class PathToMiningLocation extends Task {

    private WebWalkEvent webWalkEvent;

    public PathToMiningLocation(MethodProvider api) {
        super(api);
        webWalkEvent = new WebWalkEvent(Location.MINING.getArea().getCentralPosition());
        webWalkEvent.setPathPreferenceProfile(Utils.getStandardPathPreferenceProfile());
    }

    @Override
    public boolean canProcess() {
        return Pickaxe.playerHasUsable() && !Location.MINING.getArea().contains(api.myPlayer());
    }

    @Override
    public void process() {
        webWalkEvent.setEnergyThreshold(MethodProvider.random(1, 10));
        DynaMiner.getApi().execute(webWalkEvent);
    }
}
