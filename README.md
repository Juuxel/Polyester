<img src="icon.png" align="left" width="180px"/>

# Polyester

![](https://img.shields.io/github/license/Juuxel/Polyester.svg) ![](https://img.shields.io/github/release/Juuxel/Polyester.svg) ![](https://img.shields.io/badge/minecraft-1.14-blueviolet.svg)

A library mod for my mods. • [Downloads](https://github.com/Juuxel/Polyester/releases) • [CurseForge](https://minecraft.curseforge.com/projects/polyester)

## Contents

- Registry helpers: `PolyesterRegistry` and `PolyesterContent`
  - Allows defining identifiers, block item settings etc. in the content classes
- `PropertyDelegate` implementations
- Plugin API
  - Wood types

### Plugins

Polyester plugins are classes that implement the `PolyesterPlugin` interface.
Currently, they're only used for wood types.

Register them as entrypoints in your `fabric.mod.json` with the entrypoint type `polyester`.
