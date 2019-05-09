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

public final class PolyesterMenuRegistry {
    private static final Map<ContainerType<?>, MenuScreenFactory<?, ?>> SCREEN_FACTORIES = new HashMap<>();

    private PolyesterMenuRegistry() {}

    public static <T extends Container> ContainerType<T> registerMenu(Identifier id, MenuFactory<T> menuFactory) {
        try {
            Constructor<?> constructor = ContainerType.class.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            ContainerType<?> menuType = (ContainerType<?>) constructor.newInstance((Object) null);
            ContainerTypeHooks hooks = (ContainerTypeHooks) menuType;
            hooks.polyester_setFactory(menuFactory);
            return (ContainerType<T>) Registry.register(Registry.CONTAINER, id, menuType);
        } catch (Exception e) {
            throw new RuntimeException("Menu registration failed", e);
        }
    }

    public static <M extends Container, U extends Screen & ContainerProvider<M>> void registerScreen(
            ContainerType<M> menuType,
            MenuScreenFactory<M, U> screenFactory
    ) {
        if (SCREEN_FACTORIES.put(menuType, screenFactory) != null) {
            throw new IllegalStateException("Duplicate registration for " + Registry.CONTAINER.getId(menuType));
        }
    }

    public static ImmutableMap<ContainerType<?>, MenuScreenFactory<?, ?>> getScreenFactories() {
        return ImmutableMap.copyOf(SCREEN_FACTORIES);
    }
}
