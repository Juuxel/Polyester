package io.github.juuxel.polyester.block

import io.github.juuxel.polyester.registry.HasDescription
import io.github.juuxel.polyester.registry.PolyesterContent
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item

interface PolyesterBlock : PolyesterContent<Block>, HasDescription {
    val itemSettings: Item.Settings?
    val blockEntityType: BlockEntityType<*>? get() = null
}
