package com.staticsyntax.dynaminer.tasks;

import com.staticsyntax.dynaminer.DynaMiner;
import com.staticsyntax.dynaminer.data.Pickaxe;
import com.staticsyntax.dynaminer.data.Rock;
import com.staticsyntax.dynaminer.utils.Utils;

public class BankJunk extends Task {

    public BankJunk(DynaMiner script, String name) {
        super(script, name);
    }

    @Override
    public boolean canProcess() {
        boolean canDeposit = api.getBank().isOpen() || api.getDepositBox().isOpen();
        if(Pickaxe.playerHasUsable(api)) {
            return !api.getInventory().onlyContains(Utils.concat(new String[]{Pickaxe.getCurrent(api).getName()}, Rock.getAllOreNames())) && canDeposit;
        } else {
            return !api.getInventory().onlyContains(Utils.concat(Pickaxe.getNames(), Rock.getAllOreNames())) && canDeposit;
        }
    }

    @Override
    public void process() {
        if(api.getBank().isOpen()) {
            if(Pickaxe.playerHasUsable(api)) {
                api.getBank().depositAllExcept(Pickaxe.getCurrent(api).getName());
            } else {
                api.getBank().depositAllExcept(Pickaxe.getNames());
            }
        } else if(api.getDepositBox().isOpen()) {
            if(Pickaxe.playerHasUsable(api)) {
                api.getDepositBox().depositAllExcept(Pickaxe.getCurrent(api).getName());
            } else {
                api.getDepositBox().depositAllExcept(Pickaxe.getNames());
            }
        }
    }
}
