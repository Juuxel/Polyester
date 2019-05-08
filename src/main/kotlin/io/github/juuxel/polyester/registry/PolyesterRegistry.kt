package io.github.juuxel.polyester.registry

import io.github.juuxel.polyester.block.PolyesterBlock
import io.github.juuxel.polyester.block.PolyesterBlockEntityType
import io.github.juuxel.polyester.item.PolyesterItem
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.network.chat.Component
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

abstract class PolyesterRegistry(private val namespace: String) {
    protected fun <R, C : PolyesterContent<R>> register(registry: Registry<in R>, content: C): C {
        register(
            registry,
            content.name,
            content.unwrap()
        )
        return content
    }

    protected fun <R> register(registry: Registry<in R>, name: String, content: R): R {
        return Registry.register(
            registry,
            Identifier(namespace, name),
            content
        )
    }

    protected fun <T : PolyesterBlock> registerBlock(content: T): T {
        register(Registry.BLOCK, content)

        if (content.itemSettings != null)
            register(
                Registry.ITEM,
                content.name,
                object : BlockItem(content.unwrap(), content.itemSettings), PolyesterItem, HasDescription by content {
                    override val name = content.name

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
            )

        val blockEntityType = content.blockEntityType

        if (blockEntityType != null) {
            if (!Registry.BLOCK_ENTITY.contains(blockEntityType)) {
                register(
                    Registry.BLOCK_ENTITY,
                    content.name,
                    blockEntityType
                )
            }

            if (blockEntityType is PolyesterBlockEntityType<*>) {
                blockEntityType.addBlock(content.unwrap())
            }
        }

        return content
    }

    protected fun <R : Recipe<*>> registerRecipe(name: String): RecipeType<R> {
        return register(Registry.RECIPE_TYPE, object : RecipeType<R>, PolyesterContent<RecipeType<R>> {
            override val name = name
            override fun toString() = name
        })
    }

    protected fun <T : PolyesterItem> registerItem(content: T): T =
        register(Registry.ITEM, content)
}
