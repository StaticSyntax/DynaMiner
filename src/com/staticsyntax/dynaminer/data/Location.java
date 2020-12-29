package com.staticsyntax.dynaminer.data;

import com.staticsyntax.dynaminer.DynaMiner;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

import java.util.ArrayList;

public enum Location {
    MINING(new Area(-1, -1, -1, -1), false),
    AL_KHARID(Banks.AL_KHARID, false),
    ARCEUUS_HOUSE(Banks.ARCEUUS_HOUSE, true),
    ARDOUGNE_NORTH(Banks.ARDOUGNE_NORTH, true),
    ARDOUGNE_SOUTH(Banks.ARDOUGNE_SOUTH, true),
    CAMELOT(Banks.CAMELOT, true),
    CANIFIS(Banks.CANIFIS, true),
    CASTLE_WARS(Banks.CASTLE_WARS, false),
    CATHERBY(Banks.CATHERBY, true),
    DRAYNOR(Banks.DRAYNOR, false),
    DUEL_ARENA(Banks.DUEL_ARENA, false),
    EDGEVILLE(Banks.EDGEVILLE, false),
    FALADOR_EAST(Banks.FALADOR_EAST, false),
    FALADOR_WEST(Banks.FALADOR_WEST, false),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD, true),
    GRAND_EXCHANGE(Banks.GRAND_EXCHANGE, false),
    HOSIDIUS_HOUSE(Banks.HOSIDIUS_HOUSE, true),
    LOVAKENGJ_HOUSE(Banks.LOVAKENGJ_HOUSE, true),
    LOVAKITE_MINE(Banks.LOVAKITE_MINE, true),
    LUMBRIDGE_LOWER(Banks.LUMBRIDGE_LOWER, true),
    LUMBRIDGE_UPPER(Banks.LUMBRIDGE_UPPER, false),
    PEST_CONTROL(Banks.PEST_CONTROL, true),
    PISCARILIUS_HOUSE(Banks.PISCARILIUS_HOUSE, true),
    SHAYZIEN_HOUSE(Banks.SHAYZIEN_HOUSE, true),
    TZHAAR(Banks.TZHAAR, true),
    VARROCK_EAST(Banks.VARROCK_EAST, false),
    VARROCK_WEST(Banks.VARROCK_WEST, false),
    YANNILE(Banks.YANILLE, true),
    BURGH_DE_ROTT(new Area(3496, 3213, 3499, 3210), true),
    CORSAIR_COVE(new Area(2569, 2864, 2571, 2865), false),
    CRAFTING_GUILD(new Area(2933, 3284, 2936, 3281), false),
    FOSSIL_ISLAND_LAND(new Area(3739, 3806, 3742, 3802), true),
    FOSSIL_ISLAND_VOLCANO(new Area(3816, 3811, 3820, 3806), true),
    FOSSIL_ISLAND_SEA(new Area(3768, 3900, 3771, 3896), true),
    MINING_GUILD(new Area(3011, 9720, 3013, 9716), true),
    JATISZO(new Area(2415, 3803, 2418, 3801), true),
    NEITIZNOT(new Area(2335, 3808, 2337, 3805), true),
    NARDAH(new Area(3424, 2892, 3430, 2889), true),
    PISCATORIS(new Area(2327, 3690, 2332, 3687), true),
    SHILO_VILLAGE(new Area(2849, 2955, 2855, 2953), true),
    SULPHUR_MINE(new Area(1453, 3859, 1458, 3856), true),
    KOUREND_COOKING(new Area(1653, 3613, 1658, 3607), true),
    KOUREND_SAND_CRABS(new Area(1717, 3466, 1722, 3463), true),
    KOUREND_CASTLE(new Area(1610, 3683, 1613, 3680).setPlane(2), true);

    Location(Area area, boolean membersOnly) {
        this.area = area;
        this.membersOnly = membersOnly;
    }

    private Area area;
    private boolean membersOnly;

    public static Area[] getBanks() {
        boolean includeMembersBanks = DynaMiner.getApi().getWorlds().isMembersWorld();
        ArrayList<Area> bankAreas = new ArrayList<>();
        for(int i = 1; i < Location.values().length; i++) {
            if(includeMembersBanks) {
                bankAreas.add(Location.values()[i].getArea());
            } else {
                if(!Location.values()[i].membersOnly) bankAreas.add(Location.values()[i].getArea());
            }
        }
        return bankAreas.toArray(new Area[0]);
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
