package io.github.juuxel.polyester.registry

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeType
import net.minecraft.text.TextComponent
import net.minecraft.text.TextFormat
import net.minecraft.text.TranslatableTextComponent
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

abstract class PolyesterRegistry(private val namespace: String) {
    protected fun <R, C : PolyesterContent<R>> register(registry: Registry<in R>, content: C): C {
        Registry.register(
            registry,
            Identifier(namespace, content.name),
            content.unwrap()
        )
        return content
    }

    protected fun <R> register(registry: Registry<R>, name: String, content: R): R {
        return Registry.register(
            registry,
            Identifier(namespace, name),
            content
        )
    }

    protected fun <T : PolyesterBlock> registerBlock(content: T): T {
        register(Registry.BLOCK, content)

        if (content.itemSettings != null)
            Registry.register(
                Registry.ITEM,
                Identifier(namespace, content.name),
                object : BlockItem(content.unwrap(), content.itemSettings), PolyesterItem {
                    override val name = content.name

                    override fun buildTooltip(
                        stack: ItemStack?,
                        world: World?,
                        list: MutableList<TextComponent>,
                        context: TooltipContext?
                    ) {
                        super.buildTooltip(stack, world, list, context)
                        PolyesterItem.appendTooltipToList(list, this)
                    }
                }
            )

        if (content.blockEntityType != null) {
            Registry.register(
                Registry.BLOCK_ENTITY,
                Identifier(namespace, content.name),
                content.blockEntityType
            )
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
