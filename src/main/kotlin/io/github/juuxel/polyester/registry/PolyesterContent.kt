/*
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.juuxel.polyester.registry

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item

interface PolyesterContent<out T> {
    val name: String

    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = this as T
}

interface BlockLikeContent<out T> : PolyesterContent<T> {
    val hasDescription: Boolean get() = false
    val descriptionKey: String get() = "%TranslationKey.desc"
    val itemSettings: Item.Settings?
}

interface PolyesterBlock : BlockLikeContent<Block> {
    val blockEntityType: BlockEntityType<*>? get() = null
}
