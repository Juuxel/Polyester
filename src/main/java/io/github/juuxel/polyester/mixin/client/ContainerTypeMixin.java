package io.github.juuxel.polyester.mixin.client;

import io.github.juuxel.polyester.container.impl.ContainerTypeHooks;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerType.class)
public abstract class ContainerTypeMixin<T extends Container> implements ContainerTypeHooks {
    @SuppressWarnings("unchecked")
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private void onCreate(int syncId, PlayerInventory playerInventory, CallbackInfoReturnable<T> info) {
        if (polyester_getFactory() != null) {
            info.setReturnValue((T) polyester_getFactory().create(syncId, playerInventory));
        }
    }
}
