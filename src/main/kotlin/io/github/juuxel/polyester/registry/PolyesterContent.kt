package io.github.juuxel.polyester.registry

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemProvider

interface PolyesterContent<out T> {
    val name: String

    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = this as T
}

interface BlockLikeContent<out T> : PolyesterContent<T> {
    val hasDescription: Boolean get() = false
    // TODO: Figure out what to do with descriptionKey
    val descriptionKey: String get() = "%TranslationKey.desc"
    val itemSettings: Item.Settings?
}

interface PolyesterBlock : BlockLikeContent<Block> {
    val blockEntityType: BlockEntityType<*>? get() = null
}

interface PolyesterItem : PolyesterContent<Item>, ItemProvider {
    override fun getItem() = unwrap()
}
