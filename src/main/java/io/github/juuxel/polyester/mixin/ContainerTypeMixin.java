package io.github.juuxel.polyester.mixin;

import io.github.juuxel.polyester.container.ContainerFactory;
import io.github.juuxel.polyester.container.impl.ContainerTypeHooks;
import net.minecraft.container.ContainerType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ContainerType.class)
public class ContainerTypeMixin implements ContainerTypeHooks {
    private ContainerFactory polyester_containerFactory = null;

    @Override
    public ContainerFactory polyester_getFactory() {
        return polyester_containerFactory;
    }

    @Override
    public void polyester_setFactory(ContainerFactory factory) {
        if (polyester_containerFactory == null) {
            polyester_containerFactory = factory;
        } else {
            throw new IllegalStateException("Trying to modify ContainerType's factory after it has been set!");
        }
    }
}
