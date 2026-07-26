package net.jerryxf.baputils

import net.fabricmc.api.ClientModInitializer
import net.jerryxf.baputils.event.EventRegistry

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BapUtils : ClientModInitializer {
    const val MOD_ID = "baputils"

    val logger: Logger = LoggerFactory.getLogger(BapUtils::class.java)

    override fun onInitializeClient() {
        EventRegistry.registerAll()

        logger.info("Initializing BapUtils!")
    }

}
