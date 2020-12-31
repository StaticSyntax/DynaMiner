package com.staticsyntax.dynaminer.data;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.MethodProvider;

public enum Pickaxe {
    BRONZE("Bronze pickaxe", 1, 1),
    IRON("Iron pickaxe", 1, 1),
    STEEL("Steel pickaxe", 6, 5),
    BLACK("Black pickaxe", 11, 10),
    MITHRIL("Mithril pickaxe", 21, 20),
    ADAMANT("Adamant pickaxe", 31, 30),
    RUNE("Rune pickaxe", 41, 40),
    DRAGON("Dragon pickaxe", 61, 60),
    DRAGON_OR("Dragon pickaxe (or)", 61, 60),
    INFERNAL("Infernal pickaxe", 61, 60),
    THIRD_AGE("3rd age pickaxe", 61, 65),
    CRYSTAL("Crystal pickaxe", 71, 70);

    private String name;
    private int miningLevel, attackLevel;

    Pickaxe(String name, int miningLevel, int attackLevel) {
        this.name = name;
        this.miningLevel = miningLevel;
        this.attackLevel = attackLevel;
    }

    public boolean canUse(MethodProvider api) {
        return canUse(this, api);
    }

    public static boolean canUse(Pickaxe pickaxe, MethodProvider api) {
        return api.getSkills().getVirtualLevel(Skill.MINING) >= pickaxe.miningLevel;
    }

    public boolean canWield(MethodProvider api) {
        return canWield(this, api);
    }

    public static boolean canWield(Pickaxe pickaxe, MethodProvider api) {
        return api.getSkills().getVirtualLevel(Skill.ATTACK) >= pickaxe.attackLevel;
    }

    public static boolean playerHasUsable(MethodProvider api) {
        return getCurrent(api) != null;
    }

    public static Pickaxe getCurrent(MethodProvider api) {
        for(int i = Pickaxe.values().length - 1; i >= 0; i--) {
            if((api.getInventory().contains(Pickaxe.values()[i].name)
                    || api.getEquipment().contains(Pickaxe.values()[i].name))
                    && api.getSkills().getVirtualLevel(Skill.MINING) >= Pickaxe.values()[i].miningLevel) {
                return Pickaxe.values()[i];
            }
        }
        return null;
    }

    public static Pickaxe getBestUsableBanked(MethodProvider api) {
        for(int i = Pickaxe.values().length - 1; i >= 0; i--) {
            if(api.getBank().contains(Pickaxe.values()[i].name)
                    && api.getSkills().getVirtualLevel(Skill.MINING) >= Pickaxe.values()[i].miningLevel) {
                return Pickaxe.values()[i];
            }
        }
        return null;
    }

    public static String[] getNames() {
        String[] names = new String[Pickaxe.values().length];
        for(int i = 0; i < Pickaxe.values().length; i++) {
            names[i] = Pickaxe.values()[i].name;
        }
        return names;
    }

    public String getName() {
        return name;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }
}
