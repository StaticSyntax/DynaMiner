package com.staticsyntax.dynaminer.data;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

public enum Location {
    MINING(new Area(-1, -1, -1, -1)),
    AL_KHARID(Banks.AL_KHARID),
    ARCEUUS_HOUSE(Banks.ARCEUUS_HOUSE),
    ARDOUGNE_NORTH(Banks.ARDOUGNE_NORTH),
    ARDOUGNE_SOUTH(Banks.ARDOUGNE_SOUTH),
    CAMELOT(Banks.CAMELOT),
    CANIFIS(Banks.CANIFIS),
    CASTLE_WARS(Banks.CASTLE_WARS),
    CATHERBY(Banks.CATHERBY),
    DRAYNOR(Banks.DRAYNOR),
    DUEL_ARENA(Banks.DUEL_ARENA),
    EDGEVILLE(Banks.EDGEVILLE),
    FALADOR_EAST(Banks.FALADOR_EAST),
    FALADOR_WEST(Banks.FALADOR_WEST),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE),
    HOSIDIUS_HOUSE(Banks.HOSIDIUS_HOUSE),
    LOVAKENGJ_HOUSE(Banks.LOVAKENGJ_HOUSE),
    LOVAKITE_MINE_MINE(Banks.LOVAKITE_MINE),
    LUMBRIDGE_LOWER(Banks.LUMBRIDGE_LOWER),
    LUMBRIDGE_UPPER(Banks.LUMBRIDGE_UPPER),
    PEST_CONTROL(Banks.PEST_CONTROL),
    PISCARILIUS_HOUSE(Banks.PISCARILIUS_HOUSE),
    SHAYZIEN_HOUSE(Banks.SHAYZIEN_HOUSE),
    TZHAAR(Banks.TZHAAR),
    VARROCK_EAST(Banks.VARROCK_EAST),
    VARROCK_WEST(Banks.VARROCK_WEST),
    YANNILE(Banks.YANILLE),
    BURGH_DE_ROTT(new Area(3496, 3213, 3499, 3210)),
    CORSAIR_COVE(new Area(2569, 2864, 2571, 2865)),
    CRAFTING_GUILD(new Area(2933, 3284, 2936, 3281)),
    FOSSIL_ISLAND_LAND(new Area(3739, 3806, 3742, 3802)),
    FOSSIL_ISLAND_VOLCANO(new Area(3816, 3811, 3820, 3806)),
    FOSSIL_ISLAND_SEA(new Area(3768, 3900, 3771, 3896)),
    MINING_GUILD(new Area(3011, 9720, 3013, 9716)),
    JATISZO(new Area(2415, 3803, 2418, 3801)),
    NEITIZNOT(new Area(2335, 3808, 2337, 3805)),
    NARDAH(new Area(3424, 2892, 3430, 2889)),
    PISCATORIS(new Area(2327, 3690, 2332, 3687)),
    SHILO_VILLAGE(new Area(2849, 2955, 2855, 2953)),
    SULPHUR_MINE(new Area(1453, 3859, 1458, 3856)),
    KOUREND_COOKING(new Area(1653, 3613, 1658, 3607)),
    KOUREND_SAND_CRABS(new Area(1717, 3466, 1722, 3463)),
    KOUREND_CASTLE(new Area(1610, 3683, 1613, 3680).setPlane(2));

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

    public static void setMiningArea() {
        int x1, y1, x2, y2;
        x1 = DynaMiner.getApi().myPosition().getX() - DynaMiner.getMiningSettings().getRadius();
        y1 = DynaMiner.getApi().myPosition().getY() + DynaMiner.getMiningSettings().getRadius();
        x2 = DynaMiner.getApi().myPosition().getX() + DynaMiner.getMiningSettings().getRadius();
        y2 = DynaMiner.getApi().myPosition().getY() - DynaMiner.getMiningSettings().getRadius();
        Location.MINING.area = new Area(x1, y1, x2, y2);
    }

    public Area getArea() {
        return area;
    }
}
