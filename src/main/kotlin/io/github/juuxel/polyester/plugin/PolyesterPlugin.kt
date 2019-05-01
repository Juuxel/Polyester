package io.github.juuxel.polyester.plugin

import io.github.juuxel.polyester.block.WoodType

@Deprecated("Will be removed in 0.3.0")
interface PolyesterPlugin {
    val woodTypes: Set<WoodType>
}
