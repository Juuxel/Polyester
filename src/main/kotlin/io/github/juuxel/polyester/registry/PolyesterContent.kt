package io.github.juuxel.polyester.registry

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.item.ItemProvider
import net.minecraft.text.TextComponent
import net.minecraft.text.TextFormat
import net.minecraft.text.TranslatableTextComponent

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
        fun appendTooltipToList(list: MutableList<TextComponent>, content: PolyesterItem) = with(content) {
            if (hasDescription) {
                list.add(
                    TranslatableTextComponent(
                        descriptionKey.replace(
                            "%TranslationKey",
                            unwrap().translationKey
                        )
                    ).modifyStyle {
                        it.isItalic = true
                        it.color = TextFormat.DARK_GRAY
                    }
                )
            }
        }
    }
}
