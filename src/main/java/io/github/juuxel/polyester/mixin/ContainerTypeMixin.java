package io.github.juuxel.polyester.mixin;

import io.github.juuxel.polyester.menu.ContainerFactory;
import io.github.juuxel.polyester.menu.impl.ContainerTypeHooks;
import net.minecraft.container.ContainerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ContainerType.class)
public class ContainerTypeMixin implements ContainerTypeHooks {
    @Unique
    private static final Logger LOGGER = LogManager.getLogger();
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
            LOGGER.warn("[Polyester] Trying to modify ContainerType's factory after it has been set!");
        }
    }
}
