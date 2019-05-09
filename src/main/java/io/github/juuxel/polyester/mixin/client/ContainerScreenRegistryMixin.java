package io.github.juuxel.polyester.mixin.client;

import io.github.juuxel.polyester.menu.MenuScreenFactory;
import io.github.juuxel.polyester.menu.PolyesterMenuRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ContainerProvider;
import net.minecraft.client.gui.ContainerScreenRegistry;
import net.minecraft.client.gui.Screen;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ContainerScreenRegistry.class)
public class ContainerScreenRegistryMixin {
    @Inject(method = "openScreen", cancellable = true, at = @At(value = "INVOKE", ordinal = 1, shift = At.Shift.BEFORE, target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V", remap = false))
    private static <T extends Container> void onOpenScreen(ContainerType<T> menuType, MinecraftClient client, int syncId, Component title, CallbackInfo info) {
        Map<ContainerType<?>, MenuScreenFactory<?, ?>> factories = PolyesterMenuRegistry.getScreenFactories();
        if (factories.containsKey(menuType)) {
            Screen screen = ((MenuScreenFactory<T, ?>) factories.get(menuType)).create(menuType.create(syncId, client.player.inventory), client.player.inventory, title);
            client.player.container = ((ContainerProvider<?>) screen).getContainer();
            client.openScreen(screen);
            info.cancel();
        }
    }
}
