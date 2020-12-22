package com.staticsyntax.dynaminer.data;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

public enum Location {
    MINING(new Area(-1, -1, -1, -1)),
    LUMBRIDGE_UPPER_BANK(Banks.LUMBRIDGE_UPPER),
    AL_KHARID_BANK(Banks.AL_KHARID),
    DRAYNOR_BANK(Banks.DRAYNOR),
    FALADOR_EAST_BANK(Banks.FALADOR_EAST),
    FALADOR_WEST_BANK(Banks.FALADOR_WEST),
    EDGEVILLE_BANK(Banks.EDGEVILLE),
    VARROCK_EAST_BANK(Banks.VARROCK_EAST),
    VARROCK_WEST_BANK(Banks.VARROCK_WEST),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE);

    Location(Area area) {
        this.area = area;
    }

    private Area area;

    public static Area[] getBanks() {
        Area[] areas = new Area[Location.values().length - 1];
        for(int i = 1; i < Location.values().length; i++) {
            areas[i-1] = Location.values()[i].getArea();
        }
        return areas;
    }

    public Area getMiningArea() {
        return Location.MINING.getArea();
    }

    public void setMiningArea() {

    }

    public Area getArea() {
        return area;
    }
}
