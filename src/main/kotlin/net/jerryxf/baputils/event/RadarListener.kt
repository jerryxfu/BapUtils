package net.jerryxf.baputils.events

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.jerryxf.baputils.BapUtils
import net.jerryxf.baputils.event.Listener
import net.minecraft.world.entity.Entity

object RadarListener : Listener {
    private const val RANGE = 3.0

    /** Refreshed every tick. */
    var nearby: List<Entity> = emptyList()
        private set

    override fun register() {
        ClientTickEvents.END_CLIENT_TICK.register { client ->
            val player = client.player ?: return@register
            val level = client.level ?: return@register

            nearby = level.getEntities(player, player.boundingBox.inflate(RANGE))

            nearby.forEach {
                BapUtils.logger.info(
                    "{} | {}",
                    it.javaClass.simpleName,
                    it.customName?.string ?: it.type.description.string
                )
            }
        }
    }
}