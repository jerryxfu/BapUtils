package net.jerryxf.baputils.event

import net.jerryxf.baputils.events.RadarListener

object EventRegistry {
    private val listeners = listOf<Listener>(
        RadarListener,
    )

    fun registerAll() {
        listeners.forEach { it.register() }
    }
}