package com.staticsyntax.dynaminer.data;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.api.ui.Skill;

public enum Rock {
    RUNE_ESSENCE(new int[] { 34773 }, "Rune essence", 1),
    CLAY(new int[] { 11362, 11363 }, "Clay", 1),
    COPPER(new int[] { 10943, 11161 }, "Copper ore", 1),
    TIN(new int[] { 11360, 11361 }, "Tin ore", 1),
    IRON(new int[] { 11364, 11365 }, "Iron ore", 15),
    SILVER(new int[] { 11368, 11369}, "Silver ore", 20),
    COAL(new int[] { 11366, 11367 }, "Coal", 30),
    GOLD(new int[] { 11370, 11371 }, "Gold ore", 40),
    MITHRIL(new int[] { 11372, 11373 }, "Mithril ore", 55),
    ADAMANT(new int[] { 11374, 11375 }, "Adamantite ore", 70),
    RUNITE(new int[] { 11376, 11377 }, "Runite ore", 85);

    private int[] ids;
    private String oreName;
    private int miningLevel;

    Rock(int[] ids, String oreName, int miningLevel) {
        this.ids = ids;
        this.oreName = oreName;
        this.miningLevel = miningLevel;
    }

    public boolean canMine() {
        return canMine(this);
    }

    public static boolean canMine(Rock rock) {
        return DynaMiner.getApi().getSkills().getVirtualLevel(Skill.MINING) >= rock.miningLevel;
    }

    public int[] getIds() {
        return ids;
    }

    public static String[] getOreNames() {
        String[] names = new String[Rock.values().length];
        for(int i = 0; i < Rock.values().length; i++) {
            names[i] = Rock.values()[i].oreName;
        }
        return names;
    }

    public String getOreName() {
        return oreName;
    }

    public int getMiningLevel() {
        return miningLevel;
    }
}
