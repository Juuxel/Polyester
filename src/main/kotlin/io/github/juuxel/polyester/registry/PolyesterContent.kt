package io.github.juuxel.polyester.registry

import net.minecraft.ChatFormat
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent

interface PolyesterContent<out T> {
    val name: String

    @Suppress("UNCHECKED_CAST")
    fun unwrap(): T = this as T
}

interface HasDescription {
    val hasDescription: Boolean get() = false
    val descriptionKey: String get() = "%TranslationKey.desc"
}

interface BlockLikeContent<out T> : PolyesterContent<T>, HasDescription {
    val itemSettings: Item.Settings?
}

interface PolyesterBlock : BlockLikeContent<Block> {
    val blockEntityType: BlockEntityType<*>? get() = null
}

interface PolyesterItem : PolyesterContent<Item>, HasDescription {
    companion object {
        fun appendTooltipToList(list: MutableList<Component>, content: PolyesterItem) = with(content) {
            if (hasDescription) {
                list.add(
                    TranslatableComponent(
                        descriptionKey.replace(
                            "%TranslationKey",
                            unwrap().translationKey
                        )
                    ).modifyStyle {
                        it.isItalic = true
                        it.color = ChatFormat.DARK_GRAY
                    }
                )
            }
        }
    }
}
