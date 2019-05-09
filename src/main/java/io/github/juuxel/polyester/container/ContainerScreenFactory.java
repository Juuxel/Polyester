package io.github.juuxel.polyester.container;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ContainerProvider;
import net.minecraft.client.gui.Screen;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.chat.Component;

/**
 * Mirrors {@code ContainerScreenRegistry.GuiFactory}.
 */
@Environment(EnvType.CLIENT)
public interface ContainerScreenFactory<T extends Container, U extends Screen & ContainerProvider<T>> {
    U create(T menu, PlayerInventory playerInventory, Component title);
}
