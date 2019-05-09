package io.github.juuxel.polyester.container;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;

/**
 * Mirrors {@code ContainerType.Factory}.
 */
@FunctionalInterface
public interface ContainerFactory<T extends Container> {
    T create(int syncId, PlayerInventory playerInventory);
}
