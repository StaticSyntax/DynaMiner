package com.staticsyntax.progressiveminer.data;

import com.staticsyntax.progressiveminer.ProgressiveMiner;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

import java.util.ArrayList;
import java.util.List;

public enum Location {
    LUMBRIDGE_UPPER_BANK(Banks.LUMBRIDGE_UPPER, true),
    AL_KHARID_BANK(Banks.AL_KHARID, true),
    DRAYNOR_BANK(Banks.DRAYNOR, true),
    FALADOR_EAST_BANK(Banks.FALADOR_EAST, true),
    FALADOR_WEST_BANK(Banks.FALADOR_WEST, true),
    EDGEVILLE_BANK(Banks.EDGEVILLE, true),
    VARROCK_EAST_BANK(Banks.VARROCK_EAST, true),
    VARROCK_WEST_BANK(Banks.VARROCK_WEST, true),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE, true),

    LUMBRIDGE_EAST(new Area(3221, 3149, 3231, 3143),
            new Rock[] { Rock.COPPER, Rock.TIN }),
    LUMBRIDGE_WEST(new Area(3149, 3153, 3142, 3143),
            new Rock[] { Rock.COAL, Rock.MITHRIL, Rock.ADAMANT }),
    AL_KHARID_NORTH(new Area(3289, 3320, 3309, 3279), 14,
            new Rock[] { Rock.COPPER, Rock.TIN, Rock.IRON, Rock.COAL, Rock.SILVER, Rock.GOLD, Rock.MITHRIL, Rock.ADAMANT }),
    AL_KHARID_EAST(new Area(3399, 3173, 3406, 3166),
            new Rock[] { Rock.IRON, Rock.COAL }),
    RIMMINGTON(new Area(2968, 3249, 2988, 3232),
            new Rock[] { Rock.CLAY, Rock.COPPER, Rock.TIN, Rock.IRON, Rock.GOLD }),
    CRAFTING_GUILD(new Area(2937, 3291, 2943, 3276),
            new Rock[] { Rock.CLAY, Rock.SILVER, Rock.GOLD }),
    DWARVERN_MINE_EAST(new Area(3055, 9829, 3051, 9810),
            new Rock[] { Rock.CLAY, Rock.TIN, Rock.IRON }),
    DWARVERN_MINE_WEST(new Area(3034, 9829, 3020, 9797),
            new Rock[] { Rock.CLAY, Rock.COPPER, Rock.TIN, Rock.IRON }),
    DWARVERN_MINE_SOUTH(new Area(3033, 9801, 3057, 9760), 32,
            new Rock[] { Rock.COPPER, Rock.TIN, Rock.IRON, Rock.COAL, Rock.GOLD, Rock.MITHRIL, Rock.ADAMANT }),
    MINING_GUILD(new Area(3025, 9754, 3055, 9731),
            new Rock[] { Rock.IRON, Rock.COAL, Rock.MITHRIL, Rock.ADAMANT }),
    BARBARIAN_VILLAGE(new Area(3078, 3432, 3084, 3417),
            new Rock[] { Rock.TIN, Rock.COAL }),
    EDGEVILLE_DUNGEON(new Area(3145, 9868, 3134, 9880), 25,
            new Rock[] { Rock.COPPER, Rock.TIN, Rock.IRON, Rock.COAL, Rock.SILVER, Rock.MITHRIL, Rock.ADAMANT }),
    VARROCK_EAST(new Area(3280, 3371, 3291, 3360),
            new Rock[] { Rock.COPPER, Rock.TIN, Rock.IRON }),
    VARROCK_WEST(new Area(3184, 3380, 3171, 3364), 6,
            new Rock[] { Rock.CLAY, Rock.TIN, Rock.IRON, Rock.SILVER }),
    ESSENCE_MINE(new Area(14753, 2637, 14796, 2597),
            new Rock[] { Rock.RUNE_ESSENCE}),
    WILDERNESS_SOUTH(new Area(3101, 3563, 3106, 3571), 25,
            new Rock[] { Rock.IRON, Rock.COAL }),
    WILDERNESS_SOUTH_WEST(new Area(3025, 3585, 3008, 3599), 22,
            new Rock[] { Rock.COAL }),
    WILDERNESS_BANDIT_CAMP(new Area(3073, 3747, 3102, 3775), 28,
            new Rock[] { Rock.IRON, Rock.COAL, Rock.MITHRIL, Rock.ADAMANT }),
    WILDERNESS_RUNITE(new Area(3058, 3886, 3061, 3883), 34,
            new Rock[] { Rock.RUNITE });

    Location(Area area, boolean bank) {
        construct(area, bank, 0, new Rock[0]);
    }

    Location(Area area, Rock[] rocks) {
        construct(area, false, 0, rocks);
    }

    Location(Area area, int highestLevelAggressor, Rock[] rocks) {
        construct(area, false, highestLevelAggressor, rocks);
    }

    private void construct(Area area, boolean bank, int highestLevelAggressor, Rock[] rocks) {
        this.area = area;
        this.bank = bank;
        this.highestLevelAggressor = highestLevelAggressor;
        this.rocks = rocks;
    }

    private Area area;
    private boolean bank = false;
    private int highestLevelAggressor = 0;
    private Rock[] rocks = new Rock[0];

    public static Area[] getBanks() {
        return getAreas(true);
    }

    public static Area[] getMiningAreas() {
        return getAreas(false);
    }

    private static Area[] getAreas(boolean banks) {
        List<Area> areas = new ArrayList<>();
        for(Location loc : Location.values()) {
            if(banks) if(loc.isBank()) areas.add(loc.getArea());
            else if(!loc.isBank()) areas.add(loc.getArea());
        }
        return areas.toArray(new Area[0]);
    }

    public static boolean isPlayerSafe(Location loc) {
        if(ProgressiveMiner.getApi().getCombat().getCombatLevel() > loc.highestLevelAggressor * 2) return true;
        else return false;
    }

    public boolean isPlayerSafe() {
        return isPlayerSafe(this);
    }

    public Area getArea() {
        return area;
    }

    public boolean isBank() {
        return bank;
    }

    public int getHighestLevelAggressor() {
        return highestLevelAggressor;
    }
}