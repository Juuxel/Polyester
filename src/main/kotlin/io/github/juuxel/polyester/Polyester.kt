package io.github.juuxel.polyester

import io.github.juuxel.polyester.plugin.PolyesterPluginManager
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

object Polyester : ModInitializer {
    private val LOGGER = LogManager.getLogger()

    override fun onInitialize() {
        val woodTypesWithPlugins = PolyesterPluginManager.plugins.flatMap { it.woodTypes.map { woodType -> it to woodType } }

        for ((_, woodType) in woodTypesWithPlugins) {
            // Find all matching wood types
            val matching = woodTypesWithPlugins.filter {
                it.second == woodType
            }

            if (matching.size > 1) {
                val plugins = matching.map { (plugin, _) -> plugin }
                LOGGER.warn(
                    "[Polyester] Duplicate wood type registration for ${woodType.id} from " +
                            plugins.joinToString()
                )
            }
        }

        val woodTypes = woodTypesWithPlugins.map { (_, woodType) -> woodType.id }.distinct()

        LOGGER.info("[Polyester] Registered wood types: " + woodTypes.joinToString())
    }
}
