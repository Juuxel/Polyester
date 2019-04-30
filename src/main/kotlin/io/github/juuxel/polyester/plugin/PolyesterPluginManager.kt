package io.github.juuxel.polyester.plugin

import net.fabricmc.loader.api.FabricLoader

object PolyesterPluginManager {
    val plugins: List<PolyesterPlugin> by lazy {
        FabricLoader.getInstance().getEntrypoints("polyester", PolyesterPlugin::class.java)
    }
}
