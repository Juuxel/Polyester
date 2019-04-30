package io.github.juuxel.polyester.plugin

import io.github.juuxel.polyester.block.WoodType

interface PolyesterPlugin {
    val woodTypes: Set<WoodType>
}
