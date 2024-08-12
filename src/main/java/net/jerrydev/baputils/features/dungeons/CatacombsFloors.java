package net.jerrydev.baputils.features.dungeons;

import javax.annotation.Nullable;

public enum CatacombsFloors {
    FLOOR0("f0", false, "CATACOMBS_ENTRANCE", "The Catacombs, Entrance"),
    FLOOR1("f1", false, "CATACOMBS_FLOOR_ONE", "The Catacombs, Floor I"),
    FLOOR2("f2", false, "CATACOMBS_FLOOR_TWO", "The Catacombs, Floor II"),
    FLOOR3("f3", false, "CATACOMBS_FLOOR_THREE", "The Catacombs, Floor III"),
    FLOOR4("f4", false, "CATACOMBS_FLOOR_FOUR", "The Catacombs, Floor IV"),
    FLOOR5("f5", false, "CATACOMBS_FLOOR_FIVE", "The Catacombs, Floor V"),
    FLOOR6("f6", false, "CATACOMBS_FLOOR_SIX", "The Catacombs, Floor VI"),
    FLOOR7("f7", false, "CATACOMBS_FLOOR_SEVEN", "The Catacombs, Floor VII"),
    MASTER0("m0", true, "MASTER_CATACOMBS_ENTRANCE", "MM The Catacombs, Entrance"),
    MASTER1("m1", true, "MASTER_CATACOMBS_FLOOR_ONE", "MM The Catacombs, Floor I"),
    MASTER2("m2", true, "MASTER_CATACOMBS_FLOOR_TWO", "MM The Catacombs, Floor II"),
    MASTER3("m3", true, "MASTER_CATACOMBS_FLOOR_THREE", "MM The Catacombs, Floor III"),
    MASTER4("m4", true, "MASTER_CATACOMBS_FLOOR_FOUR", "MM The Catacombs, Floor IV"),
    MASTER5("m5", true, "MASTER_CATACOMBS_FLOOR_FIVE", "MM The Catacombs, Floor V"),
    MASTER6("m6", true, "MASTER_CATACOMBS_FLOOR_SIX", "MM The Catacombs, Floor VI"),
    MASTER7("m7", true, "MASTER_CATACOMBS_FLOOR_SEVEN", "MM The Catacombs, Floor VII");

    @Nullable
    public final String floorCode;
    public final boolean isMaster;
    public final String commandCode;
    public final String chatName;
    public final String shortName;

    CatacombsFloors(String _floorCode, boolean _isMaster, String _commandCode, String _chatName) {
        this.floorCode = _floorCode;
        this.isMaster = _isMaster;
        this.commandCode = _commandCode;
        this.chatName = _chatName;
        this.shortName = _chatName.replace("The Catacombs, ", "").replace("MM Catacombs, ", "MM ");
    }

    @Nullable
    public static CatacombsFloors getFloorByCode(String code) {
        for (final CatacombsFloors floor : CatacombsFloors.values()) {
            if (floor.floorCode.equals(code.toLowerCase())) {
                return floor;
            }
        }
        return null;
    }

    @Nullable
    public static CatacombsFloors getFloorByChatName(String name) {
        for (final CatacombsFloors floor : CatacombsFloors.values()) {
            if (floor.chatName.equals(name)) {
                return floor;
            }
        }
        return null;
    }
}
