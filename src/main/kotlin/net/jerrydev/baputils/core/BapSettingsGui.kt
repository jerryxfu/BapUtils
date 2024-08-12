//@file:JvmName("BapConfigGui")

package net.jerrydev.baputils.core

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object BapSettingsGui : Vigilant(File("./config/jerrydev/baputils/modconfig.toml")) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Global toggle (unavailable)",
        description = "[WIP] Enable all features of this mod",
        category = "General",
        subcategory = "Master"
    )
    var modMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Client chat verbose",
        description = "Let BapUtils show you more messages (client chat).",
        category = "General"
    )
    var clientChatVerbose = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Client chat debug",
        description = "Let BapUtils show you debug messages (client chat).",
        category = "General"
    )
    var clientChatDebug = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Speedometer",
        description = "Show your current speed at the top left of your screen.",
        category = "General"
    )
    var speedometer = true

    @Property(
        type = PropertyType.SLIDER,
        min = 1,
        max = 64,
        name = "Radar scan range",
        description = "Range in blocks for the radar (/bap radar|r) to scan for entities.",
        category = "General"
    )
    var radarScanRange = 16

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle radar LOS check",
        description = "Also performs a line-of-sight check on scanned mobs.",
        category = "General"
    )
    var radarLOSCheck = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle entity count display",
        description = "Toggle the display of the entity count in the bottom left corner of the screen.",
        category = "General"
    )
    var displayEntityCount = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow Party Takeover",
        description = "Allow other players to take your party.",
        category = "Party",
    )
    var partyTakeoverMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Party Takeover trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to take your party.",
        category = "Party",
    )
    var partyTakeoverTrustedOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow Party Warp",
        description = "Allow other players to warp your party.",
        category = "Party",
    )
    var partyWarpMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Party Takeover trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to warp your party.",
        category = "Party",
    )
    var partyWarpTrustedOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Allow JoinDungeon",
        description = "Allow other players to join a dungeon run on your behalf.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonMaster = true

    @Property(
        type = PropertyType.SWITCH,
        name = "JoinDungeon trusted only (unavailable)",
        description = "[WIP] Only allow trusted players to join a dungeon run on your behalf.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonTrustedOnly = false

    @Property(
        type = PropertyType.CHECKBOX,
        name = "Allow AutoJoinIn",
        description = "Listen for 'going/go in 5s' messages and enter a run after the delay (0 to 127s). Optionally specify the floor: 'going in 5s f7'.",
        category = "Party",
        subcategory = "Dungeons"
    )
    var joinDungeonAutoJoinIn = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Auto-requeue",
        description = "Automatically requeue after a dungeon run.",
        category = "Dungeons"
    )
    var autoRequeueMaster = false

    @Property(
        type = PropertyType.SLIDER,
        min = 0,
        max = 30,
        name = "Auto-requeue delay",
        description = "Delay in seconds before requeueing after a dungeon run. There is a 3s-ish delay before auto requeue kicks in.",
        category = "Dungeons"
    )
    var autoRequeueDelay = 5

    @Property(
        type = PropertyType.SWITCH,
        name = "Dungeon breakdown",
        description = "Show a summary of major events after a dungeon run.",
        category = "Dungeons"
    )
    var dungeonBreakdown = true

    @Property(
        type = PropertyType.TEXT,
        name = "Dungeon death message",
        description = "Custom message to send when a player dies in a dungeon. Clear the field to disable.",
        category = "Dungeons"
    )
    var dungeonDeathMsg = ""

    @Property(
        type = PropertyType.TEXT,
        name = "Puzzle fail message",
        description = "Custom message to send when a player fails a puzzle. Clear the field to disable.",
        category = "Dungeons"
    )
    var puzzleFailMsg = ""

    init {
        super.initialize()

        super.setCategoryDescription(
            "General",
            "General settings"
        )
        /* super.setSubcategoryDescription(
            "General",
            "Master",
            "Master switches"
        ); */

        super.setCategoryDescription(
            "Party",
            "Hypixel party utilities"
        )
        /* super.setSubcategoryDescription(
            "Party",
            "Dungeons",
            "Dungeon-related features"
        ); */

        super.setCategoryDescription(
            "Dungeons",
            "Dungeon-related features"
        )

        super.setCategoryDescription(
            "Slayers",
            "Slayer-related features"
        )

        super.setCategoryDescription(
            "Utility",
            "Utility features"
        )

        addDependency("partyTakeoverTrustedOnly", "partyTakeoverMaster")
        addDependency("joinDungeonTrustedOnly", "joinDungeonMaster")
        addDependency("partyWarpTrustedOnly", "partyWarpMaster")
        addDependency("autoRequeueDelay", "autoRequeueMaster")
    }
}
