package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Utils;
import org.osbot.rs07.script.MethodProvider;

public class BankJunk extends Task {

    public BankJunk(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return !api.getInventory().onlyContains(Utils.concat(Pickaxe.getNames(), Rock.getAllOreNames())) && api.getBank().isOpen();
    }

    @Override
    public void process() {
        api.getBank().depositAllExcept(Pickaxe.getNames());
    }
}
