/*
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.juuxel.polyester.registry

import net.minecraft.item.block.BlockItem
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

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
                BlockItem(content.unwrap(), content.itemSettings)
            )
        if (content.blockEntityType != null)
            Registry.register(
                Registry.BLOCK_ENTITY,
                Identifier(namespace, content.name),
                content.blockEntityType
            )

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
