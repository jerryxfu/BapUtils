package net.jerrydev.baputils.features.dungeons;

import net.jerrydev.baputils.AtomicCache;
import net.jerrydev.baputils.core.BapSettingsGui;
import net.jerrydev.baputils.utils.CausalRelation;
import net.jerrydev.baputils.utils.ChatEmojis;
import net.jerrydev.baputils.utils.ChatUtils.CCodes;
import net.jerrydev.baputils.utils.Debug;
import net.minecraft.client.Minecraft;

import java.util.List;

import static net.jerrydev.baputils.BapUtils.clientMessage;
import static net.jerrydev.baputils.utils.ChatUtils.cc;
import static net.jerrydev.baputils.utils.Debug.dout;

public final class DungeonStatus {
    public static void onRunEnd(String scoreMessage) {
        dout("Dungeon run ended. Running post-dungeon tasks.");
        if (AtomicCache.dungeonFails.get().isEmpty()) {
            dout("No skill issues this run!");
            return;
        }

        new Thread(() -> {
            try {
                dout("Sleep 500ms " + Debug.getThreadInfoFormatted());
                Thread.sleep(500);
                dout("Resume " + Debug.getThreadInfoFormatted());

                if (BapSettingsGui.INSTANCE.getDungeonBreakdown()) {
                    clientMessage(cc(CCodes.YELLOW, "Dungeon run breakdown:"));
                    AtomicCache.dungeonFails.get().forEach((CausalRelation event) -> {
                        if (event.leftGreen) {
                            if (event.positive) {
                                // used by puzzle completions
                                clientMessage(cc(CCodes.GREEN, event.actor)
                                    + " " + cc(CCodes.DARK_GREEN, ChatEmojis.LEFT_ARROW.c)
                                    + " " + cc(CCodes.GREEN, event.target));
                                return;
                            }
                            // used by puzzle fails
                            clientMessage(cc(CCodes.GREEN, event.actor)
                                + " " + cc(CCodes.DARK_RED, ChatEmojis.LEFT_ARROW.c)
                                + " " + cc(CCodes.RED, event.target));
                            return;
                        }
                        if (event.positive) {
                            // this shouldn't happen
                            clientMessage(cc(CCodes.RED, event.actor)
                                + " " + cc(CCodes.DARK_GREEN, ChatEmojis.LEFT_ARROW.c)
                                + " " + cc(CCodes.GREEN, event.target));
                        }
                        // used by player deaths
                        clientMessage(cc(CCodes.RED, event.actor)
                            + " " + cc(CCodes.DARK_RED, ChatEmojis.LEFT_ARROW.c)
                            + " " + cc(CCodes.GREEN, event.target));
                    });
                }

                AtomicCache.dungeonFails.updateAndGet((List<CausalRelation> list) -> {
                    list.clear(); // clear the list of deaths after end of run
                    return list;
                });

                if (BapSettingsGui.INSTANCE.getAutoRequeueMaster())
                    if (AtomicCache.lastPartyLeader.get().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                        AutoRequeue.handle();
                    }
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
