package com.staticsyntax.dynaminer.data;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.api.ui.Skill;

import java.util.ArrayList;

public enum Rock {
    ESSENCE(new int[] {34773},
            new String[] {"Rune essence", "Pure essence"},
            1),
    CLAY(new int[] {11362, 11363},
            new String[] {"Clay"},
            1),
    COPPER(new int[] {10943, 11161},
            new String[] {"Copper ore"},
            1),
    TIN(new int[] {11360, 11361},
            new String[] {"Tin ore"},
            1),
    IRON(new int[] {11364, 11365},
            new String[] {"Iron ore"},
            15),
    SILVER(new int[] {11368, 11369},
            new String[] {"Silver ore"},
            20),
    VOLCANIC_ASH(new int[] {30985},
            new String[] {"Soda ash", "Volcanic ash"},
            22),
    COAL(new int[] {11366, 11367},
            new String[] {"Coal"},
            30),
    SANDSTONE(new int[] {11386},
            new String[] {"Sandstone (1kg)", "Sandstone (2kg)", "Sandstone (5kg)", "Sandstone (10kg)"},
            35),
    GOLD(new int[] {11370, 11371},
            new String[] {"Gold ore"},
            40),
    GEM(new int[] {11380},
            new String[] {"Uncut opal", "Uncut jade", "Uncut red topaz", "Uncut sapphire", "Uncut emerald", "Uncut ruby", "Uncut diamond"},
            40),
    VOLCANIC_SULPHUR(new int[] {28496, 28497, 28498},
            new String[] {"Volcanic sulphur"},
            42),
    GRANITE(new int[] {11387},
            new String[] {"Granite (500g)", "Granite (2kg)", "Granite (5kg)"},
            45),
    MITHRIL(new int[] {11372, 11373},
            new String[] {"Mithril ore"},
            55),
    ADAMANT(new int[] {11374, 11375},
            new String[] {"Adamantite ore"},
            70),
    RUNITE(new int[] {11376, 11377},
            new String[] {"Runite ore"},
            85),
    AMETHYST(new int[] {30371, 30372},
            new String[] {"Amethyst"},
            92);

    private int[] ids;
    private String[] oreNames;
    private int miningLevel;
    private boolean target = false;

    Rock(int[] ids, String[] oreNames, int miningLevel) {
        this.ids = ids;
        this.oreNames = oreNames;
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

    public static String[] getAllOreNames() {
        ArrayList<String> allOreNames = new ArrayList<>();
        for(Rock rock : Rock.values()) {
            for(String name : rock.getOreNames()) {
                allOreNames.add(name);
            }
        }
        return allOreNames.toArray(new String[0]);
    }

    public String[] getOreNames() {
        return oreNames;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    public boolean isTarget() {
        return target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }
}
