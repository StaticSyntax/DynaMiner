package com.staticsyntax.progressiveminer.data;

import com.staticsyntax.progressiveminer.ProgressiveMiner;
import org.osbot.rs07.api.ui.Skill;

public enum Rock {
    RUNE_ESSENCE(new int[] { 34773 }, 1),
    CLAY(new int[] { 11362, 11363 }, 1),
    COPPER(new int[] { 10943, 11161 }, 1),
    TIN(new int[] { 11360, 11361 }, 1),
    IRON(new int[] { 11364, 11365 }, 15),
    SILVER(new int[] { 11368, 11369}, 20),
    COAL(new int[] { 11366, 11367 }, 30),
    GOLD(new int[] { 11370, 11371 }, 40),
    MITHRIL(new int[] { 11372, 11373 }, 55),
    ADAMANT(new int[] { 11374, 11375 }, 70),
    RUNITE(new int[] { 11376, 11377 }, 85);

    private int[] ids;
    private int miningLevel;

    Rock(int[] ids, int miningLevel) {
        this.ids = ids;
        this.miningLevel = miningLevel;
    }

    public boolean canMine() {
        return canMine(this);
    }

    public static boolean canMine(Rock rock) {
        return ProgressiveMiner.getApi().getSkills().getVirtualLevel(Skill.MINING) >= rock.miningLevel;
    }

    public int[] getIds() {
        return ids;
    }

    public int getMiningLevel() {
        return miningLevel;
    }
}