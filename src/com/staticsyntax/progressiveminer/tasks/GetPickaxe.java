package com.staticsyntax.progressiveminer.tasks;

import com.staticsyntax.progressiveminer.ProgressiveMiner;
import com.staticsyntax.progressiveminer.data.Location;
import com.staticsyntax.progressiveminer.data.Pickaxe;
import com.staticsyntax.progressiveminer.utils.Utils;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.event.webwalk.PathPreferenceProfile;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

public class GetPickaxe extends Task {

    private Script script;
    private WebWalkEvent webWalkEvent;

    public GetPickaxe(MethodProvider api, Script script) {
        super(api);
        this.script = script;
        webWalkEvent = new WebWalkEvent(Location.getBanks());
        PathPreferenceProfile ppp = new PathPreferenceProfile();
        ppp.setAllowTeleports(true);
        webWalkEvent.setPathPreferenceProfile(ppp);
    }

    @Override
    public boolean canProcess() {
        return !Pickaxe.playerHasUsable();
    }

    @Override
    public void process() {
        webWalkEvent.setEnergyThreshold(MethodProvider.random(1, 10));
        ProgressiveMiner.getApi().execute(webWalkEvent);
        if(api.getBank().isOpen()) {
            if(Pickaxe.getBestUsableBanked() != null) {
                api.getBank().withdraw(Pickaxe.getBestUsableBanked().getName(), 1);
            } else {
                api.log("Unable to find usable Pickaxe in bank. Stopping script.");
                script.stop(false);
            }
        } else {
            Utils.openBank();
        }
    }
}