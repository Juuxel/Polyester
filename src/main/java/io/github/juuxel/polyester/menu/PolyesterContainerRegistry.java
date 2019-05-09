package io.github.juuxel.polyester.menu;

import com.google.common.collect.ImmutableMap;
import io.github.juuxel.polyester.menu.impl.ContainerTypeHooks;
import net.minecraft.client.gui.ContainerProvider;
import net.minecraft.client.gui.Screen;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public final class PolyesterContainerRegistry {
    private static final Map<ContainerType<?>, ContainerScreenFactory<?, ?>> SCREEN_FACTORIES = new HashMap<>();

    private PolyesterContainerRegistry() {}

    public static <T extends Container> ContainerType<T> createContainerType(ContainerFactory<T> containerFactory) {
        try {
            Constructor<?> constructor = ContainerType.class.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            ContainerType<?> containerType = (ContainerType<?>) constructor.newInstance((Object) null);
            ContainerTypeHooks hooks = (ContainerTypeHooks) containerType;
            hooks.polyester_setFactory(containerFactory);
            return (ContainerType<T>) containerType;
        } catch (Exception e) {
            throw new RuntimeException("Container type creation failed", e);
        }
    }

    public static <T extends Container> ContainerType<T> registerContainer(Identifier id, ContainerFactory<T> containerFactory) {
        return Registry.register(Registry.CONTAINER, id, createContainerType(containerFactory));
    }

    public static <M extends Container, U extends Screen & ContainerProvider<M>> void registerScreen(
            ContainerType<M> containerType,
            ContainerScreenFactory<M, U> screenFactory
    ) {
        if (SCREEN_FACTORIES.put(containerType, screenFactory) != null) {
            throw new IllegalStateException("Duplicate registration for " + Registry.CONTAINER.getId(containerType));
        }
    }

    public static ImmutableMap<ContainerType<?>, ContainerScreenFactory<?, ?>> getScreenFactories() {
        return ImmutableMap.copyOf(SCREEN_FACTORIES);
    }
}
