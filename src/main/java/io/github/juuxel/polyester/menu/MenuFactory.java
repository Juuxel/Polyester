package io.github.juuxel.polyester.menu;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;

/**
 * Mirrors {@code ContainerType.Factory}.
 */
@FunctionalInterface
public interface MenuFactory<T extends Container> {
    T create(int syncId, PlayerInventory playerInventory);
}
