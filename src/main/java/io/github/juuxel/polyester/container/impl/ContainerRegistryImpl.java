package io.github.juuxel.polyester.container.impl;

import com.google.common.collect.ImmutableMap;
import io.github.juuxel.polyester.container.ContainerFactory;
import io.github.juuxel.polyester.container.ContainerRegistry;
import io.github.juuxel.polyester.container.ContainerScreenFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ContainerProvider;
import net.minecraft.client.gui.Screen;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.registry.Registry;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * A registry for registering containers and screens.
 */
public enum ContainerRegistryImpl implements ContainerRegistry {
    INSTANCE;

    private static final Lazy<MethodHandle> CONTAINER_TYPE_CONSTRUCTOR = new Lazy<>(() -> {
        try {
            // ContainerType only has a single constructor
            Constructor<?> constructor = ContainerType.class.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return MethodHandles.lookup().unreflectConstructor(constructor);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find ContainerType constructor", e);
        }
    });
    private final Map<ContainerType<?>, ContainerScreenFactory<?, ?>> screenFactories = new HashMap<>();

    /**
     * Creates a ContainerType instance using the {@code containerFactory}.
     *
     * @param containerFactory the {@link ContainerFactory} instance
     * @param <T> the container type
     * @return a ContainerType instance
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Container> ContainerType<T> createContainerType(ContainerFactory<T> containerFactory) {
        try {
            MethodHandle constructor = CONTAINER_TYPE_CONSTRUCTOR.get();
            ContainerType<?> containerType = (ContainerType<?>) constructor.invoke((Object) null);
            ContainerTypeHooks hooks = (ContainerTypeHooks) containerType;
            hooks.polyester_setFactory(containerFactory);
            return (ContainerType<T>) containerType;
        } catch (Throwable t) {
            throw new RuntimeException("Container type creation failed", t);
        }
    }

    /**
     * Registers a screen factory for a ContainerType.
     *
     * @param containerType the ContainerType
     * @param screenFactory the screen factory
     * @param <C> the container type
     * @param <S> the screen type
     */
    @Environment(EnvType.CLIENT)
    @Override
    public <C extends Container, S extends Screen & ContainerProvider<C>> void registerScreen(
            ContainerType<? extends C> containerType,
            ContainerScreenFactory<C, S> screenFactory
    ) {
        if (screenFactories.put(containerType, screenFactory) != null) {
            throw new IllegalStateException("Duplicate registration for " + Registry.CONTAINER.getId(containerType));
        }
    }

    public ImmutableMap<ContainerType<?>, ContainerScreenFactory<?, ?>> getScreenFactories() {
        return ImmutableMap.copyOf(screenFactories);
    }
}
