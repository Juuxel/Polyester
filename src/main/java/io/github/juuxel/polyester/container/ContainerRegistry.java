package io.github.juuxel.polyester.container;

import io.github.juuxel.polyester.container.impl.ContainerRegistryImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ContainerProvider;
import net.minecraft.client.gui.Screen;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A registry for registering containers and screens.
 */
public interface ContainerRegistry {
    ContainerRegistry INSTANCE = ContainerRegistryImpl.INSTANCE;

    /**
     * Creates a ContainerType instance using the {@code containerFactory}.
     *
     * @param containerFactory the {@link ContainerFactory} instance
     * @param <T> the container class
     * @return a ContainerType instance
     */
    <T extends Container> ContainerType<T> createContainerType(ContainerFactory<T> containerFactory);

    /**
     * Creates a {@code ContainerType} and registers it in {@code Registry.CONTAINER}.
     *
     * @param id the registry ID
     * @param containerFactory the container factory
     * @param <T> the container type
     * @return a ContainerType instance
     * @see #createContainerType(ContainerFactory)
     */
    default <T extends Container> ContainerType<T> registerContainer(Identifier id, ContainerFactory<T> containerFactory) {
        return Registry.register(Registry.CONTAINER, id, createContainerType(containerFactory));
    }

    /**
     * Registers a screen factory for a ContainerType.
     *
     * @param containerType the ContainerType
     * @param screenFactory the screen factory
     * @param <T> the container class
     * @param <U> the screen class
     */
    @Environment(EnvType.CLIENT)
    <T extends Container, U extends Screen & ContainerProvider<T>> void registerScreen(
            ContainerType<? extends T> containerType,
            ContainerScreenFactory<T, U> screenFactory
    );
}
