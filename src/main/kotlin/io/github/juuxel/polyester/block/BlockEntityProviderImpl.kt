package io.github.juuxel.polyester.block

import io.github.juuxel.polyester.registry.PolyesterBlock
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.world.BlockView

interface BlockEntityProviderImpl : PolyesterBlock, BlockEntityProvider {
    override val blockEntityType: BlockEntityType<*>

    override fun createBlockEntity(view: BlockView?) = blockEntityType.instantiate()
}
