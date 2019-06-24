package io.github.juuxel.polyester.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

/**
 * Mirrors {@code ContainerScreenRegistry.GuiFactory}. Used to creates {@code Screen} instances for containers.
 */
@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface ContainerScreenFactory<T extends Container, U extends Screen & ContainerProvider<T>> {
    U create(T menu, PlayerInventory playerInventory, Text title);
}
