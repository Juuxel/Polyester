package io.github.juuxel.polyester.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.chat.Component
import net.minecraft.world.World

open class PolyesterBaseItem(override val name: String, settings: Settings) : Item(settings),
    PolyesterItem {
    override fun buildTooltip(
        stack: ItemStack?,
        world: World?,
        list: MutableList<Component>,
        context: TooltipContext?
    ) {
        super.buildTooltip(stack, world, list, context)
        PolyesterItem.appendTooltipToList(list, this)
    }
}
