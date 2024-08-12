package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.BapUtils;
import net.jerrydev.baputils.Constants;
import net.jerrydev.baputils.commands.IBapHandleable;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.CausalRelation;
import net.jerrydev.baputils.utils.ChatEmojis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.jerrydev.baputils.utils.Debug.dout;

public class DungeonDeath implements IBapHandleable {
    @Override
    public List<String> getPatterns() {
        return Constants.kDungeonDeathPs;
    }

    @Override
    public void handle(List<String> args) {
        final String cleanMessage = args.get(0);

        if (!BapSettingsGui.INSTANCE.getDungeonDeathMsg().isEmpty() && !cleanMessage.startsWith(" " + ChatEmojis.DEATH_SKULL.c + " You")) {
            BapUtils.queueServerMessage(BapSettingsGui.INSTANCE.getDungeonDeathMsg(), false);
        }

        for (final String patternStr : Constants.kDungeonDeathPs) {
            if (cleanMessage.matches(patternStr)) {
                final Pattern pattern = Pattern.compile(patternStr);
                final Matcher matcher = pattern.matcher(cleanMessage);

                if (matcher.find()) {
                    final String suspect = getSuspect(patternStr, matcher);

                    AtomicCache.dungeonFails.updateAndGet((List<CausalRelation> list) -> {
                        list.add(new CausalRelation(suspect, matcher.group(1), false, false));
                        return list;
                    });
                    dout("Death registered");
                }
            }
        }
    }

    protected static String getSuspect(String patternStr, Matcher matcher) {
        if (patternStr.equals(Constants.kDungeonDeathBurnedP)) {
            return "Fire";
        } else if (patternStr.equals(Constants.kDungeonDeathCrushedP)) {
            return "Crusher";
        } else if (patternStr.equals(Constants.kDungeonDeathDcP)) {
            return "Disconnect";
        } else if (patternStr.equals(Constants.kDungeonDeathGhostP)) {
            return "Death";
        } else if (patternStr.equals(Constants.kDungeonDeathMobP)) {
            return "Mob";
        } else if (patternStr.equals(Constants.kDungeonDeathTrapP)) {
            return "Trap";
        } else {
            return matcher.group(2);
        }
    }
}
