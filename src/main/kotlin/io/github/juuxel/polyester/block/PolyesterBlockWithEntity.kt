/*
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.juuxel.polyester.block

import io.github.juuxel.polyester.registry.PolyesterBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.world.BlockView

abstract class PolyesterBlockWithEntity(settings: Block.Settings) : BlockWithEntity(settings), PolyesterBlock {
    abstract override val blockEntityType: BlockEntityType<*>

    override fun getRenderType(state: BlockState?) = BlockRenderType.MODEL
    final override fun createBlockEntity(view: BlockView?) = blockEntityType.instantiate()
}
