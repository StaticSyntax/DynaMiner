package com.staticsyntax.progressiveminer.data;

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
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE, true);

    Location(Area area, int highestLevelAggressor, Rock[] rocks) {
        construct(area, false, highestLevelAggressor, rocks);
    }

    Location(Area area, boolean bank) {
        construct(area, bank, 0, new Rock[0]);
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