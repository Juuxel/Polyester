package io.github.juuxel.polyester.container;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;

/**
 * Mirrors {@code ContainerType.Factory}. Used to create {@code Container} instances on the client.
 */
@FunctionalInterface
public interface ContainerFactory<T extends Container> {
    T create(int syncId, PlayerInventory playerInventory);
}
