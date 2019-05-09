package io.github.juuxel.polyester.menu.impl;

import io.github.juuxel.polyester.menu.MenuFactory;

/**
 * Internal Polyester interface!
 */
public interface ContainerTypeHooks {
    MenuFactory polyester_getFactory();
    void polyester_setFactory(MenuFactory factory);
}
