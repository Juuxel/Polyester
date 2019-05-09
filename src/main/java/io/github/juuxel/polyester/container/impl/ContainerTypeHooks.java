package io.github.juuxel.polyester.container.impl;

import io.github.juuxel.polyester.container.ContainerFactory;

/**
 * Internal Polyester interface!
 */
public interface ContainerTypeHooks {
    ContainerFactory polyester_getFactory();
    void polyester_setFactory(ContainerFactory factory);
}
