package com.staticsyntax.progressiveminer.tasks;

import com.staticsyntax.progressiveminer.data.Pickaxe;
import com.staticsyntax.progressiveminer.data.Rock;
import com.staticsyntax.progressiveminer.utils.Utils;
import org.osbot.rs07.script.MethodProvider;

public class BankJunk extends Task {

    public BankJunk(MethodProvider api) {
        super(api);
    }

    @Override
    public boolean canProcess() {
        return !api.getInventory().onlyContains(Utils.concat(Pickaxe.getNames(), Rock.getOreNames())) && api.getBank().isOpen();
    }

    @Override
    public void process() {
        api.getBank().depositAllExcept(Utils.concat(Pickaxe.getNames(), Rock.getOreNames()));
    }
}
