package io.github.juuxel.polyester.item

import io.github.juuxel.polyester.registry.HasDescription
import io.github.juuxel.polyester.registry.PolyesterContent
import net.minecraft.ChatFormat
import net.minecraft.item.Item
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent

interface PolyesterItem : PolyesterContent<Item>,
    HasDescription {
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