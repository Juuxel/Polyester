package io.github.juuxel.polyester.menu.impl;

import io.github.juuxel.polyester.menu.ContainerFactory;

/**
 * Internal Polyester interface!
 */
public interface ContainerTypeHooks {
    ContainerFactory polyester_getFactory();
    void polyester_setFactory(ContainerFactory factory);
}
