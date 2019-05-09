package io.github.juuxel.polyester.mixin;

import io.github.juuxel.polyester.menu.MenuFactory;
import io.github.juuxel.polyester.menu.impl.ContainerTypeHooks;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ContainerType.class)
public abstract class ContainerTypeMixin<T extends Container> implements ContainerTypeHooks {
    @Unique
    private static final Logger LOGGER = LogManager.getLogger();
    private MenuFactory polyester_menuFactory = null;

    @Override
    public void polyester_setFactory(MenuFactory factory) {
        if (polyester_menuFactory == null) {
            polyester_menuFactory = factory;
        } else {
            LOGGER.warn("[Polyester] Trying to modify ContainerType's factory after it has been set!");
        }
    }
}
