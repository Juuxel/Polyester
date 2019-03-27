/*
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package io.github.juuxel.polyester.container.property

import net.minecraft.container.PropertyDelegate

class SimplePropertyDelegate(private val getter: () -> Int, private val setter: (Int) -> Unit) : PropertyDelegate {
    override fun get(var1: Int) = getter()
    override fun set(var1: Int, var2: Int) = setter(var2)
    override fun size() = 1
}
