# musmeta-plugin-template

Starter skeleton for a [MusMeta](https://github.com/mas6y6/MusMeta) plugin. It includes a
`Plugin` entry point, a settings-tab registration, and a working mixin that targets
`Utils.isRunningAsRoot()`.

The template pulls the MusMeta app + plugin framework from a single JitPack artifact,
`com.github.mas6y6:MusMeta:<version>`, so there is nothing to publish to `mavenLocal`.

## Requirements

- JDK 25

## Getting started

1. **Point the template at a MusMeta release** — set the version in `gradle.properties`:

   ```properties
   musmeta=1.0.0
   ```

   The value must be a tag on the `mas6y6/MusMeta` repository (`v1.0.0` can be referenced
   as `1.0.0`). The first build pulls the fat JAR (app + framework) from `jitpack.io`.

2. **Rename the template** to your plugin:

   - `settings.gradle` → change `rootProject.name` (also becomes the JAR name).
   - `plugin.json` → change `id`, `name`, `version`, `authors`, `main`.
   - Move `com/example/musmeta/` to your own package and update `main` in `plugin.json`.
   - Update `hello.mixins.json`'s `package` (and rename the file to match the new `mixins` entry).
   - `build.gradle` → `group` and `version`.

3. **Build**:

   ```bash
   ./gradlew build
   ```

4. **Install into MusMeta**:

   ```bash
   ./gradlew publishPlugin
   ```

   This copies the JAR into `~/.musmeta/plugins` (or the directory set via the
   `musmeta.plugins` Gradle property). Restart MusMeta and the plugin will be discovered.

## Project layout

```
build.gradle            Just the plugin id + group/version (everything else lives in build-logic)
build-logic/            Included build containing the `musmeta.plugin` convention
gradle.properties       The MusMeta version to compile against
src/main/java           Plugin entry point + mixin classes
src/main/resources      plugin.json + mixin config
```

## Extension points

- `onBoot()` — plugin is loaded, mixins are being applied; safe time to register
  into the `Registries` (e.g. `SETTING_TABS`).
- `onEnable()` / `onDisable()` — lifecycle hooks when MusMeta enables or disables you.
- Mixins — drop classes under the `package` listed in your `<id>.mixins.json` and add
  them to its `mixins` array.