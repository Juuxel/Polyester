package io.github.juuxel.polyester.plugin.impl

import io.github.juuxel.polyester.block.WoodType
import io.github.juuxel.polyester.plugin.PolyesterPlugin

object PolyesterVanillaPlugin : PolyesterPlugin {
    override val woodTypes = setOf(
        WoodType.OAK,
        WoodType.SPRUCE,
        WoodType.BIRCH,
        WoodType.JUNGLE,
        WoodType.ACACIA,
        WoodType.DARK_OAK
    )
}
