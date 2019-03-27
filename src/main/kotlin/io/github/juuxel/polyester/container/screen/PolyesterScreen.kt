/*
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.juuxel.polyester.container.screen

import com.mojang.blaze3d.platform.GlStateManager
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.ContainerScreen
import net.minecraft.container.Container
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.TranslatableTextComponent
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
open class PolyesterScreen<C : Container>(
    container: C, player: PlayerEntity, id: Identifier
) : ContainerScreen<C>(
    container,
    player.inventory,
    TranslatableTextComponent("container.${id.namespace}.${id.path}")
) {
    private val background = Identifier(id.namespace, "textures/gui/${id.path}.png")

    override fun render(i1: Int, i2: Int, f: Float) {
        drawBackground()
        super.render(i1, i2, f)
        drawMouseoverTooltip(i1, i2)
    }

    override fun drawBackground(v: Float, i: Int, i1: Int) {
        GlStateManager.color4f(1f, 1f, 1f, 1f)
        client?.textureManager?.bindTexture(background)
        drawTexturedRect(left, top, 0, 0, width, height)
    }

    override fun drawForeground(int_1: Int, int_2: Int) {
        val nameText = title.formattedText
        fontRenderer.draw(
            nameText,
            (this.width / 2 - this.fontRenderer.getStringWidth(nameText) / 2).toFloat(),
            6.0f,
            4210752
        )
        fontRenderer.draw(
            this.playerInventory.displayName.formattedText,
            8.0f,
            (this.height - 96 + 2).toFloat(),
            4210752
        )
    }

    protected fun drawOutline(x1: Int, y1: Int, x2: Int, y2: Int, color: Int) {
        drawRect(x1, y1, x2, y1 + 1, color) // Top
        drawRect(x1, y2 - 1, x2, y2, color) // Bottom
        drawRect(x1, y1, x1 + 1, y2, color) // Left
        drawRect(x2 - 1, y1, x2, y2, color) // Right
    }
}
