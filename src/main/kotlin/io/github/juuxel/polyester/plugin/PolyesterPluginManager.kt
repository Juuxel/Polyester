package io.github.juuxel.polyester.plugin

import net.fabricmc.loader.api.FabricLoader

@Deprecated("Will be removed in 0.3.0")
object PolyesterPluginManager {
    val plugins: List<PolyesterPlugin> by lazy {
        FabricLoader.getInstance().getEntrypoints("polyester", PolyesterPlugin::class.java)
    }
}
