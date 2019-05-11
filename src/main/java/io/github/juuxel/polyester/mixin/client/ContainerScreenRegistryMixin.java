package io.github.juuxel.polyester.mixin.client;

import io.github.juuxel.polyester.container.ContainerRegistry;
import io.github.juuxel.polyester.container.ContainerScreenFactory;
import io.github.juuxel.polyester.container.impl.ContainerRegistryImpl;
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
    @SuppressWarnings("unchecked")
    @Inject(method = "openScreen", cancellable = true, at = @At(value = "INVOKE", ordinal = 1, shift = At.Shift.BEFORE, target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V", remap = false))
    private static <T extends Container> void onOpenScreen(ContainerType<T> containerType, MinecraftClient client, int syncId, Component title, CallbackInfo info) {
        Map<ContainerType<?>, ContainerScreenFactory<?, ?>> factories = ((ContainerRegistryImpl) ContainerRegistry.INSTANCE).getScreenFactories();
        if (factories.containsKey(containerType)) {
            ContainerScreenFactory<T, ?> screenFactory = (ContainerScreenFactory<T, ?>) factories.get(containerType);
            Screen screen = screenFactory.create(
                    containerType.create(syncId, client.player.inventory),
                    client.player.inventory, title
            );
            client.player.container = ((ContainerProvider<?>) screen).getContainer();
            client.openScreen(screen);
            info.cancel();
        }
    }
}
