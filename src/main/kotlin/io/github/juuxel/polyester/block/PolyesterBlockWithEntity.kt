package io.github.juuxel.polyester.block

import io.github.juuxel.polyester.registry.PolyesterBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity

abstract class PolyesterBlockWithEntity(settings: Block.Settings) : BlockWithEntity(settings), PolyesterBlock, BlockEntityProviderImpl {
    override fun getRenderType(state: BlockState) = BlockRenderType.MODEL
}
