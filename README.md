# musmeta-plugin-template

Latest MusMeta release: [![](https://jitpack.io/v/mas6y6/MusMeta.svg)](https://jitpack.io/#mas6y6/MusMeta)

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

5. **Test it locally**:

   ```bash
   ./gradlew runMusMeta
   ```

   Builds the plugin, installs it into the plugins directory, and launches MusMeta
   itself so you can test your plugin. The app JAR is picked in this order:

   1. `musmeta.jar` Gradle property — explicit path to a `MusMeta-*-all.jar`.
   2. The newest `*-all.jar` from a sibling MusMeta checkout (`../build/libs`).
   3. The JitPack artifact `com.github.mas6y6:MusMeta:<musmeta>` (downloaded on first run).

   Useful properties:

   | Property | Purpose |
   |----------|---------|
   | `-Pmusmeta.jar=<path>` | Run a specific MusMeta app JAR. |
   | `-Pmusmeta.home=<dir>` | Isolated test home (config + plugins go there instead of `~/.musmeta`). |
   | `-Pmusmeta.plugins=<dir>` | Where `publishPlugin` installs the JAR (defaults to `<home>/plugins` or `~/.musmeta/plugins`). |
   | `-Pmusmeta.args="--debug"` | Program arguments passed to MusMeta. |

## Version compatibility

The template declares the MusMeta version it was built against via the
`"musmeta"` field in `plugin.json`. It is filled in automatically from
`gradle.properties` (`musmeta=1.3.2`) at build time, so it always matches your
compile dependency.

- Plugins compiled against a version with the **same major** as the running app
  and **not newer than it** load normally.
- Different major versions, or plugins newer than the running app, are skipped
  at startup with a message in the log.
- A plugin without the field (or with `"musmeta": "*"`) always loads.

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
