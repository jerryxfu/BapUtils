package net.jerrydev.baputils;

import net.jerrydev.baputils.utils.ChatEmojis;
import org.intellij.lang.annotations.RegExp;

import java.util.Arrays;
import java.util.List;

public final class Constants {
    public static final String kModId = "baputils";
    public static final String kModName = "BapUtils";
    public static final String kModVersion = "0.9.0";
    public static final String kUserAgent = kModId + "/" + kModVersion;
    public static final String kGitHubRepo = "https://github.com/AspectOfJerry/BapUtils/";
    public static final String kGitHubIssues = kGitHubRepo + "issues";
    public static final String kModDocs = "https://bap.jerrydev.net";

    public static final List<String> kModAdmins = Arrays.asList("AspectOfJerry", "Tomassy");
    public static final List<String> kModMvps = Arrays.asList(kModAdmins + "FailingPig", "FishingIsMagic");

    public static final String kClientPrefix = "[Bap]";
    public static final String kServerPrefix = "bap";
    public static final byte kChatDelayMs = 50;
    public static final int kServerChatDelayMs = 250;

    @RegExp
    public static final String kMcUserP = "[a-zA-Z0-9_]{2,16}";

    // chat
    @RegExp // Source: net.minecraft.util.StringUtils
    public static final String kColorCodeP = "(?i)\\u00A7[0-9A-FK-OR]";
    @RegExp
    public static final String kHypixelRankP = "\\[[A-Z+]+?] ";
    @RegExp
    public static final String kCatacombsJoinP = "-+?\\n(" + kMcUserP + ") entered (.*, .*)!\\n-+?";
    @RegExp
    public static final String kAutoJoinInP = "^Party > (" + kMcUserP + "): (?:go|enter)(?:ing)? (?:in )?(now|\\d+).*?([fm][0-7])?$";
    public static final List<String> kPLeaderP = Arrays.asList(
        "^Party Leader: (" + kMcUserP + ") ●$",
        "^You have joined (" + kMcUserP + ")'s party!$",
        "^The party was transferred to (" + kMcUserP + ") by (.*)$",
        "(" + kMcUserP + ") invited (?:" + kMcUserP + ") to the party! They have 60 seconds to accept\\.$"
    );
    // remove duplicates here from kPLeaderP !
    public static final List<String> kInPartyP = Arrays.asList(
//        "^You have joined (?:" + kMcUserP + ")'s party!$",
//        "(?:" + kMcUserP + ") invited (?:" + kMcUserP + ") to the party! They have 60 seconds to accept\\.$",
        "(?:" + kMcUserP + ") joined the party\\.$",
        "(?:" + kMcUserP + ") is already in the party\\.$"
    );
    public static final List<String> kNotInPartyP = Arrays.asList(
        "You are not currently in a party\\.$",
        "You are not in a party right now\\.$",
        "You left the party\\.$",
        "You have been kicked from the party by (?:" + kMcUserP + ")\\.",
        "(?:" + kMcUserP + ") has disbanded the party!$",
        "The party was disbanded because all invites expired and the party was empty\\.$"
    );
    @RegExp
    public static final String kPDisbandP = "";
    @RegExp
    public static final String kDungeonEndP = "^ {26,29}Team Score: (\\d+) \\(([A-D]|S\\+?)\\)$";

    @RegExp
    public static final String kPuzzleSolvedP = "^PUZZLE SOLVED! (" + kMcUserP + ") .*$|\\[STATUE] Oruo the Omniscient: (" + kMcUserP + ") answered the final question correctly!";

    // dungeon skill issues
    @RegExp
    public static final String kPuzzleFailP = "^PUZZLE FAIL! (" + kMcUserP + ") .*$" +
        "|^(" + kMcUserP + ") chose the wrong answer!.*$";
    @RegExp
    public static final String kDungeonDeathBurnedP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") burned to death and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathCrushedP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") (?:was|were) crushed and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathDcP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") disconnected and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathKilledP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") (?:was|were) killed by (.*) and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathMobP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") died to a mob and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathGhostP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") died and became a ghost\\.$";
    @RegExp
    public static final String kDungeonDeathTrapP = "^ " + ChatEmojis.DEATH_SKULL.c + " (" + kMcUserP + ") died to a trap and became a ghost\\.$";
    public static final List<String> kDungeonDeathPs = Arrays.asList(
        kDungeonDeathBurnedP,
        kDungeonDeathCrushedP,
        kDungeonDeathDcP,
        kDungeonDeathKilledP,
        kDungeonDeathMobP,
        kDungeonDeathTrapP
    );

    // bap commands
    @RegExp
    public static final String kJoinDungeonP = "^Party > " + kMcUserP + ": \\$jd\\.([fm][0-7])$";
    @RegExp
    public static final String kTakeoverP = "^Party > " + kMcUserP + ": \\$pto$";
    @RegExp
    public static final String kBapCrashP = "Party > " + kMcUserP + ": bap > \\$FMLCommonHandler#exitJava < .*";
    @RegExp
    public static final String kPartyWarpP = "^Party > " + kMcUserP + ": \\$warp$";
}
